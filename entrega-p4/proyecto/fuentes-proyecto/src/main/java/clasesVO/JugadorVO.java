package clasesVO;

/**
 * Clase que representa a un jugador en el sistema.
 * Esta clase contiene la información sobre un jugador, incluyendo su nombre de usuario,
 * el nombre del jugador y el equipo al que pertenece.
 */
public class JugadorVO {
    
    /** Nombre de usuario del jugador. Referencia a la tabla {@code USUARIO}. */
    private String nombreUsuario;
    
    /** Nombre del jugador. */
    private String nombreJugador;
    
    /** Identificador del equipo al que pertenece el jugador. Referencia a la tabla {@code EQUIPO}. */
    private int equipo;

    /**
     * Constructor para inicializar un objeto {@code JugadorVO} con los valores proporcionados.
     * 
     * @param nombreUsuario El nombre de usuario del jugador.
     * @param nombreJugador El nombre completo del jugador.
     * @param equipo El identificador del equipo al que pertenece el jugador.
     */
    public JugadorVO(String nombreUsuario, String nombreJugador, int equipo) {
        this.nombreUsuario = nombreUsuario;
        this.nombreJugador = nombreJugador;
        this.equipo = equipo;
    }

    /**
     * Obtiene el nombre de usuario del jugador.
     * 
     * @return El nombre de usuario del jugador.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario del jugador.
     * 
     * @param nombreUsuario El nombre de usuario del jugador.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return El nombre del jugador.
     */
    public String getNombreJugador() {
        return nombreJugador;
    }

    /**
     * Establece el nombre del jugador.
     * 
     * @param nombreJugador El nombre del jugador.
     */
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    /**
     * Obtiene el identificador del equipo al que pertenece el jugador.
     * 
     * @return El identificador del equipo.
     */
    public int getEquipo() {
        return equipo;
    }

    /**
     * Establece el identificador del equipo al que pertenece el jugador.
     * 
     * @param equipo El identificador del equipo.
     */
    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code JugadorVO}.
     * 
     * @return Una cadena que representa al objeto {@code JugadorVO}.
     */
    @Override
    public String toString() {
        return "Jugador{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", nombreJugador='" + nombreJugador + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}
