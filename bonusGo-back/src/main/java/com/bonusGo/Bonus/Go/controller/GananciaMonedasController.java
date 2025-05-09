package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.GananciaMonedas;
import com.bonusGo.Bonus.Go.model.Objetivo;
import com.bonusGo.Bonus.Go.service.GananciaMonedasService;
import com.bonusGo.Bonus.Go.service.ObjetivoService;
import com.bonusGo.Bonus.Go.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ganancia")
public class GananciaMonedasController {

    @Autowired
    private GananciaMonedasService gananciaMonedasService;

    @Autowired
    private ObjetivoService objetivoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/habilitar")
    public ResponseEntity<?> cambiarEstadoObjetivo(@RequestBody GananciaMonedas ganancia) {
        try {
            int idUsuario = ganancia.getUsuario().getId_Usuario();
            int idObjetivo = ganancia.getObjetivo().getIdObjetivo();
            boolean habilitar = ganancia.isHabilitado();

            GananciaMonedas resultado = habilitar
                    ? gananciaMonedasService.habilitarObjetivoParaUsuario(idUsuario, idObjetivo)
                    : gananciaMonedasService.deshabilitarObjetivoParaUsuario(idUsuario, idObjetivo);

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<Integer>> obtenerUsuariosConObjetivoHabilitado(@RequestParam int idObjetivo) {
        try {
            List<Integer> usuarios = gananciaMonedasService.obtenerIdsUsuariosConObjetivoHabilitado(idObjetivo);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint para marcar un objetivo como reclamado
    @PostMapping("/reclamar")
    public void reclamarObjetivo(@RequestParam int idUsuario, @RequestParam int idObjetivo) {
        Objetivo objetivo = objetivoService.buscarObjetivo(idObjetivo);

        int monedasGanadas = objetivo.getMonedas();

        usuarioService.updateMonedas(idUsuario, monedasGanadas);
        gananciaMonedasService.marcarObjetivoComoReclamado(idUsuario, idObjetivo);
    }
}
