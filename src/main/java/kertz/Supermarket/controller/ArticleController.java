package kertz.Supermarket.controller;

import kertz.Supermarket.exception.ArticleNotFoundException;
import kertz.Supermarket.model.Article;
import kertz.Supermarket.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    private ArticleRepository repository;

    @Autowired
    ArticleController(ArticleRepository repository){
        this.repository = repository;
    }

    @GetMapping("articles")
    public List<Article> getArticles(){
        return repository.findAll();
    }

    @PostMapping("articles")
    public Article newArticle(@RequestBody Article newArticle){
        return repository.save(newArticle);
    }

    @GetMapping("articles/{id}")
    Article getArticle(@PathVariable Integer id){
        return repository.findById(id).orElseThrow( () -> new ArticleNotFoundException(id));
    }

    @DeleteMapping("articles/{id}")
    void deleteArticle(@PathVariable Integer id){
        repository.deleteById(id);
    }
}
