package kertz.Supermarket.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Category {
   @Id
   private String name;

   @ManyToMany(mappedBy = "categories")
   private Set<Article> articles;

   public Category() { }
   public Category(String name) { this.name = name; }

   String getName(){ return name; }

   void setName(String name) { this.name = name; }
}
