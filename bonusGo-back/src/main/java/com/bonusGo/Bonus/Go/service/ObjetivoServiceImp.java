package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.*;
import com.bonusGo.Bonus.Go.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjetivoServiceImp implements ObjetivoService {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Override
    public Objetivo registObjetivo(Objetivo objetivo) {
        return objetivoRepository.save(objetivo);
    }

    @Override
    public void eliminarObjetivo(int id) {
        Objetivo objetivo = objetivoRepository.findById(id).get();

        if (objetivo != null) {
            objetivoRepository.delete(objetivo);
        }else {
            System.out.println("Este objetivo no existe");
        }
    }

    @Override
    public List<Objetivo> listarObjetivos() {
        return objetivoRepository.findAll();
    }

    @Override
    public Objetivo buscarObjetivo(int id) {
        try {
            Optional<Objetivo> objetivoOptional = objetivoRepository.findById(id);
            if (objetivoOptional.isPresent()) {
                return objetivoOptional.get();
            } else {
                System.out.println("Objetivo no encontrado con ID: " + id);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar objetivo: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Objetivo actualizar(int id, Objetivo nuevoObjetivo) {
        try {
            Optional<Objetivo> objetivoOptional = objetivoRepository.findById(id);
            if (!objetivoOptional.isPresent()) {
                System.out.println("Objetivo no encontrado con ID: " + id);
                return null;
            }

            Objetivo objetivoExistente = objetivoOptional.get();
            objetivoExistente.setNombre(nuevoObjetivo.getNombre());
            objetivoExistente.setDescripcion(nuevoObjetivo.getDescripcion());
            objetivoExistente.setCategoria(nuevoObjetivo.getCategoria());
            objetivoExistente.setMonedas(nuevoObjetivo.getMonedas());
            objetivoExistente.setImagen(nuevoObjetivo.getImagen());

            return objetivoRepository.save(objetivoExistente);
        } catch (Exception e) {
            System.out.println("Error al actualizar objetivo: " + e.getMessage());
            return null;
        }
    }

}
