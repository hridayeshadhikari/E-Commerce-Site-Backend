package com.ecommerceproject.Service;


import com.ecommerceproject.Entity.Cart;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Request.AddItemRequest;

public interface CartService {
    Cart createCart(User user);
    String addCartItem(Long userId, AddItemRequest request);
    Cart findUserCart(Long userId);

}
