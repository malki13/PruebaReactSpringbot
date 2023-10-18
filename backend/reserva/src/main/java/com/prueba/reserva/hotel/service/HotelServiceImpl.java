package com.prueba.reserva.hotel.service;

import com.prueba.reserva.hotel.dto.HabitcionInf;
import com.prueba.reserva.hotel.dto.HotelDTO;
import com.prueba.reserva.hotel.dto.HotelInf;
import com.prueba.reserva.hotel.entity.Habitacion;
import com.prueba.reserva.hotel.entity.Hotel;
import com.prueba.reserva.hotel.mapper.HabitacionMapper;
import com.prueba.reserva.hotel.mapper.HotelMapper;
import com.prueba.reserva.hotel.repository.HabitacionCrudRepository;
import com.prueba.reserva.hotel.repository.HotelCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{
    @Autowired
    private HotelCrudRepository hotelCrudRepository;

    @Autowired
    private HabitacionCrudRepository habitacionCrudRepository;

    @Autowired
    private HotelMapper mapper;

    @Autowired
    private HabitacionMapper habitacionMapper;

    @Override
    public Hotel save(Hotel hotel) {
        Hotel save = hotelCrudRepository.save(hotel);
        return save;
    }

    @Override
    public Hotel save2(HotelDTO hotel) {
        Hotel newHotel = new Hotel();
        newHotel.setNombre(hotel.getNombre());
        newHotel.setDireccion(hotel.getDireccion());
        newHotel.setDescripcion(hotel.getDescripcion());
        Hotel res_save = hotelCrudRepository.save(newHotel);
        for (int i = 0; i < hotel.getHabitaciones(); i++) {
            Habitacion newHabitacion = new Habitacion();
            newHabitacion.setHotel(res_save);
            newHabitacion.setDisponible(true);
            newHabitacion.setTipo("Normal");
            newHabitacion.setPrecio(20.00);
            newHabitacion.setNumero(Integer.toString(i+1));
            Habitacion res = habitacionCrudRepository.save(newHabitacion);
        }
        return res_save;
    }

    @Override
    public List<Hotel> listar() {
        List<Hotel> lista = hotelCrudRepository.findAll();
        return lista;
    }

    @Override
    public List<HotelInf> listar2() {
        List<Hotel> lista = hotelCrudRepository.findAll();
        List<HotelInf> res= new ArrayList<>();
        for (Hotel d:lista) {
            res.add(mapper.toHotelDTO(d));
        }
        return res;
    }
//    @Override
//    public Page<DispositivoInfoOnly> findAll(Pageable pageable) {
//        return deviceCrudRepository.findAll(pageable).map(dispositivo -> mapper.toDispositivoInfoOnly(dispositivo));
//    }

    @Override
    public Hotel actualizar(Hotel hotel) {
        System.out.println(hotel);
        Hotel aux_hotel = hotelCrudRepository.getReferenceById(hotel.getIden());
        System.out.println(aux_hotel.getNombre());

        aux_hotel.setNombre(hotel.getNombre());
        aux_hotel.setDescripcion(hotel.getDescripcion());
        aux_hotel.setDireccion(hotel.getDireccion());
        System.out.println(aux_hotel.getNombre());
        hotelCrudRepository.save(aux_hotel);
//        hotelCrudRepository.
        return hotel;
    }

    @Override
    public boolean eliminar(Integer iden) {
        Hotel aux_hotel = hotelCrudRepository.getReferenceById(iden);
        List<Habitacion> aux = aux_hotel.getHabitaciones();
        for (Habitacion h:aux) {
            habitacionCrudRepository.delete(h);
        }
        hotelCrudRepository.delete(aux_hotel);
        return true;
    }

    @Override
    public List<HabitcionInf> listarHabitacion(Integer idHotel) {
        List<Habitacion> res = habitacionCrudRepository.getAllByHotel(idHotel);
        List<HabitcionInf> aux_res= new ArrayList<>();
        for (Habitacion d:res) {
            aux_res.add(habitacionMapper.toHabitacionInf(d));
        }
        return aux_res;
    }

}
