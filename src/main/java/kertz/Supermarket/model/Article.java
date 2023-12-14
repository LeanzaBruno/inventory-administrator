package kertz.Supermarket.model;

import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private String description;
    private float wholesalerPrice;
    private int gainPercentage;
    private int ivaPercentage;
    private int stock;

    public Article(){}
    public Article(int code, String description, float wholesalerPrice, int gainPercentage, int ivaPercentage, int stock) {
        this.code = code;
        this.description = description;
        this.wholesalerPrice = wholesalerPrice;
        this.gainPercentage = gainPercentage;
        this.ivaPercentage = ivaPercentage;
        this.stock = stock;
    }

    public int getCode() { return code; }

    public String getDescription() { return description; }

    public float getWholeSalerPrice() { return wholesalerPrice; }

    public int getGainPercentage() { return gainPercentage; }

    public int getIVA(){ return ivaPercentage; }

    public float getGrossPrice() { return wholesalerPrice + wholesalerPrice * gainPercentage / 100; }

    public float getNetPrice() {
        final float grossPrice = getGrossPrice();
        return grossPrice + grossPrice * ivaPercentage / 100;
    }

    public int getStock() { return stock; }

    public void setDescription(String description){ this.description = description; }

    public void setWholeSalerPrice(float wholesalerPrice){ this.wholesalerPrice = wholesalerPrice; }

    public void setIVAPercentage(int iva){ this.ivaPercentage = iva; }

    public void setGainPercentage(int gain){ this.gainPercentage = gain;}

    public void setStock(int stock) { this.stock = stock; }

    @Override
    public boolean equals(Object anotherArticle){
        if(anotherArticle == null || anotherArticle.getClass() != this.getClass() ) return false;
        return this.code == ((Article)anotherArticle).code;
    }
}