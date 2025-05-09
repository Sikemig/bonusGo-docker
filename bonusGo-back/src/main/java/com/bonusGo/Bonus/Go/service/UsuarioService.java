package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario getlogin(String correo, String pass);

    Usuario registerUsuario(Usuario usuario);

    List<Usuario> getAllUsuarios();

    Usuario getUsuarioById(int id);

    void deleteUsuario(int id);

    Usuario updateMonedas(int id, int monedas);

    Usuario updateUsuario(int id, Usuario usuario);

    Usuario updateUsuarioPerfil(int id, Usuario usuario);

    boolean existsByEmail(String email);

}
