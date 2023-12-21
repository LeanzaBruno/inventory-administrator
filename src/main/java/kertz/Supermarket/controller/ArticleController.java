package kertz.Supermarket.controller;

import kertz.Supermarket.exception.ArticleNotFoundException;
import kertz.Supermarket.model.Article;
import kertz.Supermarket.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class ArticleController {
    private ArticleRepository repository;

    @Autowired
    ArticleController(ArticleRepository repository){
        this.repository = repository;
    }

    @GetMapping("/articles")
    public List<Article> getArticles(){
        return repository.findAll();
    }

    @PostMapping("/articles")
    public Article newArticle(@RequestBody Article newArticle){
        return repository.save(newArticle);
    }

    @PutMapping("/articles/{code}")
    public Article updateArticle(@PathVariable long code, @RequestBody Article article){
        Article updateArticle = repository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code) );
        updateArticle.copyFrom(article);
        repository.save(updateArticle);
        return updateArticle;
    }

    @GetMapping("/articles/{code}")
    Article getArticle(@PathVariable long code){
        return repository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code));
    }

    @DeleteMapping("/articles/{code}")
    void deleteArticle(@PathVariable long code){
        repository.deleteById(code);
    }
}
