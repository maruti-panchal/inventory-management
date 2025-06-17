package com.learnspring.inventorymanagement.controller;

import com.learnspring.inventorymanagement.dtos.ProductReqDto;
import com.learnspring.inventorymanagement.dtos.ProductResDto;
import com.learnspring.inventorymanagement.respositories.ProductRepository;
import com.learnspring.inventorymanagement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;
    ProductService productService;

   ProductController(ProductService productService, ProductRepository productRepository){
       this.productService = productService;
       this.productRepository = productRepository;
   }

   @DeleteMapping("/{id}")
   public boolean deleteProduct(@PathVariable Long id) {
       return productService.deleteProduct(id);
   }

    @PostMapping("")
    public ProductResDto createProduct(@RequestBody ProductReqDto productReqDto){
        return productService.addProduct(productReqDto);
    }

    @GetMapping("")
    public List<ProductResDto> getAllProducts(){
       return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResDto getProduct(@PathVariable Long id){
       return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResDto updateProduct(@PathVariable Long id, @RequestBody ProductReqDto productReqDto){
       return productService.updateProduct(id, productReqDto);
    }
}
