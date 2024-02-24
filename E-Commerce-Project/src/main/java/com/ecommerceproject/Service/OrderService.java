package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.Address;
import com.ecommerceproject.Entity.Order;
import com.ecommerceproject.Entity.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, Address shippingAddress);
    Order placedOrder (Long orderId) throws Exception;
    Order confirmedOrder(Long orderId) throws Exception;
    Order shippedOrder(Long orderId) throws Exception;
    Order deliveredOrder(Long orderId) throws Exception;
    Order findOrderById(Long orderId) throws Exception;
    Order cancelOrder(Long orderId) throws Exception;
    void deleteOrder(Long orderId) throws Exception;
    List<Order> userOrderHistory(Long userId);
    List<Order> getAllOrder();

}
