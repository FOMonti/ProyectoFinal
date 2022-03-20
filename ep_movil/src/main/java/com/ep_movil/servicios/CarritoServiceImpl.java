
package com.ep_movil.servicios;

import com.ep_movil.dao.ICarritoDao;
import com.ep_movil.entidades.Carrito;

import java.util.List;

import com.ep_movil.entidades.ItemCarrito;
import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoServiceImpl implements ICarritoService {

    @Autowired
    private ICarritoDao carritoDao;

    @Transactional(readOnly = true)
    @Override
    public List<Carrito> listarCarritos() {
        return carritoDao.findAll();
    }

    @Transactional
    @Override
    public void guardarCarrito(Carrito carrito) {
        carritoDao.save(carrito);
    }

    @Transactional
    @Override
    public void eliminarCliente(Carrito carrito) {
        carritoDao.delete(carrito);
    }

    @Transactional(readOnly = true)
    @Override
    public Carrito encontrarCliente(Carrito carrito) {
        return carritoDao.findById(carrito.getId()).orElse(null);
    }


}
