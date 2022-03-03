package com.ep_movil.controladores;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.ICarritoService;
import com.ep_movil.servicios.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping({"/carrito"})
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;


    @GetMapping("/productosCarrito")
    public String toCarrito(@RequestParam Map<String, Object> params, Model model, Usuario usuario) {
        Carrito carrito = carritoService.findByUsuarioId(usuario.getId());
        model.addAttribute("titulo", "Carrito");
        model.addAttribute("carrito", carrito);
        model.addAttribute("usuario", usuario);
        return "/user/carrito2";
    }

    @GetMapping("/productosGuardados")
    public String toCarritoo(Model model, Usuario usuario) {
/*  Si el usuario no esta logeado...?
 if (usuario.equals(null)) {
     return "redirect:/";
 }
*/

        model.addAttribute("carrito", carritoService.findByUsuarioId(usuario.getId()));
        model.addAttribute("titulo", "Mostrando Carrito");
        model.addAttribute("usuario", usuario);
        return "/user/carrito";
    }
}
