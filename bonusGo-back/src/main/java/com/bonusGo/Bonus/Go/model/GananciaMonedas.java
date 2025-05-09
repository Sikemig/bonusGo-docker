package com.bonusGo.Bonus.Go.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ganancia_monedas")
public class GananciaMonedas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Ganancia")
    private int idGanancia;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Objetivo")
    private Objetivo objetivo;

    @Column(name = "reclamado")
    private boolean reclamado;

    @Column(name = "habilitado")
    private boolean habilitado;


    public GananciaMonedas(int idTransaccion, Usuario usuario, Objetivo objetivo, boolean reclamado, boolean habilitado) {
        this.idGanancia = idTransaccion;
        this.usuario = usuario;
        this.objetivo = objetivo;
        this.reclamado = reclamado;
        this.habilitado = habilitado;
    }

    public GananciaMonedas(Usuario usuario, Objetivo objetivo, boolean reclamado, boolean habilitado) {
        this.usuario = usuario;
        this.objetivo = objetivo;
        this.reclamado = reclamado;
        this.habilitado = habilitado;
    }

    public GananciaMonedas() {
    }

    public int getIdGanancia() {
        return idGanancia;
    }

    public void setIdGanancia(int idTransaccion) {
        this.idGanancia = idTransaccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public boolean isReclamado() {
        return reclamado;
    }

    public void setReclamado(boolean reclamado) {
        this.reclamado = reclamado;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
