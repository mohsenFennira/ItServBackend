package com.mohsenfn.authservice.Service;

import com.mohsenfn.authservice.Entity.JwtResponse;
import com.mohsenfn.authservice.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;

public interface UserIService {
    public User createAccount(User user);
    public JwtResponse generateToken(String username);
    public void validateToken(String token);
    public String getRolefromToken(String token);
    public User getuserbutoken(@NonNull HttpServletRequest request);
}
