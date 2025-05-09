package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.GananciaMonedas;
import com.bonusGo.Bonus.Go.model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GananciaMonedasRepository extends JpaRepository<GananciaMonedas, Integer> {

    // Para ver que objetivos ha reclamado X usuario (historial)
    @Query("SELECT t.objetivo FROM GananciaMonedas t WHERE t.usuario.id_Usuario = :userId AND t.reclamado = true")
    List<Objetivo> findObjetivosReclamadosPorUsuario(int userId);


    // Muestra los objetivos disponibles por el usuario para reclamar
    @Query("SELECT t.objetivo FROM GananciaMonedas t WHERE t.usuario.id_Usuario = :userId AND t.reclamado = false AND t.habilitado = true")
    List<Objetivo> findObjetivosDisponiblesParaUsuario(int userId);


    // Necesario para habilitar los objetivos a X usuario
    @Query("SELECT gm FROM GananciaMonedas gm WHERE gm.usuario.id_Usuario = :idUsuario AND gm.objetivo.idObjetivo = :idObjetivo")
    GananciaMonedas relacionUsuarioYObjetivo(@Param("idUsuario") int idUsuario, @Param("idObjetivo") int idObjetivo);

    // Para ver que usuarios tienen habilitados objetivos
    @Query("SELECT gm.usuario.id_Usuario FROM GananciaMonedas gm WHERE gm.objetivo.idObjetivo = :idObjetivo AND gm.habilitado = true")
    List<Integer> findUsuariosConObjetivoHabilitado(@Param("idObjetivo") int idObjetivo);

}
