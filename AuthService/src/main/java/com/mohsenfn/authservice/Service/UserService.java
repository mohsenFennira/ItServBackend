package com.mohsenfn.authservice.Service;

import com.mohsenfn.authservice.Entity.JwtResponse;
import com.mohsenfn.authservice.Entity.User;
import com.mohsenfn.authservice.Repository.UserRepository;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
@Service
public class UserService implements UserIService {
    @Autowired
    UserRepository ur;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JWTService jwtService;


    @Override
    public User createAccount(User user) {
        user.setDateCreation(Instant.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return ur.save(user);
    }
   public User getuserbutoken(@NonNull HttpServletRequest request){
        final String autHeader=request.getHeader("Authorization");
        final String jwt;
        final String Username;
        jwt = autHeader.substring(7);
       Username=jwtService.extractUsername(jwt);
        User user=  ur.findByUsername(Username).get();
        return user;
    }
    @Override
    public JwtResponse generateToken(String username) {
        String token=jwtService.generateToken(username);
        User user=ur.findByUsername(username).get();
        return new JwtResponse(user, token);
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    @Override
    public String getRolefromToken(String token) {
        return jwtService.getRoleFromToken(token);
    }
}
