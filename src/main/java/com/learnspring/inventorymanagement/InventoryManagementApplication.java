package com.learnspring.inventorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(InventoryManagementApplication.class, args);
        System.out.println("Inventory Management Application Started");
    }

}
