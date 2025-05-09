package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.GananciaMonedas;
import com.bonusGo.Bonus.Go.model.Objetivo;
import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.repository.GananciaMonedasRepository;
import com.bonusGo.Bonus.Go.repository.ObjetivoRepository;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GananciaMonedasServiceImp implements GananciaMonedasService{

    @Autowired
    private GananciaMonedasRepository gananciaMonedasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Override
    public GananciaMonedas habilitarObjetivoParaUsuario(int idUsuario, int idObjetivo) {
        Usuario usuarioEncontrado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Objetivo objetivoEncontrado = objetivoRepository.findById(idObjetivo)
                .orElseThrow(() -> new IllegalArgumentException("Objetivo no encontrado"));

        GananciaMonedas relacionUsuarioObjetivo = gananciaMonedasRepository.relacionUsuarioYObjetivo(idUsuario, idObjetivo);

        if (relacionUsuarioObjetivo == null) {
            relacionUsuarioObjetivo = new GananciaMonedas(usuarioEncontrado, objetivoEncontrado, false, true);
        } else {
            if (relacionUsuarioObjetivo.isReclamado()) {
                throw new IllegalStateException("El usuario ya ha reclamado este objetivo.");
            }
            relacionUsuarioObjetivo.setHabilitado(true);
        }

        return gananciaMonedasRepository.save(relacionUsuarioObjetivo);
    }

    @Override
    public GananciaMonedas deshabilitarObjetivoParaUsuario(int idUsuario, int idObjetivo) {
        GananciaMonedas relacion = gananciaMonedasRepository.relacionUsuarioYObjetivo(idUsuario, idObjetivo);

        if (relacion == null) {
            throw new IllegalStateException("No existe relación entre el usuario y el objetivo.");
        }

        relacion.setHabilitado(false);

        return gananciaMonedasRepository.save(relacion);
    }

    @Override
    public List<Integer> obtenerIdsUsuariosConObjetivoHabilitado(int idObjetivo) {
        return gananciaMonedasRepository.findUsuariosConObjetivoHabilitado(idObjetivo);
    }

    @Override
    public void marcarObjetivoComoReclamado(int idUsuario, int idObjetivo) {
        GananciaMonedas relacion = gananciaMonedasRepository.relacionUsuarioYObjetivo(idUsuario, idObjetivo);

        if (relacion == null || !relacion.isHabilitado()) {
            throw new IllegalStateException("El objetivo no está habilitado para este usuario o no existe la relación.");
        }

        relacion.setReclamado(true);
        relacion.setHabilitado(false);

        gananciaMonedasRepository.save(relacion);
    }
}
