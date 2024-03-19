package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.*;
import com.ecommerceproject.Exception.OrderException;
import com.ecommerceproject.Repository.AddressRepository;
import com.ecommerceproject.Repository.OrderItemRepository;
import com.ecommerceproject.Repository.OrderRepository;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private CartService cartService;
    private OrderRepository orderRepository;
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
        createdOrder.getPaymentDetails().setPaymentStatus("PENDING");

        Order saveOrder=orderRepository.save(createdOrder);
        for(OrderItem item:orderItems){
            item.setOrder(saveOrder);
            orderItemRepository.save(item);
        }
        return saveOrder;
    }

    @Override
    public Order placedOrder(Long orderId) throws Exception {
        Order order=findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setPaymentStatus("COMPLETED");
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> order =orderRepository.findById(orderId);
        if(order.isEmpty()){
            throw new OrderException("no order found with this id "+orderId);
        }
        return order.get();
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        List<Order> usersAllOrders=orderRepository.getOrdersOfUser(userId);
        return usersAllOrders;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> allOrders=orderRepository.findAll();
        return allOrders;
    }
}
