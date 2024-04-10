package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.Role;
import com.ecommerceproject.Repository.RoleRepository;
import com.ecommerceproject.Service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
