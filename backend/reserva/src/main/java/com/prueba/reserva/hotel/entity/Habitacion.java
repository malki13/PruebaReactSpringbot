package com.prueba.reserva.hotel.entity;

import com.prueba.reserva.cliente.entity.Reserva;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "rs_habitacion")
public class Habitacion {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hb_iden")
    private Integer iden;
    @Column(name = "hb_numero", length = 250)
    private String numero;
    @Column(name = "hb_tipo", length = 250)
    private String tipo;
    @Column(name = "hb_precio")
    private double precio;
    @Column(name = "hb_disponible")
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "hb_ht_id")
    private Hotel hotel;

    @ManyToMany(mappedBy = "habitaciones")
    private List<Reserva> reserva;

    public Habitacion() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
