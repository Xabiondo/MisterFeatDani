package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class JugadorDAO {
    private SessionFactory sessionFactory;

    public JugadorDAO() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Jugador.class)
                .buildSessionFactory();
    }

    public void guardar(Jugador jugador) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(jugador); // Usa persist() para insertar
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al guardar jugador: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Jugador buscarPorId(int id) {
        Session session = sessionFactory.openSession();
        Jugador jugador = session.get(Jugador.class, id);
        session.close();
        return jugador;
    }

    public List<Jugador> listarTodos() {
        Session session = sessionFactory.openSession();
        List<Jugador> jugadores = session.createQuery("FROM Jugador", Jugador.class).list();
        session.close();
        return jugadores;
    }

    public void actualizar(Jugador jugador) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.merge(jugador); // Usa merge() para actualizar
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al actualizar jugador: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void eliminar(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Jugador jugador = session.get(Jugador.class, id);
            if (jugador != null) {
                session.remove(jugador); // Elimina el jugador
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar jugador: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void cerrar() {
        sessionFactory.close();
    }
}