package dev.kertz.inventory.exception;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(Long id){
        super("Error: Could not found article with id " + id);
    }
}
