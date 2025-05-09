package com.bonusGo.Bonus.Go.model;

public enum Tipo {
    ROPA(1, "Ropa", "Prendas de vestir y artículos de moda diseñados para el día a día, ocasiones especiales y actividades deportivas."),
    EXPERIENCIA(2, "Experiencia", "Eventos y actividades únicas que incluyen entretenimiento, viajes, gastronomía y aventuras emocionantes."),
    TARJETAS(3, "Tarjetas Regalo", "Tarjetas de regalo utilizables en múltiples comercios, ideales para obsequios y recompensas personalizadas.");

    private final int id_Tipo;  // Hacerlo final porque no cambiará
    private final String nombre;
    private final String descripcion;

    Tipo(int id_Tipo, String nombre, String descripcion) {
        this.id_Tipo = id_Tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_Tipo() {
        return id_Tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
