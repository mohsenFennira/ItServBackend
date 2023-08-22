package com.mohsenfn.reservationservice.External.client;

import com.mohsenfn.reservationservice.External.Request.UserRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTH-SERVICE/auth")

public interface UserIService {
    @GetMapping("getSessionByToken")
    public UserRequest getuserbyoken(@RequestParam("token") String token);
}
