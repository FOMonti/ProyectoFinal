package com.ep_movil.controladores;

import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping({"/usuario"})
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/registrar")
    public String toCreate(Model model) {
        model.addAttribute("titulo", "Crear Usuario");
        model.addAttribute("usuario", new Usuario());
        return "usuario-form";
    }

    @GetMapping("/logear")
    public String toLog(Model model) {
        model.addAttribute("titulo", "Logeando");
        model.addAttribute("usuario", new Usuario());
        return "usuario-login";
    }

    @PostMapping("/login")
    public String toLogin(Model model) {
        model.addAttribute("titulo", "Crear Usuario");
        model.addAttribute("usuario", new Usuario());
        return "redirect:/usuario-login";
    }

    @PostMapping("/registro")
    public String guardarUsuarios(/*@RequestParam(name = "file", required = false) MultipartFile portada,*/
            Model model, Usuario usuario) { //RedirectAttributes redirect / Model model
        usuarioService.guardarUsuario(usuario);
        return "redirect:/";
    }
}
