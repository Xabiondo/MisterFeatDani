package org.example;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.rendering.template.JavalinFreemarker;
import freemarker.template.Configuration;

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

        // Crear la aplicación Javalin
        Javalin app = Javalin.create(config -> {
            config.fileRenderer(new JavalinFreemarker(freemarkerConfig));
        }).start(7070);

        // Ruta principal: Mostrar el formulario de login
        app.get("/", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Bienvenido a Fantasy Liga");
            ctx.render("Login.ftl", model); // Renderiza la plantilla "Login.ftl"
        });

        app.post("/login", ctx -> {
            String nombre = ctx.formParam("usuario");
            String password = ctx.formParam("password");

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


                ctx.redirect("/interfaz");

            } else {
                ctx.result("Nombre de usuario o contraseña incorrectos.");
            }
        });
        app.get("/interfaz", ctx -> {
            // Verificar si el usuario tiene una sesión activa
            String nombre = ctx.sessionAttribute("nombre");
            if (nombre == null) {
                // Si no hay sesión activa, redirigir al inicio de sesión
                ctx.redirect("/");
                return;
            }

            // Renderizar la página principal
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Fantasy Liga - Interfaz");
            model.put("usuario", nombre);
            ctx.render("Interface.ftl", model);
        });

        // Ruta GET para mostrar el formulario de registro
        app.get("/register", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Registro de Usuario");
            ctx.render("Register.ftl", model); // Renderiza la plantilla "Register.ftl"
        });

        // Ruta POST para procesar el formulario de registro
        app.post("/register", ctx -> {
            String nombre = ctx.formParam("usuario");
            String password = ctx.formParam("password");

            // Validar que los campos no estén vacíos
            if (nombre == null || nombre.isEmpty() || password == null || password.isEmpty()) {
                ctx.result("Por favor, completa todos los campos.");
                return;
            }

            // Registrar al usuario en la base de datos
            boolean registrado = DatabaseManager.registerUsuario(nombre, password);

            if (registrado) {
                ctx.result("El usuario se ha registrado correctamente.");
                ctx.redirect("/");
            } else {
                ctx.result("Error al registrar usuario. Inténtalo de nuevo.");
            }
        });
        JugadorDAO dao = new JugadorDAO();

        // Crear un nuevo jugador
        Jugador jugador = new Jugador("Lionel Messi", "PSG", 100000000);

        // Guardar el jugador en la base de datos
        System.out.println("Guardando jugador...");
        dao.guardar(jugador);

        Handler authMiddleware = ctx -> {
            String nombre = ctx.sessionAttribute("nombre");
            if (nombre == null) {
                ctx.redirect("/");
            }
        };

// Aplicar el middleware a rutas protegidas
        app.before("/interfaz", authMiddleware);


        /*// Ruta GET para "Poner jugador a subasta" (protegida)
        app.get("/poner-subasta", ctx -> {
            String nombre = ctx.sessionAttribute("nombre");

            Map<String, Object> model = new HashMap<>();
            model.put("title", "Poner Jugador a Subasta");
            model.put("nombreUsuario", nombre);

            ctx.render("poner-subasta.ftl", model);
        });

        // Ruta GET para "Pujar por un jugador" (protegida)
        app.get("/pujar-jugador", ctx -> {
            String nombre = ctx.sessionAttribute("nombre");

            Map<String, Object> model = new HashMap<>();
            model.put("title", "Pujar por un Jugador");
            model.put("nombreUsuario", nombre);

            ctx.render("pujar-jugador.ftl", model);
        });

        // Ruta GET para "Ver mi equipo" (protegida)
        app.get("/ver-equipo", ctx -> {
            String nombre = ctx.sessionAttribute("nombre");

            Map<String, Object> model = new HashMap<>();
            model.put("title", "Ver Mi Equipo");
            model.put("nombreUsuario", nombre);

            ctx.render("ver-equipo.ftl", model);
        });

        // Ruta GET para "Ver mi dinero disponible" (protegida)
        app.get("/ver-dinero", ctx -> {
            String nombre = ctx.sessionAttribute("nombre");

            Map<String, Object> model = new HashMap<>();
            model.put("title", "Ver Mi Dinero Disponible");
            model.put("nombreUsuario", nombre);

            ctx.render("ver-dinero.ftl", model);
        });

        // Ruta GET para cerrar sesión
        app.get("/logout", ctx -> {
            ctx.sessionAttribute("nombre", null);
            ctx.sessionAttribute("password", null);
            ctx.redirect("/");
        });*/
    }
}