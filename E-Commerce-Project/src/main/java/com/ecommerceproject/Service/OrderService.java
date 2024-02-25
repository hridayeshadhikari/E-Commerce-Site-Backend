package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.Address;
import com.ecommerceproject.Entity.Order;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.OrderException;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, Address shippingAddress);
    Order placedOrder (Long orderId) throws Exception;
    Order confirmedOrder(Long orderId) throws OrderException;
    Order shippedOrder(Long orderId) throws OrderException;
    Order deliveredOrder(Long orderId) throws OrderException;
    Order findOrderById(Long orderId) throws OrderException;
    Order cancelOrder(Long orderId) throws OrderException;
    void deleteOrder(Long orderId) throws OrderException;
    List<Order> userOrderHistory(Long userId);
    List<Order> getAllOrder();

}
