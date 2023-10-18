package com.prueba.reserva.cliente.service;

import com.prueba.reserva.cliente.dto.ClienteInf;
import com.prueba.reserva.cliente.dto.ReservaDTO;
import com.prueba.reserva.cliente.dto.ReservaInf;
import com.prueba.reserva.cliente.entity.Cliente;
import com.prueba.reserva.cliente.entity.Reserva;

import java.util.List;

public interface ClienteService {
    Cliente guardar(Cliente cliente);
    List<Cliente> listar();
    List<ClienteInf> listar2();
    Cliente actualizar(Cliente cliente);
    boolean eliminar(Integer iden);

    ReservaDTO reservar(ReservaDTO reservaDTO);

    List<ReservaInf> listaReservar();
    boolean eliminarReserva(Integer iden);


}
