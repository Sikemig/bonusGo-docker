package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/registrar")
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto)
    {

        Producto nuevoProducto = productoService.registrarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        try {
            productoService.eliminarProducto(id);
            return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto producto){
            try{
                Producto actualizado = productoService.actualizar(id, producto);
                return new ResponseEntity<>(actualizado, HttpStatus.OK);
            } catch (RuntimeException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Producto>> listaProductos() {
        List<Producto> productos = productoService.listaProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}

