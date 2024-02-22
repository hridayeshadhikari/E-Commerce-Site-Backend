package com.ecommerceproject.Request;
import com.ecommerceproject.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequest {

    private Double rating;
    private Long productId;
}
