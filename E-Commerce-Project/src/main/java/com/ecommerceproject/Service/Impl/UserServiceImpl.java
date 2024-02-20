package com.ecommerceproject.Service.Impl;

import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.UserException;
import com.ecommerceproject.Repository.UserRepository;
import com.ecommerceproject.Security.JwtProvider;
import com.ecommerceproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserException("no user found with this id"+userId);
        }

        return user.get();
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email= JwtProvider.getEmailFromJwtToken(jwt);
        User user=userRepository.findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
