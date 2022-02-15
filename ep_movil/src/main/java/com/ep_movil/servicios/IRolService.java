
package com.ep_movil.servicios;

import com.ep_movil.entidades.Rol;
import java.util.List;


public interface IRolService {
    
    public List<Rol>listarRoles();
    
    public void guardarRol(Rol rol);
    
    public void eliminarRol(Rol rol);

    public Rol encontrarRol(Rol rol);
}
