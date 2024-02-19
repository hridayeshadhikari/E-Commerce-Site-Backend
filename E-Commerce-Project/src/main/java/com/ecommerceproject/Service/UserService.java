package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.User;

public interface UserService {

    public User findUserById(Long userId);
    public User findUserByJwt(String jwt);

    User findUserByEmail(String email);
}
