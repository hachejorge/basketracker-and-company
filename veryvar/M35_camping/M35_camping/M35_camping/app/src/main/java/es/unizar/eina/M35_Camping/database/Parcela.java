package es.unizar.eina.M35_Camping.database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Clase anotada como entidad que representa una parcela.
 * Contiene información sobre su nombre, descripción, número máximo de ocupantes y precio por persona.
 */
@Entity(tableName = "parcela") // Define la tabla "parcela" en la base de datos
public class Parcela {

    // Constantes para identificar acciones específicas
    public static final int DELETE_ID = 1;
    public static final int EDIT_ID = 2;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nombre") // Define la columna "nombre" como clave primaria
    private String nombre;

    @ColumnInfo(name = "desc") // Define la columna "desc" para la descripción de la parcela
    private String des;

    @ColumnInfo(name = "maxOcup") // Define la columna "maxOcup" para el número máximo de ocupantes
    private Integer maxOcup;

    @ColumnInfo(name = "precPer") // Define la columna "precPer" para el precio por persona
    private Float precPer;

    /**
     * Constructor de la clase Parcela.
     *
     * @param nombre  Nombre único de la parcela.
     * @param des     Descripción de la parcela.
     * @param maxOcup Número máximo de ocupantes permitidos en la parcela.
     * @param precPer Precio por persona en la parcela.
     */
    public Parcela(@NonNull String nombre, String des, Integer maxOcup, Float precPer) {
        this.nombre = nombre;
        this.des = des;
        this.maxOcup = maxOcup;
        this.precPer = precPer;
    }


    /**
     * Devuelve el nombre de la parcela.
     *
     * @return Nombre de la parcela.
     */
    @NonNull
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve la descripción de la parcela.
     *
     * @return Descripción de la parcela.
     */
    public String getDes() {
        return this.des;
    }

    /**
     * Devuelve el número máximo de ocupantes permitidos en la parcela.
     *
     * @return Número máximo de ocupantes.
     */
    public Integer getMaxOcup() {
        return this.maxOcup;
    }

    /**
     * Devuelve el precio por persona de la parcela.
     *
     * @return Precio por persona.
     */
    public Float getPrecPer() {
        return this.precPer;
    }

    /**
     * Implementación de equals() para comparar cada atributo relevante de Parcela.
     *
     * @param o Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcela parcela = (Parcela) o;
        return nombre.equals(parcela.nombre) &&
                Objects.equals(des, parcela.des) &&
                Objects.equals(maxOcup, parcela.maxOcup) &&
                Objects.equals(precPer, parcela.precPer);
    }

    /**
     * Implementación de hashCode() para garantizar la coherencia con equals().
     *
     * @return Código hash basado en los atributos de la parcela.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, des, maxOcup, precPer);
    }
}
