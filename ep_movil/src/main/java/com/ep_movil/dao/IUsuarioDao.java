
package com.ep_movil.dao;

import com.ep_movil.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}
