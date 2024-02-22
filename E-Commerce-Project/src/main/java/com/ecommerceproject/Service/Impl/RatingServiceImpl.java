package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Entity.Rating;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.UserException;
import com.ecommerceproject.Repository.RatingRepository;
import com.ecommerceproject.Request.RatingRequest;
import com.ecommerceproject.Service.ProductService;
import com.ecommerceproject.Service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private ProductService productService;

    @Override
    public Rating createRating(User user, RatingRequest request) throws Exception {
        Product product=productService.findProductById(request.getProductId());
        Rating rating=new Rating();
        rating.setRating(request.getRating());
        rating.setProduct(product);
        rating.setUser(user);
        rating.setTimeStamp(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) {

        return ratingRepository.getAllProductRating(productId);
    }
}
