package clasesVO;

/**
 * Clase que representa la relación de un usuario con una competición favorita.
 * Esta clase está relacionada con la tabla {@code COMPETICION_FAV} en la base de datos.
 */
public class CompeticionFavVO {
    
    /** Nombre del usuario que ha marcado la competición como favorita. Referencia a la tabla {@code USUARIO}. */
    private String nombreUsuario; 
    
    /** Nombre de la competición que ha sido marcada como favorita. Referencia a la tabla {@code COMPETICION}. */
    private String competicion;

    /**
     * Constructor para inicializar un objeto {@code CompeticionFavVO} con los valores proporcionados.
     * 
     * @param nombreUsuario El nombre del usuario que marca la competición como favorita.
     * @param competicion El nombre de la competición marcada como favorita.
     */
    public CompeticionFavVO(String nombreUsuario, String competicion) {
        this.nombreUsuario = nombreUsuario;
        this.competicion = competicion;
    }

    /**
     * Obtiene el nombre del usuario que ha marcado la competición como favorita.
     * 
     * @return El nombre del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre del usuario que ha marcado la competición como favorita.
     * 
     * @param nombreUsuario El nombre del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el nombre de la competición marcada como favorita.
     * 
     * @return El nombre de la competición.
     */
    public String getCompeticion() {
        return competicion;
    }

    /**
     * Establece el nombre de la competición marcada como favorita.
     * 
     * @param competicion El nombre de la competición.
     */
    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code CompeticionFavVO}.
     * 
     * @return Una cadena que representa al objeto {@code CompeticionFavVO}.
     */
    @Override
    public String toString() {
        return "CompeticionFav{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", competicion='" + competicion + '\'' +
                '}';
    }
}
