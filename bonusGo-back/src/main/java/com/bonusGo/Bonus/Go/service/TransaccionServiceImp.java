package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.model.Transaccion;
import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.repository.ProductoRepository;
import com.bonusGo.Bonus.Go.repository.TransaccionRepository;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class TransaccionServiceImp implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Transaccion canjearProducto(int userId, int productoId) {
        Transaccion existente = transaccionRepository.findTransaccionCanjeadaPorUsuarioYProducto(userId, productoId);
        if (existente != null) {
            throw new RuntimeException("El producto ya fue canjeado por este usuario.");
        }

        Usuario usuarioEncontrado = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Producto productoEncontrado = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));


        if (usuarioEncontrado.getMoneda() < productoEncontrado.getCoste()) {
            throw new RuntimeException("No tienes suficientes PigCoins para canjear este producto.");
        }

        usuarioEncontrado.setMoneda(usuarioEncontrado.getMoneda() - productoEncontrado.getCoste());
        usuarioRepository.save(usuarioEncontrado);

        Transaccion transaccion = new Transaccion(usuarioEncontrado, productoEncontrado, true);
        return transaccionRepository.save(transaccion);
    }

    @Override
    public List<Producto> obtenerProductosCanjeadosPorUsuario(int userId) {
        return transaccionRepository.findProductosCanjeadosByUsuarioId(userId);
    }
}
