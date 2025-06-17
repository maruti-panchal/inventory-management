package com.learnspring.inventorymanagement.services;

import com.learnspring.inventorymanagement.dtos.ProductReqDto;
import com.learnspring.inventorymanagement.dtos.ProductResDto;
import com.learnspring.inventorymanagement.modals.Category;
import com.learnspring.inventorymanagement.modals.Product;
import com.learnspring.inventorymanagement.respositories.CategoryRepository;
import com.learnspring.inventorymanagement.respositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    public boolean deleteProduct(Long id){
        try {
            productRepository.findById(id).ifPresent(product -> {
                productRepository.delete(product);
            });
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting product " + id);
        }
        return true;
    }
    public ProductResDto updateProduct(Long id, ProductReqDto productReqDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Optional: check if category needs to be updated or created
        Category category = categoryRepository.findByName(productReqDto.getCategory())
                .orElseGet(() -> categoryRepository.save(
                        Category.builder().name(productReqDto.getCategory()).build()
                ));

        // Update fields
        product.setTitle(productReqDto.getTitle());
        product.setBrand(productReqDto.getBrand());
        product.setColor(productReqDto.getColor());
        product.setModel(productReqDto.getModel());
        product.setPrice(productReqDto.getPrice());
        product.setDescription(productReqDto.getDescription());
        product.setImage(productReqDto.getImage());
        product.setDiscount(productReqDto.getDiscount());
        product.setPopular(productReqDto.isPopular());
        product.setCategory(category); // set updated or existing category

        // Save updated product
        productRepository.save(product);

        return productToResDto(product);
    }
    public ProductResDto getProductById(Long id){
        Product product=productRepository.findById(id).orElse(null);
        return productToResDto(product);
    }
    public List<ProductResDto> getAllProducts(){
        Iterable<Product>product=productRepository.findAll();
        List<ProductResDto> productResDtos=new ArrayList<>();

        for(Product p:product){
            productResDtos.add(productToResDto(p));
        }

        return productResDtos;
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
