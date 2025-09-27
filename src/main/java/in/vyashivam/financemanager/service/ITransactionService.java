package in.vyashivam.financemanager.service;

import in.vyashivam.financemanager.model.TransactionDTO;

public interface ITransactionService {
    String registerTransaction(TransactionDTO transaction);
}
