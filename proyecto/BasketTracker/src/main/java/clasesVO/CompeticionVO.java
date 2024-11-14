package clasesVO;

/**
 * Clase que representa una competición.
 * Esta clase está relacionada con la tabla {@code COMPETICION} en la base de datos.
 */
public class CompeticionVO {

    /** Nombre de la competición. Referencia a la tabla {@code COMPETICION}. */
    private String nombre;

    /**
     * Constructor para inicializar un objeto {@code CompeticionVO} con el nombre de la competición.
     * 
     * @param nombre El nombre de la competición.
     */
    public CompeticionVO(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de la competición.
     * 
     * @return El nombre de la competición.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la competición.
     * 
     * @param nombre El nombre de la competición.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code CompeticionVO}.
     * 
     * @return Una cadena que representa al objeto {@code CompeticionVO}.
     */
    @Override
    public String toString() {
        return "Competicion{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
