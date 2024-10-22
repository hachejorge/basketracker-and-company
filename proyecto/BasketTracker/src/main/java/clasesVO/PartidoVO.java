package clasesVO;

public class PartidoVO {
    private int idPartido;
    private int equipoLocal;
    private int equipoVisitante;
    private int jornada;
    private int ptsC1Local;
    private int ptsC2Local;
    private int ptsC3Local;
    private int ptsC4Local;
    private int ptsC1Visit;
    private int ptsC2Visit;
    private int ptsC3Visit;
    private int ptsC4Visit;

    // Constructor
    public PartidoVO(int idPartido, int equipoLocal, int equipoVisitante, int jornada,
                   int ptsC1Local, int ptsC2Local, int ptsC3Local, int ptsC4Local,
                   int ptsC1Visit, int ptsC2Visit, int ptsC3Visit, int ptsC4Visit) {
        this.idPartido = idPartido;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.jornada = jornada;
        this.ptsC1Local = ptsC1Local;
        this.ptsC2Local = ptsC2Local;
        this.ptsC3Local = ptsC3Local;
        this.ptsC4Local = ptsC4Local;
        this.ptsC1Visit = ptsC1Visit;
        this.ptsC2Visit = ptsC2Visit;
        this.ptsC3Visit = ptsC3Visit;
        this.ptsC4Visit = ptsC4Visit;
    }

    // Getters y Setters
    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public int getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(int equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public int getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(int equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public int getPtsC1Local() {
        return ptsC1Local;
    }

    public void setPtsC1Local(int ptsC1Local) {
        this.ptsC1Local = ptsC1Local;
    }

    public int getPtsC2Local() {
        return ptsC2Local;
    }

    public void setPtsC2Local(int ptsC2Local) {
        this.ptsC2Local = ptsC2Local;
    }

    public int getPtsC3Local() {
        return ptsC3Local;
    }

    public void setPtsC3Local(int ptsC3Local) {
        this.ptsC3Local = ptsC3Local;
    }

    public int getPtsC4Local() {
        return ptsC4Local;
    }

    public void setPtsC4Local(int ptsC4Local) {
        this.ptsC4Local = ptsC4Local;
    }

    public int getPtsC1Visit() {
        return ptsC1Visit;
    }

    public void setPtsC1Visit(int ptsC1Visit) {
        this.ptsC1Visit = ptsC1Visit;
    }

    public int getPtsC2Visit() {
        return ptsC2Visit;
    }

    public void setPtsC2Visit(int ptsC2Visit) {
        this.ptsC2Visit = ptsC2Visit;
    }

    public int getPtsC3Visit() {
        return ptsC3Visit;
    }

    public void setPtsC3Visit(int ptsC3Visit) {
        this.ptsC3Visit = ptsC3Visit;
    }

    public int getPtsC4Visit() {
        return ptsC4Visit;
    }

    public void setPtsC4Visit(int ptsC4Visit) {
        this.ptsC4Visit = ptsC4Visit;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "idPartido=" + idPartido +
                ", equipoLocal=" + equipoLocal +
                ", equipoVisitante=" + equipoVisitante +
                ", jornada=" + jornada +
                ", ptsC1Local=" + ptsC1Local +
                ", ptsC2Local=" + ptsC2Local +
                ", ptsC3Local=" + ptsC3Local +
                ", ptsC4Local=" + ptsC4Local +
                ", ptsC1Visit=" + ptsC1Visit +
                ", ptsC2Visit=" + ptsC2Visit +
                ", ptsC3Visit=" + ptsC3Visit +
                ", ptsC4Visit=" + ptsC4Visit +
                '}';
    }
}
