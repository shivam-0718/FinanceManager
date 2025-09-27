package in.vyashivam.financemanager.controller;

import in.vyashivam.financemanager.model.TransactionDTO;
import in.vyashivam.financemanager.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private ITransactionService service;

    @Autowired
    public TransactionController(ITransactionService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerTransaction(@RequestBody TransactionDTO transaction) {
        String response = service.registerTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<TransactionDTO> transactions = service.getTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


}
