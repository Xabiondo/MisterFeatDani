package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // Configuración de la conexión a la base de datos
    private static final String DB_URL_SIN_DB = "jdbc:mysql://localhost:3306/"; // Sin base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fantasy_liga"; // Con base de datos
    private static final String USER = "root"; // Usuario de MySQL
    private static final String PASSWORD = "root"; // Contraseña de MySQL

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
     * Método para conectar sin seleccionar ninguna base de datos.
     */
    public static Connection connectSinDB() {
        try {
            return DriverManager.getConnection(DB_URL_SIN_DB, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar sin base de datos: " + e.getMessage());
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

        // Tablas y Inserts (tu código original)
        String crearTablaUsuarios = """
    CREATE TABLE IF NOT EXISTS usuarios (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
        contraseña VARCHAR(100) NOT NULL,
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
    id_usuario INT NULL,
    posicion varchar(30) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE SET NULL
);
""";
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
        String crearTablaPreguntas = """
CREATE TABLE IF NOT EXISTS preguntas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    enunciado VARCHAR(255) NOT NULL,
    respuesta VARCHAR(255) NOT NULL
);
""";
        String insertUsuario = """
    INSERT INTO usuarios (id, nombre, contraseña, rol, dinero)
    VALUES (4, 'admin', 'admin', 'admin', 10000);
""";

        String insertJugadores = """
    INSERT INTO jugadores (nombre, equipo, precio, id_usuario, posicion) VALUES
    ('Cristiano Ronaldo', 'Al-Nassr', 11500000, NULL, 'delantero'),
    ('Kylian Mbappé', 'PSG', 11000000, NULL, 'delantero'),
    ('Erling Haaland', 'Manchester City', 10500000, NULL, 'delantero'),
    ('Neymar Jr', 'Santos', 10000000, NULL, 'delantero'),
    ('Vinícius Júnior', 'Real Madrid', 9500000, NULL, 'delantero'),
    ('Jude Bellingham', 'Real Madrid', 9000000, NULL, 'centrocampista'),
    ('Kevin De Bruyne', 'Manchester City', 8500000, NULL, 'centrocampista'),
    ('Harry Kane', 'Bayern Munich', 8000000, NULL, 'delantero'),
    ('Mohamed Salah', 'Liverpool', 7800000, NULL, 'delantero'),
    ('Robert Lewandowski', 'FC Barcelona', 7600000, NULL, 'delantero'),
    ('Luka Modric', 'Real Madrid', 7400000, NULL, 'centrocampista'),
    ('Toni Kroos', 'Real Madrid', 7200000, NULL, 'centrocampista'),
    ('Rodri', 'Manchester City', 7000000, NULL, 'centrocampista'),
    ('Karim Benzema', 'Al-Ittihad', 6800000, NULL, 'delantero'),
    ('Heung-min Son', 'Tottenham Hotspur', 6600000, NULL, 'delantero'),
    ('Luis Suárez', 'Inter Miami', 6400000, NULL, 'delantero'),
    ('Pedri', 'FC Barcelona', 6200000, NULL, 'centrocampista'),
    ('Antoine Griezmann', 'Atlético de Madrid', 6000000, NULL, 'delantero'),
    ('Phil Foden', 'Manchester City', 5800000, NULL, 'centrocampista'),
    ('Gavi', 'FC Barcelona', 5600000, NULL, 'centrocampista'),
    ('Frenkie de Jong', 'FC Barcelona', 5400000, NULL, 'centrocampista'),
    ('João Cancelo', 'FC Barcelona', 5200000, NULL, 'defensa'),
    ('Bukayo Saka', 'Arsenal', 5000000, NULL, 'delantero'),
    ('Jamal Musiala', 'Bayern Munich', 4800000, NULL, 'centrocampista'),
    ('Rafael Leão', 'AC Milan', 4600000, NULL, 'delantero'),
    ('Marcus Rashford', 'Manchester United', 4400000, NULL, 'delantero'),
    ('Ilkay Gündogan', 'FC Barcelona', 4200000, NULL, 'centrocampista'),
    ('Casemiro', 'Manchester United', 4000000, NULL, 'centrocampista'),
    ('Jadon Sancho', 'Manchester United', 3800000, NULL, 'delantero'),
    ('Achraf Hakimi', 'PSG', 3600000, NULL, 'defensa'),
    ('Riyad Mahrez', 'Al-Ahli', 3400000, NULL, 'delantero'),
    ('Jules Koundé', 'FC Barcelona', 3200000, NULL, 'defensa'),
    ('Mats Hummels', 'Borussia Dortmund', 3000000, NULL, 'defensa'),
    ('Joshua Kimmich', 'Bayern Munich', 2800000, NULL, 'centrocampista'),
    ('Federico Valverde', 'Real Madrid', 2600000, NULL, 'centrocampista'),
    ('Eduardo Camavinga', 'Real Madrid', 2400000, NULL, 'centrocampista'),
    ('Aurélien Tchouaméni', 'Real Madrid', 2200000, NULL, 'centrocampista'),
    ('Alphonso Davies', 'Bayern Munich', 2000000, NULL, 'defensa'),
    ('Trent Alexander-Arnold', 'Liverpool', 1800000, NULL, 'defensa'),
    ('Virgil van Dijk', 'Liverpool', 1600000, NULL, 'defensa'),
    ('João Félix', 'FC Barcelona', 1400000, NULL, 'delantero'),
    ('Sergio Ramos', 'Sevilla', 1200000, NULL, 'defensa'),
    ('Gianluigi Donnarumma', 'PSG', 1000000, NULL, 'portero'),
    ('Jan Oblak', 'Atlético de Madrid', 900000, NULL, 'portero'),
    ('Thibaut Courtois', 'Real Madrid', 800000, NULL, 'portero'),
    ('Manuel Neuer', 'Bayern Munich', 700000, NULL, 'portero'),
    ('Marc-André ter Stegen', 'FC Barcelona', 600000, NULL, 'portero'),
    ('Pepe', 'Porto', 500000, NULL, 'defensa'),
    ('Gerard Piqué', 'Retirado', 400000, NULL, 'defensa');
""";

        String insertPreguntas = """
    INSERT INTO preguntas (enunciado, respuesta) VALUES
    ('¿Quién ganó el Balón de Oro en 2023?', 'Messi'),
    ('¿En qué equipo juega Jude Bellingham?', 'Real Madrid'),
    ('¿Cuántos mundiales tiene Brasil?', '5'),
    ('¿Equipo primero LaLiga 2024/25?', 'Barcelona'),
    ('¿Segundo clasificado LaLiga 2024/25?', 'Real Madrid'),
    ('¿Pichichi LaLiga 2024/25?', 'Lewandowski'),
    ('¿Primer Descendido LaLiga 2025?', 'Valladolid'),
    ('¿Balón de Oro 2024?', 'Rodri'),
    ('¿Estadio más grande LaLiga?', 'Camp Nou'),
    ('¿Más Champions?', 'Real Madrid'),
    ('¿Capitán Real Madrid 2025?', 'Carvajal'),
    ('¿Entrenador Barça 2025?', 'Flick'),
    ('¿Jugador más joven LaLiga? (Con apellido)', 'Lamine Yamal'),
    ('¿Equipo de Vinicius?', 'Real Madrid'),
    ('¿Equipo de Griezmann?', 'Atlético'),
    ('¿Entrenador de la Real? (sólo nombre)', 'Imanol'),
    ('¿Equipo más pechofrío de la liga?', 'Atlético'),
    ('¿Color camiseta Rayo?', 'Blanca'),
    ('¿Equipo de Isco 2025?', 'Betis'),
    ('¿Equipo de Aspas?', 'Celta'),
    ('¿Equipo de Kubo?', 'Real Sociedad'),
    ('¿Apodo Athletic Club? (una palabra)', 'Leones'),
    ('¿Ciudad del Cádiz CF?', 'Cádiz'),
    ('¿Color camiseta Osasuna?', 'Roja'),
    ('¿Palabra para método sin retorno en Java?', 'void'),
    ('¿Cómo declarar constante en JS?', 'const'),
    ('¿Operador AND en C?', '&&'),
    ('¿Comentario de línea en SQL?', '--'),
    ('¿Lenguaje compilado de Google?', 'Go'),
    ('¿Extensión de archivo Python?', '.py'),
    ('¿Función básica de Python?', 'print()'),
    ('¿Tipo de dato flotante en C?', 'float'),
    ('¿Cómo importar paquete en Java?', 'import'),
    ('¿Palabra clave herencia en Java?', 'extends'),
    ('¿Qué devuelve len("hola") en Py?', '4'),
    ('¿Bucle que itera conocido?', 'for'),
    ('¿Cómo terminar comentario en C?', '*/'),
    ('¿Operador no igual en Java?', '!='),
    ('¿Bloque tras try en Java?', 'finally'),
    ('¿Paquete básico de Java?', 'java.lang'),
    ('¿Palabra para clase en Java?', 'class'),
    ('¿Cómo abrir arreglo en JS?', '[]'),
    ('¿Palabra clave salida en C?', 'return'),
    ('¿Operador asignación en C?', '='),
    ('¿Cómo definir función en C?', 'void'),
    ('¿Framework Java popular?', 'Spring'),
    ('¿Qué significa CPU?', 'Procesador'),
    ('¿Qué es RAM?', 'Memoria'),
    ('¿Qué es ROM?', 'Solo lectura'),
    ('¿Significado de LAN?', 'Red local'),
    ('¿Puerto estándar HTTP?', '80'),
    ('¿Puerto estándar SSH?', '22'),
    ('¿Función de DNS?', 'Resuelve nombres'),
    ('¿Protocolo para correo saliente?', 'SMTP');
""";
        try (Connection connSinDB = connectSinDB();
             Statement stmt = connSinDB.createStatement()) {

            // Paso 1: Crear la base de datos si no existe
            stmt.executeUpdate(crearBaseDeDatos);

            // Paso 2: Usar la base de datos
            stmt.executeUpdate(usarBaseDeDatos);

        } catch (SQLException e) {
            System.err.println("Error al preparar la base de datos: " + e.getMessage());
        }

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            // Paso 3: Crear las tablas si no existen
            stmt.executeUpdate(crearTablaUsuarios);
            stmt.executeUpdate(crearTablaJugadores);
            stmt.executeUpdate(crearTablaSubastas);
            stmt.executeUpdate(crearTablaPujas);
            stmt.executeUpdate(crearTablaPreguntas);

            // Insertar datos iniciales
            stmt.executeUpdate(insertUsuario);
            stmt.executeUpdate(insertJugadores);
            stmt.executeUpdate(insertPreguntas);

            System.out.println("✔ Base de datos y tablas creadas correctamente.");
            System.out.println("✔ Datos iniciales insertados.");

        } catch (SQLException e) {
            System.err.println("❌ Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    // Para probar directamente desde aquí
    public static void main(String[] args) {
        DatabaseManager.inicializarBaseDeDatos();
    }
}