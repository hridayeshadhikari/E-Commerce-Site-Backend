package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Repository.UserRepository;
import com.ecommerceproject.Request.AuthRequest;
import com.ecommerceproject.Response.AuthResponse;
import com.ecommerceproject.Security.JwtProvider;
import com.ecommerceproject.Service.Impl.CustomUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private UserRepository userRepository;
    private CustomUserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody User user) throws Exception {

        String email=user.getEmail();
        String password=user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();
        User isUserExist=userRepository.findUserByEmail(email);
        if(isUserExist!=null){
            throw new Exception("this email is associated with another account");
        }

        User createUser=new User();
        createUser.setEmail(email);
        createUser.setPassword(passwordEncoder.encode(password));
        createUser.setFirstName(firstName);
        createUser.setLastName(lastName);
        User newUser=userRepository.save(createUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(newUser.getEmail(),newUser.getPassword());
        String token= JwtProvider.generateToken(authentication);
        AuthResponse response= new AuthResponse(token,"Registration Successful");
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginRequest(@RequestBody AuthRequest authRequest){
        Authentication authentication=authenticate(authRequest.getEmail() ,authRequest.getPassword());
        String token=JwtProvider.generateToken(authentication);
        AuthResponse response=new AuthResponse(token,"Login Successful");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    private Authentication authenticate(String email,String password){
        UserDetails userDetails= userDetailService.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("No user exists with this email register first");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}
