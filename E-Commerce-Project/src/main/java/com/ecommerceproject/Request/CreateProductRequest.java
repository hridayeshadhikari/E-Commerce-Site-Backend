package com.ecommerceproject.Request;


import com.ecommerceproject.Entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String title;
    private String description;
    private Double price;
    private Double discountedPrice;
    private int discountPercent;
    private int quantity;
    private String brand;
    private String color;
    private Set<Size> size=new HashSet<>();
    private String imageUrl;
    private String category;

}
