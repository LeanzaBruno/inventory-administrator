package kertz.Supermarket.controller;

import kertz.Supermarket.exception.ArticleNotFoundException;
import kertz.Supermarket.model.Article;
import kertz.Supermarket.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
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

    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable int id, @RequestBody Article article){
        Article updateArticle = repository.findById(id).orElseThrow( () -> new ArticleNotFoundException(id) );
        updateArticle.setDescription(article.getDescription());
        updateArticle.setPrice(article.getPrice());
        updateArticle.setIVA(article.getIVA());
        updateArticle.setStock(article.getStock());
        repository.save(updateArticle);
        return updateArticle;
    }

    @GetMapping("/articles/{id}")
    Article getArticle(@PathVariable int id){
        return repository.findById(id).orElseThrow( () -> new ArticleNotFoundException(id));
    }

    @DeleteMapping("/articles/{id}")
    void deleteArticle(@PathVariable int id){
        repository.deleteById(id);
    }
}
