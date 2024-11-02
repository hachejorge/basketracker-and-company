package clasesVO;

public class HistoricoVO {
	private String nombreUsuario;
    private int partidosJugados;
    private int puntosTotales;
    private int minutosTotales;
    private int tirosLibresTotales;
    private int tirosLibresAnotados; // Nuevo campo para tiros libres anotados
    private int triplesTotales;
    private int faltasTotales;

    // Constructor
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

    // Getters
    public String getNombreUsuario () {
    	return nombreUsuario;
    }
    
    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public int getMinutosTotales() {
        return minutosTotales;
    }

    public int getTirosLibresTotales() {
        return tirosLibresTotales;
    }

    public int getTirosLibresAnotados() {
        return tirosLibresAnotados;
    }

    public int getTriplesTotales() {
        return triplesTotales;
    }

    public int getFaltasTotales() {
        return faltasTotales;
    }

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
