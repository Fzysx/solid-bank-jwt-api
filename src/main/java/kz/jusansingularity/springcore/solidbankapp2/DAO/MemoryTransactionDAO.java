package kz.jusansingularity.springcore.solidbankapp2.DAO;

import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryTransactionDAO implements TransactionDAO{

    private List<Transaction> transactions = new ArrayList<>();
    @Override
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
