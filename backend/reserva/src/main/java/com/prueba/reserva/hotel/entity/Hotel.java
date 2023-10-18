package com.prueba.reserva.hotel.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "rs_hotel")
public class Hotel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ht_iden")
    private Integer iden;

    @Column(name = "ht_nombre", length = 250)
    private String nombre;

    @Column(name = "ht_direccion", length = 250)
    private String direccion;

    @Column(name = "ht_descripcion", length = 250)
    private String descripcion;

//    @OneToMany(mappedBy = "hotel")
//    private List<Habitacion> habitaciones;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Habitacion> habitaciones;

    public Hotel() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}
