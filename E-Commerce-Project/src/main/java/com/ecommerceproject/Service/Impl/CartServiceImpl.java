package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Cart;
import com.ecommerceproject.Entity.CartItem;
import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Repository.CartRepository;
import com.ecommerceproject.Request.AddItemRequest;
import com.ecommerceproject.Service.CartItemService;
import com.ecommerceproject.Service.CartService;
import com.ecommerceproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;
    @Override
    public Cart createCart(User user) {
        Cart cart=new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws Exception {
        Cart cart=cartRepository.findByUserId(userId);
        Product product=productService.findProductById(request.getProductId());
        CartItem cartItemPresent=cartItemService.isCartExistAlready(cart,product, request.getSize(), userId);
        if(cartItemPresent==null){
            CartItem cartItem=new CartItem();
            cartItem.setQuantity(request.getQuantity());
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setUserId(userId);

            Double price=request.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(request.getSize());
            CartItem createdCartItem=cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        return "added to cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart=cartRepository.findByUserId(userId);

        double totalPrice=0;
        double totalDiscount=0;
        int totalItem=0;

        for(CartItem cartItem:cart.getCartItems()){
            totalPrice=totalPrice+cartItem.getPrice();
            totalDiscount=totalDiscount+cartItem.getDiscountPrice();
            totalItem=totalItem+cartItem.getQuantity();
        }

        cart.setTotalPrice(totalPrice);
        cart.setDiscountPrice(totalDiscount);
        cart.setItemQuantity(totalItem);
        cart.setDiscount(totalPrice-totalDiscount);
        return cartRepository.save(cart);
    }
}
