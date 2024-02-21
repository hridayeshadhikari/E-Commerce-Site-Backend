package com.ecommerceproject.Repository;

import com.ecommerceproject.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
