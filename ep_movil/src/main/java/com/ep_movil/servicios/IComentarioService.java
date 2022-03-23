
package com.ep_movil.servicios;


import com.ep_movil.entidades.Comentario;
import com.ep_movil.entidades.Producto;
import com.ep_movil.entidades.Usuario;

import java.util.List;


public interface IComentarioService {

    public List<Comentario> listarComentarios(Producto producto);

    public void guardarComentario(Comentario comentario);

    public void eliminarComentario(Comentario comentario);

    public Comentario encontrarComentario(Comentario comentario);

    public List<Comentario> listarComentariosUsuario(Usuario usuario);
}
