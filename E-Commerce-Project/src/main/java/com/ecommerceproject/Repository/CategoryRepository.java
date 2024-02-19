package com.ecommerceproject.Repository;

import com.ecommerceproject.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
}
