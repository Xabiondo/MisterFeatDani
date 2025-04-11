public class Main {
    public static void main(String[] args) {
        // Crear jugadores de ejemplo
        Jugador messi = new Jugador(1, "Messi", Posicion.delantero);
        Jugador ramos = new Jugador(2, "Sergio Ramos", Posicion.defensa);
        Jugador neymar = new Jugador(3, "Neymar", Posicion.delantero);
        Jugador terStegen = new Jugador(4, "Ter Stegen", Posicion.portero);

        // Crear un usuario (como si fuera un cliente)
        Usuario usuario = new Usuario(1, "Dani", 1234);

        // Mostrar estado inicial
        System.out.println("Inventario de " + usuario.getNombreUsuario() + " antes de comprar:");
        mostrarInventario(usuario);

        // Crear mercado
        Mercado mercado = new Mercado();
        mercado.jugadoresEnVenta.add(messi);
        mercado.jugadoresEnVenta.add(ramos);
        mercado.jugadoresEnVenta.add(neymar);
        mercado.jugadoresEnVenta.add(terStegen);
        // Comprar jugadores
        System.out.println("\nComprando jugadores...");
        mercado.comprarJugador(usuario, messi);
        mercado.comprarJugador(usuario, ramos);
        mercado.comprarJugador(usuario, terStegen);
        mercado.comprarJugador(usuario, neymar);

        // Mostrar estado del inventario después de la compra
        System.out.println("\nInventario después de comprar jugadores:");
        mostrarInventario(usuario);

        // Intentar alinear jugadores
        System.out.println("\nAlineando jugadores...");
        try {
            usuario.alinearJugador(Posicion.delantero, messi); // Alinear a Messi en DELANTERO
            usuario.alinearJugador(Posicion.portero, terStegen); // Alinear a Ter Stegen en PORTERO
            usuario.alinearJugador(Posicion.defensa, ramos); // Alinear a Ramos en DEFENSOR
            usuario.alinearJugador(Posicion.delantero,neymar);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Mostrar estado del Once titular después de alinear jugadores
        System.out.println("\nOnce titular después de alinear jugadores:");
        mostrarOnce(usuario);

        // Intentar desalinear jugadores
        System.out.println("\nDesalineando jugadores...");
        try {
            usuario.desalinearJugador(Posicion.delantero); // Desalinear a Messi
            usuario.desalinearJugador(Posicion.portero);  // Desalinear a Ter Stegen
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Mostrar estado del Once titular después de desalinear jugadores
        System.out.println("\nOnce titular después de desalinear jugadores:");
        mostrarOnce(usuario);
    }

    // Método para mostrar el inventario de un usuario
    private static void mostrarInventario(Usuario usuario) {
        if (usuario.getInventario().isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Jugador jugador : usuario.getInventario()) {
                System.out.println(jugador.getNombre() + " (" + jugador.getPosicion() + ")");
            }
        }
    }


    // Método para mostrar el estado del Once titular
    private static void mostrarOnce(Usuario usuario) {
        Once oncetitular = usuario.getOnce();
        for (Posicion posicion : Posicion.values()) {
            Jugador jugador = oncetitular.getJugadorEnPosicion(posicion);
            if (jugador != null) {
                System.out.println(posicion + ": " + jugador.getNombre());
            } else {
                System.out.println(posicion + ": VACÍO");
            }
        }
    }
}
