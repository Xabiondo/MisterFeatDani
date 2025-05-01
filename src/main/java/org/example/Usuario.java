package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private int dinero;



    // Constructor vacío (requerido por Hibernate)
    public Usuario() {
        this.rol = "usuario"; // Valor predeterminado
        this.dinero = 100000; // Valor predeterminado
    }
    public Usuario(String nombre , String contraseña , String rol) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol; // Valor predeterminado
        this.dinero = 100000; // Valor predeterminado
    }



    // Constructor simplificado para crear un usuario con solo nombre y contraseña
    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = "usuario"; // Valor predeterminado
        this.dinero = 100000; // Valor predeterminado
    }

    public Usuario(int idUsuario) {
        this.id = idUsuario; // Asignar el ID proporcionado
    }

    // Getters y setters
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}