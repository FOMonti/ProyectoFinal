
package com.ep_movil.dao;

import com.ep_movil.entidades.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);

    public Usuario getById(Long id);
}
