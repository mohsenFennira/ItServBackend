package com.mohsenfn.authservice.Controller;

import com.mohsenfn.authservice.DTO.UserRequest;
import com.mohsenfn.authservice.Entity.JwtResponse;
import com.mohsenfn.authservice.Entity.User;
import com.mohsenfn.authservice.Repository.UserRepository;
import com.mohsenfn.authservice.Service.JWTService;
import com.mohsenfn.authservice.Service.SessionService;
import com.mohsenfn.authservice.Service.UserIService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;


@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    UserIService uis;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    SessionService ss;
    @PostMapping("/register")
    public User createAccount(@RequestBody User user) {
        return uis.createAccount(user);
    }
    @PostMapping("/token")
    public JwtResponse generateToken(@RequestBody UserRequest user) {
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
    public String getRolefromToken(@RequestParam String token) {
        return uis.getRolefromToken(token);
    }
    @GetMapping("/GetUser")
    public String getCurrentUser(@AuthenticationPrincipal Authentication authentication) {
        // The authentication object holds information about the currently authenticated user
        if (authentication != null) {
            String username = authentication.getName();
            return "Currently authenticated user: " + username;
        } else {
            return "No user currently authenticated";
        }
    }
    @GetMapping("/getSession")
    public User getuserbutoken(@NonNull HttpServletRequest request){
        return uis.getuserbutoken(request);
    }
    @Autowired
    JWTService jwtService;
    @Autowired
    UserRepository ur;
    @GetMapping("/getSessionByToken")
    public User getuserbyoken(@RequestParam("token") String token){
        String Username=jwtService.extractUsername(token);
        User user=  ur.findByUsername(Username).get();
        return user;
    }
    @PostMapping("/manualLogout")
    public String customLogut(HttpServletRequest request) throws ServletException
    {
        request.logout();
        return "redirect:/";
    }
    @GetMapping("/getAllAccount")
    public List<User> getAllAccount() {
        return uis.getAllAccount();
    }
}
