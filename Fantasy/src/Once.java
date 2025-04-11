import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Once {
    private Map<Posicion, Jugador> alineacion;

    public Once() {
        alineacion = new HashMap<>();
        // Inicializa las posiciones con null
        for (Posicion p : Posicion.values()) {
            alineacion.put(p, null);  // Asignamos null a todas las posiciones al inicio
        }
    }

    public void colocarJugador(Posicion posicion, Jugador jugador) {
        alineacion.put(posicion, jugador);
    }

    public Jugador getJugadorEnPosicion(Posicion posicion) {
        return alineacion.get(posicion);
    }

    public void quitarJugador(Posicion posicion) {
        alineacion.put(posicion, null);  // Vaciar la posición
    }

    public Map<Posicion, Jugador> getAlineacion() {
        return alineacion;
    }
}

