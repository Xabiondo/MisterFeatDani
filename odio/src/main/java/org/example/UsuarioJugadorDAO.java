package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class UsuarioJugadorDAO {

    private EntityManagerFactory emf;

    // Constructor: Inicializa el EntityManagerFactory
    public UsuarioJugadorDAO() {
        emf = Persistence.createEntityManagerFactory("your-persistence-unit"); // Reemplaza "your-persistence-unit" con el nombre de tu unidad de persistencia
    }

    // Método para cerrar la conexión al finalizar
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    // 1. Guardar un nuevo registro
    public void guardar(UsuarioJugador usuarioJugador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuarioJugador); // Guarda el objeto en la base de datos
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al guardar el registro", e);
        } finally {
            em.close();
        }
    }

    // 2. Actualizar un registro existente
    public void actualizar(UsuarioJugador usuarioJugador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuarioJugador); // Actualiza el objeto en la base de datos
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar el registro", e);
        } finally {
            em.close();
        }
    }

    // 3. Eliminar un registro por su clave primaria compuesta
    public void eliminar(UsuarioJugador usuarioJugador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            UsuarioJugador managedEntity = em.find(UsuarioJugador.class, usuarioJugador);
            if (managedEntity != null) {
                em.remove(managedEntity); // Elimina el objeto de la base de datos
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar el registro", e);
        } finally {
            em.close();
        }
    }

    // 4. Buscar un registro por su clave primaria compuesta
    public UsuarioJugador buscarPorId(UsuarioJugador id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(UsuarioJugador.class, id); // Busca el objeto por su clave primaria
        } finally {
            em.close();
        }
    }

    // 5. Obtener todos los registros
    public List<UsuarioJugador> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<UsuarioJugador> query = em.createQuery("SELECT u FROM UsuarioJugador u", UsuarioJugador.class);
            return query.getResultList(); // Devuelve todos los registros
        } finally {
            em.close();
        }
    }
}