public class Comentario {
    private String nombreUsuario; // Se refiere a la tabla USUARIO
    private int idPartido; // Se refiere a la tabla PARTIDO
    private String comentario;

    // Constructor
    public Comentario(String nombreUsuario, int idPartido, String comentario) {
        this.nombreUsuario = nombreUsuario;
        this.idPartido = idPartido;
        this.comentario = comentario;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", idPartido=" + idPartido +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
