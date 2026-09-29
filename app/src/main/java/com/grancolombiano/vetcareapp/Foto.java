package com.grancolombiano.vetcareapp;

/**
 * Clase modelo que representa una fotografía de la galería
 * (paciente o instalación de la clínica), con su descripción detallada.
 */
public class Foto {
    private String titulo;
    private String descripcion;
    private int imagenResId;

    public Foto(String titulo, String descripcion, int imagenResId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
