package com.ecommerceproject.Repository;

import com.ecommerceproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByEmail(String email);
}
