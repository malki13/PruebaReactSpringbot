package com.prueba.reserva.cliente.mapper;

import com.prueba.reserva.cliente.dto.ReservaDTO;
import com.prueba.reserva.cliente.dto.ReservaInf;
import com.prueba.reserva.cliente.entity.Reserva;
import com.prueba.reserva.hotel.dto.HotelDTO;
import com.prueba.reserva.hotel.dto.HotelInf;
import com.prueba.reserva.hotel.entity.Hotel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface ReservaMapper {
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "entrada", target = "entrada"),
            @Mapping(source = "salida", target = "salida"),
            @Mapping(source = "cliente.iden", target = "cliente"),
            @Mapping(source = "habitaciones", target = "habitaciones",ignore = true),
    })
    ReservaDTO toReservaDTO(Reserva reserva);

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "entrada", target = "entrada"),
            @Mapping(source = "salida", target = "salida"),
            @Mapping(source = "cliente.iden", target = "usuario"),
//            @Mapping(source = "habitaciones", target = "habitaciones",ignore = true),
    })
    ReservaInf toReservaInf(Reserva reserva);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "cliente", source = "cliente", ignore = true),
            @Mapping(target = "habitaciones", source = "habitaciones", ignore = true),
    })
    Reserva toReserva(ReservaDTO reservaDTO);

}
