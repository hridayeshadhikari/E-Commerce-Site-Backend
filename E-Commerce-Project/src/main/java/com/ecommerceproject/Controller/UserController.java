package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Exception.UserException;
import com.ecommerceproject.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException {
            String token= jwt.substring("Bearer ".length());
            User user=userService.findUserByJwt(token);
            return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
