
package com.ep_movil.servicios;

import com.ep_movil.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService {

    Page<Producto> getAll(Pageable pageable);

    List<Producto> listarProductos();

    void guardarProducto(Producto producto);

    void eliminarProducto(Producto producto);

    Producto encontrarProducto(Producto producto);

    Producto buscarPorId(Integer id);
}
