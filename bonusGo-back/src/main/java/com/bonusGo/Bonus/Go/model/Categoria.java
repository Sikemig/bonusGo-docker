package com.bonusGo.Bonus.Go.model;

public enum Categoria {

    BRONCE(1, "Bronce"),
    PLATA(2, "Plata"),
    ORO(3, "Oro");

    private final int id_Categoria;
    private final String nombre;

    Categoria(int id_Categoria, String nombre) {
        this.id_Categoria = id_Categoria;
        this.nombre = nombre;
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public String getNombre() {
        return nombre;
    }
}

