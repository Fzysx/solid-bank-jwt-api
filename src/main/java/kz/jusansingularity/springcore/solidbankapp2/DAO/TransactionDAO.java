package kz.jusansingularity.springcore.solidbankapp2.DAO;
import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;


import java.util.List;


public interface TransactionDAO {
    List<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
}
