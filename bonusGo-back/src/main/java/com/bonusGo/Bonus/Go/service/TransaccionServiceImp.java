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
        try {
            Transaccion existente = transaccionRepository.findTransaccionCanjeadaPorUsuarioYProducto(userId, productoId);
            if (existente != null) {
                System.out.println("El producto ya fue canjeado por este usuario.");
                return null;
            }

            Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);
            if (!usuarioOptional.isPresent()) {
                System.out.println("Usuario no encontrado");
                return null;
            }

            Optional<Producto> productoOptional = productoRepository.findById(productoId);
            if (!productoOptional.isPresent()) {
                System.out.println("Producto no encontrado");
                return null;
            }

            Usuario usuario = usuarioOptional.get();
            Producto producto = productoOptional.get();

            if (usuario.getMoneda() < producto.getCoste()) {
                System.out.println("No tienes suficientes PigCoins para canjear este producto.");
                return null;
            }

            usuario.setMoneda(usuario.getMoneda() - producto.getCoste());
            usuarioRepository.save(usuario);

            Transaccion transaccion = new Transaccion(usuario, producto, true);
            return transaccionRepository.save(transaccion);

        } catch (Exception e) {
            System.out.println("Error al canjear producto: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Producto> obtenerProductosCanjeadosPorUsuario(int userId) {
        return transaccionRepository.findProductosCanjeadosByUsuarioId(userId);
    }
}
