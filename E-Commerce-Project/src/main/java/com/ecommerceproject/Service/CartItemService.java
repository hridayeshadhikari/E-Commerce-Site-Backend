package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.Cart;
import com.ecommerceproject.Entity.CartItem;
import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Exception.UserException;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);
    CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws Exception; //cartItemException userexception;
    CartItem findCartItemById(Long cartItemId) throws Exception;
    CartItem isCartExistAlready(Cart cart, Product product,String size,Long userId) throws UserException;
    void removeCartItem(Long userId,Long CartItemId) throws Exception;
}
