package com.ep_movil.entidades;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
//@Inheritance(strategy = InheritanceType.JOINED)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String nombre;

    private String imagen;

    @NotNull
    @Min(value = 0)
    private Double precio;

    @NotNull
    @Min(value = 0)
    private Integer stock;

    private String especificacion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Comentario> comentarios;

    //private Integer costo--> posible entidad?;
    // Agregué esta entidad, porque creo que todos los productos pueden llegar a estar en un carrito. La diferencia
    //va a estar es que el carrito concretado, pasa a ser venta y eso va a reflejarse en el stock. Pero, mientras haya
    // stock, si hay 10 de x producto, pueden estar en 1000 carritos en simultaneo, de 1000 usuarios hasta que los
    //primeros 10 decidan hacer la compra

    public Producto() {
    }

    public Producto(Integer id, String nombre, String imagen, Double precio, Integer stock,
                    String especificacion, List<Comentario> comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.stock = stock;
        this.especificacion = especificacion;
        this.comentarios = comentarios;
//        this.carrito = carrito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

//    public List<Carrito> getCarrito() {
//        return carrito;
//    }
//
//    public void setCarrito(List<Carrito> carrito) {
//        this.carrito = carrito;
//    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

}
