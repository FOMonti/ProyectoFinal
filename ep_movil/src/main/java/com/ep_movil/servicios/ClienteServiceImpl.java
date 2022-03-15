package com.ep_movil.servicios;

import com.ep_movil.dao.IClienteDao;
import com.ep_movil.entidades.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements IClienteService {
    
    @Autowired
    private IClienteDao clienteDao;
    
    @Transactional (readOnly = true)
    @Override
    public List<Cliente> listarClientes() {
        return clienteDao.findAll();
    }
    
    @Transactional 
    @Override
    public void guardarCliente(Cliente clientes) {
        clienteDao.save(clientes);
    }

    @Transactional 
    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Transactional (readOnly = true)
    @Override
    public Cliente encontrarCliente(Cliente cliente) {
        return clienteDao.findById(Math.toIntExact(cliente.getId())).orElse(null);/*para convertir el long del 
        cliente.getId() uso el Math.toIntExact(), ya que el findById() me pide un int*/
    }

}
