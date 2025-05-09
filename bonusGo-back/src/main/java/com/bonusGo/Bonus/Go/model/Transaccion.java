package com.bonusGo.Bonus.Go.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Transaccion")
    private int idTransaccion;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Producto")
    private Producto producto;

    @Column(name = "canjeado")
    private boolean canjeado;

    public Transaccion(Usuario usuario, int idTransaccion, Producto producto, boolean canjeado) {
        this.usuario = usuario;
        this.idTransaccion = idTransaccion;
        this.producto = producto;
        this.canjeado = canjeado;
    }

    public Transaccion(Usuario usuario, Producto producto, boolean canjeado) {
        this.usuario = usuario;
        this.producto = producto;
        this.canjeado = canjeado;
    }

    public Transaccion() {
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public boolean isCanjeado() {
        return canjeado;
    }

    public void setCanjeado(boolean canjeado) {
        this.canjeado = canjeado;
    }
}
