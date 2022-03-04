
package com.ep_movil.servicios;

import com.ep_movil.dao.IRolDao;
import com.ep_movil.entidades.Rol;
import com.ep_movil.enums.RolNombre;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService /*implements IRolService*/{

    @Autowired
    private IRolDao rolDao;
    
//    public List<Rol> listarRoles() {
//        return rolDao.findAll();
//    }

    public void guardarRol(Rol rol) {
        rolDao.save(rol);
    }

//    public void eliminarRol(Rol rol) {
//        rolDao.delete(rol);
//    }

    public Optional<Rol> getByRolNombre(RolNombre roles) {
    return rolDao.findByRolNombre(roles);
    }

    public boolean existsByRoles(RolNombre roles) {
    return rolDao.existsByRolNombre(roles);
    }
    
}
