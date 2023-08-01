package com.mohsenfn.reservationservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReservation;
    private long idUser;
    private long idEquipment;
    private Instant DateReservation;
    private long quantity;
    @Enumerated(EnumType.STRING)
    private StatusReservation status;
}
