package com.ep_movil.controladores;

import com.ep_movil.entidades.Producto;
import com.ep_movil.servicios.IProductoService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> 9b71c700f068aecb9467764e1ccf8c6a087618d0
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
            RedirectAttributes redirect, @Valid Producto producto, Errors error) { //RedirectAttributes redirect / Model model

        if (error.hasErrors()) {
            return "admin/productoForm";
        }

        if (imagen.isEmpty()) {
            String ruta = "C:\\ProyectoFinal";
            String nombreUnico = UUID.randomUUID() + " " + imagen.getOriginalFilename();
            try {
                byte[] bytes = imagen.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico);
                Files.write(rutaAbsoluta, bytes);
                producto.setImagen(nombreUnico);

                productoService.guardarProducto(producto);
                redirect.addAttribute("productoGuardado", "Producto guardado con exito!");
                redirect.addFlashAttribute("producto", imagen);

            } catch (Exception e) {
                e.getCause().getMessage();
            }
        }
        return "/admin/productoForm";
    }

<<<<<<< HEAD
    @GetMapping("/modificar{idProducto}")
    public String modificarProducto(Producto producto, Model model) {
        productoService.encontrarProducto(producto);
        model.addAttribute("producto", producto);
        return "redirect:/";
    }

    @GetMapping("/eliminar")
    public String eliminarProducto(Producto producto) {
        productoService.eliminarProducto(producto);
        return "redirect:/tienda";
    }
=======
>>>>>>> 9b71c700f068aecb9467764e1ccf8c6a087618d0
}
