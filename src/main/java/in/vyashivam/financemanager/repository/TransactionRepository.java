package in.vyashivam.financemanager.repository;

import in.vyashivam.financemanager.model.Category;
import in.vyashivam.financemanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //Filtering by category
    List<Transaction> findByCategory(Category category);

    //Filtering based on date range
    List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
