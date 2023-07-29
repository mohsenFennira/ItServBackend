package com.mohsenfn.authservice.Service;

import com.mohsenfn.authservice.Entity.User;

public interface UserIService {
    public User createAccount(User user);
    public String generateToken(String username);
    public void validateToken(String token);
    public String getRolefromToken(String token);
}
