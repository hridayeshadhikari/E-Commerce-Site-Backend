package com.ecommerceproject.Controller;

import com.ecommerceproject.Entity.Cart;
import com.ecommerceproject.Entity.Role;
import com.ecommerceproject.Entity.User;
import com.ecommerceproject.Repository.UserRepository;
import com.ecommerceproject.Request.AuthRequest;
import com.ecommerceproject.Request.RegisterRequest;
import com.ecommerceproject.Response.AuthResponse;
import com.ecommerceproject.Security.JwtProvider;
import com.ecommerceproject.Service.CartService;
import com.ecommerceproject.Service.Impl.CustomUserDetailService;
import com.ecommerceproject.Service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private UserRepository userRepository;
    private CustomUserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;
    private CartService cartService;

    private final JwtProvider jwtProvider;
    private AuthenticationManager authenticationManager;
    private RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest request) throws Exception {

        String email=request.getEmail();
        String password=request.getPassword();
        String firstName=request.getFirstName();
        String lastName=request.getLastName();
        User isUserExist=userRepository.findByEmail(email);
        if(isUserExist!=null){
            throw new Exception("this email is associated with another account");
        }

        User createUser=new User();
        createUser.setEmail(email);
        createUser.setPassword(passwordEncoder.encode(password));
        createUser.setFirstName(firstName);
        createUser.setLastName(lastName);
        createUser.setMobile("9876546465");
        createUser.setTimeStamp(LocalDateTime.now());

        Role role=roleService.findRoleByName("ROLE_USER");
        Set<Role> roleSet= new HashSet<>();
        roleSet.add(role);
        createUser.setRoles(roleSet);

        User newUser=userRepository.save(createUser);
        Cart cart =cartService.createCart(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse(token,"Registration successful");


        return new ResponseEntity<>(authResponse,HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginRequest(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.generateToken(authentication);
        AuthResponse response=new AuthResponse(token,"Login Successful");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }


}
