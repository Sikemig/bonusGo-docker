package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Eliminar usuario por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getTodos")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return new ResponseEntity<>(usuarioService.getAllUsuarios(), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado)
    {
        Usuario actualizado = usuarioService.updateUsuario(id, usuarioActualizado);
        return ResponseEntity.ok(actualizado);
    }

    @PutMapping("/actualizarPerfil/{id}")
    public ResponseEntity<Usuario> actualizarUsuarioPerfil(@PathVariable int id, @RequestBody Usuario usuarioActualizado)
    {
        Usuario actualizado = usuarioService.updateUsuarioPerfil(id, usuarioActualizado);
        return ResponseEntity.ok(actualizado);
    }

}
