package com.ecommerceproject.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Order_detail")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItems=new ArrayList<>();

    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    @ManyToOne
    private Address shippingAddress;
    private Double price;
    private Double discountPrice;
    private Double discount;
    private String orderStatus;
    @Embedded
    private PaymentDetails paymentDetails=new PaymentDetails();
    private int totalItem;
    private LocalDateTime timeStamp;


}
