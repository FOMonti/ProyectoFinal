package com.ep_movil.controladores;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.entidades.ItemCarrito;
import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping({"/usuario"})
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private CarritoServiceImpl carritoService;

    @Autowired
    private IItemCarritoService iItemCarritoService;

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
            Model model, Usuario usuario, RedirectAttributes redirect) { //RedirectAttributes redirect / Model model
        usuario.setHistorialCarrito(new Carrito(usuario));
        usuarioService.guardarUsuario(usuario);
        //model.addAttribute("usuario", usuario);
        redirect.addFlashAttribute("usuario", usuario);
        return "redirect:/tienda/productos";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        logger.info("Accesos : {}", usuario);
        Optional<Usuario> user = usuarioService.findByUsername(usuario.getUsername());
        //logger.info("Usuario de db: {}", user.get());
        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
        } else {
            logger.info("Usuario no existe");
        }
        return "redirect:/";
    }

    @GetMapping("/carrito")
    public String toCarrito(Model model, Usuario usuario) {
/*  Si el usuario no esta logeado...?
 if (usuario.equals(null)) {
     return "redirect:/";s
 }
*/
        model.addAttribute("list", productoService.listarProductos());
        model.addAttribute("titulo", "Carrito");
        model.addAttribute("usuario", usuario);
        return "/user/carrito";
    }

    @GetMapping("/agregarCarrito")
    public String agregarProducto(@PathVariable("idProducto") Integer idProducto,
                                  @PathVariable("idProducto") Integer idU, RedirectAttributes redirect,
                                  Usuario usuario) {
        Producto producto = productoService.buscarPorId(idProducto);
        //Usuario usuario = usuarioService.
        ItemCarrito ic = new ItemCarrito(producto, 1);
        Carrito carrito = carritoService.findByUsuarioId(usuario.getId());
        carrito.getItems().add(ic);
        usuario.setHistorialCarrito(carrito);
        usuarioService.guardarUsuario(usuario);
        redirect.addFlashAttribute("productoEliminado", "Producto eliminado!");
        redirect.addAttribute("usuario", usuario);
        return "redirect:/tienda/productos";
    }
}
