package com.learnspring.inventorymanagement.controller;

import com.learnspring.inventorymanagement.dtos.ProductReqDto;
import com.learnspring.inventorymanagement.dtos.ProductResDto;
import com.learnspring.inventorymanagement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;

   ProductController(ProductService productService){
       this.productService = productService;
   }

    @PostMapping("")
    public ProductResDto createProduct(@RequestBody ProductReqDto productReqDto){
        return productService.addProduct(productReqDto);
    }
}
