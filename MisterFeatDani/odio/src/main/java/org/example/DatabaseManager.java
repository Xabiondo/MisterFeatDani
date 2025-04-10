package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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

    /**
     * Método para inicializar la base de datos (crear las tablas necesarias).
     */
    public static void initializeDatabase() {
        // SQL para crear las tablas
        String createUsuariosTable = """
                CREATE TABLE IF NOT EXISTS usuarios (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(50) NOT NULL,
                            contraseña VARCHAR(255) NOT NULL,
                            rol ENUM('admin', 'usuario') NOT NULL DEFAULT 'usuario'
                        
            );
        """;

        String createJugadoresTable = """
            CREATE TABLE IF NOT EXISTS jugadores (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(50) NOT NULL,
                equipo VARCHAR(50) NOT NULL,
                precio DECIMAL(10, 2) NOT NULL
            );
        """;

        String createUsuarioJugadorTable = """
            CREATE TABLE IF NOT EXISTS usuario_jugador (
                id INT AUTO_INCREMENT PRIMARY KEY,
                usuario_id INT NOT NULL,
                jugador_id INT NOT NULL,
                FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                FOREIGN KEY (jugador_id) REFERENCES jugadores(id)
            );
        """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createUsuariosTable);
            stmt.execute(createJugadoresTable);
            stmt.execute(createUsuarioJugadorTable);

            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    /**
     * Método para registrar un nuevo usuario.
     */
    public static boolean registerUsuario(String nombre, String contraseña) {
        String sql = "INSERT INTO usuarios (nombre, contraseña) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, contraseña);
            pstmt.executeUpdate();
            return true; // Registro exitoso
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false; // Error al registrar
        }
    }
    public static boolean existeUsuario(String nombre, String contraseña) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre = ? AND contraseña = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Asignar los parámetros de la consulta
            pstmt.setString(1, nombre);
            pstmt.setString(2, contraseña);

            // Ejecutar la consulta
            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); // Obtener el número de coincidencias
                    return count > 0; // Devuelve true si hay al menos una coincidencia
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia del usuario: " + e.getMessage());
        }

        return false; // En caso de error o si no se encuentra el usuario
    }
    /**
     * Método para registrar un nuevo jugador.
     */
    public static boolean registerJugador(String nombre, String equipo, double precio) {
        String sql = "INSERT INTO jugadores (nombre, equipo, precio) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, equipo);
            pstmt.setDouble(3, precio);
            pstmt.executeUpdate();
            return true; // Registro exitoso
        } catch (SQLException e) {
            System.err.println("Error al registrar jugador: " + e.getMessage());
            return false; // Error al registrar
        }
    }

    /**
     * Método para asociar un jugador con un usuario.
     */
    public static boolean asociarJugadorAUsuario(int usuarioId, int jugadorId) {
        String sql = "INSERT INTO usuario_jugador (usuario_id, jugador_id) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, jugadorId);
            pstmt.executeUpdate();
            return true; // Asociación exitosa
        } catch (SQLException e) {
            System.err.println("Error al asociar jugador con usuario: " + e.getMessage());
            return false; // Error al asociar
        }
    }
}