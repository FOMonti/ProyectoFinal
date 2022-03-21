package com.ep_movil.servicios;

import com.ep_movil.dao.IItemCarritoDao;
import com.ep_movil.entidades.ItemCarrito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemCarritoServiceImpl implements IItemCarritoService {

    @Autowired
    private IItemCarritoDao itemCarritoDao;

    @Transactional(readOnly = true)
    @Override
    public List<ItemCarrito> listarItemCarrito() {
        return itemCarritoDao.findAll();
    }

    @Transactional
    @Override
    public void guardarItemCarrito(ItemCarrito itemCarrito) {
        itemCarritoDao.save(itemCarrito);
    }

    @Transactional
    @Override
    public void eliminarCliente(ItemCarrito itemCarrito) {
        itemCarritoDao.delete(itemCarrito);
    }

    @Override
    public ItemCarrito encontrarCliente(ItemCarrito itemCarrito) {
        return null;
    }

//    @Transactional(readOnly = true)
//    @Override
//    public ItemCarrito encontrarCliente(ItemCarrito itemCarrito) {
//        return itemCarritoDao.findById(itemCarrito.getId()).orElse(null);
//    }

}
