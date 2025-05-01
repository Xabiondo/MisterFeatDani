package org.example;

import jakarta.persistence.*;
import java.util.List;

public class PreguntaDAO {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.example");

    // Guardar una nueva pregunta
    public static void guardar(Pregunta pregunta) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(pregunta);

            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la pregunta", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Obtener una pregunta por su ID
    public static Pregunta obtenerPorId(int idPregunta) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.find(Pregunta.class, idPregunta);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Obtener todas las preguntas
    public static List<Pregunta> obtenerTodas() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            String jpql = "SELECT p FROM Pregunta p";
            TypedQuery<Pregunta> query = entityManager.createQuery(jpql, Pregunta.class);
            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Obtener una pregunta aleatoria
    public static Pregunta obtenerPreguntaAleatoria() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            String jpql = "SELECT p FROM Pregunta p ORDER BY FUNCTION('RAND')";
            TypedQuery<Pregunta> query = entityManager.createQuery(jpql, Pregunta.class);
            query.setMaxResults(1);
            List<Pregunta> preguntas = query.getResultList();
            return preguntas.isEmpty() ? null : preguntas.get(0);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Eliminar una pregunta por su ID
    public static void eliminar(int idPregunta) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Pregunta pregunta = entityManager.find(Pregunta.class, idPregunta);
            if (pregunta != null) {
                entityManager.remove(pregunta);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al eliminar la pregunta", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Actualizar una pregunta existente
    public static void actualizar(Pregunta pregunta) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.merge(pregunta);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al actualizar la pregunta", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}