package kertz.Supermarket.controller;

import kertz.Supermarket.model.Article;
import kertz.Supermarket.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    @Autowired
    private ArticleRepository repository;

    @GetMapping("index")
    public Iterable<Article> getArticles(){
        return repository.findAll();
    }

    @GetMapping("usuario")
    public String usuario(){
        return "usuario";
    }

    @GetMapping("articulos")
    public String articulos(){
        return "articulos";
    }

}
