package org.example;

import jakarta.persistence.*;

import java.util.List;

public class JugadorDAO {

    private EntityManagerFactory entityManagerFactory;

    public JugadorDAO() {
        // Inicializamos el EntityManagerFactory con el nombre de la unidad de persistencia
        this.entityManagerFactory = Persistence.createEntityManagerFactory("org.example");
    }

    public List<Jugador> obtenerJugadoresSinUsuario() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            String jpql = "SELECT j FROM Jugador j WHERE j.usuario IS NULL";
            TypedQuery<Jugador> query = entityManager.createQuery(jpql, Jugador.class);
            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

   //Espera, primero un método que devuleva el id de un jugador
   public void asignarJugadoresAleatoriosAUsuario(int idUsuario) {
       EntityManager entityManager = null;
       EntityTransaction transaction = null;
       try {
           entityManager = entityManagerFactory.createEntityManager();
           transaction = entityManager.getTransaction();
           transaction.begin();

           // Paso 1: Obtener 5 jugadores aleatorios con id_usuario IS NULL
           String sqlSelect = "SELECT * FROM jugadores WHERE id_usuario IS NULL ORDER BY RAND() LIMIT ?";
           Query querySelect = entityManager.createNativeQuery(sqlSelect, Jugador.class);
           querySelect.setParameter(1, 5); // Limitar el número de resultados

           List<Jugador> jugadoresAleatorios = querySelect.getResultList();

           // Validar que se hayan encontrado suficientes jugadores
           if (jugadoresAleatorios.isEmpty()) {
               throw new RuntimeException("No hay suficientes jugadores disponibles para asignar.");
           }

           // Paso 2: Actualizar la clave foránea (id_usuario) de los jugadores seleccionados
           for (Jugador jugador : jugadoresAleatorios) {
               jugador.setUsuario(new Usuario(idUsuario)); // Asignar el usuario usando su ID
               entityManager.merge(jugador); // Actualizar el jugador en la base de datos
           }

           transaction.commit();
       } catch (Exception e) {
           if (transaction != null && transaction.isActive()) {
               transaction.rollback(); // Revertir cambios en caso de error
           }
           throw new RuntimeException("Error al asignar jugadores aleatorios al usuario", e);
       } finally {
           if (entityManager != null && entityManager.isOpen()) {
               entityManager.close(); // Cerrar el EntityManager
           }
       }
   }

    public void guardar(Jugador jugador) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(jugador); // Inserta el jugador
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error al guardar jugador: " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public Jugador buscarPorId(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.find(Jugador.class, id);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    public List<Jugador> obtenerJugadoresPorUsuario(int idUsuario) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            // Consulta JPQL para obtener jugadores con id_usuario igual al ID proporcionado
            String jpql = "SELECT j FROM Jugador j WHERE j.usuario.id = :idUsuario";
            TypedQuery<Jugador> query = entityManager.createQuery(jpql, Jugador.class);
            query.setParameter("idUsuario", idUsuario);

            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    public Jugador obtenerPorNombre(String nombre) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            // Consulta JPQL para buscar un jugador por su nombre
            String jpql = "SELECT j FROM Jugador j WHERE j.nombre = :nombre";
            TypedQuery<Jugador> query = entityManager.createQuery(jpql, Jugador.class);
            query.setParameter("nombre", nombre);

            return query.getSingleResult(); // Devuelve el jugador encontrado (o lanza excepción si no existe)
        } catch (NoResultException e) {
            // Si no se encuentra ningún jugador, devolvemos null
            return null;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close(); // Cerrar el EntityManager
            }
        }
    }


    public void actualizar(Jugador jugador) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(jugador); // Actualiza el jugador
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error al actualizar jugador: " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public void eliminar(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Jugador jugador = entityManager.find(Jugador.class, id);
            if (jugador != null) {
                entityManager.remove(jugador); // Elimina el jugador
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error al eliminar jugador: " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public void cerrar() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}