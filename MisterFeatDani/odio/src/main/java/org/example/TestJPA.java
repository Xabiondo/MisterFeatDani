package org.example;

import jakarta.persistence.*;

public class TestJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.example");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        System.out.println("Conexión exitosa");
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}