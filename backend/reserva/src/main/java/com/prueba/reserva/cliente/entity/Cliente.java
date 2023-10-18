package com.prueba.reserva.cliente.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rs_cliente")
public class Cliente {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_iden")
    private Integer iden;

    @Column(name = "cl_nombre", length = 250)
    private String nombre;
    @Column(name = "cl_correo", length = 250)
    private String correo;

    @Column(name = "cl_telefono", length = 250)
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    public Cliente() {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
