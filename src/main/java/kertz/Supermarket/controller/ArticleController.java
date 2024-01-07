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
        Article article = articlesRepository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code) );
        article.copy(updatedArticle);
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
}
