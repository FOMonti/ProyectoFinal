package com.ep_movil.controladores;

import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/", ""})
public class LandingController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/")
    public String alalanding(Model model, HttpSession session) {
        model.addAttribute("titulo", "Bienvendio EP-MOVIL");
        Long id = (Long) session.getAttribute("id");
        Usuario usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario);
        return "index";
    }
}
