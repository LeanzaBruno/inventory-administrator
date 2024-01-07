package kertz.Supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kertz.Supermarket.model.Article;
import kertz.Supermarket.repository.ArticleRepository;
import kertz.Supermarket.repository.VATRepository;
import lombok.extern.slf4j.Slf4j;
import kertz.Supermarket.exception.ArticleNotFoundException;

@Slf4j
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articlesRepository;
	
	@Autowired
	private VATRepository vatRepository;
	
	@GetMapping("/{code}")
	public String showPage(@PathVariable long code, Model model) {
        log.info("Código: " + code);
        Article article = articlesRepository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code) );
    	model.addAttribute("vats", vatRepository.findAll());
		model.addAttribute("article", article);
		model.addAttribute("backupArticle", article);
		return "article";
	}

    /**
     * Updates articles
     * @param code
     * @param updatedArticle
     * @return the view
     */
	@PostMapping("/{code}")
	public String showPage(@PathVariable long code, Article updatedArticle) {
        log.info("Articulo que llega del formulario: " + updatedArticle);
        Article article = articlesRepository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code) );
        log.info("Articulo en la base de datos: " + article);
        article.copy(updatedArticle);
        log.info("Articulo en la base de datos: " + article);
        articlesRepository.save(article);
		return "redirect:/";
	}

    @PostMapping("/delete/{code}")
    String deleteArticle(@PathVariable long code){
        articlesRepository.deleteById(code);
        return "redirect:/";
    }
    
    @GetMapping("/new")
    String showCreatePage(Model model) {
    	model.addAttribute("vats", vatRepository.findAll());
    	model.addAttribute("article", new Article());
    	return "new";
    }
    
    @PostMapping("/new")
    String createArticle(Article article) {
    	articlesRepository.save(article);
    	return "redirect:/";
    }
	
    /*
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
