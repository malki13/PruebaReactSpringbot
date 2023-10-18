package com.prueba.reserva.cliente.mapper;

import com.prueba.reserva.cliente.dto.ClienteInf;
import com.prueba.reserva.cliente.entity.Cliente;
import com.prueba.reserva.hotel.dto.HotelDTO;
import com.prueba.reserva.hotel.dto.HotelInf;
import com.prueba.reserva.hotel.entity.Hotel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "telefono", target = "telefono"),
    })
    ClienteInf toClienteInf(Cliente cliente);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "habitaciones", source = "habitaciones", ignore = true),
    })
    Hotel toHotel(HotelDTO hotelDTO);
}
