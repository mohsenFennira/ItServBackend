package com.mohsenfn.reservationservice.Service;

import com.mohsenfn.reservationservice.Entity.Reservation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;

import java.util.List;

public interface ReservationIService {
    public Reservation AssignReservationToUserAndEquipment(Reservation reservation,long idEquipment,String token);
    public List<Reservation> getReservationByToken(String token);
}
