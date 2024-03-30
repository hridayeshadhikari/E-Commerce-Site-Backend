package com.ecommerceproject.Service;

import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.UserException;

import java.util.List;

public interface UserService {

    public User findUserById(Long userId) throws UserException;
    public User findUserByJwt(String jwt) throws UserException;

    User findUserByEmail(String email);

    List<User> getAll();
}
