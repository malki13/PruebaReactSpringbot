package com.prueba.reserva.hotel.repository;

import com.prueba.reserva.hotel.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionCrudRepository extends JpaRepository<Habitacion,Integer>{

    @Query(
            value = "select d from Habitacion d where d.hotel.iden = :idHotel"
    )
    List<Habitacion> getAllByHotel(Integer idHotel);

}
