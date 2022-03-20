package com.ep_movil.controladores;

import com.ep_movil.entidades.Comentario;
import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.security.service.UsuarioService;
import com.ep_movil.servicios.ComentarioServiceImpl;
import com.ep_movil.servicios.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping({"/comentario"})
@SessionAttributes("comentario")
public class ComentarioController {

    @Autowired
    private ComentarioServiceImpl comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/cargar/{id}")
    public String cargarComentario(@PathVariable Integer id, Model model) {

        Producto producto = productoService.buscarPorId(id);
        Comentario comentario = new Comentario();

        comentario.setProducto(producto);

        model.addAttribute("comentario", comentario);
        model.addAttribute("producto", producto);

        return "comentario/form";
    }

    @PostMapping("/save-comentario")
    private String guardarComentario(Comentario comentario, Principal principal,
                                     RedirectAttributes redirect) {

        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (ou.isPresent()) {
            comentario.setUsuario(ou.get());
            comentario.setFechaDelComentario(new Date());
            comentarioService.guardarComentario(comentario);
        }
        redirect.addFlashAttribute("comentarioGuardado", "Comentario guardado");

        return "redirect:/admin/detalle/" + comentario.getProducto().getId();
    }
}
