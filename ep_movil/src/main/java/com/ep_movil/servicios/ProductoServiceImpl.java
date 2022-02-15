
package com.ep_movil.servicios;

import com.ep_movil.dao.IProductoDao;
import com.ep_movil.entidades.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements IProductoService{

    @Autowired
    private IProductoDao productoDao;
    
    @Transactional (readOnly = true)
    @Override
    public List<Producto> listarProductos() {
        return productoDao.findAll();
    }

    @Transactional 
    @Override
    public void guardarProducto(Producto producto) {
        productoDao.save(producto);
    }

    @Transactional
    @Override
    public void eliminarProducto(Producto producto) {
        productoDao.delete(producto);
    }

    @Transactional (readOnly = true)
    @Override
    public Producto encontrarProducto(Producto producto) {
        return productoDao.findById(producto.getId()).orElse(null);
    }
    
}
