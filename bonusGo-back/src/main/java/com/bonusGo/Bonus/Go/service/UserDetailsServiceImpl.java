package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(email);

        if (!usuarioOptional.isPresent()) {
            System.out.println("Usuario no encontrado con correo: " + email);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(),
                usuario.getPassword(),
                Collections.singleton(usuario.getRol())
        );
    }
}
