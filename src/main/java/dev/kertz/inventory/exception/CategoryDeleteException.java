package dev.kertz.inventory.exception;

import dev.kertz.inventory.controller.CategoryController;

public class CategoryDeleteException extends RuntimeException{
    public CategoryDeleteException(int id){
        super("ERROR: Can't delete category with id " + id + " because it has articles related");
    }
}
