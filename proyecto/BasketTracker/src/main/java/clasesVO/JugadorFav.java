public class JugadorFav {
    private String nombreUsuario; // Se refiere a la tabla USUARIO
    private String jugador; // Se refiere a la tabla JUGADOR

    // Constructor
    public JugadorFav(String nombreUsuario, String jugador) {
        this.nombreUsuario = nombreUsuario;
        this.jugador = jugador;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    @Override
    public String toString() {
        return "JugadorFav{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", jugador='" + jugador + '\'' +
                '}';
    }
}
