package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.GananciaMonedas;

import java.util.List;

public interface GananciaMonedasService {
    GananciaMonedas habilitarObjetivoParaUsuario(int idUsuario, int idObjetivo);

    GananciaMonedas deshabilitarObjetivoParaUsuario(int idUsuario, int idObjetivo);

    void marcarObjetivoComoReclamado(int idUsuario, int idObjetivo);

    List<Integer> obtenerIdsUsuariosConObjetivoHabilitado(int idObjetivo);

}
