package org.example;

import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) {
        // Inicializar la base de datos
        DatabaseManager.connect();
        DatabaseManager.initializeDatabase();

        // Prueba 1: Registrar un usuario
        System.out.println("Prueba 1: Registrar un usuario...");
        boolean registrado = DatabaseManager.registerUsuario("testUser", "testPassword");
        if (registrado) {
            System.out.println("Usuario registrado correctamente.");
        } else {
            System.out.println("Error al registrar el usuario.");
        }

        // Prueba 2: Verificar si un usuario existe
        System.out.println("\nPrueba 2: Verificar si un usuario existe...");
        boolean existe = DatabaseManager.existeUsuario("testUser", "testPassword");
        if (existe) {
            System.out.println("El usuario existe en la base de datos.");
        } else {
            System.out.println("El usuario no existe en la base de datos.");
        }

        // Prueba 3: Verificar credenciales incorrectas
        System.out.println("\nPrueba 3: Verificar credenciales incorrectas...");
        boolean credencialesIncorrectas = DatabaseManager.existeUsuario("testUser", "wrongPassword");
        if (credencialesIncorrectas) {
            System.out.println("ERROR: El sistema permite acceso con credenciales incorrectas.");
        } else {
            System.out.println("El sistema bloquea correctamente el acceso con credenciales incorrectas.");
        }

        // Prueba 4: Eliminar un usuario (opcional)
        System.out.println("\nPrueba 4: Eliminar un usuario...");
        try {
            String deleteQuery = "DELETE FROM usuarios WHERE nombre = ?";
            var conn = DatabaseManager.connect();
            var stmt = conn.prepareStatement(deleteQuery);
            stmt.setString(1, "testUser");
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún usuario para eliminar.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        }

        // Cerrar la conexión a la base de datos
     
    }
}