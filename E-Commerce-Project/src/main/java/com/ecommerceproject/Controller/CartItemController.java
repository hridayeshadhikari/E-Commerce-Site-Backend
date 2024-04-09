package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.CartItem;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.CartItemException;
import com.ecommerceproject.Exception.UserException;
import com.ecommerceproject.Response.ApiResponse;
import com.ecommerceproject.Service.CartItemService;
import com.ecommerceproject.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_item")
@AllArgsConstructor
public class CartItemController {
    private CartItemService itemService;

    private UserService userService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
                                                      @RequestHeader("Authorization")String jwt) throws UserException, CartItemException {
        String token= jwt.substring("Bearer ".length());
        User user=userService.findUserByJwt(token);
        itemService.removeCartItem(user.getId(),cartItemId);
        ApiResponse response=new ApiResponse();
        response.setMessage("item removed Successfully");
        response.setStatus(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId,
                                                   @RequestBody CartItem cartItem,
                                                   @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        String token= jwt.substring("Bearer ".length());
        User user=userService.findUserByJwt(token);
        CartItem cartItem1=itemService.updateCartItem(user.getId(),cartItemId,cartItem);
        return new ResponseEntity<CartItem>(cartItem1,HttpStatus.OK);
    }
}
