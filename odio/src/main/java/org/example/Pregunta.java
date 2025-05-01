package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String enunciado;

    @Column(nullable = false, length = 255)
    private String respuesta;

    // Constructor vacío requerido por JPA/Hibernate
    public Pregunta() {}

    // Constructor con parámetros
    public Pregunta(String enunciado, String respuesta) {
        this.enunciado = enunciado;
        this.respuesta = respuesta;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
