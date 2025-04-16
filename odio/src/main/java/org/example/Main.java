package org.example;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.rendering.template.JavalinFreemarker;
import freemarker.template.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Inicializar la base de datos


        DatabaseManager.connect();

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
            // Obtener los parámetros del formulario
            String nombre = ctx.formParam("usuario");
            String password = ctx.formParam("password");

            // Validar que los campos no estén vacíos
            if (nombre == null || nombre.isEmpty() || password == null || password.isEmpty()) {
                ctx.result("Por favor, completa todos los campos.");
                return;
            }

            // Crear una instancia del DAO
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Verificar si el usuario existe en la base de datos
            if (usuarioDAO.existeUsuario(nombre, password)) {
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

            // Obtener los datos del usuario desde la base de datos
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerPorNombre(nombre);

            if (usuario == null) {
                ctx.result("Error: Usuario no encontrado.");
                return;
            }

            // Crear el modelo con los datos del usuario
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Fantasy Liga - Interfaz");
            model.put("nombreUsuario", usuario.getNombre());
            model.put("dineroDisponible", usuario.getDinero());

            // Renderizar la página principal con los datos del usuario
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
            // Obtener los parámetros del formulario
            String nombre = ctx.formParam("usuario");
            String password = ctx.formParam("password");

            // Validar que los campos no estén vacíos
            if (nombre == null || nombre.isEmpty() || password == null || password.isEmpty()) {
                ctx.result("Por favor, completa todos los campos.");
                return;
            }

            // Crear una instancia del DAO
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Verificar si el usuario ya está registrado
            if (usuarioDAO.estaRegistrado(nombre)) {
                ctx.result("El nombre de usuario ya está en uso.");
                return;
            }

            // Registrar al usuario en la base de datos
            boolean registrado = usuarioDAO.estaRegistrado(nombre);

            // Respuesta al usuario
            if (!registrado) {
                usuarioDAO.guardar(new Usuario(nombre, password));
                ctx.result("El usuario se ha registrado correctamente.");
                ctx.redirect("/");
            } else {
                ctx.result("Error al registrar usuario. Inténtalo de nuevo.");
            }
        });



        app.get("/Mercado", ctx -> {
            // Obtener todos los jugadores desde la base de datos
            JugadorDAO jugadorDAO = new JugadorDAO();
            List<Jugador> jugadores = jugadorDAO.obtenerTodosLosJugadores();

            // Crear el modelo para FreeMarker
            Map<String, Object> model = new HashMap<>();
            model.put("jugadores", jugadores);

            // Renderizar la plantilla Mercado.ftl
            ctx.render("Mercado.ftl", model);
        });



       // JugadorDAO dao = new JugadorDAO();
        // Crear un nuevo jugador
        //Jugador jugador = new Jugador("Lionel Messi", "PSG", 100000000);

        // Guardar el jugador en la base de datos
      //  System.out.println("Guardando jugador...");
      //  dao.guardar(jugador);

        Handler authMiddleware = ctx -> {
            String nombre = ctx.sessionAttribute("nombre");
            if (nombre == null) {
                ctx.redirect("/");
            }
        };

// Aplicar el middleware a rutas protegidas



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