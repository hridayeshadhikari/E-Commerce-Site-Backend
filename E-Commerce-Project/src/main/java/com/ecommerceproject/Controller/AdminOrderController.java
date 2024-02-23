package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Order;
import com.ecommerceproject.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/order")
public class AdminOrderController {
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders=orderService.getAllOrder();
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmedOrder(){

    }

}
