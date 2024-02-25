package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Entity.Review;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest request, User user) throws ProductException;
    List<Review> getAllProductReview(Long productId);
}
