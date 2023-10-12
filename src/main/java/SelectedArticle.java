class SelectedArticle {
    private final StoreArticle article;
    private int quantity;

    SelectedArticle(StoreArticle article, int quantity){
        this.article = article;
        this.quantity = quantity;
    }

    StoreArticle getArticle(){
        return article;
    }
    float getPrice(){ return article.getPricePlusIVA(); }
    void increaseQuantity(int value){ quantity += value; }
    void decreaseQuantity(int value){ quantity -= value; }
    int getQuantity(){ return quantity; }

    @Override
    public String toString(){
        return String.format("%s %11d |", article.toString(), quantity);
    }
}
