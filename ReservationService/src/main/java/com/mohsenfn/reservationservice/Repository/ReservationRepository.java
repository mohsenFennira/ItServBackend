package com.mohsenfn.reservationservice.Repository;

import com.mohsenfn.reservationservice.Entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {
}
