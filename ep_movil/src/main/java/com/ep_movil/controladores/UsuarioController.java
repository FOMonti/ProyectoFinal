package com.ep_movil.controladores;

import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.IProductoService;
import com.ep_movil.servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/usuario"})
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private IProductoService productoService;

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
        usuarioService.guardarUsuario(usuario);
        redirect.addAttribute("usuario", usuario);
        return "redirect:/";
    }

    @GetMapping("/cuenta")
    public String cuenta(Model model) {
        return "usuario-perfil";
    }

    @GetMapping("/carrito")
    public String toCarrito(Model model, Usuario usuario) {
/*  Si el usuario no esta logeado...?
 if (usuario.equals(null)) {
     return "redirect:/";
 }
*/
        model.addAttribute("list", productoService.listarProductos());
        model.addAttribute("titulo", "Carrito");
        model.addAttribute("usuario", usuario);
        return "/user/carrito";
    }
//    @GetMapping("/carrito")
//    public String toCarrito(Model model, Usuario usuario) {
///*  Si el usuario no esta logeado...?
// if (usuario.equals(null)) {
//     return "redirect:/";
// }
//*/

//        model.addAttribute("carrito", productoService.findByUsuarioId(usuario.id));
//        model.addAttribute("titulo", "Mostrando Carrito");
//        model.addAttribute("usuario", usuario);
//        return "/user/carrito";
//    }
}
