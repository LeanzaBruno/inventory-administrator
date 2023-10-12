public class StoreArticle extends Article implements IVA {
    private float pricePlusIVA;

    StoreArticle(int id, String name, float price){
        super(id, name, price);
        pricePlusIVA = price + calculateIVA();
    }


    StoreArticle(String name, float price){
        super(name, price);
    }


    public float calculateIVA() {
        return price * 0.21f;
    }

    float getPricePlusIVA(){
        return pricePlusIVA;
    }


    @Override
    public String toString(){
        return String.format("%s $%20.2f |", super.toString(), pricePlusIVA) ;
    }
}
