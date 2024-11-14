package clasesVO;

/**
 * Clase que representa las estadísticas de un jugador en un partido específico.
 * Incluye puntos, tiros de 3 puntos, tiros libres, faltas y minutos jugados.
 * 
 * Los campos como {@code ptsAnt}, {@code trpAnt}, {@code tlbLan}, {@code tlbAnt}, 
 * {@code faltas}, y {@code mntJd} pueden ser nulos en caso de que no se registren esos datos.
 */
public class PtsJugParVO {
    
    /** El identificador único del partido. Referencia a la tabla {@code PARTIDO}. */
    private int idPartido;
    
    /** El nombre de usuario del jugador. Referencia a la tabla {@code JUGADOR}. */
    private String nombreUsuario;
    
    /** Puntos obtenidos por el jugador en el partido. Puede ser nulo. */
    private Integer ptsAnt;
    
    /** Tiros de 3 puntos realizados por el jugador. Puede ser nulo. */
    private Integer trpAnt;
    
    /** Tiros libres realizados por el jugador. Puede ser nulo. */
    private Integer tlbLan;
    
    /** Tiros libres fallados por el jugador. Puede ser nulo. */
    private Integer tlbAnt;
    
    /** Faltas cometidas por el jugador. Puede ser nulo. */
    private Integer faltas;
    
    /** Minutos jugados por el jugador. Puede ser nulo. */
    private Integer mntJd;

    /** Constructor vacío */
    public PtsJugParVO() {
        // Constructor vacío, utilizado para inicializar el objeto sin parámetros
    }

    /**
     * Constructor para inicializar un objeto {@code PtsJugParVO} con los valores proporcionados.
     * 
     * @param idPartido El identificador del partido al que pertenece el jugador.
     * @param nombreUsuario El nombre de usuario del jugador.
     * @param ptsAnt Puntos obtenidos por el jugador en el partido.
     * @param trpAnt Tiros de 3 puntos realizados por el jugador.
     * @param tlbLan Tiros libres realizados por el jugador.
     * @param tlbAnt Tiros libres fallados por el jugador.
     * @param faltas Faltas cometidas por el jugador.
     * @param mntJd Minutos jugados por el jugador.
     */
    public PtsJugParVO(int idPartido, String nombreUsuario, Integer ptsAnt, Integer trpAnt,
                     Integer tlbLan, Integer tlbAnt, Integer faltas, Integer mntJd) {
        this.idPartido = idPartido;
        this.nombreUsuario = nombreUsuario;
        this.ptsAnt = ptsAnt;
        this.trpAnt = trpAnt;
        this.tlbLan = tlbLan;
        this.tlbAnt = tlbAnt;
        this.faltas = faltas;
        this.mntJd = mntJd;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador del partido al que pertenece el jugador.
     * 
     * @return El identificador del partido.
     */
    public int getIdPartido() {
        return idPartido;
    }

    /**
     * Establece el identificador del partido al que pertenece el jugador.
     * 
     * @param idPartido El identificador del partido.
     */
    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
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
     * Obtiene los puntos obtenidos por el jugador en el partido.
     * 
     * @return Los puntos obtenidos por el jugador.
     */
    public Integer getPtsAnt() {
        return ptsAnt;
    }

    /**
     * Establece los puntos obtenidos por el jugador en el partido.
     * 
     * @param ptsAnt Los puntos obtenidos por el jugador.
     */
    public void setPtsAnt(Integer ptsAnt) {
        this.ptsAnt = ptsAnt;
    }

    /**
     * Obtiene los tiros de 3 puntos realizados por el jugador.
     * 
     * @return Los tiros de 3 puntos realizados por el jugador.
     */
    public Integer getTrpAnt() {
        return trpAnt;
    }

    /**
     * Establece los tiros de 3 puntos realizados por el jugador.
     * 
     * @param trpAnt Los tiros de 3 puntos realizados por el jugador.
     */
    public void setTrpAnt(Integer trpAnt) {
        this.trpAnt = trpAnt;
    }

    /**
     * Obtiene los tiros libres realizados por el jugador.
     * 
     * @return Los tiros libres realizados por el jugador.
     */
    public Integer getTlbLan() {
        return tlbLan;
    }

    /**
     * Establece los tiros libres realizados por el jugador.
     * 
     * @param tlbLan Los tiros libres realizados por el jugador.
     */
    public void setTlbLan(Integer tlbLan) {
        this.tlbLan = tlbLan;
    }

    /**
     * Obtiene los tiros libres fallados por el jugador.
     * 
     * @return Los tiros libres fallados por el jugador.
     */
    public Integer getTlbAnt() {
        return tlbAnt;
    }

    /**
     * Establece los tiros libres fallados por el jugador.
     * 
     * @param tlbAnt Los tiros libres fallados por el jugador.
     */
    public void setTlbAnt(Integer tlbAnt) {
        this.tlbAnt = tlbAnt;
    }

    /**
     * Obtiene las faltas cometidas por el jugador.
     * 
     * @return Las faltas cometidas por el jugador.
     */
    public Integer getFaltas() {
        return faltas;
    }

    /**
     * Establece las faltas cometidas por el jugador.
     * 
     * @param faltas Las faltas cometidas por el jugador.
     */
    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    /**
     * Obtiene los minutos jugados por el jugador en el partido.
     * 
     * @return Los minutos jugados por el jugador.
     */
    public Integer getMntJd() {
        return mntJd;
    }

    /**
     * Establece los minutos jugados por el jugador en el partido.
     * 
     * @param mntJd Los minutos jugados por el jugador.
     */
    public void setMntJd(Integer mntJd) {
        this.mntJd = mntJd;
    }

    /**
     * Representación en cadena de texto del objeto {@code PtsJugParVO}.
     * 
     * @return La representación en cadena del objeto.
     */
    @Override
    public String toString() {
        return "PtsJugPar{" +
                "idPartido=" + idPartido +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", ptsAnt=" + ptsAnt +
                ", trpAnt=" + trpAnt +
                ", tlbLan=" + tlbLan +
                ", tlbAnt=" + tlbAnt +
                ", faltas=" + faltas +
                ", mntJd=" + mntJd +
                '}';
    }
}
