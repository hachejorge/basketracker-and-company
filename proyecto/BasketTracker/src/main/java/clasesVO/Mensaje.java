import java.sql.Date;
import java.sql.Time;

public class Mensaje {
    private int idMensaje;
    private String nombreUsuario; // Se refiere a la tabla JUGADOR
    private String mensaje;
    private Time hora;
    private Date fecha;

    // Constructor
    public Mensaje(int idMensaje, String nombreUsuario, String mensaje, Time hora, Date fecha) {
        this.idMensaje = idMensaje;
        this.nombreUsuario = nombreUsuario;
        this.mensaje = mensaje;
        this.hora = hora;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "idMensaje=" + idMensaje +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", hora=" + hora +
                ", fecha=" + fecha +
                '}';
    }
}
