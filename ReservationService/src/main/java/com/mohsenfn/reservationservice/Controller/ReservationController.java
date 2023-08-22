package com.mohsenfn.reservationservice.Controller;

import com.mohsenfn.reservationservice.Entity.Reservation;
import com.mohsenfn.reservationservice.Service.ReservationIService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Reservation")
@RestController
public class ReservationController {
    @Autowired
    ReservationIService ris;
    @PostMapping("/Add")
    public Reservation AssignReservationToUserAndEquipment(@RequestBody Reservation reservation,@RequestParam long idEquipment,@RequestParam String token){
        return ris.AssignReservationToUserAndEquipment(reservation, idEquipment,token);
    }
    @GetMapping("/GetReservation")
    public List<Reservation> getReservationByToken(@RequestParam String token) {
        return ris.getReservationByToken(token);
    }
    }
