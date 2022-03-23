package com.ep_movil.entidades;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDelComentario;

    public Comentario() {
    }

    public Comentario(String comentario, Producto producto, Usuario usuario, Date fechaDelComentario) {
        this.texto = comentario;
        this.producto = producto;
        this.usuario = usuario;
        this.fechaDelComentario = fechaDelComentario;
    }

    public Comentario(Integer id, String comentario, Producto producto, Usuario usuario, Date fechaDelComentario) {
        this.id = id;
        this.texto = comentario;
        this.producto = producto;
        this.usuario = usuario;
        this.fechaDelComentario = fechaDelComentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario cliente) {
        this.usuario = cliente;
    }

    public Date getFechaDelComentario() {
        return fechaDelComentario;
    }

    public void setFechaDelComentario(Date fechaDelComentario) {
        this.fechaDelComentario = fechaDelComentario;
    }

}
