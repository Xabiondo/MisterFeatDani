package org.example;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinFreemarker;
import freemarker.template.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Inicializar la base de datos
        DatabaseManager.inicializarBaseDeDatos();
        //tienes que tener el workbench la base de datos creada, este metodo solo crea las tablas
        // create database fantasy_liga


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
            Usuario usuario = usuarioDAO.obtenerPorNombre(nombre);

            // Verificar si el usuario existe en la base de datos
            if (usuarioDAO.existeUsuario(nombre, password)) {
                // Guardar los datos del usuario en la sesión
                UsuarioDAO user = new UsuarioDAO();
                int idUser = user.obtenerIdPorNombre(nombre);

                ctx.sessionAttribute("nombre", nombre);
                ctx.sessionAttribute("password", password);
                ctx.sessionAttribute("idUsuario"  , idUser);
                ctx.sessionAttribute("rol", usuario.getRol());

                if (ctx.sessionAttribute("rol").equals("admin")){
                    ctx.redirect("/admin");
                    return;
                }

                // Redirigir al usuario a la página principal
                ctx.redirect("/interfaz");
            } else {
                ctx.result("Nombre de usuario o contraseña incorrectos.");
            }
        });

        app.get("/admin", ctx -> {
            // Verifica si hay sesión y si el usuario es "admin"
            String nombre = ctx.sessionAttribute("nombre");
            if (nombre == null || !nombre.equals("admin")) {
                ctx.status(403).result("Acceso denegado: solo disponible para el administrador.");
                return;
            }

            // Carga todos los usuarios
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();

            // Prepara el modelo para la plantilla
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Panel de Administración");
            model.put("usuarios", usuarios);

            // Renderiza la plantilla de administración (crea Admin.ftl)
            ctx.render("Admin.ftl", model);
        });
        app.post("/admin/update/{id}", ctx -> {
            String nombreAdmin = ctx.sessionAttribute("nombre");
            // Puedes mejorar el control de privilegios si tienes roles
            if (nombreAdmin == null /* || !esAdmin(nombreAdmin) */) {
                ctx.status(403).result("Acceso denegado");
                return;
            }
            int idUsuario = Integer.parseInt(ctx.pathParam("id"));
            String dineroStr = ctx.formParam("dinero");
            double dinero;
            try {
                dinero = Double.parseDouble(dineroStr);
            } catch (NumberFormatException e) {
                ctx.result("Cantidad no válida");
                return;
            }
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerPorId(idUsuario);
            if (usuario == null) {
                ctx.result("Usuario no encontrado");
                return;
            }
            usuario.setDinero((int) dinero);
            usuarioDAO.actualizar(usuario);
            ctx.redirect("/admin?mensaje=Dinero%20actualizado");
        });


        app.post("/admin/delete/{id}", ctx -> {
            // Verificar que el usuario es admin
            String nombre = ctx.sessionAttribute("nombre");
            if (nombre == null || !nombre.equals("admin")) {
                ctx.status(403).result("Acceso denegado");
                return;
            }

            int userId = Integer.parseInt(ctx.pathParam("id"));

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            JugadorDAO jugadorDAO = new JugadorDAO();

            // Quitar la FK de los jugadores de este usuario
            jugadorDAO.quitarUsuarioDeJugadores(userId);

            // Eliminar el usuario
            usuarioDAO.eliminar(userId);

            // Redirigir al panel de administración
            ctx.redirect("/admin");
        });
        app.post("/admin/create", ctx -> {
            String nombreAdmin = ctx.sessionAttribute("nombre");
            // Puedes mejorar el control de privilegios si tienes roles
            if (nombreAdmin == null /* || !esAdmin(nombreAdmin) */) {
                ctx.status(403).result("Acceso denegado");
                return;
            }
            String nombre = ctx.formParam("nombre");
            String password = ctx.formParam("password");
            String rol = ctx.formParam("rol");

            if (nombre == null || nombre.isEmpty() || password == null || password.isEmpty() || rol == null || rol.isEmpty()) {
                ctx.result("Por favor, completa todos los campos.");
                return;
            }
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.estaRegistrado(nombre)) {
                ctx.result("El nombre de usuario ya está en uso.");
                return;
            }
            Usuario nuevoUsuario = new Usuario(nombre, password, rol);
            usuarioDAO.guardar(nuevoUsuario);
            ctx.redirect("/admin?mensaje=Usuario%20creado");
        });



// GET: Muestra la interfaz con el dinero y el botón
        app.get("/Preguntas", ctx -> {
            String nombre = ctx.sessionAttribute("nombre");
            if (nombre == null) {
                ctx.redirect("/");
                return;
            }
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerPorNombre(nombre);

            // Obtener una pregunta aleatoria de la base de datos
            PreguntaDAO preguntaDAO = new PreguntaDAO();
            Pregunta pregunta = preguntaDAO.obtenerPreguntaAleatoria();
            if (pregunta == null) {
                ctx.result("No hay preguntas en la base de datos.");
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("title", "Fantasy Liga - Pregunta para ganar dinero");
            model.put("nombreUsuario", usuario.getNombre());
            model.put("dineroDisponible", usuario.getDinero());
            model.put("pregunta", pregunta); // Pasa el objeto pregunta al FTL
            model.put("mensaje", ctx.queryParam("mensaje"));
            ctx.render("preguntas.ftl", model);
        });
// POST: Suma dinero y recarga la interfaz
        app.post("/Preguntas", ctx -> {
            String nombre = ctx.sessionAttribute("nombre");
            if (nombre == null) {
                ctx.redirect("/");
                return;
            }
            int preguntaId = Integer.parseInt(ctx.formParam("id"));
            String respuestaUsuario = ctx.formParam("respuesta");

            PreguntaDAO preguntaDAO = new PreguntaDAO();
            Pregunta pregunta = preguntaDAO.obtenerPorId(preguntaId);

            boolean acertada = pregunta.getRespuesta().equalsIgnoreCase(respuestaUsuario.trim());

            String mensaje;
            if (acertada) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.obtenerPorNombre(nombre);
                usuario.setDinero(usuario.getDinero() + 1000);
                usuarioDAO.actualizar(usuario);
                mensaje = "¡Correcto! Has ganado 1000 euros.";
            } else {
                mensaje = "Incorrecto. Inténtalo de nuevo.";
            }
            ctx.redirect("/Preguntas?mensaje=" + java.net.URLEncoder.encode(mensaje, "UTF-8"));
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
            JugadorDAO jugadorDAO = new JugadorDAO();

            // Verificar si el usuario ya está registrado
            if (usuarioDAO.estaRegistrado(nombre)) {
                ctx.result("El nombre de usuario ya está en uso.");
                return;
            }

            // Registrar al usuario en la base de datos
            Usuario nuevoUsuario = new Usuario(nombre, password);
            usuarioDAO.guardar(nuevoUsuario);

            // Obtener el ID del usuario recién registrado
            Integer usuarioId = usuarioDAO.obtenerIdPorNombre(nombre);

            if (usuarioId == null) {
                ctx.result("Error al obtener el ID del usuario.");
                return;
            }

            // Asignar 5 jugadores aleatorios al usuario
            try {
                jugadorDAO.asignarJugadoresAleatoriosAUsuario(usuarioId);
                ctx.result("El usuario se ha registrado correctamente y se le han asignado 5 jugadores.");
                ctx.redirect("/");
            } catch (Exception e) {
                ctx.result("Error al asignar jugadores al usuario: " + e.getMessage());
            }
        });

        // Añado comentario para git



        app.get("/Mercado", ctx -> {
            // Obtener todas las subastas activas


            List<Subasta> subastasActivas = SubastaDAO.obtenerSubastasActivas();

            // Crear el modelo para FreeMarker
            Map<String, Object> model = new HashMap<>();
            model.put("subastas", subastasActivas);

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            Usuario usuario = usuarioDAO.obtenerPorNombre(ctx.sessionAttribute("nombre"));
            if (usuario != null) {
                model.put("nombreUsuario", usuario.getNombre());
                model.put("dineroDisponible", usuario.getDinero());
            }

            // Renderizar la plantilla Mercado.ftl
            ctx.render("Mercado.ftl", model);
        });
        app.post("/Mercado", ctx -> {
            String nombreUsuario = ctx.sessionAttribute("nombre");
            Integer idUsuario = ctx.sessionAttribute("idUsuario");
            if (nombreUsuario == null || idUsuario == null) {
                ctx.redirect("/");
                return;
            }

            String subastaIdStr = ctx.formParam("subastaId");
            if (subastaIdStr == null || subastaIdStr.isEmpty()) {
                ctx.result("Error: ID de subasta no proporcionado.");
                return;
            }

            int subastaId;
            try {
                subastaId = Integer.parseInt(subastaIdStr);
            } catch (NumberFormatException e) {
                ctx.result("Error: ID de subasta inválido.");
                return;
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario comprador = usuarioDAO.obtenerPorId(idUsuario);
            if (comprador == null) {
                ctx.result("Error: Usuario no encontrado.");
                return;
            }

            Subasta subasta = SubastaDAO.obtenerPorId(subastaId);
            if (subasta == null) {
                ctx.result("Error: La subasta no existe.");
                return;
            }
            Jugador jugador = subasta.getJugador();

            if (comprador.getDinero() < subasta.getPrecioSalida()) {
                ctx.result("No tienes suficiente dinero para comprar este jugador.");
                return;
            }

            // 1. Actualizar el dinero del comprador
            comprador.setDinero((int) (comprador.getDinero() - subasta.getPrecioSalida()));
            usuarioDAO.actualizar(comprador);

            // 2. Cambiar el dueño del jugador y romper la relación con la subasta
            JugadorDAO jugadorDAO = new JugadorDAO();
            jugador.setUsuario(comprador);
            jugador.setSubasta(null); // Rompe la relación
            jugadorDAO.actualizar(jugador);

            // 3. Eliminar la subasta
            SubastaDAO.eliminar(4);

            ctx.redirect("/Mercado?mensaje=Jugador%20comprado%20correctamente");
        });





        app.get("/buscar", ctx -> {
            String nombreJugador = ctx.queryParam("nombre"); // Lee el parámetro de la URL
            List<Subasta> resultados = new ArrayList<>();
            if (nombreJugador != null && !nombreJugador.trim().isEmpty()) {
                resultados = SubastaDAO.buscarPorNombreJugador(nombreJugador.trim());
            }
            Map<String, Object> model = new HashMap<>();
            model.put("subastas", resultados);
            ctx.render("Mercado.ftl", model); // Reutiliza la plantilla para mostrar los resultados
        });



      /*  Handler authMiddleware = ctx -> {
            String nombre = ctx.sessionAttribute("nombre");
            if (nombre == null) {
                ctx.redirect("/");
            }
        };*/


        // Ruta GET para "Poner jugador a subasta" (protegida)
        app.get("/poner-subasta", ctx -> {
            // Verificar si el usuario tiene una sesión activa
            String nombreUsuario = ctx.sessionAttribute("nombre");
            if (nombreUsuario == null) {
                ctx.redirect("/");
                return;
            }

            // Obtener el ID del usuario logueado
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Integer idUsuario = usuarioDAO.obtenerIdPorNombre(nombreUsuario);

            if (idUsuario == null) {
                ctx.result("Error: No se pudo obtener el ID del usuario.");
                return;
            }

            // Obtener el inventario del usuario (jugadores asignados a este usuario)
            JugadorDAO jugadorDAO = new JugadorDAO();
            List<Jugador> inventario = jugadorDAO.obtenerJugadoresPorUsuarioSinSubastar(idUsuario);

            // Validar si el inventario está vacío
            if (inventario == null || inventario.isEmpty()) {
                ctx.result("No tienes jugadores asignados para poner a subasta.");
                return;
            }

            // Crear el modelo para FreeMarker
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Poner Jugador a Subasta");
            model.put("nombreUsuario", nombreUsuario);
            model.put("inventario", inventario);

            // Renderizar la vista
            ctx.render("poner-subasta.ftl", model);
        });

        app.post("/subastar-jugador", ctx -> {
            // Obtener el nombre del jugador y el precio de salida del formulario
            String nombreJugador = ctx.formParam("jugador");
            String precioSalidaStr = ctx.formParam("precio");
            UsuarioDAO user = new UsuarioDAO();
            Usuario usuario = user.obtenerPorId(ctx.sessionAttribute("idUsuario"));
            System.out.println(precioSalidaStr);

            // Convertir el precio de salida a double
            double precioSalida;
            try {
                precioSalida = Double.parseDouble(precioSalidaStr);
            } catch (NumberFormatException e) {
                ctx.result("El precio de salida debe ser un número válido.");
                return;
            }

            if (precioSalida <= 0) {
                ctx.result("El precio de salida debe ser mayor que cero.");
                return;
            }

            // Obtener el jugador seleccionado
            JugadorDAO jugadorDAO = new JugadorDAO();
            Jugador jugador = jugadorDAO.obtenerPorNombre(nombreJugador);

            if (jugador == null) {
                ctx.result("El jugador seleccionado no existe.");
                return;
            }

            // Crear la subasta
            Subasta subasta = new Subasta(jugador, precioSalida , usuario);
            SubastaDAO.guardar(subasta);

            // Redirigir al usuario con un mensaje de éxito
            ctx.redirect("/interfaz?mensaje=Jugador%20subastado%20correctamente");
        });
        app.get("/Equipo", ctx -> {
            // Verificar sesión
            String nombreUsuario = ctx.sessionAttribute("nombre");
            if (nombreUsuario == null) {
                ctx.redirect("/");
                return;
            }

            // Obtener ID del usuario logueado
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Integer idUsuario = usuarioDAO.obtenerIdPorNombre(nombreUsuario);

            if (idUsuario == null) {
                ctx.result("Error al obtener el ID del usuario.");
                return;
            }

            // Obtener los jugadores del usuario
            JugadorDAO jugadorDAO = new JugadorDAO();
            List<Jugador> inventario = jugadorDAO.obtenerJugadoresPorUsuarioSinSubastar(idUsuario);

            if (inventario == null || inventario.isEmpty()) {
                inventario = new ArrayList<>(); // Aseguramos que no sea null
            }

            // Preparar modelo para FreeMarker
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Mi Equipo");
            model.put("nombreUsuario", nombreUsuario);
            model.put("inventario", inventario);

            ctx.render("Equipo.ftl", model);
        });


        app.get("/Ayuda", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Ver Mi Equipo");

            ctx.render("Ayuda.ftl", model);
        });



        // Ruta GET para cerrar sesión
        app.get("/logout", ctx -> {
            ctx.sessionAttribute("nombre", null);
            ctx.sessionAttribute("password", null);
            ctx.redirect("/");
        });
    }
}