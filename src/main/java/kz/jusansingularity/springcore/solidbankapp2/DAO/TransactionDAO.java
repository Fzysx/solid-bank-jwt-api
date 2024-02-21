package kz.jusansingularity.springcore.solidbankapp2.DAO;
import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, String> {
    @Query("SELECT t FROM Transaction t WHERE t.ownerAccount.id = :accountID")
    List<Transaction> findByAccountID(String accountID);
}