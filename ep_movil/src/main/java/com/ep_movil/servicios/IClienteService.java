
package com.ep_movil.servicios;

import com.ep_movil.entidades.Cliente;
import java.util.List;


public interface IClienteService {
    
    public List<Cliente>listarClientes();
    
    public void guardarCliente(Cliente clientes);
    
    public void eliminarCliente(Cliente clientess);

    public Cliente encontrarCliente(Cliente clientes);
}
