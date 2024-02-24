package com.ecommerceproject.Repository;

import com.ecommerceproject.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o Where o.user.id=:userId AND (o.orderStatus=PLACED OR o.orderStatus=SHIPPED OR o.orderStatus=CONFIRMED OR o.orderStatus=DELIVERED)")
    public List<Order> getOrdersOfUser(@Param("userId") Long userId);
}
