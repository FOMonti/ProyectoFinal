
package com.ep_movil.dao;

import com.ep_movil.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentarioDao  extends JpaRepository<Comentario,Integer>{
    
}
