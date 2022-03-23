package com.ep_movil.servicios;

import com.ep_movil.dao.PublicidadDao;
import com.ep_movil.entidades.Publicidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicidadService {

    @Autowired
    private PublicidadDao publicidadDao;

    @Transactional(readOnly = true)
    public List<Publicidad> listarPublicidades() {
        return publicidadDao.findAll();
    }

    @Transactional
    public void guardarCarrito(Publicidad publicidad) {
        publicidadDao.save(publicidad);
    }
}
