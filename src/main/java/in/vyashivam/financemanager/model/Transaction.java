package in.vyashivam.financemanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Sr no")
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Category")
    private Category category;

    @Column(name = "Description")
    private String description;

    @Column(name = "AmountINR", precision = 15, scale = 2)
    private BigDecimal amount;

    @Builder
    public Transaction(LocalDate date, Category category, String description, BigDecimal amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }
}
