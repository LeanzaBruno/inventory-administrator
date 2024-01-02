package kertz.Supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kertz.Supermarket.model.Article;
import kertz.Supermarket.repository.ArticleRepository;
import kertz.Supermarket.exception.ArticleNotFoundException;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository repository;
	
	@GetMapping("/{code}")
	public String showPage(@PathVariable long code, Model model) {
        Article article = repository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code) );
		model.addAttribute("article", article);
		return "article";
	}

    @PostMapping("/{code}/delete")
    String deleteArticle(@PathVariable long code){
        repository.deleteById(code);
        return "redirect:/";
    }
	
    /*
    @PutMapping
    public String saveArticle(Article article){
        Article updateArticle = repository.findById(article.getCode()).orElseThrow( () -> new ArticleNotFoundException(code) );
        updateArticle.copy(article);
        repository.save(updateArticle);
		return "/article";
    }

    @PutMapping("/articles/{code}")
    public Article updateArticle(@PathVariable long code, @RequestBody Article article){
        System.out.println(article);
        repository.save(updateArticle);
        return updateArticle;
    }

    @GetMapping("/articles/{code}")
    Article getArticle(@PathVariable long code){
        return repository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code));
    }

    */


}
