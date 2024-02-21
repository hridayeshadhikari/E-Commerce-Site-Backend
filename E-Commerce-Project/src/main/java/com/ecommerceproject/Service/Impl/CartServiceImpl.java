package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Cart;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Request.AddItemRequest;
import com.ecommerceproject.Service.CartService;

public class CartServiceImpl implements CartService {
    @Override
    public Cart createCart(User user) {
        return null;
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) {
        return null;
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
