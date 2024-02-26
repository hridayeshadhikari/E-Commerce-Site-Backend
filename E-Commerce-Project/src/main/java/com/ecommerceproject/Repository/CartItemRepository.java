package com.ecommerceproject.Repository;

import com.ecommerceproject.Entity.Cart;
import com.ecommerceproject.Entity.CartItem;
import com.ecommerceproject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart=:cart AND ci.userId=:userId AND ci.product=:product AND ci.size=:size")
    CartItem isCartItemExist(@Param("cart") Cart cart, @Param("userId") Long userId, @Param("product")Product product,@Param("size")String size);
}
