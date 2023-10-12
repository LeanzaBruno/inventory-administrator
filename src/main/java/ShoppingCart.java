import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ShoppingCart {
    private final List<SelectedArticle> articles = new ArrayList<>();

    ShoppingCart(){}

    boolean add(StoreArticle article, int quantity){
        SelectedArticle selArt;
        if((selArt = getArticle(article)) != null)
            selArt.increaseQuantity(quantity);
        else
            articles.add(new SelectedArticle(article, quantity));
        return true;
    }

    /**
     * Elimina cierta cantidad de elementos del carrito.
     * Si la cantidad a eliminar es igual a la existente en el carrito, se elimina el artículo por completo
     * @param id el id del artículo a eliminar
     * @param quantity la cantidad del elementos a eliminar
     * @return false si hubo algún error al borrar; true si se borró correctamente
     */
    boolean remove(int id, int quantity){
        if(quantity < 0 ) return false;

        final Optional<SelectedArticle> optional = articles.stream().filter(art -> art.getArticle().getID() == id).findFirst();
        if(optional.isEmpty()) return false;

        final SelectedArticle selectedArticle = optional.get();
        if( selectedArticle.getQuantity() == quantity )
            articles.remove(selectedArticle);
        else
            selectedArticle.decreaseQuantity(quantity);

        return true;
    }

    List<SelectedArticle> getAllArticles(){ return List.copyOf(articles); }

    boolean isEmpty(){ return articles.isEmpty(); }

    float totalImport(){
        float total = 0f;
        for(var item : articles) total += item.getPrice() * item.getQuantity();
        return total;
    }

    void clearCart(){ articles.clear(); }

    SelectedArticle getArticle(StoreArticle article){
        for(var selArt : articles)
            if(selArt.getArticle().equals(article)) return selArt;
        return null;
    }
}
