package com.ep_movil.controladores;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.entidades.ItemCarrito;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.ICarritoService;
import com.ep_movil.servicios.IItemCarritoService;
import com.ep_movil.servicios.UsuarioServiceImpl;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping({"/carrito"})
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private IItemCarritoService itemCarritoService;

    @GetMapping("/productosCarrito")
    public String toCarrito(@RequestParam Map<String, Object> params, Model model,
                            Principal principal) {
        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (!ou.isPresent()) return "redirect:/";
        Usuario usuario = ou.get();
        Carrito carrito = usuario.getHistorialCarrito();
        model.addAttribute("titulo", "Carrito");
        model.addAttribute("carrito", carrito);
        model.addAttribute("items", itemCarritoService.listarItemCarrito(carrito));
        model.addAttribute("usuario", usuario);
        return "usuarioo/carrito2";
    }

    @GetMapping("/quitarItem/{id}")
    public String quitarDelCarrito(@RequestParam Map<String, Object> params,
                                   RedirectAttributes redirect,
                                   Principal principal, @PathVariable("id") Integer id) {
        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (!ou.isPresent()) {
            return "redirect:/";
        }
        Usuario usuario = ou.get();
        Carrito carrito = usuario.getHistorialCarrito();
        ItemCarrito itemCarrito = itemCarritoService.getById(id);
        itemCarritoService.eliminarCliente(itemCarrito);
        redirect.addFlashAttribute("itemCarritoEliminado", "Producto quitado del carrito eliminado!");
        return "redirect:/carrito/productosCarrito";
    }


}

