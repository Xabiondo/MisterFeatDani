package org.example;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinFreemarker;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Inicializar la base de datos
        DatabaseManager.connect();
        DatabaseManager.initializeDatabase();

        // Crear configuración de FreeMarker
        Configuration freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        try {
            // Configurar el directorio de plantillas
            freemarkerConfig.setClassForTemplateLoading(Main.class, "/templates");
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

        // Ruta principal: Mostrar enlaces a las funcionalidades
        app.get("/", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Bienvenido a Fantasy Liga");
            ctx.render("form.ftl", model); // Renderiza la plantilla "form.ftl"

        });

        app.post("/inicio", ctx -> {
            String nombre = ctx.formParam("usuario"); // Obtener el nombre de usuario del formulario
            String password = ctx.formParam("password"); // Obtener la contraseña del formulario

            // Validar que los campos no estén vacíos
            if (nombre == null || nombre.isEmpty() || password == null || password.isEmpty()) {
                ctx.result("Por favor, completa todos los campos.");
                return;
            }

            // Verificar si el usuario existe en la base de datos
            if (DatabaseManager.existeUsuario(nombre, password)) {
                // Guardar los datos del usuario en la sesión
                ctx.sessionAttribute("nombre", nombre);
                ctx.sessionAttribute("password", password);


                // Redirigir al usuario a la página principal
                ctx.redirect("/inicio");
            } else {
                // Mostrar un mensaje de error si las credenciales son incorrectas
                ctx.result("Nombre de usuario o contraseña incorrectos.");
            }
        });

        app.get("/inicio", ctx -> {
            // Obtener los datos del usuario desde la sesión
            String nombre = ctx.sessionAttribute("nombre");
            String password = ctx.sessionAttribute("password");

            // Verificar si el usuario está autenticado
            if (nombre != null && password != null && DatabaseManager.existeUsuario(nombre, password)) {
                // Crear el modelo para la plantilla
                Map<String, Object> model = new HashMap<>();
                model.put("title", "Página Principal");
                model.put("nombreUsuario", nombre); // Pasar el nombre del usuario a la plantilla

                // Renderizar la plantilla "inicio.ftl"
                ctx.render("inicio.ftl", model);
            } else {
                // Redirigir al usuario a la página de login si no está autenticado
                ctx.redirect("/login");
            }
        });


        // Ruta para mostrar el formulario de registro de usuario
        app.get("/registro", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Registro de Usuario");
            ctx.render("registro.ftl", model);
        });

        // Ruta para procesar el formulario de registro de usuario
        app.post("/registro", ctx -> {


        });
    }
}