package com.ep_movil.controladores;

import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping({"/usuario"})
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/crear")
    public String toCreate(Model model) {
        model.addAttribute("titulo", "Crear Usuario");
        model.addAttribute("usuario", new Usuario());
        return "usuario-form";
    }

    @GetMapping("/login")
    public String toLogin(Model model) {
        model.addAttribute("titulo", "Crear Usuario");
        model.addAttribute("usuario", new Usuario());
        return "usuario-login";
    }
}
