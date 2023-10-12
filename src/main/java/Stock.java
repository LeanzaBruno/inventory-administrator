class Stock {
    private StoreArticle article;
    private int currentQuantity;

    Stock(StoreArticle article, int currentQuantity){
        this.article = article;
        this.currentQuantity = currentQuantity;
    }

    StoreArticle getArticle(){
        return article;
    }

    int getStock(){
        return currentQuantity;
    }

    void setStock(int newStock){
        currentQuantity = newStock;
    }

    void decreaseStock(int quantity){
        setStock(currentQuantity - quantity);
    }

    void increaseStock(int quantity){
        setStock(currentQuantity + quantity);
    }

    @Override
    public String toString(){
        return String.format("%s %11d |", article.toString(), currentQuantity);
    }
}
