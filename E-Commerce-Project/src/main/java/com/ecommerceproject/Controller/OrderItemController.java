package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.OrderItem;
import com.ecommerceproject.Service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class OrderItemController {
    private OrderItemService itemService;

    @PostMapping("/orderitem")
    public ResponseEntity<OrderItem> createOrderItemHandler(@RequestBody OrderItem orderItem){
        OrderItem orderItem1=itemService.createOrderItem(orderItem);
        return new ResponseEntity<OrderItem>(orderItem1, HttpStatus.CREATED);
    }
}
