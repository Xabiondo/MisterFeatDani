package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // Configuración de la conexión a la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fantasy_liga"; // URL de MySQL
    private static final String USER = "root"; // Usuario de MySQL
    private static final String PASSWORD = "root"; // Contraseña de MySQL (cámbiala si es necesario)

    /**
     * Método para conectar a la base de datos.
     */
    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}