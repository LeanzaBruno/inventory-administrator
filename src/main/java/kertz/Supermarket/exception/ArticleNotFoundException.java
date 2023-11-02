package kertz.Supermarket.exception;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(Integer id){
        super("Error: Could not found article with id " + id);
    }
}
