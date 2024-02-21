package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Address;
import com.ecommerceproject.Entity.Order;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(User user, Address shippingAddress) {

        return null;
    }

    @Override
    public Order placedOrder(Long orderId) {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) {
        return null;
    }

    @Override
    public Order cancelOrder(Long orderId) {
        return null;
    }

    @Override
    public Order deleteOrder(Long orderId) {
        return null;
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        return null;
    }
}
