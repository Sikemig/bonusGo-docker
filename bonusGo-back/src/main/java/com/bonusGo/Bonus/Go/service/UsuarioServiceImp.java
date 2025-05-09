package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Rol;
import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.payload.LoginResponse;
import com.bonusGo.Bonus.Go.repository.GananciaMonedasRepository;
import com.bonusGo.Bonus.Go.repository.RolRepository;
import com.bonusGo.Bonus.Go.repository.TransaccionRepository;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario getlogin(String correo, String pass) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByCorreo(correo);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            if (passwordEncoder.matches(pass, usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario registerUsuario(Usuario usuario) {
        try {
            usuario.setMoneda(0);

            Optional<Rol> rolOptional = rolRepository.findById(1);
            if (!rolOptional.isPresent()) {
                System.out.println("Rol no encontrado");
                return null;
            }
            usuario.setRol(rolOptional.get());

            if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
                System.out.println("El correo ya está registrado");
                return null;
            }

            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public void deleteUsuario(int id) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            if (usuarioOptional.isPresent()) {
                usuarioRepository.deleteById(id);
            } else {
                System.out.println("Usuario no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario updateMonedas(int id, int monedas) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            if (!usuarioOptional.isPresent()) {
                System.out.println("Usuario no encontrado");
                return null;
            }

            Usuario usuario = usuarioOptional.get();
            int nuevasMonedas = usuario.getMoneda() + monedas;
            usuario.setMoneda(nuevasMonedas);
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println("Error al actualizar monedas: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Usuario updateUsuario(int id, Usuario usuarioActualizado) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            if (!usuarioOptional.isPresent()) {
                System.out.println("Usuario no encontrado con ID: " + id);
                return null;
            }

            Usuario existente = usuarioOptional.get();
            existente.setNombre(usuarioActualizado.getNombre());
            existente.setApellido(usuarioActualizado.getApellido());
            existente.setCorreo(usuarioActualizado.getCorreo());
            existente.setTelefono(usuarioActualizado.getTelefono());
            existente.setRol(usuarioActualizado.getRol());
            existente.setMoneda(usuarioActualizado.getMoneda());

            return usuarioRepository.save(existente);
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Usuario updateUsuarioPerfil(int id, Usuario usuarioActualizado) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            if (!usuarioOptional.isPresent()) {
                System.out.println("Usuario no encontrado con ID: " + id);
                return null;
            }

            Usuario existente = usuarioOptional.get();
            existente.setNombre(usuarioActualizado.getNombre());
            existente.setApellido(usuarioActualizado.getApellido());
            existente.setCorreo(usuarioActualizado.getCorreo());
            existente.setTelefono(usuarioActualizado.getTelefono());

            return usuarioRepository.save(existente);
        } catch (Exception e) {
            System.out.println("Error al actualizar perfil de usuario: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByCorreo(email);
    }
}
