
package com.ep_movil.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comentarios")
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    private String comentario;
    
    @ManyToOne
    private Producto producto;
    
    @ManyToOne
    private Cliente cliente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDelComentario;

    public Comentario() {
    }

    public Comentario(Integer id, String comentario, Producto producto, Cliente cliente, Date fechaDelComentario) {
        this.id = id;
        this.comentario = comentario;
        this.producto = producto;
        this.cliente = cliente;
        this.fechaDelComentario = fechaDelComentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaDelComentario() {
        return fechaDelComentario;
    }

    public void setFechaDelComentario(Date fechaDelComentario) {
        this.fechaDelComentario = fechaDelComentario;
    }
    
    
}
