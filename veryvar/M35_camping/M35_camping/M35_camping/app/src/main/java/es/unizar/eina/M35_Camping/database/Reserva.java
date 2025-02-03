package es.unizar.eina.M35_Camping.database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Clase anotada como entidad que representa una reserva.
 * Contiene información sobre el cliente, su contacto, las fechas de la reserva y el precio total.
 */
@Entity(tableName = "reserva")
public class Reserva {
    public static final int DELETE_ID = 1;
    public static final int EDIT_ID = 2;

    /** Identificador único de la reserva, autogenerado. */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    /** Nombre del cliente asociado a la reserva. */
    @ColumnInfo(name = "cliente")
    private String cliente;

    /** Número de contacto móvil del cliente. */
    @ColumnInfo(name = "movil")
    private Integer movil;

    /** Fecha de entrada en formato de texto (YYYY-MM-DD). */
    @ColumnInfo(name = "fechEnt")
    private String fechEnt;

    /** Fecha de salida en formato de texto (YYYY-MM-DD). */
    @ColumnInfo(name = "fechSal")
    private String fechSal;

    /** Precio total de la reserva. */
    @ColumnInfo(name = "precioTotal")
    private Float precioTotal;

    /**
     * Constructor de la clase sin incluir el ID (se usará para inserciones donde el ID es autogenerado).
     *
     * @param cliente Nombre del cliente.
     * @param movil Número de contacto móvil del cliente.
     * @param fechEnt Fecha de entrada.
     * @param fechSal Fecha de salida.
     */
    @Ignore
    public Reserva(String cliente, Integer movil, String fechEnt, String fechSal) {
        this.cliente = cliente;
        this.movil = movil;
        this.fechEnt = fechEnt;
        this.fechSal = fechSal;
        this.precioTotal = 0.0f;
    }

    /**
     * Constructor que incluye el ID para escenarios donde se especifica manualmente.
     *
     * @param id Identificador único de la reserva.
     * @param cliente Nombre del cliente.
     * @param movil Número de contacto móvil del cliente.
     * @param fechEnt Fecha de entrada.
     * @param fechSal Fecha de salida.
     */
    public Reserva(Integer id, String cliente, int movil, String fechEnt, String fechSal) {
        this.id = id;
        this.cliente = cliente;
        this.movil = movil;
        this.fechEnt = fechEnt;
        this.fechSal = fechSal;
        this.precioTotal = 0.0f;
    }

    /** Devuelve el identificador único de la reserva. */
    @NonNull
    public int getId() {
        return this.id;
    }

    /** Establece el identificador único de la reserva. */
    public void setId(int id) {
        this.id = id;
    }

    /** Devuelve el nombre del cliente asociado a la reserva. */
    public String getCliente() {
        return this.cliente;
    }

    /** Devuelve el número de contacto móvil del cliente. */
    public Integer getMovil() {
        return this.movil;
    }

    /** Devuelve la fecha de entrada de la reserva. */
    public String getFechEnt() {
        return this.fechEnt;
    }

    /** Establece la fecha de entrada de la reserva. */
    public void setFechEnt(String fechEnt) {
        this.fechEnt = fechEnt;
    }

    /** Devuelve la fecha de salida de la reserva. */
    public String getFechSal() {
        return this.fechSal;
    }

    /** Establece la fecha de salida de la reserva. */
    public void setFechSal(String fechSal) {
        this.fechSal = fechSal;
    }

    /** Devuelve el precio total de la reserva. */
    public Float getPrecioTotal() {
        return precioTotal;
    }

    /** Establece el precio total de la reserva. */
    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Implementación de equals() para comparar objetos Reserva basándose en sus atributos.
     *
     * @param o Objeto a comparar.
     * @return true si los objetos son iguales; de lo contrario, false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id) &&
                Objects.equals(cliente, reserva.cliente) &&
                Objects.equals(movil, reserva.movil) &&
                Objects.equals(fechEnt, reserva.fechEnt) &&
                Objects.equals(fechSal, reserva.fechSal);
    }

    /**
     * Implementación de hashCode() para garantizar la coherencia con equals().
     *
     * @return Código hash basado en los atributos de la reserva.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, movil, fechEnt, fechSal);
    }
}
