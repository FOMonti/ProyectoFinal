
package com.ep_movil.entidades;

import com.ep_movil.enums.Roles;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "roles")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(unique=true)
    private Roles rolNombre;

    public Rol() {
    }

    public Rol(Long id, Roles rolNombre) {
        this.id = id;
        this.rolNombre = rolNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(Roles rolNombre) {
        this.rolNombre = rolNombre;
    }
    
    
}
