package com.mohsenfn.reservationservice.Service;

import com.mohsenfn.reservationservice.Entity.Reservation;
import com.mohsenfn.reservationservice.External.client.UserIService;
import com.mohsenfn.reservationservice.Repository.ReservationRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements ReservationIService {
    @Autowired
    ReservationRepository rr;
    @Autowired
    UserIService uis;
    @Override
    public Reservation AssignReservationToUserAndEquipment(Reservation reservation, long idEquipment,String token) {
        reservation.setIdEquipment(idEquipment);
        reservation.setIdUser(uis.getuserbyoken(token).getId());
        return rr.save(reservation);
    }

    @Override
    public List<Reservation> getReservationByToken(String token) {
        Long userId=uis.getuserbyoken(token).getId();
        List<Reservation>reservationList= (List<Reservation>) rr.findAll();
        List<Reservation> newList=new ArrayList<>();
        for (Reservation r : reservationList){
            if(userId.equals(r.getIdUser())){
                newList.add(r);
            }
        }
        return newList;
    }
}
