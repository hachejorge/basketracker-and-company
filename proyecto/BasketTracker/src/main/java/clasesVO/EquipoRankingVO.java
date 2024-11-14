package clasesVO;

/**
 * Clase que representa el estado de un equipo en el ranking.
 * Esta clase se utiliza para almacenar la información relacionada con el rendimiento
 * de un equipo en una competición, incluyendo los puntos, victorias, derrotas, 
 * puntos a favor y en contra.
 */
public class EquipoRankingVO {
    
    /** Identificador único del equipo. Referencia a la tabla {@code EQUIPO}. */
    private int idEquipo;
    
    /** Nombre del equipo. */
    private String nombre;
    
    /** Puntos acumulados por el equipo en la competición. */
    private int puntos;
    
    /** Número de partidos ganados por el equipo. */
    private int partidosGanados;
    
    /** Número de partidos perdidos por el equipo. */
    private int partidosPerdidos;
    
    /** Puntos a favor del equipo (diferencia de puntos en los partidos). */
    private int puntosAFavor;
    
    /** Puntos en contra del equipo (diferencia de puntos en los partidos). */
    private int puntosEnContra;

    /**
     * Constructor para inicializar un objeto {@code EquipoRankingVO} con los valores proporcionados.
     * 
     * @param idEquipo El identificador del equipo.
     * @param nombre El nombre del equipo.
     * @param puntos Los puntos acumulados por el equipo.
     * @param partidosGanados El número de partidos ganados por el equipo.
     * @param partidosPerdidos El número de partidos perdidos por el equipo.
     * @param puntosAFavor Los puntos a favor del equipo.
     * @param puntosEnContra Los puntos en contra del equipo.
     */
    public EquipoRankingVO(int idEquipo, String nombre, int puntos, int partidosGanados, int partidosPerdidos, int puntosAFavor, int puntosEnContra) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.puntos = puntos;
        this.partidosGanados = partidosGanados;
        this.partidosPerdidos = partidosPerdidos;
        this.puntosAFavor = puntosAFavor;
        this.puntosEnContra = puntosEnContra;
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
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del equipo.
     * 
     * @param nombre El nombre del equipo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los puntos acumulados por el equipo.
     * 
     * @return Los puntos del equipo.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Establece los puntos acumulados por el equipo.
     * 
     * @param puntos Los puntos del equipo.
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Obtiene el número de partidos ganados por el equipo.
     * 
     * @return El número de partidos ganados.
     */
    public int getPartidosGanados() {
        return partidosGanados;
    }

    /**
     * Establece el número de partidos ganados por el equipo.
     * 
     * @param partidosGanados El número de partidos ganados.
     */
    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    /**
     * Obtiene el número de partidos perdidos por el equipo.
     * 
     * @return El número de partidos perdidos.
     */
    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    /**
     * Establece el número de partidos perdidos por el equipo.
     * 
     * @param partidosPerdidos El número de partidos perdidos.
     */
    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    /**
     * Obtiene los puntos a favor del equipo.
     * 
     * @return Los puntos a favor del equipo.
     */
    public int getPuntosAFavor() {
        return puntosAFavor;
    }

    /**
     * Establece los puntos a favor del equipo.
     * 
     * @param puntosAFavor Los puntos a favor del equipo.
     */
    public void setPuntosAFavor(int puntosAFavor) {
        this.puntosAFavor = puntosAFavor;
    }

    /**
     * Obtiene los puntos en contra del equipo.
     * 
     * @return Los puntos en contra del equipo.
     */
    public int getPuntosEnContra() {
        return puntosEnContra;
    }

    /**
     * Establece los puntos en contra del equipo.
     * 
     * @param puntosEnContra Los puntos en contra del equipo.
     */
    public void setPuntosEnContra(int puntosEnContra) {
        this.puntosEnContra = puntosEnContra;
    }

    /**
     * Calcula la diferencia de puntos del equipo (puntos a favor - puntos en contra).
     * 
     * @return La diferencia de puntos del equipo.
     */
    public int getDiferenciaPuntos() {
        return puntosAFavor - puntosEnContra;
    }
    
    /**
     * Devuelve una representación en cadena del objeto {@code EquipoRankingVO}.
     * 
     * @return Una cadena que representa al objeto {@code EquipoRankingVO}.
     */
    @Override
    public String toString() {
        return "EquipoRankingVO{" +
               "idEquipo=" + idEquipo +
               ", nombre='" + nombre + '\'' +
               ", puntos=" + puntos +
               ", partidosGanados=" + partidosGanados +
               ", partidosPerdidos=" + partidosPerdidos +
               ", puntosAFavor=" + puntosAFavor +
               ", puntosEnContra=" + puntosEnContra +
               '}';
    }
}
