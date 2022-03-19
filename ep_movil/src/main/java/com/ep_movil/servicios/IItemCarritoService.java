package com.ep_movil.servicios;

import com.ep_movil.entidades.Carrito;
import com.ep_movil.entidades.ItemCarrito;

import java.util.List;

public interface IItemCarritoService {

    public List<ItemCarrito> listarItemCarrito(Carrito carrito);

    //public List<ItemCarrito> listarItemCarrito();

    public void guardarItemCarrito(ItemCarrito itemCarrito);

    public void eliminarCliente(ItemCarrito itemCarrito);

    public ItemCarrito encontrarCliente(ItemCarrito itemCarrito);

    public ItemCarrito getById (Integer id);
}
