package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.*;
import com.ecommerceproject.Repository.AddressRepository;
import com.ecommerceproject.Repository.OrderItemRepository;
import com.ecommerceproject.Repository.UserRepository;
import com.ecommerceproject.Service.CartService;
import com.ecommerceproject.Service.OrderItemService;
import com.ecommerceproject.Service.OrderService;
import com.ecommerceproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private CartService cartService;
    private ProductService productService;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private OrderItemService orderItemService;
    private OrderItemRepository orderItemRepository;
    @Override
    public Order createOrder(User user, Address shippingAddress) {
        shippingAddress.setUser(user);
        Address address=addressRepository.save(shippingAddress);
        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart=cartService.findUserCart(user.getId());
        List<OrderItem> orderItems=new ArrayList<>();
        for (CartItem item:cart.getCartItems()){
            OrderItem orderItem=new OrderItem();
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountPrice());

            OrderItem createdOrderItem=orderItemRepository.save(orderItem);
            orderItems.add(createdOrderItem);
        }
        Order createdOrder=new Order();
        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalItem(cart.getItemQuantity());
        createdOrder.setPrice(cart.getTotalPrice());
        createdOrder.setDiscountPrice(cart.getDiscountPrice());
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setTimeStamp(LocalDateTime.now());
        createdOrder.setShippingAddress(address);
        createdOrder.setDiscount(cart.getDiscount());
        createdOrder.setOrderStatus("PENDING");
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
