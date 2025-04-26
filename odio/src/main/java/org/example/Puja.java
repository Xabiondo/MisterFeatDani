package org.example;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pujas")
public class Puja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_subasta", nullable = false)
    private Subasta subasta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private double cantidad;

    @Column(nullable = false)
    private LocalDateTime fecha;

    // Constructor vacío (requerido por Hibernate)
    public Puja() {
        this.fecha = LocalDateTime.now(); // Fecha de la puja automática
    }

    // Constructor con parámetros
    public Puja(Subasta subasta, Usuario usuario, double cantidad) {
        this.subasta = subasta;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}