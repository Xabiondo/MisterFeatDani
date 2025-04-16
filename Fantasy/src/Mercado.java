import java.util.ArrayList;
import java.util.List;

public class Mercado {
    public List<Jugador> jugadoresEnVenta;

    public Mercado() {
        jugadoresEnVenta = new ArrayList<Jugador>();
    }
    public List<Jugador> listarJugadoresEnVenta() {
        return jugadoresEnVenta;
    }
    public boolean comprarJugador(Usuario comprador, Jugador jugador) {
        if (!jugadoresEnVenta.contains(jugador)) return false;

        jugadoresEnVenta.remove(jugador);
        comprador.añadirJugadorAlInventario(jugador);
        return true;
    }
    public boolean venderJugador(Usuario vendedor, Jugador jugador) {
        if (!vendedor.getInventario().contains(jugador)) return false;

        vendedor.eliminarJugadorDelInventario(jugador);
        jugadoresEnVenta.add(jugador);
        return true;
    }


}
