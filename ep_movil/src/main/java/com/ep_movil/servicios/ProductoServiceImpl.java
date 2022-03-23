package com.ep_movil.servicios;

import com.ep_movil.dao.IProductoDao;
import com.ep_movil.entidades.Producto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDao productoDao;

    @Transactional(readOnly = true)
    @Override
    public Page<Producto> getAll(Pageable pageable) {
        return productoDao.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> listarProductos() {
        return productoDao.findAll();
    }

    @Transactional
    @Override
    public void guardarProducto(Producto producto) {
        productoDao.save(producto);
    }

    @Transactional
    @Override
    public void eliminarProducto(Producto producto) {
        productoDao.delete(producto);
    }

    @Transactional(readOnly = true)
    @Override
    public Producto encontrarProducto(Producto producto) {
        return productoDao.findById(producto.getId()).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Producto buscarPorId(Integer id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public int actualPage(@RequestParam Map<String, Object> params) {
        return params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
    }

    @Override
    public Model paginacionSinOrden(@RequestParam Map<String, Object> params, Model model, int cantElements) {

        int page = actualPage(params);

        PageRequest pageRequest = PageRequest.of(page, cantElements);//size : Cantidad de elementos por pagina

        Page<Producto> pageProducto = getAll(pageRequest);

        int totalPage = pageProducto.getTotalPages();

        return actualizarVista(page, pageProducto, model);
    }

    @Override
    public Model paginacionXPrecioASC(@RequestParam Map<String, Object> params, Model model, int cantElements) {

        int page = actualPage(params);

        PageRequest pageRequest = PageRequest.of(page, cantElements, Sort.by(Sort.Direction.ASC, "precio"));//size : Cantidad de elementos por pagina

        Page<Producto> pageProducto = getAll(pageRequest);

        int totalPage = pageProducto.getTotalPages();

        return actualizarVista(page, pageProducto, model);
    }

    @Override
    public Model paginacionXPrecioDESC(@RequestParam Map<String, Object> params, Model model, int cantElements) {

        int page = actualPage(params);

        PageRequest pageRequest = PageRequest.of(page, cantElements, Sort.by(Sort.Direction.DESC, "precio"));//size : Cantidad de elementos por pagina

        Page<Producto> pageProducto = getAll(pageRequest);

        int totalPage = pageProducto.getTotalPages();

        return actualizarVista(page, pageProducto, model);
    }

    @Override
    public Model paginacionXNombreDESC(@RequestParam Map<String, Object> params, Model model, int cantElements) {

        int page = actualPage(params);

        PageRequest pageRequest = PageRequest.of(page, cantElements, Sort.by(Sort.Direction.DESC, "nombre"));//size : Cantidad de elementos por pagina

        Page<Producto> pageProducto = getAll(pageRequest);

        int totalPage = pageProducto.getTotalPages();

        return actualizarVista(page, pageProducto, model);
    }

    @Override
    public Model paginacionXNombreASC(@RequestParam Map<String, Object> params, Model model, int cantElements) {

        int page = actualPage(params);

        PageRequest pageRequest = PageRequest.of(page, cantElements, Sort.by(Sort.Direction.ASC, "nombre"));//size : Cantidad de elementos por pagina

        Page<Producto> pageProducto = getAll(pageRequest);

        int totalPage = pageProducto.getTotalPages();

        return actualizarVista(page, pageProducto, model);
    }

    @Override
    public Model actualizarVista(int page, Page<Producto> pageProducto, Model model) {
        int totalPage = pageProducto.getTotalPages();

        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("list", pageProducto.getContent()); //lista de productos a mostrar
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return model;
    }

    @Transactional(readOnly = true)
    @Override
    public Model paginacionFiltrada(Map<String, Object> params, Model model, int cantElements, String query) {
        int page = actualPage(params);

        PageRequest pageRequest = PageRequest.of(page, cantElements);

        Page<Producto> pageProducto = productoDao.findByNombre(query, pageRequest);

        return actualizarVista(page, pageProducto, model);
    }

}
