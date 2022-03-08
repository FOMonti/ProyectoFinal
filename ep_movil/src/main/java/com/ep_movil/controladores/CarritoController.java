
package com.ep_movil.controladores;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.servicios.CarritoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class CarritoController {
    
    @Autowired
    private CarritoServiceImpl carritoService;
    
    @GetMapping("/carrito")
    public String carrito(Model model){
        model.addAttribute("carrito", new Carrito());
        return "carrito-usuario";
    }
    
    
}

