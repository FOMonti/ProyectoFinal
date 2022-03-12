
package com.ep_movil.servicios;

import com.ep_movil.entidades.Carrito;

import java.util.List;

public interface ICarritoService {

    public List<Carrito> listarCarritos();

    public void guardarCarrito(Carrito carrito);

    public void eliminarCliente(Carrito carritos);

    public Carrito encontrarCliente(Carrito carrito);

//    public Carrito findByUsuarioId(Integer query);

}
