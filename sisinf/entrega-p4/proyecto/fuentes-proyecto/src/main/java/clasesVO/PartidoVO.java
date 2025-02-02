package clasesVO;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.sql.Date;

/**
 * Clase que representa un partido entre dos equipos en el sistema.
 * Contiene información sobre los equipos participantes, la jornada,
 * los puntos obtenidos en cada cuarto y la fecha y hora del partido.
 */
public class PartidoVO {
    
    /** Identificador único del partido. */
    private int idPartido;
    
    /** Identificador del equipo local. Referencia a la tabla {@code EQUIPO}. */
    private int equipoLocal;
    
    /** Identificador del equipo visitante. Referencia a la tabla {@code EQUIPO}. */
    private int equipoVisitante;
    
    /** La jornada en la que se juega el partido. */
    private int jornada;
    
    /** Puntos del primer cuarto del equipo local. */
    private int ptsC1Local;
    
    /** Puntos del segundo cuarto del equipo local. */
    private int ptsC2Local;
    
    /** Puntos del tercer cuarto del equipo local. */
    private int ptsC3Local;
    
    /** Puntos del cuarto cuarto del equipo local. */
    private int ptsC4Local;
    
    /** Puntos del primer cuarto del equipo visitante. */
    private int ptsC1Visit;
    
    /** Puntos del segundo cuarto del equipo visitante. */
    private int ptsC2Visit;
    
    /** Puntos del tercer cuarto del equipo visitante. */
    private int ptsC3Visit;
    
    /** Puntos del cuarto cuarto del equipo visitante. */
    private int ptsC4Visit;
    
    /** La hora en que se juega el partido. */
    private Time hora;
    
    /** La fecha en que se juega el partido. */
    private Date fecha;

    /**
     * Constructor para inicializar un objeto {@code PartidoVO} con los valores proporcionados.
     * 
     * @param idPartido El identificador único del partido.
     * @param equipoLocal El identificador del equipo local.
     * @param equipoVisitante El identificador del equipo visitante.
     * @param jornada La jornada en la que se juega el partido.
     * @param ptsC1Local Puntos del primer cuarto del equipo local.
     * @param ptsC2Local Puntos del segundo cuarto del equipo local.
     * @param ptsC3Local Puntos del tercer cuarto del equipo local.
     * @param ptsC4Local Puntos del cuarto cuarto del equipo local.
     * @param ptsC1Visit Puntos del primer cuarto del equipo visitante.
     * @param ptsC2Visit Puntos del segundo cuarto del equipo visitante.
     * @param ptsC3Visit Puntos del tercer cuarto del equipo visitante.
     * @param ptsC4Visit Puntos del cuarto cuarto del equipo visitante.
     * @param hora La hora en que se juega el partido.
     * @param fecha La fecha en que se juega el partido.
     */
    public PartidoVO(int idPartido, int equipoLocal, int equipoVisitante, int jornada,
                     int ptsC1Local, int ptsC2Local, int ptsC3Local, int ptsC4Local,
                     int ptsC1Visit, int ptsC2Visit, int ptsC3Visit, int ptsC4Visit,
                     Time hora, Date fecha) {
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
        this.hora = hora;
        this.fecha = fecha;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del partido.
     * 
     * @return El identificador del partido.
     */
    public int getIdPartido() { return idPartido; }

    /**
     * Establece el identificador único del partido.
     * 
     * @param idPartido El identificador del partido.
     */
    public void setIdPartido(int idPartido) { this.idPartido = idPartido; }

    /**
     * Obtiene el identificador del equipo local.
     * 
     * @return El identificador del equipo local.
     */
    public int getEquipoLocal() { return equipoLocal; }

    /**
     * Establece el identificador del equipo local.
     * 
     * @param equipoLocal El identificador del equipo local.
     */
    public void setEquipoLocal(int equipoLocal) { this.equipoLocal = equipoLocal; }

    /**
     * Obtiene el identificador del equipo visitante.
     * 
     * @return El identificador del equipo visitante.
     */
    public int getEquipoVisitante() { return equipoVisitante; }

    /**
     * Establece el identificador del equipo visitante.
     * 
     * @param equipoVisitante El identificador del equipo visitante.
     */
    public void setEquipoVisitante(int equipoVisitante) { this.equipoVisitante = equipoVisitante; }

    /**
     * Obtiene la jornada en la que se juega el partido.
     * 
     * @return La jornada del partido.
     */
    public int getJornada() { return jornada; }

    /**
     * Establece la jornada en la que se juega el partido.
     * 
     * @param jornada La jornada del partido.
     */
    public void setJornada(int jornada) { this.jornada = jornada; }

    /**
     * Obtiene los puntos del primer cuarto del equipo local.
     * 
     * @return Los puntos del primer cuarto del equipo local.
     */
    public int getPtsC1Local() { return ptsC1Local; }

    /**
     * Establece los puntos del primer cuarto del equipo local.
     * 
     * @param ptsC1Local Los puntos del primer cuarto del equipo local.
     */
    public void setPtsC1Local(int ptsC1Local) { this.ptsC1Local = ptsC1Local; }

    /**
     * Obtiene los puntos del segundo cuarto del equipo local.
     * 
     * @return Los puntos del segundo cuarto del equipo local.
     */
    public int getPtsC2Local() { return ptsC2Local; }

    /**
     * Establece los puntos del segundo cuarto del equipo local.
     * 
     * @param ptsC2Local Los puntos del segundo cuarto del equipo local.
     */
    public void setPtsC2Local(int ptsC2Local) { this.ptsC2Local = ptsC2Local; }

    /**
     * Obtiene los puntos del tercer cuarto del equipo local.
     * 
     * @return Los puntos del tercer cuarto del equipo local.
     */
    public int getPtsC3Local() { return ptsC3Local; }

    /**
     * Establece los puntos del tercer cuarto del equipo local.
     * 
     * @param ptsC3Local Los puntos del tercer cuarto del equipo local.
     */
    public void setPtsC3Local(int ptsC3Local) { this.ptsC3Local = ptsC3Local; }

    /**
     * Obtiene los puntos del cuarto cuarto del equipo local.
     * 
     * @return Los puntos del cuarto cuarto del equipo local.
     */
    public int getPtsC4Local() { return ptsC4Local; }

    /**
     * Establece los puntos del cuarto cuarto del equipo local.
     * 
     * @param ptsC4Local Los puntos del cuarto cuarto del equipo local.
     */
    public void setPtsC4Local(int ptsC4Local) { this.ptsC4Local = ptsC4Local; }

    /**
     * Obtiene los puntos del primer cuarto del equipo visitante.
     * 
     * @return Los puntos del primer cuarto del equipo visitante.
     */
    public int getPtsC1Visit() { return ptsC1Visit; }

    /**
     * Establece los puntos del primer cuarto del equipo visitante.
     * 
     * @param ptsC1Visit Los puntos del primer cuarto del equipo visitante.
     */
    public void setPtsC1Visit(int ptsC1Visit) { this.ptsC1Visit = ptsC1Visit; }

    /**
     * Obtiene los puntos del segundo cuarto del equipo visitante.
     * 
     * @return Los puntos del segundo cuarto del equipo visitante.
     */
    public int getPtsC2Visit() { return ptsC2Visit; }

    /**
     * Establece los puntos del segundo cuarto del equipo visitante.
     * 
     * @param ptsC2Visit Los puntos del segundo cuarto del equipo visitante.
     */
    public void setPtsC2Visit(int ptsC2Visit) { this.ptsC2Visit = ptsC2Visit; }

    /**
     * Obtiene los puntos del tercer cuarto del equipo visitante.
     * 
     * @return Los puntos del tercer cuarto del equipo visitante.
     */
    public int getPtsC3Visit() { return ptsC3Visit; }

    /**
     * Establece los puntos del tercer cuarto del equipo visitante.
     * 
     * @param ptsC3Visit Los puntos del tercer cuarto del equipo visitante.
     */
    public void setPtsC3Visit(int ptsC3Visit) { this.ptsC3Visit = ptsC3Visit; }

    /**
     * Obtiene los puntos del cuarto cuarto del equipo visitante.
     * 
     * @return Los puntos del cuarto cuarto del equipo visitante.
     */
    public int getPtsC4Visit() { return ptsC4Visit; }

    /**
     * Establece los puntos del cuarto cuarto del equipo visitante.
     * 
     * @param ptsC4Visit Los puntos del cuarto cuarto del equipo visitante.
     */
    public void setPtsC4Visit(int ptsC4Visit) { this.ptsC4Visit = ptsC4Visit; }

    /**
     * Obtiene la hora en que se juega el partido.
     * 
     * @return La hora del partido.
     */
    public Time getHora() { return hora; }

    /**
     * Establece la hora en que se juega el partido.
     * 
     * @param hora La hora del partido.
     */
    public void setHora(Time hora) { this.hora = hora; }

    /**
     * Obtiene la fecha en que se juega el partido.
     * 
     * @return La fecha del partido.
     */
    public Date getFecha() { return fecha; }

    /**
     * Establece la fecha en que se juega el partido.
     * 
     * @param fecha La fecha del partido.
     */
    public void setFecha(Date fecha) { this.fecha = fecha; }

    /**
     * Devuelve la fecha en formato "dd MMM", por ejemplo "14 Nov".
     * 
     * @return La fecha formateada.
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
     * Devuelve la hora en formato "HH:mm", por ejemplo "14:30".
     * 
     * @return La hora formateada.
     */
    public String formatHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // Formato 24 horas
        return sdf.format(this.hora);
    }

    /**
     * Representación en cadena de texto del objeto {@code PartidoVO}.
     * 
     * @return La representación en cadena del objeto.
     */
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
                ", hora=" + hora +
                ", fecha=" + fecha +
                '}';
    }
}
