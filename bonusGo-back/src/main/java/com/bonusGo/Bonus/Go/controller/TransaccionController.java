package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.service.ProductoService;
import com.bonusGo.Bonus.Go.service.TransaccionService;
import com.bonusGo.Bonus.Go.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/canjear")
    public ResponseEntity<?> canjearProducto(@RequestParam int idUsuario, @RequestParam int idProducto) {
        try {
            transaccionService.canjearProducto(idUsuario, idProducto);
            return new ResponseEntity<>("Producto canjeado con éxito", HttpStatus.OK);
        } catch (RuntimeException ex) {
            // Si se lanza una RuntimeException (por ejemplo, no hay suficientes monedas o ya se canjeó el producto)
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/canjeados/{idUsuario}")
    public ResponseEntity<List<Producto>> obtenerProductosCanjeados(@PathVariable int idUsuario) {
        List<Producto> productos = transaccionService.obtenerProductosCanjeadosPorUsuario(idUsuario);
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}
