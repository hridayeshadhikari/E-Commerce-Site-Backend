package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Cart;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Exception.UserException;
import com.ecommerceproject.Request.AddItemRequest;
import com.ecommerceproject.Response.ApiResponse;
import com.ecommerceproject.Service.CartService;
import com.ecommerceproject.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class UserCartController {

    private UserService userService;
    private CartService cartService;
    @GetMapping("/")
    public ResponseEntity<Cart> getUsersCart(@RequestHeader("Authorization") String jwt) throws UserException {
        User user=userService.findUserByJwt(jwt);
        Cart cart=cartService.findUserCart(user.getId());
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody AddItemRequest request) throws ProductException, UserException {
        User user=userService.findUserByJwt(jwt);
        cartService.addCartItem(user.getId(),request);

        ApiResponse res=new ApiResponse();
        res.setMessage("Successfully Added to Cart");
        res.setStatus(true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }
}
