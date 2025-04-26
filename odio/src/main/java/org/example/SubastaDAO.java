package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class SubastaDAO {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.example");

    // Crear una nueva subasta
    public static void crearSubasta(Subasta subasta) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            var transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(subasta);

            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la subasta", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Obtener todas las subastas activas
    public static List<Subasta> obtenerSubastasActivas() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            String jpql = "SELECT s FROM Subasta s WHERE s.estaActiva = TRUE";
            TypedQuery<Subasta> query = entityManager.createQuery(jpql, Subasta.class);
            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Finalizar una subasta
    public static void finalizarSubasta(int idSubasta) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            var transaction = entityManager.getTransaction();
            transaction.begin();

            Subasta subasta = entityManager.find(Subasta.class, idSubasta);
            if (subasta != null) {
                subasta.setEstaActiva(false);
                subasta.setFechaFin(java.time.LocalDateTime.now());
                entityManager.merge(subasta);
            }

            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error al finalizar la subasta", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}