package com.learnspring.inventorymanagement.dtos;

import com.learnspring.inventorymanagement.modals.Category;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class ProductResDto {
    private long id;
    private String title;
    private String image;
    private double price;
    private String description;
    private String brand;
    private String model;
    private String color;
    private Category category;
    private boolean popular;
    private double discount;
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class CategoryResDto{
        private long id;
        private String name;
    }
}
