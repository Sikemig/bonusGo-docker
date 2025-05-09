package com.bonusGo.Bonus.Go.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @Column(name = "ID_Usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Usuario;
    @Column(length = 30)
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String correo;
    @Column(length = 15)
    private String telefono;
    @Column(length = 4)
    private Integer moneda;
    @Column
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Rol")
    //@JsonManagedReference
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GananciaMonedas> gananciaMonedas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaccion> transacciones;

    public Usuario(int id_Usuario, String nombre, String apellido, String correo, String telefono, String password, Rol rol) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String nombre, String apellido, String correo, String telefono, String password, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String nombre, String apellido, String correo, String telefono, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
    }

    public Usuario() {
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (rol != null && rol.getNombre() != null) {
            return List.of(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<GananciaMonedas> getGananciaMonedas() {
        return gananciaMonedas;
    }

    public void setGananciaMonedas(List<GananciaMonedas> gananciaMonedas) {
        this.gananciaMonedas = gananciaMonedas;
    }
}
