package in.vyashivam.financemanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Sr no")
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "Category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "Description", nullable = false, length = 100)
    private String description;

    @Column(name = "AmountINR", precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;
}
