package com.ep_movil.entidades;

import java.util.Date;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venta_realizada")
public class VentaRealizada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne()
    private Usuario usuario;

    private Date fecha;

    @OneToOne(cascade = CascadeType.ALL)
    private Carrito carritoVendido;

    private Double precio;

    public VentaRealizada() {
    }

    public VentaRealizada(Usuario usuario, Date fecha, Carrito carritoVendido, Double precio) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.carritoVendido = carritoVendido;
        this.precio = precio;
    }

    public VentaRealizada(Integer id, Usuario usuario, Date fecha, Carrito carritoVendido, Double precio) {
        this.id = id;
        this.usuario = usuario;
        this.fecha = fecha;
        this.carritoVendido = carritoVendido;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Carrito getCarritoVendido() {
        return carritoVendido;
    }

    public void setCarritoVendido(Carrito carritoVendido) {
        this.carritoVendido = carritoVendido;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


}
