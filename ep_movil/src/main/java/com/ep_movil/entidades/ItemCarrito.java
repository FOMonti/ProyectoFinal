package com.ep_movil.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "itemcarrito")
public class ItemCarrito extends Producto {

    private Integer cantidad;

    public ItemCarrito() {
    }

    public ItemCarrito(Integer id, String nombre, String imagen, Double precio, Integer stock, List<Comentario> comentarios, List<Carrito> carrito) {
        super(id, nombre, imagen, precio, stock, comentarios, carrito);
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
    
}
