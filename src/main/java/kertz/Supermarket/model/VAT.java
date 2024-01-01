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

    public VAT(float rate){ this.rate = BigDecimal.valueOf(rate); }
    public VAT(int rate){ this.rate = BigDecimal.valueOf(rate); }

    public BigDecimal getRate() { return rate; }

    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "{\"rate\": "  + this.rate + ", \"description\": " + this.description + "}";
    }
}
