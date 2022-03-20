
package com.ep_movil.dao;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.entidades.Comentario;
import com.ep_movil.entidades.ItemCarrito;
import com.ep_movil.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComentarioDao extends JpaRepository<Comentario, Integer> {

    @Query("SELECT c FROM Comentario c WHERE c.producto = ?1 ")
    public List<Comentario> listarComentarios(Producto producto);
}
