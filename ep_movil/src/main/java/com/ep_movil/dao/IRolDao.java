package com.ep_movil.dao;

import com.ep_movil.entidades.Rol;
import com.ep_movil.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolDao extends JpaRepository<Rol, Long> {

    Optional<Rol> findByRolNombre(RolNombre roles);

    boolean existsByRolNombre(RolNombre roles);
}
