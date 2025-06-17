package com.learnspring.inventorymanagement.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="product")
@Component
@Builder
public class Product extends BaseModal{
    private String title;
    private String image;
    private double price;
    private String description;
    private String brand;
    private String model;
    private String color;
    @ManyToOne
    private Category category;
    private boolean popular;
    private double discount;


    public String toString() {
        return "Product{" +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", category='" + category + '\'' +
                ", popular=" + popular +
                ", discount=" + discount +
                '}';
    }
}
