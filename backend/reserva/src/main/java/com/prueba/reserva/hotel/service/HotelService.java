package com.prueba.reserva.hotel.service;

import com.prueba.reserva.cliente.entity.Reserva;
import com.prueba.reserva.hotel.dto.HabitcionInf;
import com.prueba.reserva.hotel.dto.HotelDTO;
import com.prueba.reserva.hotel.dto.HotelInf;
import com.prueba.reserva.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel save(Hotel hotel);
    Hotel save2(HotelDTO hotel);
    List<Hotel> listar();

    List<HotelInf> listar2();
    Hotel actualizar(Hotel hotel);
    boolean eliminar(Integer iden);
    List<HabitcionInf> listarHabitacion(Integer idHotel);

}
