
package com.ep_movil.servicios;

import com.ep_movil.dao.IUsuarioDao;
import com.ep_movil.entidades.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAll();
    }

    @Transactional
    public void guardarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Transactional
    public Usuario encontrarUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getId()).orElse(null);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    public Usuario findById(Long id) {
        return usuarioDao.getById(id);
    }

//    public String getSessionId(HttpServletRequest httpRequest) {
//        String sessionId = null;
//        HttpSession session = httpRequest.getSession(createSession);
//        if (session != null) {
//            sessionId = session.getId();
//        }
//        return sessionId;
//    }
}
