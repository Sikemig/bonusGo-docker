package com.bonusGo.Bonus.Go.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "objetivos")
public class Objetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objetivo")
    private int idObjetivo;

    @Column(length = 30)
    private String nombre;

    @Column
    private String descripcion;

    @Column(length = 4)
    private Integer monedas;

    @Column
    private String imagen;

    @OneToMany(mappedBy = "objetivo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GananciaMonedas> gananciaMonedas;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public int getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(int idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMonedas() {
        return monedas;
    }

    public void setMonedas(Integer monedas) {
        this.monedas = monedas;
    }

    public List<GananciaMonedas> getGananciaMonedas() {
        return gananciaMonedas;
    }

    public void setGananciaMonedas(List<GananciaMonedas> gananciaMonedas) {
        this.gananciaMonedas = gananciaMonedas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}
