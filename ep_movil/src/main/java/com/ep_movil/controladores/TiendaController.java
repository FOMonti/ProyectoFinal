package com.ep_movil.controladores;

import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.servicios.IProductoService;
import com.ep_movil.servicios.IUsuarioService;
import com.ep_movil.servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping({"/tienda"})
public class TiendaController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;


    @GetMapping("/tienda2")
    private String findAll(@RequestParam Map<String, Object> params, Model model, Usuario usuario) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        PageRequest pageRequest = PageRequest.of(page, 5);//size : Cantidad de elementos por pagina

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
        model.addAttribute("usuario", usuario);
        return "tienda2";
    }

    @GetMapping("/productos")
    public String inicio(Model model, Producto producto, Usuario usuario, RedirectAttributes redirect, HttpSession session) {

        List<Producto> listaProductos = productoService.listarProductos();
        Integer id = (Integer) session.getAttribute("idusuario");
        model.addAttribute("listaProductos", listaProductos);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuario", usuarioService.findById(id));
        return "tienda";
    }

}
