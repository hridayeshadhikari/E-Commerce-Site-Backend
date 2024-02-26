package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Cart;
import com.ecommerceproject.Entity.CartItem;
import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.CartItemException;
import com.ecommerceproject.Exception.UserException;
import com.ecommerceproject.Repository.CartItemRepository;
import com.ecommerceproject.Repository.CartRepository;
import com.ecommerceproject.Service.CartItemService;
import com.ecommerceproject.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private UserService userService;
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }



    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()){
            throw new CartItemException("cart item does not exist with given id");
        }
        return cartItem.get();
    }
    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem cartItem1=findCartItemById(id);
        User user=userService.findUserById(cartItem1.getUserId());
        if(user.getId().equals(userId)){
            cartItem1.setQuantity(cartItem.getQuantity());
            cartItem1.setPrice(cartItem1.getProduct().getPrice()*cartItem1.getQuantity());
            cartItem1.setDiscountPrice(cartItem1.getProduct().getDiscountedPrice()*cartItem1.getQuantity());
        }
        return cartItemRepository.save(cartItem1);
    }

    @Override
    public CartItem isCartExistAlready(Cart cart, Product product, String size, Long userId) throws UserException {
        CartItem cartItem=cartItemRepository.isCartItemExist(cart,userId,product,size);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long CartItemId) throws CartItemException, UserException {
        CartItem cartItem=findCartItemById(CartItemId);
        User user=userService.findUserById(cartItem.getUserId());
        User loggedInUser=userService.findUserById(userId);
        if(user.getId().equals(loggedInUser.getId())){
            cartItemRepository.delete(cartItem);
        }
        else {
            throw new CartItemException("no cart item found with this id");
        }

    }
}
