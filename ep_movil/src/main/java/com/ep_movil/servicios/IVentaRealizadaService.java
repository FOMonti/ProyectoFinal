
package com.ep_movil.servicios;

import com.ep_movil.entidades.VentaRealizada;
import java.util.List;

public interface IVentaRealizadaService {
    
    public List<VentaRealizada>listarVentaRealizadas();
    
    public void guardarVentaRealizada(VentaRealizada ventaRealizada);
    
    public void eliminarVentaRealizada(VentaRealizada ventaRealizada);

    public VentaRealizada encontrarVentaRealizada(VentaRealizada ventaRealizada);
}
