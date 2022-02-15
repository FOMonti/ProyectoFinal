
package com.ep_movil.servicios;


import com.ep_movil.entidades.Comentario;
import java.util.List;


public interface IComentarioService {
    
    public List<Comentario>listarComentarios();
    
    public void guardarComentario(Comentario comentario);
    
    public void eliminarComentario(Comentario comentario);

    public Comentario encontrarComentario(Comentario comentario);
    
}
