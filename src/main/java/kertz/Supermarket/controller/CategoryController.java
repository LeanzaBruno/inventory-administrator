package kertz.Supermarket.controller;

import kertz.Supermarket.exception.CategoryDeleteException;
import kertz.Supermarket.exception.CategoryNotFoundException;
import kertz.Supermarket.model.Category;
import kertz.Supermarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public List<Category> getCategories(){ return repository.findAll(); }

    @GetMapping("/{id}")
    public Category getCategories(@PathVariable int id){
        return repository.findById(id).orElseThrow( () -> new CategoryNotFoundException(id) );
    }

    @PostMapping
    public void newCategory(@RequestBody Category category){
        category.setName(category.getName().toLowerCase());
        repository.save(category);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable int id, @RequestBody Category category){
        Category categoryDB = repository.findById(id).orElseThrow( () -> new CategoryNotFoundException(id));
        categoryDB.copy(category);
        repository.save(categoryDB);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id){
        Category category = repository.findById(id).orElseThrow( () -> new CategoryNotFoundException(id) );
        if( category.hasArticlesRelated() )
            throw new CategoryDeleteException(id);
        repository.delete(category);
    }
}
