package kertz.Supermarket.repository;

import kertz.Supermarket.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
