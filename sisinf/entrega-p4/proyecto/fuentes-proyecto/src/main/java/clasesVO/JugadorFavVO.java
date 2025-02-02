package clasesVO;

/**
 * Clase que representa la relación de un usuario con un jugador favorito.
 * Esta clase contiene la información sobre el usuario que marca a otro jugador como favorito.
 */
public class JugadorFavVO {
    
    /** Nombre del usuario. Referencia a la tabla {@code USUARIO}. */
    private String nombreUsuario;
    
    /** Nombre del jugador. Referencia a la tabla {@code JUGADOR}. */
    private String jugador;

    /**
     * Constructor para inicializar un objeto {@code JugadorFavVO} con los valores proporcionados.
     * 
     * @param nombreUsuario El nombre del usuario que marca al jugador como favorito.
     * @param jugador El nombre del jugador que es marcado como favorito por el usuario.
     */
    public JugadorFavVO(String nombreUsuario, String jugador) {
        this.nombreUsuario = nombreUsuario;
        this.jugador = jugador;
    }

    /**
     * Obtiene el nombre del usuario que tiene al jugador como favorito.
     * 
     * @return El nombre del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre del usuario que tiene al jugador como favorito.
     * 
     * @param nombreUsuario El nombre del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el nombre del jugador que es favorito del usuario.
     * 
     * @return El nombre del jugador favorito.
     */
    public String getJugador() {
        return jugador;
    }

    /**
     * Establece el nombre del jugador que será marcado como favorito por el usuario.
     * 
     * @param jugador El nombre del jugador.
     */
    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code JugadorFavVO}.
     * 
     * @return Una cadena que representa al objeto {@code JugadorFavVO}.
     */
    @Override
    public String toString() {
        return "JugadorFav{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", jugador='" + jugador + '\'' +
                '}';
    }
}
