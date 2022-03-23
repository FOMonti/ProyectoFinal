package com.ep_movil.dao;

import com.ep_movil.entidades.Publicidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicidadDao extends JpaRepository<Publicidad, Integer> {
}
