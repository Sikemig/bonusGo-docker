package com.bonusGo.Bonus.Go.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String nombre;
    @NotBlank
    @Size(min = 3, max = 20)
    private String apellido;
    @NotBlank
    @Size(min = 5, max = 20)
    private String correo;
    @NotBlank
    @Size(min = 3, max = 20)
    private String telefono;
    @NotBlank
    @Size(min = 3, max = 20)
    private String password;
    @NotBlank
    private Integer monedas;
    @NotBlank
    private Rol rol;

    public RegisterRequest(String nombre, String apellido, String correo, String telefono, String password, Integer monedas, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.monedas = monedas;
        this.rol = rol;
    }

    public RegisterRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPassword() {
        return password;
    }

    public Integer getMonedas() {
        return monedas;
    }

    public Rol getRol() {
        return rol;
    }
}
