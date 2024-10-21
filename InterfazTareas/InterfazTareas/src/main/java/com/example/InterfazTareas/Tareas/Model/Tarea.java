package com.example.InterfazTareas.Tareas.Model;

public class Tarea {
    private long id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private boolean pendiente;

    public Tarea(long id, String nombre, String descripcion, String fecha, boolean pendiente) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.pendiente = pendiente;
    }

    public Tarea() {

    }

    // Métodos Getter y Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isPendiente() {
        return pendiente;
    }
    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    @Override
    public String toString() {
        String p = pendiente ? "Pendiente" : "Realizada";
        return "Nombre: " + nombre + "\nDescripción: " + descripcion + "\nFecha: " + fecha + "\nPendiente: " + p;
    }

    // Metodo para marcar como realizada
    public void marcarRealizada() {
        this.pendiente = false;
    }

    // Metodo para marcar como pendiente
    public void marcarPendiente() {
        this.pendiente = true;
    }
}

