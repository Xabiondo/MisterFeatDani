package org.example;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinFreemarker;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Inicializar la base de datos
        DatabaseManager.initializeDatabase();

        // Crear configuración de FreeMarker
        Configuration freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        try {
            // Configurar el directorio de plantillas
            freemarkerConfig.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        } catch (Exception e) {
            System.err.println("Error al configurar el directorio de plantillas: " + e.getMessage());
            e.printStackTrace();
            return; // Detener la ejecución si hay un error
        }
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        freemarkerConfig.setLogTemplateExceptions(false);
        freemarkerConfig.setWrapUncheckedExceptions(true);

        // Crear la aplicación Javalin
        Javalin app = Javalin.create(config -> {
            config.fileRenderer(new JavalinFreemarker(freemarkerConfig));
        }).start(7070);

        // Ruta para mostrar el formulario de empleado
        app.get("/", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Formulario de Empleado");
            ctx.render("form.ftl", model); // Renderiza la plantilla "form.ftl"
        });

        // Ruta para procesar el formulario de empleado
        app.post("/submit", ctx -> {
            // Obtener los datos del formulario
            String nombre = ctx.formParam("nombre");
            String sueldoStr = ctx.formParam("sueldo");
            String contrato = ctx.formParam("contrato");
            String departamento = ctx.formParam("departamento");

            // Validar que los campos no estén vacíos
            if (nombre == null || nombre.isEmpty() || sueldoStr == null || sueldoStr.isEmpty() ||
                    contrato == null || contrato.isEmpty() || departamento == null || departamento.isEmpty()) {
                ctx.result("Por favor, completa todos los campos.");
                return;
            }

            // Convertir el sueldo a double
            double sueldo;
            try {
                sueldo = Double.parseDouble(sueldoStr);
            } catch (NumberFormatException e) {
                ctx.result("El sueldo debe ser un número válido.");
                return;
            }

            // Registrar al empleado en la base de datos
            boolean registrado = DatabaseManager.registerEmpleado(nombre, sueldo, contrato, departamento);

            // Responder al usuario según el resultado
            if (registrado) {
                ctx.result("¡Empleado registrado exitosamente!");
            } else {
                ctx.result("Error al registrar empleado. Inténtalo de nuevo.");
            }
        });
    }
}