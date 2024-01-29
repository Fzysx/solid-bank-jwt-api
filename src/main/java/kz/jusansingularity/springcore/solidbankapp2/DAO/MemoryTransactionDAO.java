package kz.jusansingularity.springcore.solidbankapp2.DAO;

import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class MemoryTransactionDAO {

    private final TransactionDAO transactionDAO;
    @Autowired
    public MemoryTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public List<Transaction> findAll()  {
        return transactionDAO.findAll();
    }

    @Transactional
    public void save(Transaction transaction){
        transactionDAO.save(transaction);
    }
}
