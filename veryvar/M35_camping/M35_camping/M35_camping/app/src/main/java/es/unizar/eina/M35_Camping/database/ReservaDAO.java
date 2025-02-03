package es.unizar.eina.M35_Camping.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definición de un Data Access Object para las reservas */
@Dao
public interface ReservaDAO {

    /**
     * Inserta una nueva reserva en la base de datos.
     * Si hay un conflicto (por ejemplo, un ID duplicado), se ignora la inserción.
     *
     * @param reserva La reserva a insertar.
     * @return El ID de la fila insertada.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Reserva reserva);

    /**
     * Actualiza una reserva en la base de datos.
     *
     * @param reserva La reserva a actualizar.
     * @return El número de filas afectadas.
     */
    @Update
    int update(Reserva reserva);

    /**
     * Elimina una reserva de la base de datos.
     *
     * @param reserva La reserva a eliminar.
     * @return El número de filas afectadas.
     */
    @Delete
    int delete(Reserva reserva);

    /**
     * Elimina todas las reservas de la base de datos.
     */
    @Query("DELETE FROM reserva")
    void deleteAll();

    /**
     * Elimina una reserva de la base de datos usando su ID.
     *
     * @param id El ID de la reserva a eliminar.
     */
    @Query("DELETE FROM reserva WHERE id = :id")
    void deleteById(int id);

    /**
     * Obtiene una reserva por su ID.
     *
     * @param id El ID de la reserva a recuperar.
     * @return La reserva correspondiente.
     */
    @Query("SELECT * FROM reserva WHERE id = :id")
    LiveData<Reserva> getReservaById(int id);

    /**
     * Obtiene todas las reservas.
     *
     * @return Una lista de todas las reservas en la base de datos.
     */
    @Query("SELECT * FROM reserva")
    LiveData<List<Reserva>> getAllReservas();

    /**
     * Obtiene las reservas que coinciden con una parcela y un rango de fechas.
     *
     * @param parcela El nombre de la parcela.
     * @param fechaEntrada La fecha de entrada.
     * @param fechaSalida La fecha de salida.
     * @return Una lista de reservas que coinciden con los criterios especificados.
     */
    @Query("SELECT r.* FROM reserva r " +
            "JOIN ocupantes o ON r.id = o.reservaid " +
            "WHERE o.parcelanombre = :parcela " +
            "AND (( :fechaEntrada <= r.fechEnt AND :fechaSalida >= r.fechEnt)  " +
            "     OR (:fechaEntrada >= r.fechEnt AND :fechaSalida <= r.fechSal) " +
            "     OR (:fechaEntrada <= r.fechSal AND :fechaSalida >= r.fechSal) " +
            "     OR (:fechaEntrada <= r.fechEnt AND :fechaSalida >= r.fechSal))")
    LiveData<List<Reserva>> getReservasPorParcelaYFechas(String parcela, String fechaEntrada, String fechaSalida);
}
