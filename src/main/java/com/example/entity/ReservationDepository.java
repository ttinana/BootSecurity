package com.example.entity;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;


interface ReservationDepository extends JpaRepository<Reservation, Long> {

	Collection<Reservation> findByReservationName(String rn);

}