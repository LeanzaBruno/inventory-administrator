package kertz.Supermarket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Check;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Data
@Check(constraints = "rate >= 0")
public class VAT {
    @Id
    @Column(precision = 4, scale = 2)
    private BigDecimal rate;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;
}
