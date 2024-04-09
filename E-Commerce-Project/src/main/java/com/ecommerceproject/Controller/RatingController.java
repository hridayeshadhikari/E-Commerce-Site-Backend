package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Rating;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Request.RatingRequest;
import com.ecommerceproject.Service.RatingService;
import com.ecommerceproject.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
@AllArgsConstructor
public class RatingController {

    private RatingService ratingService;
    private UserService userService;

    @PostMapping("/rate")
    public ResponseEntity<Rating> rateProduct(@RequestBody RatingRequest req, @RequestHeader("Authorization")String jwt) throws Exception {
        String token= jwt.substring("Bearer ".length());
        User user=userService.findUserByJwt(token);
        Rating rating=ratingService.createRating(user,req);
        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId) throws ProductException {
        List<Rating> allRating=ratingService.getProductRating(productId);
        return new ResponseEntity<List<Rating>>(allRating,HttpStatus.OK);
    }
}
