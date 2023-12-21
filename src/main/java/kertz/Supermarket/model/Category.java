package kertz.Supermarket.model;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(length = 100)
   private String name;

   public Category() { }
   public Category(String name) { this.name = name; }

   public String getName(){ return name; }
}
