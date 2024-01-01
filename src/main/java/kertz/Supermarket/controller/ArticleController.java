package kertz.Supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kertz.Supermarket.model.Article;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@GetMapping
	public String showPage(Article article) {
		return "article";
	}
	
    /*
    @PostMapping
    public Article newArticle(@RequestBody Article newArticle){
        return repository.save(newArticle);
    }

    @PutMapping("/articles/{code}")
    public Article updateArticle(@PathVariable long code, @RequestBody Article article){
        Article updateArticle = repository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code) );
        System.out.println(article);
        updateArticle.copy(article);
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
    */


}
