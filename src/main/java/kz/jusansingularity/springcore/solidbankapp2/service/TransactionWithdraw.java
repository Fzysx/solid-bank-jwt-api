package kz.jusansingularity.springcore.solidbankapp2.service;
import kz.jusansingularity.springcore.solidbankapp2.DAO.TransactionDAO;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.model.DepositTransaction;
import kz.jusansingularity.springcore.solidbankapp2.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionWithdraw {
    private static long id = 1;
    AccountWithdrawService accountWithdrawService;
    TransactionDAO transactionDAO;

    @Autowired
    public TransactionWithdraw(AccountWithdrawService accountWithdrawService, TransactionDAO transactionDAO) {
        this.accountWithdrawService = accountWithdrawService;
        this.transactionDAO = transactionDAO;
    }

    public void execute(AccountWithdraw account, double amount){
        double  balanceBeforeTransaction = account.getBalance();
        accountWithdrawService.withdraw(account, amount);
        double  balanceAfterTransaction = account.getBalance();

        transactionDAO.addTransaction(new DepositTransaction(TransactionType.WITHDRAW,
                String.valueOf(id),
                account.getClientID(),
                amount, account.getId(),
                balanceBeforeTransaction,
                balanceAfterTransaction));

        incrementLastTransferId();
    }
    private void incrementLastTransferId(){
        id++;
    }
}
