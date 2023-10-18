package com.prueba.reserva.cliente.controller;

import com.prueba.reserva.cliente.dto.ClienteInf;
import com.prueba.reserva.cliente.dto.ReservaDTO;
import com.prueba.reserva.cliente.dto.ReservaInf;
import com.prueba.reserva.cliente.entity.Cliente;
import com.prueba.reserva.cliente.service.ClienteService;
import com.prueba.reserva.hotel.entity.Hotel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/Cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/guardar")
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        System.out.println("GUARDANDOOOO");
        System.out.println(cliente);
        Cliente res_save = clienteService.guardar(cliente);
        return ResponseEntity.ok(res_save);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> aux_lista = clienteService.listar();
        return ResponseEntity.ok(aux_lista);
    }

    @GetMapping("/listar2")
    public ResponseEntity<List<ClienteInf>> listarClientes2(){
        List<ClienteInf> aux_lista = clienteService.listar2();
        return ResponseEntity.ok(aux_lista);
    }

    @GetMapping("/listarReserva")
    public ResponseEntity<List<ReservaInf>> listarClientesReserva(){
        List<ReservaInf> aux_lista = clienteService.listaReservar();
        return ResponseEntity.ok(aux_lista);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Cliente> actualizar(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.actualizar(cliente));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity eliminar(@RequestParam Integer iden){
        boolean res = clienteService.eliminar(iden);
        return ResponseEntity.ok("Cliente Eliminado");
    }
    @DeleteMapping("/eliminarReserva")
    public ResponseEntity eliminarReseva(@RequestParam Integer iden){
        boolean res = clienteService.eliminarReserva(iden);
        return ResponseEntity.ok("Reserva Eliminado");
    }

    @PostMapping("/reservar")
    public ResponseEntity<ReservaDTO> reservar(@RequestBody ReservaDTO reservaDTO){
        System.out.println("GUARDANDOOOO");
        System.out.println(reservaDTO);
        ReservaDTO res_save = clienteService.reservar(reservaDTO);
        return ResponseEntity.ok(reservaDTO);
    }

}
