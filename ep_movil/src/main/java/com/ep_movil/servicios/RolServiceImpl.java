
package com.ep_movil.servicios;

import com.ep_movil.dao.IRolDao;
import com.ep_movil.entidades.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImpl implements IRolService{

    @Autowired
    private IRolDao rolDao;
    
    @Transactional (readOnly = true)
    @Override
    public List<Rol> listarRoles() {
        return rolDao.findAll();
    }

    @Transactional 
    @Override
    public void guardarRol(Rol rol) {
        rolDao.save(rol);
    }

    @Transactional
    @Override
    public void eliminarRol(Rol rol) {
        rolDao.delete(rol);
    }

    @Transactional (readOnly = true)
    @Override
    public Rol encontrarRol(Rol rol) {
        return rolDao.findById(rol.getId()).orElse(null);
    }
    
}
