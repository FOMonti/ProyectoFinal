
package com.ep_movil.dao;

import com.ep_movil.entidades.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoDao extends JpaRepository<Carrito, Integer> {

    //@Query("SELECT c FROM Carrito c WHERE c.userid == :query")
//    public Carrito findByUsuarioId(@Param("query") Integer query);
}
