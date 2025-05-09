package com.bonusGo.Bonus.Go.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Entity
@Table(name = "roles")
public class Rol implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Rol;
    @Column(length = 20)
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private String permisos;

    @OneToMany(mappedBy = "rol", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Usuario> usuarios;

    public Rol(int id_Rol, String nombre, String descripcion, String permisos, List<Usuario> usuarios) {
        this.id_Rol = id_Rol;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.permisos = permisos;
        this.usuarios = usuarios;
    }

    public Rol() {
    }

    @Override
    public String getAuthority() {
        return nombre; // "ROLE_USER", "ROLE_ADMIN
    }

    public int getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        this.id_Rol = id_Rol;
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

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

