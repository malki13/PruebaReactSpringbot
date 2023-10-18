package com.prueba.reserva.hotel.mapper;

import com.prueba.reserva.hotel.dto.HabitcionInf;
import com.prueba.reserva.hotel.dto.HotelDTO;
import com.prueba.reserva.hotel.dto.HotelInf;
import com.prueba.reserva.hotel.entity.Habitacion;
import com.prueba.reserva.hotel.entity.Hotel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface HabitacionMapper {
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "numero", target = "numero"),
            @Mapping(source = "tipo", target = "tipo"),
            @Mapping(source = "disponible", target = "disponible"),
    })
    HabitcionInf toHabitacionInf(Habitacion habitacion);

    @InheritInverseConfiguration
    @Mappings({
//            @Mapping(target = "habitaciones", source = "habitaciones", ignore = true),
    })
    Habitacion toHabitacion(HabitcionInf habitcionInf);
}
