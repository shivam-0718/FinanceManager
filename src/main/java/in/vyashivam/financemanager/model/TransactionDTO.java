package in.vyashivam.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private LocalDate date;
    private Category category;
    private String description;
    private BigDecimal amount;
}
