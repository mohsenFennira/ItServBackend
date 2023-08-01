package com.mohsenfn.authservice.Service;

import com.mohsenfn.authservice.Entity.User;
import com.mohsenfn.authservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    UserRepository userrRepository;

    public Optional<User> getUserBySession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            return userrRepository.findByUsername(username);
        } else {
            System.out.println("test");
            return null; // or throw an exception, depending on your requirements
        }
    }
}
