
package com.ep_movil.servicios;

import com.ep_movil.entidades.Producto;
import java.util.List;

public interface IProductoService {
    
    public List<Producto>listarProductos();
    
    public void guardarProducto(Producto producto);
    
    public void eliminarProducto(Producto producto);

    public Producto encontrarProducto(Producto producto);
    
    public Producto buscarPorId(Integer id);
}
