package in.vyashivam.financemanager.service;

import in.vyashivam.financemanager.exception.TransactionNotFoundException;
import in.vyashivam.financemanager.mapper.TransactionMapper;
import in.vyashivam.financemanager.model.Transaction;
import in.vyashivam.financemanager.model.TransactionDTO;
import in.vyashivam.financemanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService{
    private TransactionRepository repo;

    @Autowired
    public void setRepo(TransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public String registerTransaction(TransactionDTO transaction) {
        if (transaction.getDate() == null) {
            LocalDate date = LocalDate.now();
            transaction.setDate(date);
        }

        //converting DTO to entity
        Transaction tr = TransactionMapper.toEntity(transaction);

        //adding transaction to repo
        repo.save(tr);
        return "Transaction has been added with serial number " + tr.getId();
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        List<Transaction> transactions = repo.findAll();

        //Displaying only specific information in list.
        List<TransactionDTO> ans = new ArrayList<>();

        //sending TransactionDto to client to allow specific information to be sent
        for (Transaction t : transactions) {
            ans.add(TransactionMapper.toDto(t));
        }

        return ans;
    }

    @Override
    public String updateFullTransactionDetails(TransactionDTO transaction) {
        //converting DTO to entity
        Transaction tr = TransactionMapper.toEntity(transaction);

        Optional<Transaction> optional = repo.findById(tr.getId());
        if(optional.isPresent()) {
            Transaction updatedTransaction = repo.save(tr);
            return "Transaction info for serial number "+ tr.getId() + " has been updated successfully!";
        }

        throw new TransactionNotFoundException("Transaction with given serial number is not available. Please try again.");
    }

    @Override
    public TransactionDTO fetchTransactionById(Long id) {
        Optional<Transaction> optional = repo.findById(id);
        if(optional.isPresent()) {
            Transaction tr = optional.get();
            TransactionDTO transaction = TransactionMapper.toDto(tr);
            return transaction;
        }

        throw new TransactionNotFoundException("Transaction with given id / serial number is not available. Please try again.");
    }
}
