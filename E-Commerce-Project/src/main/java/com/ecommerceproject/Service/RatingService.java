package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.Rating;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.UserException;
import com.ecommerceproject.Request.RatingRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {
    Rating createRating(User user, RatingRequest request) throws Exception;
    List<Rating> getProductRating(Long productId);
}
