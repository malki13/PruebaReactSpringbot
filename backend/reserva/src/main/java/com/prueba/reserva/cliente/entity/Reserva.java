package com.prueba.reserva.cliente.entity;

import com.prueba.reserva.hotel.entity.Habitacion;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "rs_reserva")
public class Reserva {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_iden")
    private Integer iden;
    @Column(name = "re_fechae")
    private Date entrada;
    @Column(name = "re_fechas")
    private Date salida;

    @ManyToOne
    @JoinColumn(name = "re_cli_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "re_habitaciones",
            joinColumns = @JoinColumn(name = "re_iden"),
            inverseJoinColumns = @JoinColumn(name = "hb_iden"))
    private List<Habitacion> habitaciones;

    public Reserva() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}
