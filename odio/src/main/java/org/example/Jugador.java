package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "jugadores")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String equipo;




    @Column(nullable = false)
    private double precio;
    // Relación con Usuario (Muchos jugadores pertenecen a un usuario)
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, optional = true)
    private Subasta subasta;

    // Constructor vacío (requerido por Hibernate)
    public Jugador() {}

    // Constructor con parámetros
    public Jugador(String nombre, String equipo, double precio) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}