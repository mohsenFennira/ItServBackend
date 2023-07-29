package com.mohsenfn.authservice.Service;

import com.mohsenfn.authservice.Entity.User;
import com.mohsenfn.authservice.Repository.UserRepository;
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
    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
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
