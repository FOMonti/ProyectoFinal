package com.ep_movil.controladores;

import com.ep_movil.entidades.Publicidad;
import com.ep_movil.servicios.PublicidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping({"/publicidad"})
public class PublicidadController {

    @Autowired
    private PublicidadService publicidadService;


    @GetMapping("/publicidadForum")
    public String publicidadForum(Model model) {
        model.addAttribute("publicidad", new Publicidad());
        return "publicidad";
    }

    @PostMapping("/save")
    public String cargarPublicidad(Publicidad publicidad, RedirectAttributes redirect,
                                   @RequestParam(name = "file", required = false)
                                           MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/images");/*ruta relativa*/
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytes = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytes);
                publicidad.setImagen(imagen.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            publicidad.setImagen("PorductoSinIMG.jpg");
        }
        publicidadService.guardarCarrito(publicidad);
        return "redirect:/";
    }
}
