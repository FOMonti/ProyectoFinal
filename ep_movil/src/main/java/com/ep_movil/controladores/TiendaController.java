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

    @GetMapping("/productos")
    public String dashboard(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionSinOrden(params, model, 10);
        return "tienda";
    }

    @GetMapping("/tienda2")
    private String tienda2(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionSinOrden(params, model, 6);
        return "tienda2";
    }

    @GetMapping("/OxNA")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxNombreZ_A(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXNombreDESC(params, model, 6);
        return "tienda2";
    }

    @GetMapping("/OxND")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxNombreA_Z(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXNombreASC(params, model, 6);
        return "tienda2";
    }

    @GetMapping("/Ox-P")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxMenorPrecio(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXPrecioASC(params, model, 6);
        return "tienda2";
    }

    @GetMapping("/Ox+P")
    //este metodo aplica paginacion y filtro/orden de la tienda (dashboard)
    public String ordenarxMayorPrecio(@RequestParam Map<String, Object> params, Model model) {
        model = productoService.paginacionXPrecioDESC(params, model, 6);
        return "tienda2";
    }

}
