package es.unizar.eina.M35_Camping.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Data Access Object (DAO) para gestionar la tabla "ocupantes".
 * Proporciona métodos para realizar operaciones CRUD (crear, leer, actualizar, eliminar)
 * y consultas personalizadas en la base de datos.
 */
@Dao
public interface OcupantesDAO {

    /**
     * Inserta un nuevo registro en la tabla "ocupantes".
     * Si el registro ya existe, se ignora la operación.
     *
     * @param ocupantes Objeto Ocupantes a insertar.
     * @return ID del registro insertado, o -1 si se ignoró.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Ocupantes ocupantes);

    /**
     * Actualiza un registro existente en la tabla "ocupantes".
     *
     * @param ocupantes Objeto Ocupantes con los datos actualizados.
     * @return Número de registros actualizados (debería ser 1 si se actualizó correctamente).
     */
    @Update
    int update(Ocupantes ocupantes);

    /**
     * Elimina un registro específico de la tabla "ocupantes".
     *
     * @param ocupantes Objeto Ocupantes a eliminar.
     * @return Número de registros eliminados (debería ser 1 si se eliminó correctamente).
     */
    @Delete
    int delete(Ocupantes ocupantes);

    /**
     * Elimina todos los registros de la tabla "ocupantes".
     */
    @Query("DELETE FROM ocupantes")
    void deleteAll();

    /**
     * Obtiene todos los registros de la tabla "ocupantes".
     *
     * @return Lista de todos los ocupantes envuelta en un LiveData.
     */
    @Query("SELECT * FROM ocupantes")
    LiveData<List<Ocupantes>> getAllOcupantes();

    /**
     * Obtiene todos los registros de la tabla "ocupantes" que coincidan con el ID de reserva especificado.
     *
     * @param id ID de la reserva.
     * @return Lista de ocupantes asociada al ID de reserva envuelta en un LiveData.
     */
    @Query("SELECT * FROM ocupantes WHERE reservaid = :id")
    LiveData<List<Ocupantes>> getAllOcupantesPorId(int id);

    /**
     * Obtiene el valor máximo de la columna "reservaid" en la tabla "ocupantes".
     *
     * @return El valor máximo de "reservaid".
     */
    @Query("SELECT MAX(reservaid) FROM ocupantes")
    int getMaxId();

    /**
     * Obtiene todos los registros de la tabla "ocupantes" asociados a un nombre de parcela específico.
     *
     * @param parcela Nombre de la parcela.
     * @return Lista de ocupantes asociados a la parcela envuelta en un LiveData.
     */
    @Query("SELECT * FROM ocupantes WHERE parcelanombre = :parcela")
    LiveData<List<Ocupantes>> getOcupantesPorParcela(String parcela);

    /**
     * Obtiene un registro único de la tabla "ocupantes" que coincida con el ID de reserva y el nombre de la parcela especificados.
     *
     * @param id      ID de la reserva.
     * @param parcela Nombre de la parcela.
     * @return Ocupantes asociado al ID y parcela envuelto en un LiveData.
     */
    @Query("SELECT * FROM ocupantes WHERE reservaid = :id AND parcelanombre = :parcela")
    LiveData<Ocupantes> getOcupantesPorIdYParcela(int id, String parcela);
}
