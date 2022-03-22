package com.ep_movil.dao;

import com.ep_movil.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoDao extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p where p.nombre like %?1%")
    public List<Producto> findByNombre(String nombre);
    
    public List<Producto> findByNombreLikeIgnoreCase(String nombre);
}
