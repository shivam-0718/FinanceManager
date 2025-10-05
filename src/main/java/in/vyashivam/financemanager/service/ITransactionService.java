package in.vyashivam.financemanager.service;

import in.vyashivam.financemanager.model.Category;
import in.vyashivam.financemanager.model.TransactionDTO;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    String registerTransaction(TransactionDTO transaction);
    List<TransactionDTO> getTransactions();
    String updateFullTransactionDetails(TransactionDTO transaction);
    TransactionDTO fetchTransactionById(Long id);
    List<TransactionDTO> getTransactionsByCategory(Category category);
    List<TransactionDTO> getTransactionsByDateBetween(LocalDate startDate, LocalDate endDate);
    List<TransactionDTO> getSortedTransactions(boolean status, String... properties);
}
