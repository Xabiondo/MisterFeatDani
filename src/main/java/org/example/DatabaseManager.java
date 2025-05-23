package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // Configuración de la conexión a la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fantasy_liga"; // URL base sin especificar la base de datos
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
     * Método para inicializar la base de datos y crear las tablas necesarias.
     */
    public static void inicializarBaseDeDatos() {
        // Crear la base de datos si no existe
        String crearBaseDeDatos = "CREATE DATABASE IF NOT EXISTS fantasy_liga;";
        String usarBaseDeDatos = "USE fantasy_liga;";

        // Crear la tabla usuarios
        String crearTablaUsuarios = """
    CREATE TABLE IF NOT EXISTS usuarios (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
        contraseña VARCHAR(100) NOT NULL,
        rol VARCHAR(50) DEFAULT 'usuario',
        dinero DOUBLE DEFAULT 1000000
    );
    """;

        // Crear la tabla jugadores
        String crearTablaJugadores = """
CREATE TABLE IF NOT EXISTS jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    equipo VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    id_usuario INT NULL,
    posicion varchar(30) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE SET NULL
);
""";

        // Crear la tabla subastas
        String crearTablaSubastas = """
CREATE TABLE IF NOT EXISTS subastas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_jugador INT NOT NULL,
    precioSalida DOUBLE NOT NULL DEFAULT 1000000,
    estaActiva BOOLEAN DEFAULT TRUE,
    fechaInicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fechaFin TIMESTAMP NULL,
    id_vendedor INT NULL,
    FOREIGN KEY (id_jugador) REFERENCES jugadores(id),
    FOREIGN KEY (id_vendedor) REFERENCES usuarios(id) ON DELETE SET NULL
);
""";

        // Crear la tabla pujas
        String crearTablaPujas = """
    CREATE TABLE IF NOT EXISTS pujas (
        id INT AUTO_INCREMENT PRIMARY KEY,
        id_subasta INT NOT NULL,
        id_usuario INT NOT NULL,
        cantidad DOUBLE NOT NULL,
        fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (id_subasta) REFERENCES subastas(id),
        FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
    );
    """;

        // Crear la tabla preguntas
        String crearTablaPreguntas = """
CREATE TABLE IF NOT EXISTS preguntas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    enunciado VARCHAR(255) NOT NULL,
    respuesta VARCHAR(255) NOT NULL
);
""";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            // Paso 1: Crear la base de datos si no existe
            stmt.executeUpdate(crearBaseDeDatos);
            System.out.println("Base de datos 'fantasy_liga' creada correctamente (si no existía).\n");

            // Paso 2: Usar la base de datos
            stmt.executeUpdate(usarBaseDeDatos);
            System.out.println("Usando la base de datos 'fantasy_liga'.\n");

            // Paso 3: Crear las tablas si no existen
            stmt.executeUpdate(crearTablaUsuarios);
            stmt.executeUpdate(crearTablaJugadores);
            stmt.executeUpdate(crearTablaSubastas);
            stmt.executeUpdate(crearTablaPujas);
            stmt.executeUpdate(crearTablaPreguntas);
            System.out.println("Tablas creadas correctamente (si no existían).\n");

        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}
