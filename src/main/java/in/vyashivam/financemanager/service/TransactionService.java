package in.vyashivam.financemanager.service;

import in.vyashivam.financemanager.mapper.TransactionMapper;
import in.vyashivam.financemanager.model.Transaction;
import in.vyashivam.financemanager.model.TransactionDTO;
import in.vyashivam.financemanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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
}
