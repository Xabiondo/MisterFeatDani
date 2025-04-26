package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainPruebas {

    public static void main(String[] args) {
        // Inicializar el EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.example");

        // Crear instancias de DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        JugadorDAO jugadorDAO = new JugadorDAO();

        try {
            // Paso 1: Registrar un nuevo usuario
            String nombreUsuario = "prueba";
            String passwordUsuario = "1234";

            if (usuarioDAO.estaRegistrado(nombreUsuario)) {
                System.out.println("El usuario ya está registrado.");
            } else {
                Usuario nuevoUsuario = new Usuario(nombreUsuario, passwordUsuario);
                usuarioDAO.guardar(nuevoUsuario);
                System.out.println("Usuario registrado correctamente.");
            }

            // Paso 2: Obtener el ID del usuario recién registrado
            Integer usuarioId = usuarioDAO.obtenerIdPorNombre(nombreUsuario);
            if (usuarioId == null) {
                System.out.println("Error al obtener el ID del usuario.");
                return;
            }
            System.out.println("ID del usuario obtenido: " + usuarioId);

            // Paso 3: Asignar 5 jugadores aleatorios al usuario
            try {
                jugadorDAO.asignarJugadoresAleatoriosAUsuario(usuarioId);
                System.out.println("Se han asignado 5 jugadores al usuario.");
            } catch (Exception e) {
                System.err.println("Error al asignar jugadores: " + e.getMessage());
                e.printStackTrace();
                return;
            }

            // Paso 4: Verificar que los jugadores se hayan asignado correctamente
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                String jpql = "SELECT j FROM Jugador j WHERE j.usuario.id = :usuarioId";
                List<Jugador> jugadoresAsignados = entityManager.createQuery(jpql, Jugador.class)
                        .setParameter("usuarioId", usuarioId)
                        .getResultList();

                if (jugadoresAsignados.isEmpty()) {
                    System.out.println("No se encontraron jugadores asignados al usuario.");
                } else {
                    System.out.println("Jugadores asignados al usuario:");
                    for (Jugador jugador : jugadoresAsignados) {
                        System.out.println("- ID: " + jugador.getId() + ", Nombre: " + jugador.getNombre());
                    }
                }
            } finally {
                entityManager.close();
            }

        } finally {
            // Cerrar el EntityManagerFactory
            entityManagerFactory.close();
        }
    }
}