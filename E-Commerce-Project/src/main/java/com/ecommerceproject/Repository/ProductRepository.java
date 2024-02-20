package com.ecommerceproject.Repository;
import com.ecommerceproject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:category IS NULL OR p.category = :category) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:minDiscount IS NULL OR p.discountedPrice >= :minDiscount) AND " +
            "(:maxDiscount IS NULL OR p.discountedPrice <= :maxDiscount) " +
            "ORDER BY CASE " +
            "WHEN :sort = 'priceAsc' THEN p.price " +
            "WHEN :sort = 'priceDesc' THEN p.price END ASC, " +
            "CASE " +
            "WHEN :sort = 'discountAsc' THEN p.discountedPrice " +
            "WHEN :sort = 'discountDesc' THEN p.discountedPrice END ASC")
    public List<Product> filterProduct(@Param("category") String category,
                                        @Param("minPrice") Integer minPrice,
                                        @Param("maxPrice") Integer maxPrice,
                                        @Param("minDiscount") Integer minDiscount,
                                        @Param("maxDiscount") Integer maxDiscount,
                                        @Param("sort") String sort);
}
