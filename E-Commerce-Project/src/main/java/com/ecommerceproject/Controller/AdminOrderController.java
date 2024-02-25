package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Order;
import com.ecommerceproject.Exception.OrderException;
import com.ecommerceproject.Response.ApiResponse;
import com.ecommerceproject.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/order")
public class AdminOrderController {
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders=orderService.getAllOrder();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmOrder(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException {
        Order order=orderService.confirmedOrder(orderId);
        return new ResponseEntity<Order>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/shipped")
    public ResponseEntity<Order> shippedOrder(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException {
        Order order=orderService.shippedOrder(orderId);
        return new ResponseEntity<Order>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/delivered")
    public ResponseEntity<Order> deliveredOrder(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException {
        Order order=orderService.deliveredOrder(orderId);
        return new ResponseEntity<Order>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException {
        Order order=orderService.cancelOrder(orderId);
        return new ResponseEntity<Order>(order,HttpStatus.OK);
    }

    @DeleteMapping("/orderId/delete")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long orderId) throws OrderException {
        orderService.deleteOrder(orderId);
         ApiResponse response=new ApiResponse();
         response.setMessage("Product Deleted Successfully");
         response.setStatus(true);
         return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
