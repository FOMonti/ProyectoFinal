package com.ep_movil.controladores;

import com.ep_movil.dao.IComentarioDao;
import com.ep_movil.entidades.Comentario;
import com.ep_movil.entidades.Producto;
import com.ep_movil.servicios.IComentarioService;
import com.ep_movil.servicios.IProductoService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IComentarioService comentarioService;

    @GetMapping("/productos-form")
    public String productoForm(Model model) {
        model.addAttribute("producto", new Producto());
        return "admin/productoForm";
    }

    @PostMapping("/guardar")
    public String guardarProductos(@RequestParam(name = "file", required = false) MultipartFile imagen,
            @Valid Producto producto, Errors error, RedirectAttributes redirect) { //RedirectAttributes redirect / Model model

        if (error.hasErrors()) {
            return "admin/productoForm";
        }

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/images");/*ruta relativa*/
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytes = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytes);
                producto.setImagen(imagen.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            producto.setImagen("ProductoSinIMG.jpg");
        }

        productoService.guardarProducto(producto);
        redirect.addFlashAttribute("productoGuardado", "Producto guardado con éxito!");
//        redirect.addFlashAttribute("productoModificado", "Producto modificado con éxito!");
        return "redirect:/tienda/productos";
    }

    @GetMapping("/detalle/{id}")
    public String detalleProducto(@PathVariable("id") Integer id, Model model,
            RedirectAttributes redirect) {

        Producto producto = productoService.buscarPorId(id);
        List<Comentario> comentarios = comentarioService.listarComentarios(producto);

        model.addAttribute("comentarios", comentarios);
        model.addAttribute("titulo", "Detalle del producto: " + producto.getNombre());
        model.addAttribute("producto", producto);
        return "admin/detalleProducto";
    }

    @GetMapping("/modificar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String modificarProducto(@PathVariable("id") Integer id, Producto producto, Model model) {
        producto = productoService.buscarPorId(id); //para que aparezcan los datos cargados en el editar, hay que guardar el metodo en una variable
        model.addAttribute("producto", producto);
        return "admin/productoForm";
    }

    @GetMapping("/eliminar")
    public String eliminarProducto(Producto producto, RedirectAttributes redirect) {
        productoService.eliminarProducto(producto);
        redirect.addFlashAttribute("productoEliminado", "Producto eliminado!");
        return "redirect:/tienda/productos";
    }

    @GetMapping("/OxNA")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxNombreZ_A(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXNombreDESC(params, model, 10);
        model.addAttribute("ruta", "/admin/OxNA");
        return "tienda";
    }

    @GetMapping("/OxND")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxNombreA_Z(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXNombreASC(params, model, 10);
        model.addAttribute("ruta", "/admin/OxND");
        return "tienda";
    }

    @GetMapping("/Ox-P")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxMenorPrecio(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXPrecioASC(params, model, 10);
        model.addAttribute("ruta", "/admin/Ox-P");
        return "tienda";
    }

    @GetMapping("/Ox+P")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxMayorPrecio(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXPrecioDESC(params, model, 10);
        model.addAttribute("ruta", "/admin/Ox+P");
        return "tienda";
    }

    @GetMapping("/filtrar")
    public String productosBarraBusqueda(@RequestParam Map<String, Object> params, Model model, @RequestParam(name = "filtro") String query) {
        model = productoService.paginacionFiltrada(params, model, 5, query);
        
        model.addAttribute("ruta", "/admin/filtrar");

        return "tienda";
    }
}
