package kertz.Supermarket.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Entity
//@Check(constraints = "title <> '', description <> '', brand <> '', stock > 0, wholesaler_price > 0, gain_percentage > 0")
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
    private Set<Category> categories;

    public Article(){}

    /*
    public Article(int code,
                   String title,
                   String description,
                   String brand,
                   float purchaseGrossPrice,
                   VAT vat,
                   float gainPercentage,
                   int stock) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.stock = stock;
        this.purchaseGrossPrice = BigDecimal.valueOf(purchaseGrossPrice);
        this.vat = vat;
        this.gainPercentage = BigDecimal.valueOf(gainPercentage);
    }
     */

    /**
     * Returns the article's code
     * @return the code
     */
    public long getCode() { return code; }

    /**
     * Returns the article's title
     * @return the title
     */
    public String getTitle() { return title; }

    /**
     * Returns the article's description
     * @return the description
     */
    public String getDescription() { return description; }

    /**
     * Returns the categories which the article belongs from
     * @return A collection containing the categories
     */
    public Set<Category> getCategories(){ return categories; }

    /**
     * Returns the gross price of the buy
     * @return the price
     */
    public BigDecimal getPurchaseGrossPrice() { return purchaseGrossPrice; }

    /**
     * Returns the gain percentage of the article
     * @return the gain percentage
     */
    public BigDecimal getGainPercentage() { return gainPercentage; }

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

    public void copyFrom(Article otherArticle){
        this.title = otherArticle.title;
        this.description = otherArticle.description;
        this.brand = otherArticle.brand;
        this.stock = otherArticle.stock;
        //this.categories = Set.copyOf(otherArticle.categories);
    }
}