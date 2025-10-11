package in.vyashivam.financemanager.repository;

import in.vyashivam.financemanager.model.Category;
import in.vyashivam.financemanager.model.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Excluding the deleted transactions
    List<Transaction> findByDeletedFalse();

    //Filtering by category and deleted transactions
    List<Transaction> findByCategoryAndDeletedFalse(Category category);

    //Filtering based on date range and excluding deleted transactions
    List<Transaction> findByDateBetweenAndDeletedFalse(LocalDate startDate, LocalDate endDate);

    // Excluding the deleted transactions
    List<Transaction> findByDeletedFalse(Sort sort);
}
