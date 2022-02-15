
package com.ep_movil.servicios;

import com.ep_movil.dao.IVentaRealizadaDao;
import com.ep_movil.entidades.VentaRealizada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaRealizadaServiceImpl implements IVentaRealizadaService{

   
    @Autowired
    private IVentaRealizadaDao ventaRealizadaDao;
    
    @Transactional (readOnly = true)
    @Override
    public List<VentaRealizada> listarVentaRealizadas() {
        return ventaRealizadaDao.findAll();
    }
    
    @Transactional 
    @Override
    public void guardarVentaRealizada(VentaRealizada ventaRealizada) {
        ventaRealizadaDao.save(ventaRealizada);
    }

    @Transactional
    @Override
    public void eliminarVentaRealizada(VentaRealizada ventaRealizada) {
        ventaRealizadaDao.delete(ventaRealizada);
    }

    @Transactional (readOnly = true)
    @Override
    public VentaRealizada encontrarVentaRealizada(VentaRealizada ventaRealizada) {
        return ventaRealizadaDao.findById(ventaRealizada.getId()).orElse(null);
    }
    
}
