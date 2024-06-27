package kz.jusansingularity.springcore.solidbankapp2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kz.jusansingularity.springcore.solidbankapp2.DAO.MemoryTransactionDAO;
import kz.jusansingularity.springcore.solidbankapp2.DTO.CreateTransactionDTO;
import kz.jusansingularity.springcore.solidbankapp2.DTO.CreateTransferDTO;
import kz.jusansingularity.springcore.solidbankapp2.DTO.ReturningTransactionDTO;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;
import kz.jusansingularity.springcore.solidbankapp2.model.secureEntities.User;
import kz.jusansingularity.springcore.solidbankapp2.repositories.UserRepository;
import kz.jusansingularity.springcore.solidbankapp2.service.AccountListingService;
import kz.jusansingularity.springcore.solidbankapp2.service.TransactionDeposit;
import kz.jusansingularity.springcore.solidbankapp2.service.TransactionTransfer;
import kz.jusansingularity.springcore.solidbankapp2.service.TransactionWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.util.AccountActionResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class TransactionsController {
    private final static String clientID = "1";

    private final MemoryTransactionDAO memoryTransactionDAO;
    private final TransactionWithdraw transactionWithdraw;
    private final TransactionDeposit transactionDeposit;
    private final TransactionTransfer transactionTransfer;
    private final AccountListingService accountListingService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @GetMapping("/{account_id}/transactions")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<ReturningTransactionDTO> getTransactionsByAccountID(@PathVariable("account_id") String accountID){
        accountListingService.getClientAccount(clientID(), accountID);
        List<ReturningTransactionDTO> returningTransactionDTOS = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>(memoryTransactionDAO.findByAccountId(accountID));

        for(Transaction transaction: transactions){
            returningTransactionDTOS.add(convertToReturningTransactionDTO(transaction));
        }
        return returningTransactionDTOS;
    }

    @PostMapping("/{account_id}/withdraw")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<AccountActionResponse> accountWithdrawOperation(@PathVariable("account_id") String accountID,
                                                                          @Valid @RequestBody CreateTransactionDTO createTransactionDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return(new ResponseEntity<>(new AccountActionResponse(bindingResult.getFieldError().getDefaultMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST));
        }
        if(accountListingService.getClientAccount(clientID(), accountID).getAccountType().equals(AccountType.FIXED)){
            return new ResponseEntity<>(new AccountActionResponse(
                        "You can not withdraw money from a Fixed account.", System.currentTimeMillis()), HttpStatus.FORBIDDEN
            );
        };

        AccountWithdraw accountWithdraw = accountListingService.getClientWithdrawAccount(clientID(), accountID);

        transactionWithdraw.execute(accountWithdraw, Double.parseDouble(createTransactionDTO.getAmount()));
        AccountActionResponse successResponse = new AccountActionResponse(
                    createTransactionDTO.getAmount() + "$ transferred from " + accountID + " account", System.currentTimeMillis()
        );

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping("/{account_id}/deposit")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<AccountActionResponse> accountDepositOperation(@PathVariable("account_id") String accountID,
                                                                         @Valid @RequestBody CreateTransactionDTO createTransactionDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return(new ResponseEntity<>(new AccountActionResponse(bindingResult.getFieldError().getDefaultMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST));
        }
        Account account = accountListingService.getClientAccount(clientID(), accountID);
        transactionDeposit.execute(account, Double.parseDouble(createTransactionDTO.getAmount()));
        AccountActionResponse successResponse = new AccountActionResponse(
                createTransactionDTO.getAmount()  + "$ transferred to " + accountID + " account", System.currentTimeMillis()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping("/{account_id}/transfer")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity <AccountActionResponse> accountTransferOperation(@PathVariable("account_id") String accountID,
                                                                            @Valid @RequestBody CreateTransferDTO createTransferDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return(new ResponseEntity<>(new AccountActionResponse(bindingResult.getFieldError().getDefaultMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST));
        }

        if(accountListingService.getClientAccount(clientID(), accountID).getAccountType().equals(AccountType.FIXED)){
            return new ResponseEntity<>(new AccountActionResponse(
                    "You can not withdraw money from a Fixed account.", System.currentTimeMillis()), HttpStatus.FORBIDDEN
            );
        };

        AccountWithdraw accountWithdraw = accountListingService.getClientWithdrawAccount(clientID(), accountID);
        Account account = accountListingService.getClientAccount(createTransferDTO.getDestinationAccountId());

        transactionTransfer.execute(accountWithdraw, account, Double.parseDouble(createTransferDTO.getAmount()));

        AccountActionResponse successResponse = new AccountActionResponse(
                createTransferDTO.getAmount()  + "$ transferred from "
                        + accountID  +  " to " + createTransferDTO.getDestinationAccountId() + " account", System.currentTimeMillis()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);

    }

    private ReturningTransactionDTO convertToReturningTransactionDTO(Transaction transaction){
        return modelMapper.map(transaction, ReturningTransactionDTO.class);
    }

    private String clientID(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String username = authentication.getName();
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return String.valueOf(user.getId());
            } else {return null; }
        } else { return null; }
    }
}
