package in.vyashivam.financemanager.service;

import in.vyashivam.financemanager.model.TransactionDTO;
import java.util.List;

public interface ITransactionService {
    String registerTransaction(TransactionDTO transaction);
    List<TransactionDTO> getTransactions();
    String updateFullTransactionDetails(TransactionDTO transaction);
}
