package kertz.Supermarket.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
public class Article {
    private static final char PRECISION = 8;
    private static final char SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_DOWN;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @Column(length = 50, nullable = false)
    private String brand;

    @Column(nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VAT vat;

    @Column(precision = PRECISION, scale = SCALE, nullable = false)
    private BigDecimal purchaseGrossPrice;

    @Column(precision = PRECISION, scale = SCALE, nullable = false)
    private BigDecimal gainPercentage;

    @ManyToMany
    @JoinTable(name = "article_category",
            joinColumns = @JoinColumn(name = "article_code"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Article(){}
    
    /**
     * Returns the value of the value-added tax
     * @return the rate
     */
    public BigDecimal getVatRate(){ return vat.getRate(); }

    /**
     * Returns the net price of the purchase (without vat)
     * @return the price
     */
    public BigDecimal getPurchaseNetPrice(){
        // netPrice = 100 * grossPrice / (100 + vat)
        BigDecimal numerator =  purchaseGrossPrice.multiply(BigDecimal.valueOf(100));
        BigDecimal denominator = vat.getRate().add(BigDecimal.valueOf(100));
        return numerator.divide(denominator, SCALE, ROUNDING_MODE );
    }

    /**
     * Returns the value-added tax credit, i.e. the taxes that the article had when it was bought
     * @return the vat credit
     */
    public BigDecimal getVatCredit(){ return purchaseGrossPrice.subtract(getPurchaseNetPrice()); }


    /**
     * Returns the sale net price (without taxes)
     * @return the price
     */
    public BigDecimal getSaleNetPrice() {
        // netPrice = purchaseGrossPrice * ( 1 + gainPercentage / 100), aux = 1 + gainPercentage / 100;
        BigDecimal aux = gainPercentage.divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE).add(BigDecimal.valueOf(1));
        return purchaseGrossPrice.multiply(aux);
    }

    /**
     * Returns the sale gross price, including taxes
     * @return the price
     */
    public BigDecimal getSaleGrossPrice() {
        // saleGrossPrice = saleNetPrice * ( 1 + vatRate / 100), aux = 1 + vatRate / 100
        BigDecimal aux = vat.getRate().divide( BigDecimal.valueOf(100), SCALE, ROUNDING_MODE ).add( BigDecimal.valueOf(1) );
        return getSaleNetPrice().multiply(aux);
    }

    /**
     * Returns the debit of value-added tax, i.e. the difference between the sale gross price and the sale net price
     * @return the vat debit
     */
    public BigDecimal getVatDebit() { return getSaleGrossPrice().subtract(getSaleNetPrice()); }

    /**
     * Returns the difference between vat debit and credit.
     * @return the difference
     */
    public BigDecimal getVatDifference() { return getVatDebit().subtract(getVatCredit()); }

    /**
     * Removes a category from the article's category
     * @param category
     */
    public void removeCategory(Category category){ categories.remove(category); }

    public void copy(Article otherArticle){
        if(otherArticle.title != null) this.title = otherArticle.title;
        if(otherArticle.description != null) this.description = otherArticle.description;
        if(otherArticle.brand != null) this.brand = otherArticle.brand;
        if(otherArticle.categories != null) this.categories = Set.copyOf(otherArticle.categories);
        if(otherArticle.gainPercentage != null) this.gainPercentage = otherArticle.gainPercentage;
        if(otherArticle.vat != null) this.vat = otherArticle.vat;
    }
}