
package com.ep_movil.servicios;

import com.ep_movil.dao.IUsuarioDao;
import com.ep_movil.entidades.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAll();
    }

    @Transactional
    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Transactional
    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario encontrarUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getId()).orElse(null);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    public Usuario findById(Integer id) {
        return usuarioDao.getById(id);
    }
}
