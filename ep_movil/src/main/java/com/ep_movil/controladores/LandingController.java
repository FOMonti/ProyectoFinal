package com.ep_movil.controladores;

import com.ep_movil.entidades.Producto;
import com.ep_movil.servicios.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", ""})
public class LandingController {
    
    @Autowired
    private IProductoService productoService;
    
    @GetMapping("/")
    public String alalanding(Model model) {
        model.addAttribute("titulo", "Bienvendio EP-MOVIL");
        return "index";
    }
    
     @GetMapping("/tienda")
    public String inicio(Model model, Producto producto) {

        List<Producto> listaProductos = productoService.listarProductos();
        
        model.addAttribute("listaProductos", listaProductos);
        
        return "tienda";
    }
}
