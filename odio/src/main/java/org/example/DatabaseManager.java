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
    public static void inicializarBaseDeDatos() {
        String crearTablaUsuarios = """
        CREATE TABLE IF NOT EXISTS usuario (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(100) NOT NULL,
            contrasena VARCHAR(100) NOT NULL,
            rol VARCHAR(50) DEFAULT 'usuario',
            dinero DOUBLE DEFAULT 1000000
        );
    """;

        String crearTablaJugadores = """
        CREATE TABLE IF NOT EXISTS jugadores (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(100) NOT NULL,
            equipo VARCHAR(100) NOT NULL,
            precio DOUBLE NOT NULL,
            id_usuario INT,
            FOREIGN KEY (id_usuario) REFERENCES usuario(id)
        );
    """;

        try (Connection conn = connect();
             var stmt = conn.createStatement()) {
            stmt.executeUpdate(crearTablaUsuarios);
            stmt.executeUpdate(crearTablaJugadores);
            System.out.println("Tablas creadas correctamente (si no existían).");
        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }

}