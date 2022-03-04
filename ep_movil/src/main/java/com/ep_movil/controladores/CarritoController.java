package com.ep_movil.controladores;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.Map;

@Controller
@RequestMapping({"/carrito"})
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;


    @GetMapping("/productosCarrito")
    public String toCarrito(@RequestParam Map<String, Object> params, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("user");
        Carrito carrito = usuario.getHistorialCarrito();
        model.addAttribute("titulo", "Carrito");
        model.addAttribute("carrito", carrito);
        model.addAttribute("usuario", usuario);
        return "/user/carrito2";
    }

    @GetMapping("/productosGuardados")
    public String toCarritoo(Model model, HttpSession session) {
/*  Si el usuario no esta logeado...?
 if (usuario.equals(null)) {
     return "redirect:/";
 }
*/
        Usuario usuario = (Usuario) session.getAttribute("user");
        model.addAttribute("usuario", usuario);
        model.addAttribute("carrito", usuario.getHistorialCarrito());
        model.addAttribute("titulo", "Mostrando Carrito");
        model.addAttribute("usuario", usuario);
        return "/user/carrito";
    }
}

