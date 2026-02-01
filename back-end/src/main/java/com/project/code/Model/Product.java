package com.project.code.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

// 1. Add Entity and Table annotations:
//    - Use @Entity above the class name.
//    - Use the @Table annotation with a uniqueConstraints attribute to enforce uniqueness on the sku column.
//    - Example: @Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
public class Product {

// 2. Add 'id' field:
//    - Type: private long
//    - Auto increment.
//    - Use @Id and @GeneratedValue(strategy = GenerationType.IDENTITY).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

// 3. Add 'name' field:
//    - Type: private String
//    - Cannot be empty.
//    - Use @NotNull.
    @NotNull(message = "Name cannot be null")
    private String name;

// 4. Add 'category' field:
//    - Type: private String
//    - Cannot be empty.
//    - Use @NotNull.
    @NotNull(message = "Category cannot be null")
    private String category;

// 5. Add 'price' field:
//    - Type: private Double
//    - Cannot be empty.
//    - Use @NotNull.
    @NotNull(message = "Price cannot be null")
    private Double price;

// 6. Add 'sku' field:
//    - Type: private String
//    - Cannot be empty, must be unique (enforced by @Table above).
//    - Use @NotNull.
    @NotNull(message = "SKU cannot be null")
    private String sku;

// 7. Set up relationships:
//    - Inventory: A product can have multiple inventory entries.
//    - Use @OneToMany annotation.
//    - Use the mappedBy attribute to indicate the relationship with the Inventory class (mappedBy = "product").
//    - Apply @JsonManagedReference("inventory-product") to handle the bidirectional relationship.
    @OneToMany(mappedBy = "product")
    @JsonManagedReference("inventory-product")
    private List<Inventory> inventory;

// 8. Add Getters and Setters:
//    - Add getters and setters for all attributes: id, name, category, price, sku, and inventory.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }
}