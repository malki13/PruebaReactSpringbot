package com.prueba.reserva.hotel.controller;

import com.prueba.reserva.hotel.dto.HabitcionInf;
import com.prueba.reserva.hotel.dto.HotelDTO;
import com.prueba.reserva.hotel.dto.HotelInf;
import com.prueba.reserva.hotel.entity.Habitacion;
import com.prueba.reserva.hotel.entity.Hotel;
import com.prueba.reserva.hotel.service.HotelService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;


    @PostMapping("/guardar")
    public ResponseEntity<Hotel> save(@RequestBody Hotel hotel){
        System.out.println("GUARDANDOOOO");
        System.out.println(hotel);
        Hotel res_save = hotelService.save(hotel);
        return ResponseEntity.ok(res_save);
    }

    @PostMapping("/guardar2")
    public ResponseEntity<Hotel> save2(@RequestBody HotelDTO hotelDTO){
        System.out.println("GUARDANDOOOO");
        System.out.println(hotelDTO);
        Hotel res_save = hotelService.save2(hotelDTO);
        return ResponseEntity.ok(res_save);
    }

    @GetMapping("/listar")
    public List<Hotel> listarRegiones(){
        List<Hotel> aux_lista = hotelService.listar();
        return aux_lista;
    }

    @GetMapping("/listar2")
    public ResponseEntity<List<HotelInf>> listarRegiones2(){
        List<HotelInf> aux_lista = hotelService.listar2();
        return ResponseEntity.ok(aux_lista);
    }

    @GetMapping("/Habitaciones")
    public ResponseEntity<List<HabitcionInf>> listarHabitaciones(@RequestParam Integer idHotel){
        System.out.println(idHotel);
        List<HabitcionInf> aux_lista = hotelService.listarHabitacion(idHotel);
        return ResponseEntity.ok(aux_lista);
    }

    @PutMapping("/actualizar")
    public Hotel actualizar(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.actualizar(hotel);
        return hotel1;
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestParam Integer iden){
        boolean res = hotelService.eliminar(iden);
        return ResponseEntity.ok("Hotel Eliminado");
    }

    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }



}
