package com.mohsenfn.reservationservice.External.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest  {
    private long id;
    private String email;
    private String password;
}
