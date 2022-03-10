package com.ep_movil.controladores;

import com.ep_movil.entidades.Producto;
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
    public IProductoService productoService;

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
        }

        productoService.guardarProducto(producto);
        redirect.addFlashAttribute("productoGuardado", "Producto guardado con éxito!");
//        redirect.addFlashAttribute("productoModificado", "Producto modificado con éxito!");
        return "redirect:/tienda/productos";
    }

    @GetMapping("/detalle/{id}")
    public String detalleProducto(@PathVariable("id") Integer id, Producto producto, Model model,
            RedirectAttributes redirect) {

        producto = productoService.buscarPorId(id);

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
    public String ordenarxNombreAsc(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10,Sort.by(Sort.Direction.ASC, "nombre"));

        Page<Producto> pageProducto = productoService.getAll(pageRequest);

        int totalPage = pageProducto.getTotalPages();

        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("list", pageProducto.getContent()); //lista de productos a mostrar
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return "tienda";
    }
    @GetMapping("/OxND")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxNombreDesc(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10,Sort.by(Sort.Direction.DESC, "nombre"));

        Page<Producto> pageProducto = productoService.getAll(pageRequest);

        int totalPage = pageProducto.getTotalPages();

        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("list", pageProducto.getContent()); //lista de productos a mostrar
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return "tienda";
    }
    
    @GetMapping("/Ox-P")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxMenorPrecio(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10,Sort.by(Sort.Direction.DESC, "precio"));

        Page<Producto> pageProducto = productoService.getAll(pageRequest);

        int totalPage = pageProducto.getTotalPages();

        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("list", pageProducto.getContent()); //lista de productos a mostrar
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return "tienda";
    }
    
    @GetMapping("/Ox+P")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxMayorPrecio(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10,Sort.by(Sort.Direction.ASC, "precio"));

        Page<Producto> pageProducto = productoService.getAll(pageRequest);

        int totalPage = pageProducto.getTotalPages();

        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("listaProductos", pageProducto.getContent()); //lista de productos a mostrar
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return "tienda";
    }
}
