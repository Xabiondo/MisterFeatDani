package org.example;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "subastas")
public class Subasta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugador jugador;

    @Column(nullable = false)
    private double precioSalida;

    @Column(nullable = false)
    private boolean estaActiva;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    // Constructor vacío (requerido por Hibernate)
    public Subasta() {
        this.fechaInicio = LocalDateTime.now(); // Fecha de inicio automática
        this.estaActiva = true; // Por defecto, la subasta está activa
    }

    // Constructor con parámetros
    public Subasta(Jugador jugador, double precioSalida) {
        this.jugador = jugador;
        this.precioSalida = precioSalida;
        this.fechaInicio = LocalDateTime.now();
        this.estaActiva = true;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(double precioSalida) {
        this.precioSalida = precioSalida;
    }

    public boolean isEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}