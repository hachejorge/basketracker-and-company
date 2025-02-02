package clasesVO;

/**
 * Clase que representa el histórico de un jugador en base a sus estadísticas de juego.
 * Esta clase contiene información sobre los partidos jugados, puntos totales, minutos jugados,
 * tiros libres totales y anotados, triples totales y faltas totales de un jugador.
 */
public class HistoricoVO {
    
    /** Nombre del usuario (jugador). Referencia a la tabla {@code USUARIO}. */
    private String nombreUsuario;
    
    /** Número total de partidos jugados por el jugador. */
    private int partidosJugados;
    
    /** Puntos totales anotados por el jugador a lo largo de su carrera. */
    private int puntosTotales;
    
    /** Minutos totales jugados por el jugador a lo largo de su carrera. */
    private int minutosTotales;
    
    /** Número total de tiros libres intentados por el jugador. */
    private int tirosLibresTotales;
    
    /** Número total de tiros libres anotados por el jugador. */
    private int tirosLibresAnotados;
    
    /** Número total de triples intentados por el jugador. */
    private int triplesTotales;
    
    /** Número total de faltas cometidas por el jugador. */
    private int faltasTotales;

    /**
     * Constructor para inicializar un objeto {@code HistoricoVO} con los valores proporcionados.
     * 
     * @param nombreUsuario El nombre del jugador.
     * @param partidosJugados El número total de partidos jugados.
     * @param puntosTotales Los puntos totales anotados.
     * @param minutosTotales Los minutos totales jugados.
     * @param tirosLibresTotales Los tiros libres intentados.
     * @param tirosLibresAnotados Los tiros libres anotados.
     * @param triplesTotales Los triples intentados.
     * @param faltasTotales Las faltas cometidas.
     */
    public HistoricoVO(String nombreUsuario, int partidosJugados, int puntosTotales, int minutosTotales,
                       int tirosLibresTotales, int tirosLibresAnotados, int triplesTotales, int faltasTotales) {
        this.nombreUsuario = nombreUsuario;
        this.partidosJugados = partidosJugados;
        this.puntosTotales = puntosTotales;
        this.minutosTotales = minutosTotales;
        this.tirosLibresTotales = tirosLibresTotales;
        this.tirosLibresAnotados = tirosLibresAnotados;
        this.triplesTotales = triplesTotales;
        this.faltasTotales = faltasTotales;
    }

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return El nombre del jugador.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Obtiene el número total de partidos jugados por el jugador.
     * 
     * @return El número de partidos jugados.
     */
    public int getPartidosJugados() {
        return partidosJugados;
    }

    /**
     * Obtiene los puntos totales anotados por el jugador.
     * 
     * @return Los puntos totales anotados.
     */
    public int getPuntosTotales() {
        return puntosTotales;
    }

    /**
     * Obtiene el número total de minutos jugados por el jugador.
     * 
     * @return Los minutos totales jugados.
     */
    public int getMinutosTotales() {
        return minutosTotales;
    }

    /**
     * Obtiene el número total de tiros libres intentados por el jugador.
     * 
     * @return El número total de tiros libres intentados.
     */
    public int getTirosLibresTotales() {
        return tirosLibresTotales;
    }

    /**
     * Obtiene el número total de tiros libres anotados por el jugador.
     * 
     * @return El número total de tiros libres anotados.
     */
    public int getTirosLibresAnotados() {
        return tirosLibresAnotados;
    }

    /**
     * Obtiene el número total de triples intentados por el jugador.
     * 
     * @return El número total de triples intentados.
     */
    public int getTriplesTotales() {
        return triplesTotales;
    }

    /**
     * Obtiene el número total de faltas cometidas por el jugador.
     * 
     * @return El número total de faltas cometidas.
     */
    public int getFaltasTotales() {
        return faltasTotales;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code HistoricoVO}.
     * 
     * @return Una cadena que representa al objeto {@code HistoricoVO}.
     */
    @Override
    public String toString() {
        return "HistoricoVO{" +
                "partidosJugados=" + partidosJugados +
                ", puntosTotales=" + puntosTotales +
                ", minutosTotales=" + minutosTotales +
                ", tirosLibresTotales=" + tirosLibresTotales +
                ", tirosLibresAnotados=" + tirosLibresAnotados +
                ", triplesTotales=" + triplesTotales +
                ", faltasTotales=" + faltasTotales +
                '}';
    }
}
