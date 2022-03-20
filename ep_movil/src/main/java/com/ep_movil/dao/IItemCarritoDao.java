
package com.ep_movil.dao;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.entidades.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemCarritoDao extends JpaRepository<ItemCarrito, Integer> {

    @Query("SELECT i FROM ItemCarrito i WHERE i.carrito = ?1")
    public List<ItemCarrito> itemsCarrito(Carrito carrito);

    public ItemCarrito getById(Integer id);
}
