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

    public ItemCarrito(Integer id, String nombre, String imagen, Double precio, Integer stock, String especificacion, List<Comentario> comentarios, List<Carrito> carrito) {
        super(id, nombre, imagen, precio, stock, especificacion, comentarios, carrito);
    }

    public ItemCarrito(Producto producto, Integer cantidad) {
        super(producto.getId(), producto.getNombre(), producto.getImagen(), producto.getPrecio(), producto.getStock(), producto.getEspecificacion(), producto.getComentarios(), producto.getCarrito());
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


}
