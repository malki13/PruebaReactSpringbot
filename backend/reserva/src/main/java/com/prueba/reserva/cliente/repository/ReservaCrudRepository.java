package com.prueba.reserva.cliente.repository;

import com.prueba.reserva.cliente.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaCrudRepository extends JpaRepository<Reserva,Integer> {

}
