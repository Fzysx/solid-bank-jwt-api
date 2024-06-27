package kz.jusansingularity.springcore.solidbankapp2.service;

import kz.jusansingularity.springcore.solidbankapp2.DAO.MemoryTransactionDAO;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;
import kz.jusansingularity.springcore.solidbankapp2.model.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionTransfer {

    private static long id = 1;
    private final AccountTransferService accountTransferService;
    private final MemoryTransactionDAO memoryTransactionDAO;

    public void execute(AccountWithdraw accountFrom, Account accountTo, double amount){

        double  AccountFromBalanceBeforeTransaction = accountFrom.getBalance();
        double AccountToBalanceBeforeTransaction = accountTo.getBalance();
        accountTransferService.transfer(accountFrom, accountTo, amount);
        double  AccountFromBalanceAfterTransaction = accountFrom.getBalance();
        double AccountToBalanceAfterTransaction = accountTo.getBalance();
        String accountFromId = accountFrom.getId();
        String accountToId = accountTo.getId();

        memoryTransactionDAO.save(new Transaction(TransactionType.TRANSFER,
                String.valueOf(id),
                amount,
                accountFrom,
                accountFromId,
                accountToId,
                AccountFromBalanceBeforeTransaction,
                AccountFromBalanceAfterTransaction));

        incrementLastTransferId();

        memoryTransactionDAO.save(new Transaction(TransactionType.TRANSFER,
                String.valueOf(id),
                amount,
                accountTo,
                accountFromId,
                accountToId,
                AccountToBalanceBeforeTransaction,
                AccountToBalanceAfterTransaction));

        incrementLastTransferId();

    }

    private void incrementLastTransferId(){
        id++;
    }


}

