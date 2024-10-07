public class Equipo {
    private int idEquipo;
    private String nombreEquipo;
    private String ubicacion;
    private String competicion;

    // Constructor
    public Equipo(int idEquipo, String nombreEquipo, String ubicacion, String competicion) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.ubicacion = ubicacion;
        this.competicion = competicion;
    }

    // Getters y Setters
    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCompeticion() {
        return competicion;
    }

    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombreEquipo='" + nombreEquipo + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", competicion='" + competicion + '\'' +
                '}';
    }
}
