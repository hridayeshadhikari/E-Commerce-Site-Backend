package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Entity.Review;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.ProductException;
import com.ecommerceproject.Repository.ReviewRepository;
import com.ecommerceproject.Request.ReviewRequest;
import com.ecommerceproject.Service.ProductService;
import com.ecommerceproject.Service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ProductService productService;
    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException {

        Product product=productService.findProductById(request.getProductId());
        Review review=new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setReview(request.getReview());
        review.setTimeStamp(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllProductReview(Long productId) {

        return reviewRepository.getAllProductReview(productId);
    }
}
