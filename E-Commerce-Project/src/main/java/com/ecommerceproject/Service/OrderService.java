package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.Address;
import com.ecommerceproject.Entity.Order;
import com.ecommerceproject.Entity.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, Address shippingAddress);
    Order placedOrder (Long orderId);
    Order confirmedOrder(Long orderId);
    Order shippedOrder(Long orderId);
    Order deliveredOrder(Long orderId);
    Order findOrderById(Long orderId);
    Order cancelOrder(Long orderId);
    Order deleteOrder(Long orderId);
    List<Order> userOrderHistory(Long userId);
    List<Order> getAllOrder();

}
