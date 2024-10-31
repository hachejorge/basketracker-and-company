package clasesVO;

public class HistoricoVO {
    private int partidosJugados;
    private int puntosTotales;
    private int minutosTotales;
    private int tirosLibresTotales;
    private int triplesTotales;
    private int faltasTotales;

    // Constructor
    public HistoricoVO(int partidosJugados, int puntosTotales, int minutosTotales, 
                       int tirosLibresTotales, int triplesTotales, int faltasTotales) {
        this.partidosJugados = partidosJugados;
        this.puntosTotales = puntosTotales;
        this.minutosTotales = minutosTotales;
        this.tirosLibresTotales = tirosLibresTotales;
        this.triplesTotales = triplesTotales;
        this.faltasTotales = faltasTotales;
    }

    // Getters
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
                ", triplesTotales=" + triplesTotales +
                ", faltasTotales=" + faltasTotales +
                '}';
    }
}
