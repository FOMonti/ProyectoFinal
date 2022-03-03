package com.ep_movil.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "carrito_itemcarrito",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "itemcarrito_id"))
    private List<ItemCarrito> items;
    //Aca agrego a la entidad de usuario, porque el carrito como tal (existente con un id), empieza a existir al momento
    //que el usuario agrega un producto y lo guarda para comprarlo a futuro. Es el intermediario entre el producto y
    //usuario. Si observamos, en usuario no tenemos nada con el atributo producto y lo mismo a la inversa, en producto.
    //Para que exista el carro, se tienen que relacionar el producto con el usuario.
    @OneToOne
    private Usuario usuario;

    //cantidad de cada item pedido

    public Carrito() {
    }

    public Carrito(Usuario usuario) {
        this.usuario = usuario;
        this.items = new ArrayList<ItemCarrito>();
    }

    public Carrito(Integer id, List<ItemCarrito> items, Usuario usuario) {
        this.id = id;
        this.items = items;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}

