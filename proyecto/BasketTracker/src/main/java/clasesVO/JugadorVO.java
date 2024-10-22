package clasesVO;

public class JugadorVO {
    private String nombreUsuario; // Se refiere a la tabla USUARIO
    private String nombreJugador;
    private int equipo; // Se refiere a la tabla EQUIPO

    // Constructor
    public JugadorVO(String nombreUsuario, String nombreJugador, int equipo) {
        this.nombreUsuario = nombreUsuario;
        this.nombreJugador = nombreJugador;
        this.equipo = equipo;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", nombreJugador='" + nombreJugador + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}
