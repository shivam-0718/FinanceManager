package in.vyashivam.financemanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @PastOrPresent(message = "Transaction date cannot be in the future.")
    private LocalDate date;

    @NotNull(message = "Category is required")
    private Category category;

    @NotBlank(message = "Description is required.")
    @Size(min = 10, max = 100, message = "Description should be short.")
    private String description;

    @NotNull(message = "Amount is required.")
    @DecimalMin(value = "0.01", message = "Amount cannot be negative.")
    private BigDecimal amount;
}
