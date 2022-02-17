
package com.ep_movil.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


//Usuario es la persona registrada en la pagina.
@Entity
@Table(name="usuarios")
public class Usuario extends Cliente  {
    
    @NotEmpty
    private String username;
    
    @NotEmpty()
    @Size(min = 8)
    @Pattern(regexp ="[a-zA-Z0-9]")
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles= new HashSet();
    
    // definir si esto va a ser OneToOne o OneToMany dependiendo de cómo se plantee el carrito. 
    // Mientras tanto puse OneToOne* Un usuario tiene un solo carrito. Existe en el usuario, lleno o vacio (si esta
    //vacio, podemos hacer un boton que envíe a la tienda). Cualquier cosa, vemos el ejemplo de MercadoLibre.
    //Como acá tenemos que se relaciona con usuario, en carrito, tambien tengo que poner el atributo del carrito
    @OneToOne
    private Carrito historialCarrito;
    
    //saqué el atributo comentarios porque lo hereda del cliente.

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String apellido, String email, List<Comentario> comentario) {
        super(id, nombre, apellido, email, comentario);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Carrito getHistorialCarrito() {
        return historialCarrito;
    }

    public void setHistorialCarrito (Carrito historialCarrito) {
        this.historialCarrito = historialCarrito;
    }
    
  
    

   

    
}
