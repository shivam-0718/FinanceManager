package in.vyashivam.financemanager.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import in.vyashivam.financemanager.exception.TransactionNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TransactionControllerAdvice {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTransactionException(TransactionNotFoundException tnf) {
        ErrorDetails error = new ErrorDetails(tnf.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception e) {
        ErrorDetails error = new ErrorDetails(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String fieldMessage = fieldError.getDefaultMessage();
            errors.put(fieldName, fieldMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetails> handleEnumValidationException(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ifx = (InvalidFormatException) ex.getCause();
            if (ifx.getTargetType() != null && ifx.getTargetType().isEnum()) {
                String errorMessage = String.format(
                        "Invalid value '%s' for category. Valid values are: [FOOD, FASHION, GROCERIES, FINANCE, MEDICINE, TRAVEL, ENTERTAINMENT, UTILITIES, PERSONAL_CARE, MISCELLANEOUS]",
                        ifx.getValue()
                );
                ErrorDetails error = new ErrorDetails(errorMessage, LocalDateTime.now());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        }

        ErrorDetails error = new ErrorDetails("Invalid request format", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, ConversionFailedException.class})
    public ResponseEntity<ErrorDetails> handleDateException(MethodArgumentTypeMismatchException ex) {
        String errorMessage;

        // Check if it's a date conversion issue
        if (ex.getName().contains("Date") || ex.getRequiredType() == LocalDate.class) {
            errorMessage = "Invalid date. Please ensure day (1-31), month (1-12), and year are valid. Use format dd-MM-yyyy (e.g., 25-09-2025)";
        } else {
            errorMessage = "Invalid parameter: " + ex.getName();
        }

        ErrorDetails error = new ErrorDetails(errorMessage, LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
