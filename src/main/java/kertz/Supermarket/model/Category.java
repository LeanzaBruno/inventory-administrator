package kertz.Supermarket.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(length = 100)
   private String name;

   @ManyToMany(mappedBy = "categories")
   private Set<Article> articles = new HashSet<>();

   public boolean hasArticlesRelated(){
      return !articles.isEmpty();
   }

   public void copy(Category otherCategory){
      this.name = otherCategory.name;
   }
}
