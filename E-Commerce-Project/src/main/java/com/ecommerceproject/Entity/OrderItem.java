package com.ecommerceproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Order order;
    @ManyToOne
    private Product product;
    private Size size;
    private Integer quantity;
    private Integer price;
    private Integer discountedPrice;
    private Long userId;
    private LocalDateTime deliveryDate;
}
