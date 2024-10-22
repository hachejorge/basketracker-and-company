package clasesVO;

public class EquipoFavVO {
    private String nombreUsuario; // Se refiere a la tabla USUARIO
    private int equipo; // Se refiere a la tabla EQUIPO

    // Constructor
    public EquipoFavVO(String nombreUsuario, int equipo) {
        this.nombreUsuario = nombreUsuario;
        this.equipo = equipo;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "EquipoFav{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}
