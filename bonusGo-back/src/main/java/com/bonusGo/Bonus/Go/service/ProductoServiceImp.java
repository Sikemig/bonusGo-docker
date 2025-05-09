package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto registrarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(int id, Producto nuevoProducto) {
        try {
            Optional<Producto> productoOptional = productoRepository.findById(id);
            if (!productoOptional.isPresent()) {
                System.out.println("Producto no encontrado con ID: " + id);
                return null;
            }

            Producto productoExistente = productoOptional.get();
            productoExistente.setNombre(nuevoProducto.getNombre());
            productoExistente.setDescripcion(nuevoProducto.getDescripcion());
            productoExistente.setTipo(nuevoProducto.getTipo());
            productoExistente.setCoste(nuevoProducto.getCoste());
            productoExistente.setImagen(nuevoProducto.getImagen());

            return productoRepository.save(productoExistente);
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void eliminarProducto(int id) {
        try {
            Optional<Producto> productoOptional = productoRepository.findById(id);
            if (!productoOptional.isPresent()) {
                System.out.println("Producto no encontrado con ID: " + id);
                return;
            }

            productoRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    @Override
    public List<Producto> listaProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarProductoId(int id) {
        try {
            Optional<Producto> productoOptional = productoRepository.findById(id);
            if (productoOptional.isPresent()) {
                return productoOptional.get();
            } else {
                System.out.println("Producto no encontrado con ID: " + id);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el producto: " + e.getMessage());
            return null;
        }
    }

}
