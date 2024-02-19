package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Repository.UserRepository;
import com.ecommerceproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Override
    public User findUserById(Long userId) {
        return null;
    }

    @Override
    public User findUserByJwt(String jwt) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
