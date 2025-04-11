import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Usuario {
    int id;
    String NombreUsuario;
    int password;
    List<Jugador> inventario;
    Once once = new Once();


    public Usuario(int id, String NombreUsuario, int password) {
        this.id = id;
        this.NombreUsuario = NombreUsuario;
        this.password = password;
        this.inventario = new ArrayList<>();
    }

    public void añadirJugadorAlInventario(Jugador jugador) {
        this.inventario.add(jugador);
    }
    public void eliminarJugadorDelInventario(Jugador jugador) {
        this.inventario.remove(jugador);
    }
    public void alinearJugador(Posicion posicion, Jugador jugador) {
        if (!inventario.contains(jugador)) {
            throw new IllegalArgumentException("El jugador no está en tu inventario.");
        }

        if (jugador.getPosicion() != posicion) {
            throw new IllegalArgumentException("Este jugador no puede jugar en esa posición.");
        }

        if (once.getJugadorEnPosicion(posicion) != null) {
            throw new IllegalStateException("Ya tienes un jugador en esa posición.");
        }

        once.colocarJugador(posicion, jugador);
    }

    public void desalinearJugador(Posicion posicion) {
        if (once.getJugadorEnPosicion(posicion) == null) {
            System.out.println("No hay jugador en esa posición para quitar.");
        } else {
            once.quitarJugador(posicion);  // Vaciar la posición
            System.out.println("Jugador desalineado de la posición " + posicion);
        }
    }


    public int getId() {
        return id;
    }

    public Once getOnce() {
        return once;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public int getPassword() {
        return password;
    }

    public List<Jugador> getInventario() {
        return inventario;
    }
}
