package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioDAO {

    private EntityManagerFactory entityManagerFactory;

    public UsuarioDAO() {
        // Inicializamos el EntityManagerFactory con el nombre de la unidad de persistencia
        this.entityManagerFactory = Persistence.createEntityManagerFactory("org.example");
    }


    public void guardar(Usuario usuario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al guardar el usuario", e);
        } finally {
            entityManager.close();
        }
    }


    public void actualizar(Usuario usuario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar el usuario", e);
        } finally {
            entityManager.close();
        }
    }


    public void eliminar(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Usuario usuario = entityManager.find(Usuario.class, id);
            if (usuario != null) {
                entityManager.remove(usuario);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar el usuario", e);
        } finally {
            entityManager.close();
        }
    }


    public Usuario obtenerPorId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Usuario.class, id);
        } finally {
            entityManager.close();
        }
    }


    public List<Usuario> obtenerTodos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT u FROM Usuario u";
            TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
    public Usuario obtenerPorNombre(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Consulta JPQL para buscar un usuario por su nombre
            String jpql = "SELECT u FROM Usuario u WHERE u.nombre = :nombre";
            TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
            query.setParameter("nombre", nombre); // Asignamos el parámetro "nombre" a la consulta

            // Ejecutamos la consulta y obtenemos el resultado
            return query.getSingleResult(); // Devuelve el usuario encontrado
        } catch (Exception e) {
            // Si no se encuentra ningún usuario, capturamos la excepción y retornamos null
            return null;
        } finally {
            // Cerramos el EntityManager para liberar recursos
            entityManager.close();
        }
    }


    public Integer obtenerIdPorNombre(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Consulta JPQL para seleccionar solo el ID del usuario por su nombre
            String jpql = "SELECT u.id FROM Usuario u WHERE u.nombre = :nombre";
            TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
            query.setParameter("nombre", nombre); // Asignamos el parámetro "nombre" a la consulta

            // Ejecutamos la consulta y obtenemos el resultado
            List<Integer> resultados = query.getResultList();

            // Si se encontró un ID, lo devolvemos; de lo contrario, retornamos null
            return resultados.isEmpty() ? null : resultados.get(0);
        } catch (Exception e) {
            // Manejamos cualquier error que ocurra durante la consulta
            System.err.println("Error al obtener el ID del usuario: " + e.getMessage());
            return null;
        } finally {
            // Cerramos el EntityManager para liberar recursos
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public Boolean estaRegistrado(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Consulta JPQL para buscar un usuario por su nombre
            String jpql = "SELECT u FROM Usuario u WHERE u.nombre = :nombre";
            TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
            query.setParameter("nombre", nombre); // Asignamos el parámetro "nombre" a la consulta

            // Ejecutamos la consulta y obtenemos el resultado
            Usuario usuario = query.getSingleResult(); // Intentamos obtener un único resultado

            // Si se encontró un usuario, devolvemos true
            return usuario != null;
        } catch (Exception e) {
            // Si no se encuentra ningún usuario, Hibernate lanzará NoResultException
            return false; // Devolvemos false si no existe el usuario
        } finally {
            // Cerramos el EntityManager para liberar recursos
            entityManager.close();
        }
    }
    // Método para cerrar el EntityManagerFactory cuando ya no sea necesario
    public void cerrar() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
    public boolean existeUsuario(String nombre, String contraseña) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Consulta JPQL para contar usuarios con el nombre y contraseña dados
            String jpql = "SELECT COUNT(u) FROM Usuario u WHERE u.nombre = :nombre AND u.contraseña = :contraseña";
            TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
            query.setParameter("nombre", nombre);
            query.setParameter("contraseña", contraseña);

            // Obtenemos el resultado de la consulta
            Long count = query.getSingleResult();

            // Si el conteo es mayor que 0, el usuario existe
            return count > 0;
        } catch (Exception e) {
            System.err.println("Error al verificar la existencia del usuario: " + e.getMessage());
            return false; // En caso de error, asumimos que el usuario no existe
        } finally {
            // Cerramos el EntityManager para liberar recursos
            entityManager.close();
        }
    }
}