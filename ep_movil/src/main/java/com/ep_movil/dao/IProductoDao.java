package com.ep_movil.dao;

import com.ep_movil.entidades.Producto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoDao extends JpaRepository<Producto, Integer> {

//    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %?1%")
//    public List<Producto> findByNombre(String nombre);
//
//    public List<Producto> findByNombreLikeIgnoreCase(String nombre);
//    @Query(value = "SELECT p FROM Producto p WHERE p.nombre LIKE '%?1%'")
//     Page<Producto> findAllProductosWithPagination(String query, Pageable pageable);
    @Query(
            value = "SELECT * FROM PRODUCTOS WHERE NOMBRE = ?1",
            countQuery = "SELECT count(*) FROM PRODUCTOS WHERE NOMBRE = ?1",
            nativeQuery = true)
    Page<Producto> findByNombre(String query, Pageable pageable);
 
//    Page<Producto> findAllProductosWithPagination(String query, Pageable pageable);
    //public List<Producto> findByNombre(String nombre);
}
