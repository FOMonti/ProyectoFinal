
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

//En esta entidad, no creo que haya relaciones asociativas, en todo caso, es una composicion. El ejemplo mas claro que
//les puedo dar es con el carrito. Una venta contiene un carrito (o ex carrito en todo caso, porque es una venta
// concretada) pero un carrito, no contiene una venta. Tranquilamente se puede quedar ahi como carro o cancelarse.
@Entity
@Table(name="venta_realizada")
public class VentaRealizada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne()
    private Usuario usuario;
    
    private Date fecha;
        
    //Acá teniamos una List<Carrito> y creo que es solo un Carrito. Una venta no es una lista de carritos, en todo caso
    // es una lista de productos, que tambien podría ser una alternativa.
    
    @OneToOne(cascade = CascadeType.ALL)
    private Carrito carritoVendido;//carrito que se vendió
    
    private Double precio; // la suma de producto.precios

    public VentaRealizada() {
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
