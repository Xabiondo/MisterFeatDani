package org.example;

import jakarta.persistence.*;

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

    // Obtener una subasta por su ID
    public static Subasta obtenerPorId(int idSubasta) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            // El método find devuelve null si no existe
            return entityManager.find(Subasta.class, idSubasta);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    // Eliminar una subasta por su ID
    public static void eliminar(int idSubasta) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Subasta subasta = entityManager.find(Subasta.class, idSubasta);
            if (subasta != null) {
                entityManager.remove(subasta);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al eliminar la subasta", e);
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
    public static void guardar(Subasta subasta) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(subasta); // Guardar la subasta

            transaction.commit(); // Confirmar la transacción
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Revertir la transacción en caso de error
            }
            throw new RuntimeException("Error al guardar la subasta", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close(); // Cerrar el EntityManager
            }
        }
    }
    public static List<Subasta> buscarPorNombreJugador(String nombreJugador) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT s FROM Subasta s WHERE LOWER(s.jugador.nombre) LIKE :nombre AND s.estaActiva = true";
            TypedQuery<Subasta> query = em.createQuery(jpql, Subasta.class);
            query.setParameter("nombre", "%" + nombreJugador.toLowerCase() + "%");
            return query.getResultList();
        } finally {
            em.close();
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