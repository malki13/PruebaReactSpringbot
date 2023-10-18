package com.prueba.reserva.cliente.service;

import com.prueba.reserva.cliente.dto.ClienteInf;
import com.prueba.reserva.cliente.dto.ReservaDTO;
import com.prueba.reserva.cliente.dto.ReservaInf;
import com.prueba.reserva.cliente.entity.Cliente;
import com.prueba.reserva.cliente.entity.Reserva;
import com.prueba.reserva.cliente.mapper.ClienteMapper;
import com.prueba.reserva.cliente.mapper.ReservaMapper;
import com.prueba.reserva.cliente.repository.ClienteCrudRepository;
import com.prueba.reserva.cliente.repository.ReservaCrudRepository;
import com.prueba.reserva.hotel.dto.HotelInf;
import com.prueba.reserva.hotel.entity.Habitacion;
import com.prueba.reserva.hotel.entity.Hotel;
import com.prueba.reserva.hotel.repository.HabitacionCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ReservaCrudRepository reservaCrudRepository;
    @Autowired
    private HabitacionCrudRepository habitacionCrudRepository;

    @Autowired
    private ReservaMapper reservaMapper;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public Cliente guardar(Cliente cliente) {
        Cliente save = clienteCrudRepository.save(cliente);
        return save;
    }

    @Override
    public List<Cliente> listar() {
        return clienteCrudRepository.findAll();
    }

    @Override
    public List<ClienteInf> listar2() {
        List<Cliente> lista = clienteCrudRepository.findAll();
        List<ClienteInf> res= new ArrayList<>();
        for (Cliente d:lista) {
            res.add(clienteMapper.toClienteInf(d));
        }
        return res;
    }

    @Override
    public Cliente actualizar(Cliente cliente) {
        Cliente clienteBD = clienteCrudRepository.getReferenceById(cliente.getIden());
        clienteBD.setNombre(cliente.getNombre());
        clienteBD.setCorreo(cliente.getCorreo());
        clienteBD.setTelefono(cliente.getTelefono());
        return clienteCrudRepository.save(clienteBD);
    }

    @Override
    public boolean eliminar(Integer iden) {
        clienteCrudRepository.deleteById(iden);
        return true;
    }

    @Override
    public ReservaDTO reservar(ReservaDTO reservaDTO) {
        System.out.println(reservaDTO);
        Cliente clienteBD = clienteCrudRepository.getReferenceById(reservaDTO.getCliente());
        Habitacion habitacionBD = habitacionCrudRepository.getReferenceById(reservaDTO.getHabitaciones());

        Reserva newReserva = reservaMapper.toReserva(reservaDTO);
        newReserva.setCliente(clienteBD);
//
        List<Habitacion> res = new ArrayList<>();
        res.add(habitacionBD);
        newReserva.setHabitaciones(res);
//
        Reserva reserva = reservaCrudRepository.save(newReserva);
        return reservaMapper.toReservaDTO(reserva);
    }

    @Override
    public List<ReservaInf> listaReservar() {
        List<Reserva> lista = reservaCrudRepository.findAll();
        List<ReservaInf> res= new ArrayList<>();
        for (Reserva d:lista) {
            res.add(reservaMapper.toReservaInf(d));
        }
        return res;
    }

    @Override
    public boolean eliminarReserva(Integer iden) {
        reservaCrudRepository.deleteById(iden);
        return true;
    }

}
