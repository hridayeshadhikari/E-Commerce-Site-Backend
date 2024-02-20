package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.UserException;

public interface UserService {

    public User findUserById(Long userId) throws UserException;
    public User findUserByJwt(String jwt);

    User findUserByEmail(String email);
}
