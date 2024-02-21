package kz.jusansingularity.springcore.solidbankapp2.controllers;

import jakarta.validation.Valid;
import kz.jusansingularity.springcore.solidbankapp2.DAO.MemoryTransactionDAO;
import kz.jusansingularity.springcore.solidbankapp2.DTO.CreateTransactionDTO;
import kz.jusansingularity.springcore.solidbankapp2.DTO.ReturningTransactionDTO;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;
import kz.jusansingularity.springcore.solidbankapp2.service.AccountListingService;
import kz.jusansingularity.springcore.solidbankapp2.service.TransactionDeposit;
import kz.jusansingularity.springcore.solidbankapp2.service.TransactionWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.util.AccountActionResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class TransactionsController {
    private final static String clientID = "1";

    private final MemoryTransactionDAO memoryTransactionDAO;
    private final TransactionWithdraw transactionWithdraw;
    private final TransactionDeposit transactionDeposit;
    private final AccountListingService accountListingService;
    private final ModelMapper modelMapper;

    @GetMapping("/{account_id}/transactions")
    public List<ReturningTransactionDTO> getTransactionsByAccountID(@PathVariable("account_id") String accountID){
        accountListingService.getClientAccount(clientID, accountID);
        List<ReturningTransactionDTO> returningTransactionDTOS = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>(memoryTransactionDAO.findByAccountId(accountID));

        for(Transaction transaction: transactions){
            returningTransactionDTOS.add(convertToReturningTransactionDTO(transaction));
        }
        return returningTransactionDTOS;
    }

    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<AccountActionResponse> accountWithdrawOperation(@PathVariable("account_id") String accountID,
                                                                          @Valid @RequestBody CreateTransactionDTO createTransactionDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return(new ResponseEntity<>(new AccountActionResponse(bindingResult.getFieldError().getDefaultMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST));
        }
        if(accountListingService.getClientAccount(clientID, accountID).getAccountType().equals(AccountType.FIXED)){
            return new ResponseEntity<>(new AccountActionResponse(
                        "You can not withdraw money from a Fixed account.", System.currentTimeMillis()), HttpStatus.FORBIDDEN
            );
        };

        AccountWithdraw accountWithdraw = accountListingService.getClientWithdrawAccount(clientID, accountID);

        transactionWithdraw.execute(accountWithdraw, Double.parseDouble(createTransactionDTO.getAmount()));
        AccountActionResponse successResponse = new AccountActionResponse(
                    createTransactionDTO.getAmount() + "$ transferred from " + accountID + " account", System.currentTimeMillis()
        );

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping("/{account_id}/deposit")
    public ResponseEntity<AccountActionResponse> accountDepositOperation(@PathVariable("account_id") String accountID,
                                                                         @Valid @RequestBody CreateTransactionDTO createTransactionDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return(new ResponseEntity<>(new AccountActionResponse(bindingResult.getFieldError().getDefaultMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST));
        }
        Account account = accountListingService.getClientAccount(clientID, accountID);
        transactionDeposit.execute(account, Double.parseDouble(createTransactionDTO.getAmount()));
        AccountActionResponse successResponse = new AccountActionResponse(
                createTransactionDTO.getAmount()  + "$ transferred to " + accountID + " account", System.currentTimeMillis()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    private ReturningTransactionDTO convertToReturningTransactionDTO(Transaction transaction){
        return modelMapper.map(transaction, ReturningTransactionDTO.class);
    }
}
