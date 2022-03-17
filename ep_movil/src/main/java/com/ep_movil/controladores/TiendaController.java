package com.ep_movil.controladores;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.entidades.ItemCarrito;
import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.security.service.UsuarioService;
import com.ep_movil.servicios.ICarritoService;
import com.ep_movil.servicios.IProductoService;
import com.ep_movil.servicios.ItemCarritoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping({"/tienda"})
public class TiendaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private ItemCarritoServiceImpl itemCarritoService;

    @Autowired
    private ICarritoService carritoService;

    @GetMapping("/productos")
    public String dashboard(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionSinOrden(params, model, 10);
        return "tienda";
    }

    @GetMapping("/tienda2")
    private String tienda2(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionSinOrden(params, model, 6);
        return "tienda2";
    }

    @GetMapping("/agregarACarrito/{id}")
    private String agregarACarrito(@PathVariable("id") Integer id, HttpSession session) {
        Producto producto = productoService.buscarPorId(id);
        //Long id1 = (Long) session.getAttribute("idusuario");
        Long num = Long.valueOf(2);
        Optional<Usuario> optionalUsuariosuario = usuarioService.getUsuarioById(num);
        if (optionalUsuariosuario.isPresent()) {
            Usuario usuario = optionalUsuariosuario.get();
            Carrito carrito = usuario.getHistorialCarrito();
            ItemCarrito itemCarrito = new ItemCarrito(producto, 1, carrito);
            itemCarritoService.guardarItemCarrito(itemCarrito);
            List<ItemCarrito> items = carrito.getItems();
            items.add(itemCarrito);
            carrito.setItems(items);
            carritoService.guardarCarrito(carrito);
        }
        return "redirect:/tienda/productos";
    }

    @GetMapping("/OxNA")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxNombreZ_A(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXNombreDESC(params, model, 6);
        return "tienda2";
    }

    @GetMapping("/OxND")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxNombreA_Z(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXNombreASC(params, model, 6);
        return "tienda2";
    }

    @GetMapping("/Ox-P")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxMenorPrecio(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXPrecioASC(params, model, 6);
        return "tienda2";
    }

    @GetMapping("/Ox+P")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxMayorPrecio(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXPrecioDESC(params, model, 6);
        return "tienda2";
    }

}
