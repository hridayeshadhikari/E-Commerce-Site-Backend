package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.OrderItem;
import com.ecommerceproject.Repository.OrderItemRepository;
import com.ecommerceproject.Service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {


    private OrderItemRepository itemRepository;
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return itemRepository.save(orderItem);
    }
}
