package kertz.Supermarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int code;
    private float price;
    private String description;
    private float iva;
    private int stock;

    Article(){}
    Article(int code, String description, float price, float iva, int stock) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.iva = iva;
        this.stock = stock;
    }

    int getCode() { return code; }

    float getPrice() { return (1 + iva) * price; }

    String getDescription() { return description; }

    float getIva(){ return iva; }

    int getStock() { return stock; }

    @Override
    public boolean equals(Object anotherArticle){
        if(anotherArticle == null || anotherArticle.getClass() != this.getClass() ) return false;
        return this.code ==  ((Article)anotherArticle).code;
    }
}