
package com.ep_movil.servicios;

import com.ep_movil.dao.IComentarioDao;
import com.ep_movil.entidades.Comentario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioServiceImpl implements IComentarioService {

    @Autowired
    private IComentarioDao comentarioDao;
    
    @Transactional (readOnly = true)
    @Override
    public List<Comentario> listarComentarios() {
        return comentarioDao.findAll();
    }

    @Transactional
    @Override
    public void guardarComentario(Comentario comentario) {
        comentarioDao.save(comentario);
    }

    @Transactional 
    @Override
    public void eliminarComentario(Comentario comentario) {
        comentarioDao.delete(comentario);
    }

    @Transactional (readOnly = true)
    @Override
    public Comentario encontrarComentario(Comentario comentario) {
        return comentarioDao.findById(comentario.getId()).orElse(null);
    }
    
}
