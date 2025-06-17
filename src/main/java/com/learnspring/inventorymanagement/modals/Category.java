package com.learnspring.inventorymanagement.modals;


import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "category")
@Component
@Builder
public class Category extends BaseModal{
    private String name;
}
