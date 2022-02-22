//package com.ep_movil.controladores;
//
//import com.ep_movil.entidades.Rol;
//import com.ep_movil.entidades.Usuario;
//import com.ep_movil.enums.Roles;
//import com.ep_movil.servicios.RolServiceImpl;
//import com.ep_movil.servicios.UsuarioServiceImpl;
//import java.util.HashSet;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping({"/usuario"})
//public class UsuarioController {
//
//    @Autowired
//    private UsuarioServiceImpl usuarioService;
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    
//    @Autowired
//    private RolServiceImpl rolService;
//
//    @GetMapping("/registrar")
//    public String toCreate(Model model) {
//        model.addAttribute("titulo", "Crear Usuario");
//        model.addAttribute("usuario", new Usuario());
//        return "usuario-form";
//    }
//
//    @GetMapping("/logear")
//    public String toLog(Model model) {
//        model.addAttribute("titulo", "Logeando");
//        model.addAttribute("usuario", new Usuario());
//        return "usuario-login";
//    }
//
//    @PostMapping("/login")
//    public String toLogin(Model model) {
//        model.addAttribute("titulo", "Crear Usuario");
//        model.addAttribute("usuario", new Usuario());
//        return "redirect:/usuario-login";
//    }
//
//    @PostMapping("/save") // en este método guardamos usuarios con el rol de User
//    public String saveUser(String username, String password, RedirectAttributes redirect) {
//
//        Usuario usuario = new Usuario();
//        usuario.setUsername(username);
//        usuario.setPassword(passwordEncoder.encode(password));
//
//        Rol rolUser = rolService.encontrarRol(Roles.ROLE_USER).get();
//        Rol rolAdmin = rolService.encontrarRol(Roles.ROLE_ADMIN).get();//con este metodo mas el add admin, creo un admin
//
//        Set<Rol> roles = new HashSet<Rol>();
//
//        roles.add(rolUser);
//        roles.add(rolAdmin); //complemento para crear admin
//
//        usuario.setRoles(roles);
//
//        usuarioService.guardarUsuario(usuario);
//
//        redirect.addFlashAttribute("usuarioRegistrado", "Se ha registrado satisfactoriamente. Inicie sesión");
//
//        return "redirect:/login";
//    }
//}
