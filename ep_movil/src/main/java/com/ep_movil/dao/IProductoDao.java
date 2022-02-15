
package com.ep_movil.dao;

import com.ep_movil.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoDao extends JpaRepository<Producto,Integer> {
    
}
