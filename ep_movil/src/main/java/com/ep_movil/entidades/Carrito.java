package com.ep_movil.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "carrito")
//    @JoinTable(name = "carrito_itemcarrito",
//            joinColumns = @JoinColumn(name = "carrito_id"),
//            inverseJoinColumns = @JoinColumn(name = "itemcarrito_id"))
    private List<ItemCarrito> items;
    //Aca agrego a la entidad de usuario, porque el carrito como tal (existente con un id), empieza a existir al momento
    //que el usuario agrega un producto y lo guarda para comprarlo a futuro. Es el intermediario entre el producto y
    //usuario. Si observamos, en usuario no tenemos nada con el atributo producto y lo mismo a la inversa, en producto.
    //Para que exista el carro, se tienen que relacionar el producto con el usuario.

    //cantidad de cada item pedido

    private Double precio;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public Carrito() {
    }

    public Carrito(Usuario usuario) {
        this.items = new ArrayList<ItemCarrito>();
        this.usuario = usuario;
        this.precio = 0.0;
    }

    public Carrito(Integer id, List<ItemCarrito> items, Usuario usuario) {
        this.id = id;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}

