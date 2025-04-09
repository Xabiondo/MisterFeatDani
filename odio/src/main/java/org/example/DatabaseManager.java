package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/empleados_db"; // URL de MySQL
    private static final String USER = "root"; // Usuario de MySQL
    private static final String PASSWORD = "root"; // Contraseña de MySQL (cámbiala si tienes una)

    // Método para conectar a la base de datos
    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }

    // Método para inicializar la base de datos (crear la tabla)
    public static void initializeDatabase() {
        String sql = """
            CREATE TABLE IF NOT EXISTS empleados (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(255) NOT NULL,
                sueldo DECIMAL(10, 2) NOT NULL,
                contrato VARCHAR(50) NOT NULL,
                departamento VARCHAR(100) NOT NULL
            );
        """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Base de datos inicializada.");
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    // Método para registrar un empleado
    public static boolean registerEmpleado(String nombre, double sueldo, String contrato, String departamento) {
        String sql = "INSERT INTO empleados (nombre, sueldo, contrato, departamento) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, sueldo);
            pstmt.setString(3, contrato);
            pstmt.setString(4, departamento);
            pstmt.executeUpdate();
            return true; // Registro exitoso
        } catch (SQLException e) {
            System.err.println("Error al registrar empleado: " + e.getMessage());
            return false; // Error al registrar
        }
    }
}