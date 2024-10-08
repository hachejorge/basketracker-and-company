public class CompeticionFav {
    private String nombreUsuario; // Se refiere a la tabla USUARIO
    private String competicion; // Se refiere a la tabla COMPETICION

    // Constructor
    public CompeticionFav(String nombreUsuario, String competicion) {
        this.nombreUsuario = nombreUsuario;
        this.competicion = competicion;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCompeticion() {
        return competicion;
    }

    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    @Override
    public String toString() {
        return "CompeticionFav{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", competicion='" + competicion + '\'' +
                '}';
    }
}
