package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Address;
import com.ecommerceproject.Entity.Order;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Repository.CartRepository;
import com.ecommerceproject.Service.CartService;
import com.ecommerceproject.Service.OrderService;
import com.ecommerceproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private CartRepository cartRepository;
    private CartService cartService;
    private ProductService productService;
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
