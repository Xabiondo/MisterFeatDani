package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PujaDAO {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.example");

    // Crear una nueva puja
    public static void crearPuja(Puja puja) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            var transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(puja);

            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la puja", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Obtener todas las pujas de una subasta
    public static List<Puja> obtenerPujasPorSubasta(int idSubasta) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            String jpql = "SELECT p FROM Puja p WHERE p.subasta.id = :idSubasta ORDER BY p.cantidad DESC";
            TypedQuery<Puja> query = entityManager.createQuery(jpql, Puja.class);
            query.setParameter("idSubasta", idSubasta);

            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    // Obtener la mayor puja de una subasta
    public static Puja obtenerMayorPuja(int idSubasta) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            String jpql = "SELECT p FROM Puja p WHERE p.subasta.id = :idSubasta ORDER BY p.cantidad DESC";
            TypedQuery<Puja> query = entityManager.createQuery(jpql, Puja.class);
            query.setParameter("idSubasta", idSubasta);
            query.setMaxResults(1);

            List<Puja> pujas = query.getResultList();
            return pujas.isEmpty() ? null : pujas.get(0);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}