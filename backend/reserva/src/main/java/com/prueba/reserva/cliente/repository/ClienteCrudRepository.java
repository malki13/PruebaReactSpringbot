package com.prueba.reserva.cliente.repository;

import com.prueba.reserva.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteCrudRepository extends JpaRepository<Cliente,Integer> {
}
