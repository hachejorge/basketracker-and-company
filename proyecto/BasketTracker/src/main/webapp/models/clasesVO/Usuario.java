public class Usuario {
    private String nombreUsuario;
    private String correoElect;
    private String password;

    // Constructor
    public Usuario(String nombreUsuario, String correoElect, String password) {
        this.nombreUsuario = nombreUsuario;
        this.correoElect = correoElect;
        this.password = password;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElect() {
        return correoElect;
    }

    public void setCorreoElect(String correoElect) {
        this.correoElect = correoElect;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", correoElect='" + correoElect + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
