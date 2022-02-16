package com.ep_movil.servicios;

import com.ep_movil.entidades.ItemCarrito;
import java.util.List;

public interface IItemCarritoService {

    public List<ItemCarrito> listarItemCarrito();

    public void guardarItemCarrito(ItemCarrito itemCarrito);

    public void eliminarCliente(ItemCarrito itemCarrito);

    public ItemCarrito encontrarCliente(ItemCarrito itemCarrito);

}
