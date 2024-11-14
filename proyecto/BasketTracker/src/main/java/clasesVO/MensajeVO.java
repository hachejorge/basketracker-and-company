package clasesVO;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

/**
 * Clase que representa un mensaje enviado por un jugador en el sistema.
 * La clase contiene los atributos y métodos para gestionar la información de los mensajes,
 * como la fecha, la hora, el contenido y el nombre del usuario que lo envió.
 */
public class MensajeVO {
    
    /** Identificador único del mensaje. */
    private int idMensaje;
    
    /** Nombre del jugador que envió el mensaje. Referencia a la tabla {@code JUGADOR}. */
    private String nombreUsuario;
    
    /** El contenido del mensaje enviado. */
    private String mensaje;
    
    /** La hora en que se envió el mensaje. */
    private Time hora;
    
    /** La fecha en que se envió el mensaje. */
    private Date fecha;

    /**
     * Constructor para inicializar un objeto {@code MensajeVO} con los valores proporcionados.
     * 
     * @param idMensaje El identificador único del mensaje.
     * @param nombreUsuario El nombre del jugador que envió el mensaje.
     * @param mensaje El contenido del mensaje.
     * @param hora La hora en que se envió el mensaje.
     * @param fecha La fecha en que se envió el mensaje.
     */
    public MensajeVO(int idMensaje, String nombreUsuario, String mensaje, Time hora, Date fecha) {
        this.idMensaje = idMensaje;
        this.nombreUsuario = nombreUsuario;
        this.mensaje = mensaje;
        this.hora = hora;
        this.fecha = fecha;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del mensaje.
     * 
     * @return El identificador del mensaje.
     */
    public int getIdMensaje() {
        return idMensaje;
    }

    /**
     * Establece el identificador único del mensaje.
     * 
     * @param idMensaje El identificador del mensaje.
     */
    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    /**
     * Obtiene el nombre del jugador que envió el mensaje.
     * 
     * @return El nombre del jugador.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre del jugador que envió el mensaje.
     * 
     * @param nombreUsuario El nombre del jugador.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el contenido del mensaje.
     * 
     * @return El contenido del mensaje.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el contenido del mensaje.
     * 
     * @param mensaje El contenido del mensaje.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene la hora en que se envió el mensaje.
     * 
     * @return La hora del mensaje.
     */
    public Time getHora() {
        return hora;
    }

    /**
     * Establece la hora en que se envió el mensaje.
     * 
     * @param hora La hora del mensaje.
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * Obtiene la fecha en que se envió el mensaje.
     * 
     * @return La fecha del mensaje.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha en que se envió el mensaje.
     * 
     * @param fecha La fecha del mensaje.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Formatea la fecha del mensaje a un formato legible.
     * 
     * @return La fecha del mensaje en formato "dd MMM" (por ejemplo, "05 Ene").
     */
    public String formatFecha() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.fecha);

        // Obtener el día y el mes
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String mes = new SimpleDateFormat("MMM", new Locale("es", "ES")).format(this.fecha);

        return String.format("%02d %s", dia, mes);
    }

    /**
     * Formatea la hora del mensaje a un formato de 24 horas (HH:mm).
     * 
     * @return La hora del mensaje en formato "HH:mm" (por ejemplo, "15:30").
     */
    public String formatHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // Formato 24 horas
        return sdf.format(this.hora);
    }

    /**
     * Verifica si el mensaje puede ser eliminado según el tiempo transcurrido.
     * Un mensaje es eliminable si fue enviado en los últimos 15 minutos.
     * 
     * @return {@code true} si el mensaje puede ser eliminado, {@code false} si no.
     */
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

    /**
     * Devuelve una representación en cadena del objeto {@code MensajeVO}.
     * 
     * @return Una cadena que representa al objeto {@code MensajeVO}.
     */
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
