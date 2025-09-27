package in.vyashivam.financemanager.controller;

import in.vyashivam.financemanager.model.TransactionDTO;
import in.vyashivam.financemanager.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
