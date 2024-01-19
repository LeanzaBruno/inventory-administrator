package kertz.Supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kertz.Supermarket.model.Article;
import kertz.Supermarket.repository.ArticleRepository;
import kertz.Supermarket.repository.VATRepository;
import kertz.Supermarket.exception.ArticleNotFoundException;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleRepository articlesRepository;
	
	@Autowired
	private VATRepository vatRepository;

    @GetMapping
    public String showArticles(Model model){
        model.addAttribute("articles", articlesRepository.findAll());
        return "articles";
    }
	
    /**
     * Gets the article's view
     * @param code article's code
     * @param model the view's model
     * @return the view
     */
	@GetMapping("/{code}")
	public String showPage(@PathVariable long code, Model model) {
        Article article = articlesRepository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code) );
    	model.addAttribute("vats", vatRepository.findAll());
		model.addAttribute("article", article);
		return "article";
	}

    /**
     * Updates article
     * @param code article's code
     * @param updatedArticle article object with updated parameters
     * @return the view
     */
	@PostMapping("update/{code}")
	public String showPage(@PathVariable long code, Model model, Article updatedArticle) {
        Article article = articlesRepository.findById(code).orElseThrow( () -> new ArticleNotFoundException(code) );
        article.copy(updatedArticle);
        articlesRepository.save(article);
		return "redirect:/articles";
	}

    /**
     * Deletes article
     * @param code
     * @return home view
     */
    @PostMapping("/delete/{code}")
    String deleteArticle(@PathVariable long code, Model model){
        articlesRepository.deleteById(code);
        model.addAttribute("articles", articlesRepository.findAll());
        return "redirect:/articles";
    }
    

    /**
     * Returns the view to create a new article
     * @param model view's model
     * @return the view
     */
    @GetMapping("/new")
    String showCreatePage(Model model) {
    	model.addAttribute("vats", vatRepository.findAll());
    	model.addAttribute("article", new Article());
    	return "new";
    }
    
    /**
     * Creates a new article
     * @param article
     * @return home view
     */
    @PostMapping("/new")
    String createArticle(Article article) {
    	articlesRepository.save(article);
    	return "redirect:/articles";
    }
}