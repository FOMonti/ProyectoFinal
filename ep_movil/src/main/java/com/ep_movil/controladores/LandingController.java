package com.ep_movil.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", ""})
public class LandingController {

    @GetMapping("/")
    public String alalanding(Model model) {
        return "index";
    }
}
