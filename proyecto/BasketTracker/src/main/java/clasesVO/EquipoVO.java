package clasesVO;

/**
 * Clase que representa un equipo dentro de una competición.
 * Esta clase almacena información sobre el equipo, como su identificador,
 * nombre, ubicación y la competición a la que pertenece.
 */
public class EquipoVO {
    
    /** Identificador único del equipo. Referencia a la tabla {@code EQUIPO}. */
    private int idEquipo;
    
    /** Nombre del equipo. */
    private String nombreEquipo;
    
    /** Ubicación geográfica del equipo. */
    private String ubicacion;
    
    /** Nombre de la competición a la que pertenece el equipo. Referencia a la tabla {@code COMPETICION}. */
    private String competicion;

    /**
     * Constructor para inicializar un objeto {@code EquipoVO} con los valores proporcionados.
     * 
     * @param idEquipo El identificador del equipo.
     * @param nombreEquipo El nombre del equipo.
     * @param ubicacion La ubicación geográfica del equipo.
     * @param competicion La competición a la que pertenece el equipo.
     */
    public EquipoVO(int idEquipo, String nombreEquipo, String ubicacion, String competicion) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.ubicacion = ubicacion;
        this.competicion = competicion;
    }

    /**
     * Obtiene el identificador del equipo.
     * 
     * @return El identificador del equipo.
     */
    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * Establece el identificador del equipo.
     * 
     * @param idEquipo El identificador del equipo.
     */
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    /**
     * Obtiene el nombre del equipo.
     * 
     * @return El nombre del equipo.
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    /**
     * Establece el nombre del equipo.
     * 
     * @param nombreEquipo El nombre del equipo.
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    /**
     * Obtiene la ubicación geográfica del equipo.
     * 
     * @return La ubicación del equipo.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación geográfica del equipo.
     * 
     * @param ubicacion La ubicación del equipo.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el nombre de la competición a la que pertenece el equipo.
     * 
     * @return El nombre de la competición.
     */
    public String getCompeticion() {
        return competicion;
    }

    /**
     * Establece el nombre de la competición a la que pertenece el equipo.
     * 
     * @param competicion El nombre de la competición.
     */
    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code EquipoVO}.
     * 
     * @return Una cadena que representa al objeto {@code EquipoVO}.
     */
    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombreEquipo='" + nombreEquipo + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", competicion='" + competicion + '\'' +
                '}';
    }
}
