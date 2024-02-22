package com.ecommerceproject.Request;

import com.ecommerceproject.Entity.Product;
import com.ecommerceproject.Entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private Long productId;
    private String review;
}
