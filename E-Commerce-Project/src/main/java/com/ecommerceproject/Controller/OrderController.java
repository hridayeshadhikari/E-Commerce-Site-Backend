package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Address;
import com.ecommerceproject.Entity.Order;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.OrderException;
import com.ecommerceproject.Exception.UserException;
import com.ecommerceproject.Service.OrderService;
import com.ecommerceproject.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address address,
                                             @RequestHeader("Authorization") String jwt) throws UserException {
        User user=userService.findUserByJwt(jwt);
        Order order=orderService.createOrder(user,address);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException {
        User user=userService.findUserByJwt(jwt);
        List<Order> allOrders=orderService.userOrderHistory(user.getId());
        return new ResponseEntity<List<Order>>(allOrders,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws OrderException {

        Order order=orderService.findOrderById(id);
        return new ResponseEntity<Order>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrderRequest(@PathVariable Long orderId) throws OrderException {
        Order order=orderService.cancelOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}
