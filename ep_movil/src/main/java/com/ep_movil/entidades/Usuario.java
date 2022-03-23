package com.ep_movil.entidades;

import com.ep_movil.passwordConfig.ValidPassword;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

//Usuario es la persona registrada en la pagina.
@Entity
@Table(name = "usuarios")
public class Usuario extends Cliente {

    @NotEmpty
    private String username;

    private String imagen;

    @NotEmpty()
    @Length(max = 25, min = 5, message = "La contraseña debe tener de 5 a 25 caracteres")
    @ValidPassword
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<Rol>();

    // definir si esto va a ser OneToOne o OneToMany dependiendo de cómo se plantee el carrito. 
    // Mientras tanto puse OneToOne* Un usuario tiene un solo carrito. Existe en el usuario, lleno o vacio (si esta
    //vacio, podemos hacer un boton que envíe a la tienda). Cualquier cosa, vemos el ejemplo de MercadoLibre.
    //Como acá tenemos que se relaciona con usuario, en carrito, tambien tengo que poner el atributo del carrito
    @OneToOne(cascade = CascadeType.ALL)
    private Carrito historialCarrito;//usuario compra === new carrito ===set

    public Usuario() {
    }

    public Usuario(String username, String password, String imagen, Carrito historialCarrito, Long id, String nombre, String apellido, String email) {
        super(id, nombre, apellido, email);
        this.username = username;
        this.password = password;
        this.imagen = imagen;
        this.historialCarrito = historialCarrito;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    public void setHistorialCarrito(Carrito historialCarrito) {
        this.historialCarrito = historialCarrito;
    }

}
