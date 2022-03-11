package com.ep_movil.controladores;

import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;
import com.ep_movil.security.service.UsuarioService;
import com.ep_movil.servicios.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping({"/tienda"})
public class TiendaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IProductoService productoService;


    @GetMapping("/tienda2")
    private String findAll(@RequestParam Map<String, Object> params, Model model, Usuario usuario, Integer valor) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        PageRequest pageRequest = null;
        if (valor == null) {
            pageRequest = PageRequest.of(page, 10);
        } else {
            switch (valor) {
                case 1:
                    pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "nombre"));
                    break;
                case 2:
                    pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "nombre"));//size : Cantidad de elementos por pagina
                    break;
                case 3:

                    pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "precio"));//size : Cantidad de elementos por pagina
                    break;
                case 4:
                    pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "precio"));//size : Cantidad de elementos por pagina
                    break;
                default:
            }
        }
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
    private String inicio(@RequestParam Map<String, Object> params, Model model, Usuario usuario, @RequestParam(name = "value") Integer valor) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        PageRequest pageRequest = pageRequest = PageRequest.of(page, 10);
        if (valor == null) {
            pageRequest = PageRequest.of(page, 10);
        } else {
            switch (valor) {
                case 1:
                    pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "nombre"));
                    break;
                case 2:
                    pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "nombre"));//size : Cantidad de elementos por pagina
                    break;
                case 3:

                    pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "precio"));//size : Cantidad de elementos por pagina
                    break;
                case 4:
                    pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "precio"));//size : Cantidad de elementos por pagina
                    break;
                default:
            }
        }
        Page<Producto> pageProducto = productoService.getAll(pageRequest);
        int totalPage = pageProducto.getTotalPages();

        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("listaProductos", pageProducto.getContent()); //lista de productos a mostrar
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        model.addAttribute("usuario", usuario);
        return "tienda";
    }

}
