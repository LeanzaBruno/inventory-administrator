package kertz.Supermarket.controller;

import kertz.Supermarket.model.Article;
import org.springframework.ui.Model;
import kertz.Supermarket.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ArticleRepository repository;

    @GetMapping
    public String showMainPage(Model model){
    	List<Article> articles = repository.findAll();
    	model.addAttribute("articles", articles);
    	return "index";
    }
}
