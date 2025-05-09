package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto registrarProducto(Producto producto);

    Producto actualizar(int id, Producto nuevoProducto);

    void eliminarProducto(int id);

    List<Producto> listaProductos();

    Producto buscarProductoId(int id);
}
