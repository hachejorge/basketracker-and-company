package clasesVO;

/**
 * Clase que representa la relación de un usuario con un equipo favorito.
 * Esta clase está relacionada con la tabla {@code EQUIPO_FAV} en la base de datos.
 */
public class EquipoFavVO {
    
    /** Nombre del usuario que ha marcado el equipo como favorito. Referencia a la tabla {@code USUARIO}. */
    private String nombreUsuario; 
    
    /** Identificador del equipo marcado como favorito. Referencia a la tabla {@code EQUIPO}. */
    private int equipo;

    /**
     * Constructor para inicializar un objeto {@code EquipoFavVO} con los valores proporcionados.
     * 
     * @param nombreUsuario El nombre del usuario que marca el equipo como favorito.
     * @param equipo El identificador del equipo marcado como favorito.
     */
    public EquipoFavVO(String nombreUsuario, int equipo) {
        this.nombreUsuario = nombreUsuario;
        this.equipo = equipo;
    }

    /**
     * Obtiene el nombre del usuario que ha marcado el equipo como favorito.
     * 
     * @return El nombre del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre del usuario que ha marcado el equipo como favorito.
     * 
     * @param nombreUsuario El nombre del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el identificador del equipo marcado como favorito.
     * 
     * @return El identificador del equipo.
     */
    public int getEquipo() {
        return equipo;
    }

    /**
     * Establece el identificador del equipo marcado como favorito.
     * 
     * @param equipo El identificador del equipo.
     */
    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code EquipoFavVO}.
     * 
     * @return Una cadena que representa al objeto {@code EquipoFavVO}.
     */
    @Override
    public String toString() {
        return "EquipoFav{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}
