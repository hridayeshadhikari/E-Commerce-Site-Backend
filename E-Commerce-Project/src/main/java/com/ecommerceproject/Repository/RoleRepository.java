package com.ecommerceproject.Repository;

import com.ecommerceproject.Entity.Role;
import com.ecommerceproject.Entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(RoleEnum name);
}
