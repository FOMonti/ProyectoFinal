package com.ep_movil.controladores;

import com.ep_movil.entidades.Comentario;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.ComentarioServiceImpl;
import com.ep_movil.servicios.UsuarioServiceImpl;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PerfilUsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ComentarioServiceImpl comentarioService;

    @GetMapping("/perfil")
    public String toPerfil(Model model, Principal principal) {
        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (!ou.isPresent()) {
            return "redirect:/";
        }
        Usuario usuario = ou.get();
        List<Comentario> comentarios = comentarioService.listarComentariosUsuario(usuario);
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("usuario", usuario);
        return "usuarioo/perfil";
    }

    @PostMapping("/savePerfil")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String savePerfil(Usuario usuario, Model model, Principal principal) {
//@RequestParam(name="password")String password
        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (!ou.isPresent()) {
            return "redirect:/";
        }

        usuario = ou.get();

        model.addAttribute("usuario", usuario);
        return "usuarioo/perfil";
    }

    @GetMapping("/editarPerfil")
    public String editarPerfil(Model model, Principal principal) {

        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (!ou.isPresent()) {
            return "redirect:/";
        }
        
        Usuario usuario=ou.get();

        model.addAttribute("usuario", usuario);
        
        return "usuario-form";
    }

}
