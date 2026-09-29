package com.grancolombiano.vetcareapp;

/**
 * Clase modelo que representa una Cita agendada por el propietario de la mascota.
 */
public class Cita {
    private String nombreMascota;
    private String fecha;
    private String hora;
    private String motivo;
    private String estado; // "Confirmada" o "Cancelada"

    public Cita(String nombreMascota, String fecha, String hora, String motivo, String estado) {
        this.nombreMascota = nombreMascota;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
