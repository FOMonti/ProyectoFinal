package com.ep_movil.controladores;

import com.ep_movil.entidades.Producto;
import com.ep_movil.servicios.IProductoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String guardarProductos(/*@RequestParam(name = "file", required = false) MultipartFile portada,*/
            Model model, Producto producto) { //RedirectAttributes redirect / Model model
        productoService.guardarProducto(producto);
        model.addAttribute("mensaje", "producto guardado con exito");
        return "/admin/productoForm";
    }


//    @GetMapping("/modificar{idProducto}")
//    public String modificarProducto(Producto producto){
//        productoService.encontrarProducto(producto);
//        return "redirect:/";
}
