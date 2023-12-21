package kertz.Supermarket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Check;
import java.math.BigDecimal;

@Entity
@Check(constraints = "rate >= 0")
public class VAT {
    @Id
    @Column(precision = 4, scale = 2)
    private BigDecimal rate;

    @Column(nullable = false)
    private String description;

    public VAT(){ }

    public VAT(BigDecimal rate){ this.rate = rate; }

    public BigDecimal getRate() { return rate; }

    public String getDescription() { return description; }
}
