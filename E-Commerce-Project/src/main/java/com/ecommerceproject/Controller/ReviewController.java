package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Review;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Exception.UserException;
import com.ecommerceproject.Request.ReviewRequest;
import com.ecommerceproject.Service.ReviewService;
import com.ecommerceproject.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
public class ReviewController {
    private UserService userService;
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request,
                                              @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        String token= jwt.substring("Bearer ".length());
        User user=userService.findUserByJwt(token);
        Review review=reviewService.createReview(request,user);
        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId){
        List<Review> allReviews=reviewService.getAllProductReview(productId);
        return new ResponseEntity<List<Review>>(allReviews,HttpStatus.OK);
    }

}
