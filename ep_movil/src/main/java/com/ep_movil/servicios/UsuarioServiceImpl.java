//package com.ep_movil.servicios;
//
//import com.ep_movil.dao.IUsuarioDao;
//import com.ep_movil.entidades.Rol;
//import com.ep_movil.entidades.Usuario;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service("userDetailsService")
//public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {
//
//    @Autowired
//    private IUsuarioDao usuarioDao;
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<Usuario> listarUsuarios() {
//        return usuarioDao.findAll();
//    }
//
//    @Transactional
//    @Override
//    public void guardarUsuario(Usuario usuario) {
//        usuarioDao.save(usuario);
//    }
//
//    @Transactional
//    @Override
//    public void eliminarUsuario(Usuario usuario) {
//        usuarioDao.delete(usuario);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public Usuario encontrarUsuario(Usuario usuario) {
//        return usuarioDao.findById(usuario.getId()).orElse(null);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Usuario usuario = usuarioDao.findByUsername(username);
//
//        if (usuario == null) {
//            throw new UsernameNotFoundException(username);
//        }
//
//        List roles = new ArrayList<GrantedAuthority>();
//
//        for (Rol aux : usuario.getRoles()) {
//            roles.add(new SimpleGrantedAuthority(aux.getRolNombre().toString()));
//        }
//
//        return new User(usuario.getUsername(), usuario.getPassword(), roles);
//    }
//
//}
