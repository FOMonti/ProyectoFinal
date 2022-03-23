package com.ep_movil.controladores;

import com.ep_movil.entidades.*;
import com.ep_movil.servicios.*;
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
import java.util.*;

@Controller
@RequestMapping({"/carrito"})
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private IItemCarritoService itemCarritoService;

    @Autowired
    private IVentaRealizadaService iVentaRealizadaService;

    @Autowired
    private IProductoService productoService;

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
        return "usuarioo/carrito";
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
        carrito.setPrecio(carrito.getPrecio() - (itemCarrito.getProducto().getPrecio() * itemCarrito.getCantidad()));
        carritoService.guardarCarrito(carrito);
        itemCarritoService.eliminarCliente(itemCarrito);
        redirect.addFlashAttribute("itemCarritoEliminado", "Producto quitado del carrito eliminado!");
        return "redirect:/carrito/productosCarrito";
    }

    @GetMapping("/comprar")
    public String comprar(RedirectAttributes redirect, Principal principal) {
        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (!ou.isPresent()) {
            return "redirect:/";
        }
        Usuario usuario = ou.get();
        Carrito carrito = usuario.getHistorialCarrito();
        VentaRealizada ventaRealizada = new VentaRealizada(usuario, new Date(), carrito, carrito.getPrecio());
        iVentaRealizadaService.guardarVentaRealizada(ventaRealizada);
        usuario.setHistorialCarrito(new Carrito(usuario));
        usuarioService.guardarUsuario(usuario);
        List<ItemCarrito> items = itemCarritoService.listarItemCarrito(carrito);
        for (ItemCarrito ic : items) {
            Producto producto = ic.getProducto();
            producto.setStock(producto.getStock() - ic.getCantidad());
            productoService.guardarProducto(producto);
        }
        redirect.addFlashAttribute("compraExitosa", "Te fuiste robado perro");
        return "redirect:/carrito/productosCarrito";
    }
}

