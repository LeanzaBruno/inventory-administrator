class Article {
    protected int id = -1;
    protected float price;
    protected String name;

    Article(int id, String name, float price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    Article(String name, float price){
        this.name = name;
        this.price = price;
    }

    int getID(){ return id; }

    float getPrice() { return price; }

    String getName(){ return name; }

    @Override
    public String toString(){ return String.format("| %-3d | %-80s |", id, name); }

    @Override
    public boolean equals(Object anotherArticle){
        if(anotherArticle == null || anotherArticle.getClass() != this.getClass() ) return false;
        return this.id ==  ((Article)anotherArticle).id;
    }
}