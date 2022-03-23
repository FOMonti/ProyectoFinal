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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping({"/tienda"})
@SessionAttributes("itemCarrito")
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
        model.addAttribute("ruta", "/tienda/productos");
        return "tienda";
    }

    @GetMapping("/tienda2")
    private String tienda2(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionSinOrden(params, model, 6);
        ItemCarrito itemCarrito = new ItemCarrito();
        model.addAttribute("itemCarrito", itemCarrito);
        model.addAttribute("ruta", "/tienda/tienda2");
        return "tienda2";
    }

    @GetMapping("/OxNA")
    public String ordenarxNombreZ_A(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXNombreDESC(params, model, 6);
        model.addAttribute("ruta", "/tienda/OxNA");
        return "tienda2";
    }

    @GetMapping("/OxND")
    public String ordenarxNombreA_Z(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXNombreASC(params, model, 6);
        model.addAttribute("ruta", "/tienda/OxND");
        return "tienda2";
    }

    @GetMapping("/Ox-P")
    public String ordenarxMenorPrecio(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXPrecioASC(params, model, 6);
        model.addAttribute("ruta", "/tienda/Ox-P");
        return "tienda2";
    }

    @GetMapping("/Ox+P")
    public String ordenarxMayorPrecio(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXPrecioDESC(params, model, 6);
        model.addAttribute("ruta", "/tienda/Ox+P");
        return "tienda2";
    }

    @GetMapping("/modificar/{id}")
    public String modificarProducto(@PathVariable("id") Integer id, Producto producto, Model model) {
        producto = productoService.buscarPorId(id); //para que aparezcan los datos cargados en el editar, hay que guardar el metodo en una variable
        model.addAttribute("producto", producto);
        return "admin/productoForm";
    }

    @GetMapping("/eliminar")
    public String eliminarProducto(Producto producto, RedirectAttributes redirect) {
        productoService.eliminarProducto(producto);
        redirect.addFlashAttribute("productoEliminado", "Producto eliminado!");
        return "redirect:/tienda/tienda2";
    }

    @GetMapping("/detalle/{id}")
    public String detalleProducto(@PathVariable("id") Integer id, Producto producto, Model model,
                                  RedirectAttributes redirect) {
        producto = productoService.buscarPorId(id);

        model.addAttribute("titulo", "Detalle del producto: " + producto.getNombre());
        model.addAttribute("producto", producto);
        return "admin/detalleProducto";
    }

    @PostMapping("/agregarACarrito")
    private String agregarACarrito(@RequestParam(name = "cantidad") Integer cantidad,
                                   @RequestParam(name = "idProducto") Integer id,
                                   Principal principal) {
        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        Producto producto = productoService.buscarPorId(id);
        if (ou.isPresent()) {
            Usuario usuario = ou.get();
            Carrito carrito = usuario.getHistorialCarrito();
            List<ItemCarrito> items = itemCarritoService.listarItemCarrito(carrito);
            for (ItemCarrito ic : items) {
                if (ic.getProducto().equals(producto)) {
                    Integer cant = ic.getCantidad() + cantidad;
                    if (cant > producto.getStock()) {
                        //Agregar alerta, no hay stock
                        return "redirect:/tienda/tienda2";
                    } else {
                        ic.setCantidad(cant);
                        itemCarritoService.guardarItemCarrito(ic);
                        carrito.setPrecio(carrito.getPrecio() + (producto.getPrecio() * cantidad));
                        carritoService.guardarCarrito(carrito);
                        return "redirect:/tienda/tienda2";
                    }
                }
            }
            //arreglar
            if(cantidad > producto.getStock()){
                cantidad = producto.getStock();
            }
            ItemCarrito itemCarrito = new ItemCarrito(cantidad, producto, carrito);
            itemCarritoService.guardarItemCarrito(itemCarrito);
            carrito.setPrecio(carrito.getPrecio() + (producto.getPrecio() * cantidad));
            carritoService.guardarCarrito(carrito);
        }
        return "redirect:/tienda/tienda2";
    }

}
