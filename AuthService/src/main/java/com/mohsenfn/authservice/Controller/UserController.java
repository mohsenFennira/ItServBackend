package com.mohsenfn.authservice.Controller;

import com.mohsenfn.authservice.DTO.UserRequest;
import com.mohsenfn.authservice.Entity.User;
import com.mohsenfn.authservice.Service.UserIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    UserIService uis;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public User createAccount(@RequestBody User user) {
        return uis.createAccount(user);
    }
    @PostMapping("/token")
    public String generateToken(@RequestBody UserRequest user) {
        Authentication a= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(a.isAuthenticated()) {
            return uis.generateToken(user.getUsername());
        }
        else {
            throw new RuntimeException("invalid information");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        uis.validateToken(token);
        return "validate token";
    }
    @GetMapping("/getrole")
    public String getRolefromToken(String token) {
        return uis.getRolefromToken(token);
    }
}
