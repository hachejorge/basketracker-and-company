package clasesVO;

public class EquipoRankingVO {
    private int idEquipo;
    private String nombre;
    private int puntos;
    private int partidosGanados;
    private int partidosPerdidos;
    private int puntosAFavor;
    private int puntosEnContra;

    // Constructor
    public EquipoRankingVO(int idEquipo, String nombre, int puntos, int partidosGanados, int partidosPerdidos, int puntosAFavor, int puntosEnContra) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.puntos = puntos;
        this.partidosGanados = partidosGanados;
        this.partidosPerdidos = partidosPerdidos;
        this.puntosAFavor = puntosAFavor;
        this.puntosEnContra = puntosEnContra;
    }

    // Getters y setters
    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getPuntosAFavor() {
        return puntosAFavor;
    }

    public void setPuntosAFavor(int puntosAFavor) {
        this.puntosAFavor = puntosAFavor;
    }

    public int getPuntosEnContra() {
        return puntosEnContra;
    }

    public void setPuntosEnContra(int puntosEnContra) {
        this.puntosEnContra = puntosEnContra;
    }

    public int getDiferenciaPuntos() {
        return puntosAFavor - puntosEnContra;
    }
    
    // Método toString para mostrar la información del equipo en el ranking
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
