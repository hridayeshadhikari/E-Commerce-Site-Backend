package com.ecommerceproject.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddItemRequest{

    private Long productId;
    private int quantity;
    private String size;
    private int price;
}
