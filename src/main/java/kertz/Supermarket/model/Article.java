package kertz.Supermarket.model;


import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private float price;
    private String description;
    private float iva;
    private int stock;

    public Article(){}
    public Article(int code, String description, float price, float iva, int stock) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.iva = iva;
        this.stock = stock;
    }

    public int getCode() { return code; }

    public float getPrice() { return price; }

    public String getDescription() { return description; }

    public float getIVA(){ return iva; }

    public int getStock() { return stock; }

    public void setDescription(String description){ this.description = description; }

    public void setPrice(float price){ this.price = price; }

    public void setIVA(float iva){ this.iva = iva; }

    public void setStock(int stock) { this.stock = stock; }

    @Override
    public boolean equals(Object anotherArticle){
        if(anotherArticle == null || anotherArticle.getClass() != this.getClass() ) return false;
        return this.code ==  ((Article)anotherArticle).code;
    }

    @Override
    public String toString(){
        return "Article [code = " + code + ", description = " + description + ", price = " + price + ", iva = " + iva + ", stock = " + stock + "]";
    }
}