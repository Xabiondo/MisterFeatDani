public class Jugador {
    int id;
    String nombre;
    int Valor;
    Posicion posicion;
    String equipo;

    public Jugador(int id, String equipo, int valor, String nombre, Posicion posicion) {
        this.id = id;
        this.equipo = equipo;
        Valor = valor;
        this.nombre = nombre;
        this.posicion = posicion;
    }
    public Jugador(int id, String nombre,Posicion posicion) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
    }
    public Jugador() {}

    public Posicion getPosicion() {
        return posicion;
    }

    public String getEquipo() {
        return equipo;
    }

    public int getValor() {
        return Valor;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
