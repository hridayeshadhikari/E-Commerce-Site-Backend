package com.ecommerceproject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();

    private Date orderDate;
    private Date deliveryDate;
    @OneToOne
    private String shippingAddress;
    private Double price;
    private Double discountPrice;
    private Integer discount;
    private String orderStatus;
    @Embedded
    private PaymentDetails paymentDetails=new PaymentDetails();
    private int totalItem;
    private LocalDateTime timeStamp;


}
