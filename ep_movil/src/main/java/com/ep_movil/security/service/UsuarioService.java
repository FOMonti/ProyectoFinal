package com.ep_movil.security.service;

import com.ep_movil.dao.IUsuarioDao;
import com.ep_movil.entidades.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAll();
    }

    public void guardarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    public Optional<Usuario> getByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioDao.findById(id);
    }

    public boolean existsById(Long id) {
        return usuarioDao.existsById(id);
    }

    public boolean existsByUserName(String username) {
        return usuarioDao.existsByUsername(username);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }
    
   public Usuario findByUsername2(String username){
       return usuarioDao.findByUsername(username).orElse(null);
   }
}
