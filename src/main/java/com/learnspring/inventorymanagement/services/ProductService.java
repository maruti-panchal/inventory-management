package com.learnspring.inventorymanagement.services;

import com.learnspring.inventorymanagement.dtos.ProductReqDto;
import com.learnspring.inventorymanagement.dtos.ProductResDto;
import com.learnspring.inventorymanagement.modals.Category;
import com.learnspring.inventorymanagement.modals.Product;
import com.learnspring.inventorymanagement.respositories.CategoryRepository;
import com.learnspring.inventorymanagement.respositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResDto addProduct(ProductReqDto productReqDto){
        // Check if category exists
        Category category = categoryRepository.findByName(productReqDto.getCategory())
                .orElseGet(() -> {
                    Category newCategory = Category.builder()
                            .name(productReqDto.getCategory())
                            .build();
                    return categoryRepository.save(newCategory); // Save if not exists
                });

        Product product=Product.builder()
                .title(productReqDto.getTitle())
                .brand(productReqDto.getBrand())
                .color(productReqDto.getColor())
                .model(productReqDto.getModel())
                .price(productReqDto.getPrice())
                .description(productReqDto.getDescription())
                .image(productReqDto.getImage())
                .discount(productReqDto.getDiscount())
                .popular(productReqDto.isPopular())
                .category(category)
                .build();

        productRepository.save(product);
        return productToResDto(product);
    }
    public ProductResDto productToResDto(Product product){
        ProductResDto productResDto=ProductResDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .brand(product.getBrand())
                .color(product.getColor())
                .image(product.getImage())
                .price(product.getPrice())
                .description(product.getDescription())
                .model(product.getModel())
                .discount(product.getDiscount())
                .popular(product.isPopular())
                .category(product.getCategory())
                .build();
        return productResDto;
    }
}
