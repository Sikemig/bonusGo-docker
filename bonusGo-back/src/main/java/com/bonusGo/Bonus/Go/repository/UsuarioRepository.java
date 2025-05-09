package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> getByCorreo(String correo);
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}