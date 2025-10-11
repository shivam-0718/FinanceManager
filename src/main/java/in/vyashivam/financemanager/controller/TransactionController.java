package in.vyashivam.financemanager.controller;

import in.vyashivam.financemanager.model.Category;
import in.vyashivam.financemanager.model.TransactionDTO;
import in.vyashivam.financemanager.service.ITransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Arrays;
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
    public ResponseEntity<String> registerTransaction(@Valid @RequestBody TransactionDTO transaction) {
        String response = service.registerTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<TransactionDTO> transactions = service.getTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTransactionDetails(@PathVariable Long id, @RequestBody TransactionDTO transaction) {
        transaction.setId(id); // Ensure ID matches path parameter
        String response = service.updateFullTransactionDetails(transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fetch-transaction/{id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id) {
        TransactionDTO transaction = service.fetchTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TransactionDTO>> getByCategory(@RequestParam String category) {
        Category cat = Category.valueOf(category.toUpperCase());
        List<TransactionDTO> transactionsList = service.getTransactionsByCategory(cat);
        return new ResponseEntity<>(transactionsList, HttpStatus.OK);
    }

    @GetMapping("/filter/date-range")
    public ResponseEntity<List<TransactionDTO>> getByDateRange(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate)  {
        List<TransactionDTO> transactionsList = service.getTransactionsByDateBetween(startDate, endDate);
        return new ResponseEntity<>(transactionsList, HttpStatus.OK);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<TransactionDTO>> getSortedTransactions(@RequestParam(defaultValue = "false") boolean ascending, String... sortBy) {
        List<String> allowedFields = Arrays.asList("date", "amount", "category");
        for (String field : sortBy) {
            if (!allowedFields.contains(field.toLowerCase())) {
                return ResponseEntity.badRequest().build();
            }
        }
        List<TransactionDTO> transactionsList = service.getSortedTransactions(ascending, sortBy);
        return new ResponseEntity<>(transactionsList, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        String response = service.deleteTransaction(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
