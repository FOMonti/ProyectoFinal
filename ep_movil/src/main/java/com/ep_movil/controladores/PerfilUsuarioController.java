package com.ep_movil.controladores;

import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.UsuarioServiceImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PerfilUsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/perfil")
    public String toPerfil(Model model, Principal principal) {
        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (!ou.isPresent()) return "redirect:/";
        Usuario usuario = ou.get();
        model.addAttribute("usuario", usuario);
        return "usuarioo/perfil";
    }

    @GetMapping("/modificar")
    public String modificarImagen(@RequestParam(name = "file", required = false) MultipartFile imagen,
            @Valid Usuario perfil, Errors error, @PathVariable("id") Integer id, RedirectAttributes redirect, 
            Principal principal) { 

        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (!ou.isPresent()){
            return "redirect:/";
        }
        
        perfil = ou.get();
        
        if (error.hasErrors()) {
            return "usuario-form";
        }

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/images");/*ruta relativa*/
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytes = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytes);
                perfil.setImagen(imagen.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            perfil.setImagen("unknown.jpg");
        }
        
        return "perfil";
    }
    
    
}
