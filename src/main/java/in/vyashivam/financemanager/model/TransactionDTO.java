package in.vyashivam.financemanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private LocalDate date;

    private Category category;
    private String description;
    private BigDecimal amount;
}
