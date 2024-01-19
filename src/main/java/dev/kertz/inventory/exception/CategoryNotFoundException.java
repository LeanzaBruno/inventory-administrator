package dev.kertz.inventory.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(int id){
        super("ERROR: Couldn't find category with id " + id);
    }
}
