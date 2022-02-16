
package com.ep_movil.dao;

import com.ep_movil.entidades.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemCarritoDao extends JpaRepository<ItemCarrito,Integer>{
    
}
