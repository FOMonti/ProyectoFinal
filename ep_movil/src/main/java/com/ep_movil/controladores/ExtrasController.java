
package com.ep_movil.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExtrasController {
    
    @GetMapping("/about")
    public String sobreNosotros(Model model) {
        return "acerca-de";
    }
}
