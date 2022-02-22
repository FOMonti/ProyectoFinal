package com.ep_movil.controladores;

import com.ep_movil.entidades.Producto;
import com.ep_movil.servicios.IProductoService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "redirect:/tienda";
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
    public String modificarProducto(@PathVariable("id") Integer id, Producto producto, Model model) {
        producto = productoService.buscarPorId(id); //para que aparezcan los datos cargados en el editar, hay que guardar el metodo en una variable
        model.addAttribute("producto", producto);
        return "admin/productoForm";
    }

    @GetMapping("/eliminar")
    public String eliminarProducto(Producto producto, RedirectAttributes redirect) {
        productoService.eliminarProducto(producto);
        redirect.addFlashAttribute("productoEliminado", "Producto eliminado!");
        return "redirect:/tienda";
    }

}
