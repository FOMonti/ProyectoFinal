package com.ep_movil.entidades;

import javax.persistence.*;

@Entity
@Table(name = "publicidad")
public class Publicidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String marca;

    private String imagen;

    private String slogan;

    private String descripcion;

    public Publicidad() {
    }

    public Publicidad(Integer id, String marca, String imagen, String slogan) {
        this.id = id;
        this.marca = marca;
        this.imagen = imagen;
        this.slogan = slogan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getMarca() {
        return marca;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
