package com.ep_movil.servicios;

import com.ep_movil.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IProductoService {

    Page<Producto> getAll(Pageable pageable);

    List<Producto> listarProductos();

    void guardarProducto(Producto producto);

    void eliminarProducto(Producto producto);

    Producto encontrarProducto(Producto producto);

    Producto buscarPorId(Integer id);

    int actualPage(@RequestParam Map<String, Object> params);

    Model actualizarVista(int page, Page<Producto> pageProducto, Model model);

    Model paginacionSinOrden(@RequestParam Map<String, Object> params, Model model, int cantElements);

    Model paginacionXPrecioASC(@RequestParam Map<String, Object> params, Model model, int cantElements);

    Model paginacionXPrecioDESC(@RequestParam Map<String, Object> params, Model model, int cantElements);

    Model paginacionXNombreASC(@RequestParam Map<String, Object> params, Model model, int cantElements);

    Model paginacionXNombreDESC(@RequestParam Map<String, Object> params, Model model, int cantElements);

//   List<Producto> filtrarNombre(String nombre);
    Model paginacionFiltrada(@RequestParam Map<String, Object> params, Model model, int cantElements, String query);

//    Model filtroNombre(String nombre);
}
