package com.prueba.reserva.hotel.mapper;

import com.prueba.reserva.hotel.dto.HotelDTO;
import com.prueba.reserva.hotel.dto.HotelInf;
import com.prueba.reserva.hotel.entity.Habitacion;
import com.prueba.reserva.hotel.entity.Hotel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "direccion", target = "direccion"),
    })
    HotelInf toHotelDTO(Hotel hotel);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "habitaciones", source = "habitaciones", ignore = true),
    })
    Hotel toHotel(HotelDTO hotelDTO);

}
