package org.example;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioJugador implements Serializable {

    private int usuario_id;
    private int jugador_id;

    // Getters y setters
    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getJugador_id() {
        return jugador_id;
    }

    public void setJugador_id(int jugador_id) {
        this.jugador_id = jugador_id;
    }

    // Equals y hashCode (necesarios para comparar claves primarias)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioJugador that = (UsuarioJugador) o;
        return usuario_id == that.usuario_id && jugador_id == that.jugador_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario_id, jugador_id);
    }
}