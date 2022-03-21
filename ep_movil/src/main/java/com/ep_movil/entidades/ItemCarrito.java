package com.ep_movil.entidades;

import javax.persistence.*;

@Entity
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cantidad;

    @OneToOne
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

    public ItemCarrito() {

    }

    public ItemCarrito(Integer id) {
        this.id = id;

    }

    public ItemCarrito(Integer cantidad, Producto producto, Carrito carrito) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.carrito = carrito;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getId() {
        return id;
    }
}
