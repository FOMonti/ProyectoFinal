package com.ep_movil.controladores;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.entidades.ItemCarrito;
import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.*;
import com.ep_movil.entidades.Rol;
import com.ep_movil.enums.RolNombre;
import com.ep_movil.security.service.UsuarioService;
import com.ep_movil.servicios.RolService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ep_movil.servicios.IProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping({"/usuario"})
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolService rolService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private CarritoServiceImpl carritoService;

    @Autowired
    private IItemCarritoService iItemCarritoService;

    @GetMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario-form";
    }

    @PostMapping("/save") // en este método guardamos usuarios con el rol de User
    public String saveUser(Usuario usuario, @Valid String username, Errors usernameError,
                           @Valid String password, Errors passwordError, RedirectAttributes redirect) {

        if (usernameError.hasErrors() || passwordError.hasErrors()) {
            return "usuario-form";
        }

        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));

        Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
        Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();//con este metodo mas el add admin, creo un admin

        Set<Rol> roles = new HashSet<Rol>();

        roles.add(rolUser);
        roles.add(rolAdmin); //complemento para crear admin

        usuario.setRoles(roles);

        usuarioService.guardarUsuario(usuario);

        redirect.addFlashAttribute("usuarioRegistrado", "Se ha registrado satisfactoriamente. Inicie sesión");
//        redirect.addFlashAttribute("usuario", usuario);

        return "redirect:/usuario/login";
    }

    @GetMapping("/login")
    public String toLogin(Model model) {
        model.addAttribute("titulo", "Inicio de sesión");
        model.addAttribute("usuario", new Usuario());
        return "usuario-login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session, @AuthenticationPrincipal User user1) {
        log.info("Accesos : {}", usuario);
        log.info("usuario que hizo el loggin " + user1);

        Optional<Usuario> user = usuarioService.findByUsername(usuario.getUsername());
        //logger.info("Usuario de db: {}", user.get());
        if (user.isPresent()) {
            session.setAttribute("id", user.get().getId());
            //session.setAttribute("user", user);
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

//    @GetMapping("/agregarCarrito")
//    public String agregarProducto(@PathVariable("idProducto") Integer idProducto, RedirectAttributes redirect, HttpSession session) {
//        Producto producto = productoService.buscarPorId(idProducto);
//        Usuario usuario = (Usuario) session.getAttribute("user");
//        ItemCarrito ic = new ItemCarrito(producto, 1);
//        Carrito carrito = usuario.getHistorialCarrito();
//        carrito.getItems().add(ic);
//        usuario.setHistorialCarrito(carrito);
//        usuarioService.guardarUsuario(usuario);
//        redirect.addFlashAttribute("productoEliminado", "Producto eliminado!");
//        redirect.addAttribute("usuario", usuario);
//        return "redirect:/tienda/productos";
//    }

}
