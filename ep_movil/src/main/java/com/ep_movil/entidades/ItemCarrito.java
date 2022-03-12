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

    @ManyToOne
    private Carrito carrito;

    public ItemCarrito() {

    }

    public ItemCarrito(Integer id) {
        this.id = id;

    }

    public ItemCarrito(Producto producto, Integer cantidad, Carrito carrito) {
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


}
