package es.unizar.eina.M35_Camping.database;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import java.util.Objects;

/**
 * Clase anotada como entidad que representa a los ocupantes de una reserva en una parcela.
 * Se utiliza en una base de datos gestionada con Room.
 */
@Entity(
        tableName = "ocupantes", // Nombre de la tabla en la base de datos
        primaryKeys = {"reservaid", "parcelanombre"}, // Claves primarias compuestas
        foreignKeys = {
                @ForeignKey(
                        entity = Reserva.class,   // Clase que representa la tabla de referencia para reservaid
                        parentColumns = "id",     // Columna clave primaria en la tabla Reserva
                        childColumns = "reservaid", // Columna en Ocupantes
                        onDelete = CASCADE         // Acción al eliminar la reserva (CASCADE elimina los ocupantes)
                ),
                @ForeignKey(
                        entity = Parcela.class,   // Clase que representa la tabla de referencia para parcelanombre
                        parentColumns = "nombre", // Columna clave primaria en la tabla Parcela
                        childColumns = "parcelanombre", // Columna en Ocupantes
                        onDelete = CASCADE        // Acción al eliminar la parcela (CASCADE elimina los ocupantes)
                )
        },
        indices = {
                @Index(value = "parcelanombre") // Crea un índice en la columna parcelanombre
        }
)
public class Ocupantes {

    @NonNull
    @ColumnInfo(name = "reservaid") // Define la columna reservaid en la tabla
    private Integer reservaid;

    @NonNull
    @ColumnInfo(name = "parcelanombre") // Define la columna parcelanombre en la tabla
    private String parcelanombre;

    @ColumnInfo(name = "ocp") // Define la columna ocp en la tabla
    private Integer ocp;

    @ColumnInfo(name = "precioparcela") // Define la columna precioparcela en la tabla
    private Double precioparcela;

    /**
     * Constructor de la clase Ocupantes.
     *
     * @param reservaid      ID de la reserva asociada.
     * @param parcelanombre  Nombre de la parcela ocupada.
     * @param ocp            Número de ocupantes.
     * @param precioparcela  Precio asociado a la parcela.
     */
    public Ocupantes(@NonNull Integer reservaid, @NonNull String parcelanombre, Integer ocp, Double precioparcela) {
        this.reservaid = reservaid;
        this.parcelanombre = parcelanombre;
        this.ocp = ocp;
        this.precioparcela = precioparcela;
    }

    /**
     * Devuelve el ID de la reserva.
     *
     * @return ID de la reserva.
     */
    @NonNull
    public Integer getReservaid() {
        return this.reservaid;
    }

    /**
     * Devuelve el nombre de la parcela ocupada.
     *
     * @return Nombre de la parcela.
     */
    @NonNull
    public String getParcelanombre() {
        return this.parcelanombre;
    }

    /**
     * Devuelve el precio asociado a la parcela ocupada.
     *
     * @return Precio de la parcela.
     */
    public Double getPrecioparcela() {
        return precioparcela;
    }

    /**
     * Devuelve el número de ocupantes en la reserva.
     *
     * @return Número de ocupantes.
     */
    public Integer getOcp() {
        return this.ocp;
    }

    /**
     * Implementación del método equals para comparar cada atributo relevante de Ocupantes.
     *
     * @param o Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ocupantes ocupantes = (Ocupantes) o;
        return Objects.equals(reservaid, ocupantes.reservaid) &&
                Objects.equals(parcelanombre, ocupantes.parcelanombre) &&
                Objects.equals(ocp, ocupantes.ocp);
    }

}
