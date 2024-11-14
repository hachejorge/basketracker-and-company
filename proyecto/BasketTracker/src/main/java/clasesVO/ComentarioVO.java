package clasesVO;

/**
 * Clase que representa un comentario realizado por un usuario sobre un partido.
 * Esta clase está relacionada con la tabla {@code COMENTARIO} en la base de datos.
 */
public class ComentarioVO {
    
    /** Nombre del usuario que realiza el comentario. Referencia a la tabla {@code USUARIO}. */
    private String nombreUsuario; 
    
    /** Identificador único del partido relacionado con el comentario. Referencia a la tabla {@code PARTIDO}. */
    private int idPartido; 
    
    /** Texto del comentario realizado por el usuario. */
    private String comentario;

    /**
     * Constructor para inicializar un objeto {@code ComentarioVO} con los valores proporcionados.
     * 
     * @param nombreUsuario El nombre del usuario que realiza el comentario.
     * @param idPartido El identificador del partido al que pertenece el comentario.
     * @param comentario El texto del comentario.
     */
    public ComentarioVO(String nombreUsuario, int idPartido, String comentario) {
        this.nombreUsuario = nombreUsuario;
        this.idPartido = idPartido;
        this.comentario = comentario;
    }

    /**
     * Obtiene el nombre del usuario que realizó el comentario.
     * 
     * @return El nombre del usuario que realizó el comentario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre del usuario que realizó el comentario.
     * 
     * @param nombreUsuario El nombre del usuario que realizó el comentario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el identificador del partido relacionado con el comentario.
     * 
     * @return El identificador del partido.
     */
    public int getIdPartido() {
        return idPartido;
    }

    /**
     * Establece el identificador del partido relacionado con el comentario.
     * 
     * @param idPartido El identificador del partido.
     */
    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    /**
     * Obtiene el texto del comentario.
     * 
     * @return El texto del comentario.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece el texto del comentario.
     * 
     * @param comentario El texto del comentario.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code ComentarioVO}.
     * 
     * @return Una cadena que representa al objeto {@code ComentarioVO}.
     */
    @Override
    public String toString() {
        return "Comentario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", idPartido=" + idPartido +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
