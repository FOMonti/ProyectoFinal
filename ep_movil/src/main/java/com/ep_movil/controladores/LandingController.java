package com.ep_movil.controladores;

import com.ep_movil.entidades.Publicidad;
import com.ep_movil.servicios.PublicidadService;
import com.ep_movil.servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/", ""})
public class LandingController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private PublicidadService publicidadService;

    @GetMapping("/")
    public String alalanding(Model model) {
        List<Publicidad> publicidades = publicidadService.listarPublicidades();
        model.addAttribute("publicidad", publicidades.get(0));
        model.addAttribute("publicidad2", publicidades.get(1));
        //model.addAttribute("publicidad1", publicidades.get(2));
        return "index";
    }


}
