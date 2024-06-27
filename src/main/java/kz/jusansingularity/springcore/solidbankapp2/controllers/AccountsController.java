package kz.jusansingularity.springcore.solidbankapp2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kz.jusansingularity.springcore.solidbankapp2.DTO.CreateAccountDTO;
import kz.jusansingularity.springcore.solidbankapp2.DTO.ReturningAccountDTO;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import kz.jusansingularity.springcore.solidbankapp2.model.secureEntities.User;
import kz.jusansingularity.springcore.solidbankapp2.repositories.UserRepository;
import kz.jusansingularity.springcore.solidbankapp2.service.*;
import kz.jusansingularity.springcore.solidbankapp2.util.*;
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
public class AccountsController {

    private final String clientID = "1";
    private final AccountListingService accountListingService;
    private final BankCore bankCore;
    private final AccountRemoveService accountRemoveService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<ReturningAccountDTO> getAccounts(){
        List<ReturningAccountDTO> returningAccountDTOS = new ArrayList<>();
        List<Account> accounts = new ArrayList<>(accountListingService.getClientAccounts(clientID()));

        for(Account account: accounts){
            returningAccountDTOS.add(convertToReturningAccountDTO(account));
        }
        return returningAccountDTOS;
    }


    @GetMapping("/{account_id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ReturningAccountDTO getAccount(@PathVariable("account_id") String accountID){
        return convertToReturningAccountDTO(accountListingService.getClientAccount(clientID(), accountID));
    }

    @PostMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<AccountActionResponse> createAccountRequest(@Valid @RequestBody CreateAccountDTO createAccountDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return(new ResponseEntity<>(new AccountActionResponse(bindingResult.getFieldError().getDefaultMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST));
        }
        String accountId = bankCore.createNewAccount(AccountType.valueOf(createAccountDTO.getAccountType().toUpperCase()), clientID());
        AccountActionResponse successResponse = new AccountActionResponse(
                    createAccountDTO.getAccountType().toUpperCase() + " account successfully created, id = " + accountId, System.currentTimeMillis()
            );
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }


    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @DeleteMapping("/{account_id}")
    public ResponseEntity<AccountActionResponse> accountRemove(@PathVariable("account_id") String accountID){
            Account removingAccount =  accountListingService.getClientAccount(clientID(), accountID);
            accountRemoveService.remove(removingAccount);
            AccountActionResponse accountActionResponse = new AccountActionResponse(
                    "Account id = " + accountID + " successfully deleted", System.currentTimeMillis()
            );
        return new ResponseEntity<>(accountActionResponse, HttpStatus.OK);
    }

    private ReturningAccountDTO convertToReturningAccountDTO(Account account){
        return modelMapper.map(account,ReturningAccountDTO.class);
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
