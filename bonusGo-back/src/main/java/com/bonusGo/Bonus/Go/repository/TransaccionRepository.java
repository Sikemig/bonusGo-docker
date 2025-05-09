package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

    // Para ver que productos ha canjeado un usuario concreto (historial)
    @Query("SELECT t.producto FROM Transaccion t WHERE t.usuario.id_Usuario = :userId AND t.canjeado = true")
    List<Producto> findProductosCanjeadosByUsuarioId(int userId);

    // Verifica si un usuario ya ha canjeado un producto específico
    @Query("SELECT t FROM Transaccion t WHERE t.usuario.id_Usuario = :userId AND t.producto.id_Producto = :productoId AND t.canjeado = true")
    Transaccion findTransaccionCanjeadaPorUsuarioYProducto(int userId, int productoId);
}
