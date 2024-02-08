package kz.jusansingularity.springcore.solidbankapp2.service;
import kz.jusansingularity.springcore.solidbankapp2.DAO.MemoryTransactionDAO;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;
import kz.jusansingularity.springcore.solidbankapp2.model.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionWithdraw {
    private static long id = 1;
    private final AccountWithdrawService accountWithdrawService;
    private final MemoryTransactionDAO memoryTransactionDAO;

    public void execute(AccountWithdraw account, double amount){
        double  balanceBeforeTransaction = account.getBalance();
        accountWithdrawService.withdraw(account, amount);
        double  balanceAfterTransaction = account.getBalance();

        memoryTransactionDAO.save(new Transaction(TransactionType.WITHDRAW,
                String.valueOf(id),
                account.getClientID(),
                amount, account,
                balanceBeforeTransaction,
                balanceAfterTransaction));

        incrementLastTransferId();
    }
    private void incrementLastTransferId(){
        id++;
    }
}
