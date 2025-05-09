package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.model.Transaccion;

import java.util.List;

public interface TransaccionService {

    Transaccion canjearProducto(int userId, int productoId);
    List<Producto> obtenerProductosCanjeadosPorUsuario(int userId);
}
