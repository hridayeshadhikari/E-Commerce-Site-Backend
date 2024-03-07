package com.ecommerceproject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String title;

    private String description;
    private Double price;
    @Column(name = "discounted_price")
    private Double discountedPrice;
    @Column(name = "discount_percent")
    private int discountPercent;

    private int quantity;
    private String brand;
    private String color;
    @Column(name = "Image_Url")
    private String imageUrl;

    @Embedded
    @ElementCollection
    private Set<Size> size=new HashSet<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating> rating=new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> review=new ArrayList<>();

    private int numRating;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime timeStamp;

}
