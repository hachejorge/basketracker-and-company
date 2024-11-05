package clasesVO;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Locale;

public class MensajeVO {
    private int idMensaje;
    private String nombreUsuario; // Se refiere a la tabla JUGADOR
    private String mensaje;
    private Time hora;
    private Date fecha;

    // Constructor
    public MensajeVO(int idMensaje, String nombreUsuario, String mensaje, Time hora, Date fecha) {
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
    
    public String formatFecha() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.fecha);

        // Obtener el día y el mes
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String mes = new SimpleDateFormat("MMM", new Locale("es", "ES")).format(this.fecha);

        return String.format("%02d %s", dia, mes);
    }
    // Método para convertir la hora a formato "hh:mm"
    public String formatHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // Formato 24 horas
        return sdf.format(this.hora);
    }


    public boolean esEliminable() {
        // Convertir `fecha` a `LocalDate` y `hora` a `LocalTime`
        LocalDate localDate = fecha.toLocalDate();
        LocalTime localTime = hora.toLocalTime();

        // Combinar `LocalDate` y `LocalTime` en `LocalDateTime`
        LocalDateTime fechaHoraEnvio = LocalDateTime.of(localDate, localTime);

        // Calcula la diferencia en minutos entre la hora de envío y la hora actual
        Duration duracion = Duration.between(fechaHoraEnvio, LocalDateTime.now());
        return duracion.toMinutes() <= 15;
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
