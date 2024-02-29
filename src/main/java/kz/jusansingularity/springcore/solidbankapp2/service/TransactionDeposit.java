package kz.jusansingularity.springcore.solidbankapp2.service;

import kz.jusansingularity.springcore.solidbankapp2.DAO.MemoryTransactionDAO;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;
import kz.jusansingularity.springcore.solidbankapp2.model.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionDeposit {
    private static long id = 1;
    private final AccountDepositService accountDepositService;
    private final MemoryTransactionDAO memoryTransactionDAO;

    public void execute(Account account, double amount){

        double  balanceBeforeTransaction = account.getBalance();
        accountDepositService.deposit(account, amount);
        double  balanceAfterTransaction = account.getBalance();
        String accountFromId = "-";
        String accountToId = "-";

        memoryTransactionDAO.save(new Transaction(TransactionType.DEPOSIT,
                String.valueOf(id),
                amount,
                account,
                accountFromId,
                accountToId,
                balanceBeforeTransaction,
                balanceAfterTransaction));

        incrementLastTransferId();
    }

    private void incrementLastTransferId(){
        id++;
    }

}
