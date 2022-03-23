package com.ep_movil.controladores;

import com.ep_movil.entidades.Carrito;

import com.ep_movil.entidades.Comentario;
import com.ep_movil.entidades.Rol;
import com.ep_movil.enums.RolNombre;
import com.ep_movil.security.service.UsuarioService;
import com.ep_movil.servicios.IComentarioService;
import com.ep_movil.servicios.RolService;

import javax.validation.Valid;

import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.IProductoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@SessionAttributes("comentarios")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolService rolService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/repara")
    public String repara(Model model) {
        return "repara";
    }

    @Autowired
    private IComentarioService comentarioService;

    @GetMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario-form";
    }

    @PostMapping("/save") // en este método guardamos usuarios con el rol de User
    public String saveUser(@Valid Usuario usuario, Errors error, String username, String password,
                           RedirectAttributes redirect, @RequestParam(name = "file", required = false) MultipartFile imagen) throws AddressException, MessagingException {

        if (error.hasErrors()) {
            return "usuario-form";
        }

        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));

        Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
//        Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();//con este metodo mas el add admin, creo un admin
        Set<Rol> roles = new HashSet<Rol>();

        roles.add(rolUser);
//        roles.add(rolAdmin); //complemento para crear admin

        usuario.setRoles(roles);

        Carrito historialCarrito = new Carrito(usuario);

        usuario.setHistorialCarrito(historialCarrito);

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/images");/*ruta relativa*/
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytes = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytes);
                usuario.setImagen(imagen.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            usuario.setImagen("unknown.jpg");
        }

        usuarioService.guardarUsuario(usuario);

        Properties props = System.getProperties();
        Session session = Session.getDefaultInstance(props);
        MimeMessage email = new MimeMessage(session);

        try {
            email.setRecipients(Message.RecipientType.TO, usuario.getEmail());
        } catch (MessagingException ex) {
            java.util.logging.Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        email.setSubject("El equipo de EP-Movil le da la bienvenida!!");
        try {
            email.setText("Hola " + usuario.getNombre() + "! \nEP-Movil te da la bienvenida!! "
                    + "\n A partir de ahora podrás disfrutar de la totalidad de nuestra tienda on-line, mantenerte"
                    + " actualizado def nuestros productos y acceder a los beneficios que tenemos para vos!!"
                    + "\nNo olvides seguirnos en nuestras redes!! \nGracias por elegirnos!! \nTe saluda, el equipo de EP-Movil");
        } catch (MessagingException ex) {
            java.util.logging.Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mailSender.send(email);

        redirect.addFlashAttribute("usuarioRegistrado", "Se ha registrado satisfactoriamente. Inicie sesión");

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String toLogin(Model model) {
        return "usuario-login";
    }

//    @PostMapping("/signin")
//    public String acceder(Usuario usuario, HttpSession session, RedirectAttributes redirect) {
//
//        Optional<Usuario> user = usuarioService.findByUsername(usuario.getUsername());
//
//        if (!user.isPresent()) {
//            redirect.addFlashAttribute("accederFallido", "Credenciales erróneas. Revise el nombre de usuario y/o contraseña ingresados.");
//            logger.info("AVISO: Se intentó ingresar con un usuario que no se encuentra registrado.");
//            return "redirect:/login";
//        }
//
//        session.setAttribute("idusuario", user.get().getId());
//        log.info("usuario que hizo login: " + usuario.getUsername());
//        redirect.addFlashAttribute("usuario", usuario);
//
//        return "redirect:/";
//    }

    @GetMapping("/carrito")
    public String toCarrito(Model model, Usuario usuario) {
        /*  Si el usuario no esta logeado...?
 if (usuario.equals(null)) {
     return "redirect:/";
 }
         */
        model.addAttribute("list", productoService.listarProductos());
        model.addAttribute("titulo", "Carrito");
        model.addAttribute("usuario", usuario);
        return "/user/carrito";
    }

    @GetMapping("/comentarios")
    public String verComentarios(Model model, Principal principal) {
        //redirectAttributes.getAttribute()
        Optional<Usuario> ou = usuarioService.findByUsername(principal.getName());
        if (!ou.isPresent()) {
            return "redirect:/";
        }
        Usuario usuario = ou.get();
        List<Comentario> comentarios = comentarioService.listarComentariosUsuario(usuario);
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("usuario", usuario);
        return "usuarioo/perfil";
    }

}
