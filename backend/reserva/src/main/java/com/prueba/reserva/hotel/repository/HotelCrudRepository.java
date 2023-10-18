package com.prueba.reserva.hotel.repository;

import com.prueba.reserva.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelCrudRepository extends JpaRepository<Hotel,Integer> {
}
