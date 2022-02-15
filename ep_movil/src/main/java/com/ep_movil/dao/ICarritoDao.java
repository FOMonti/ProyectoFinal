
package com.ep_movil.dao;

import com.ep_movil.entidades.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoDao extends JpaRepository<Carrito,Integer>{
    
}
