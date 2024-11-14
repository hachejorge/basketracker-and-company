package clasesVO;

public class PtsJugParVO {
    private int idPartido; // Se refiere a la tabla PARTIDO
    private String nombreUsuario; // Se refiere a la tabla JUGADOR
    private Integer ptsAnt; // Puede ser null
    private Integer trpAnt; // Puede ser null
    private Integer tlbLan; // Puede ser null
    private Integer tlbAnt; // Puede ser null
    private Integer faltas; // Puede ser null
    private Integer mntJd; // Puede ser null

    public PtsJugParVO() {
    	
    }
    
    // Constructor
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
    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getPtsAnt() {
        return ptsAnt;
    }

    public void setPtsAnt(Integer ptsAnt) {
        this.ptsAnt = ptsAnt;
    }

    public Integer getTrpAnt() {
        return trpAnt;
    }

    public void setTrpAnt(Integer trpAnt) {
        this.trpAnt = trpAnt;
    }

    public Integer getTlbLan() {
        return tlbLan;
    }

    public void setTlbLan(Integer tlbLan) {
        this.tlbLan = tlbLan;
    }

    public Integer getTlbAnt() {
        return tlbAnt;
    }

    public void setTlbAnt(Integer tlbAnt) {
        this.tlbAnt = tlbAnt;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    public Integer getMntJd() {
        return mntJd;
    }

    public void setMntJd(Integer mntJd) {
        this.mntJd = mntJd;
    }

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
