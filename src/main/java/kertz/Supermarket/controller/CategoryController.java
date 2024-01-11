package kertz.Supermarket.controller;

import kertz.Supermarket.exception.CategoryDeleteException;
import kertz.Supermarket.exception.CategoryNotFoundException;
import kertz.Supermarket.model.Category;
import kertz.Supermarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public String getCategories(Model model){
        model.addAttribute("categories", repository.findAll());
        model.addAttribute("newCategory", new Category());
        return "categories";
    }


    @PostMapping("/new")
    public String newCategory(Category newCategory, Model model){
        repository.save(newCategory);
        model.addAttribute("categories", repository.findAll());
        model.addAttribute("newCategory", new Category());
        return "redirect:/categories";
    }


    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id, Model model){
        Category category = repository.findById(id).orElseThrow( () -> new CategoryNotFoundException(id) );
        if( category.hasArticlesRelated() )
            throw new CategoryDeleteException(id);
        else
            repository.delete(category);

        model.addAttribute("categories", repository.findAll());
        model.addAttribute("newCategory", new Category());
        return "redirect:/categories";
    }
}
