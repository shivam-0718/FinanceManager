package in.vyashivam.financemanager.service;

import in.vyashivam.financemanager.mapper.TransactionMapper;
import in.vyashivam.financemanager.model.Transaction;
import in.vyashivam.financemanager.model.TransactionDTO;
import in.vyashivam.financemanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService{
    private TransactionRepository repo;

    @Autowired
    public void setRepo(TransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public String registerTransaction(TransactionDTO transaction) {
        //converting DTO to entity
        Transaction tr = TransactionMapper.toEntity(transaction);
        repo.save(tr);
        return "Transaction has been added with serial number " + tr.getId();
    }
}
