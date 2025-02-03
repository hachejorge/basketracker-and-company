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
 * Data Access Object (DAO) para gestionar la tabla "parcela".
 * Proporciona métodos para realizar operaciones CRUD (crear, leer, actualizar, eliminar)
 * y consultas personalizadas sobre la entidad Parcela.
 */
@Dao
public interface ParcelaDAO {

    /**
     * Inserta una nueva parcela en la tabla "parcela".
     * Si el registro ya existe, se ignora la operación.
     *
     * @param parcela Objeto Parcela a insertar.
     * @return ID del registro insertado, o -1 si se ignoró.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Parcela parcela);

    /**
     * Actualiza un registro existente en la tabla "parcela".
     *
     * @param parcela Objeto Parcela con los datos actualizados.
     * @return Número de registros actualizados (debería ser 1 si se actualizó correctamente).
     */
    @Update
    int update(Parcela parcela);

    /**
     * Elimina un registro específico de la tabla "parcela".
     *
     * @param parcela Objeto Parcela a eliminar.
     * @return Número de registros eliminados (debería ser 1 si se eliminó correctamente).
     */
    @Delete
    int delete(Parcela parcela);

    /**
     * Elimina todos los registros de la tabla "parcela".
     */
    @Query("DELETE FROM parcela")
    void deleteAll();

    /**
     * Obtiene todos los registros de la tabla "parcela".
     *
     * @return Lista de todas las parcelas envuelta en un LiveData.
     */
    @Query("SELECT * FROM parcela")
    LiveData<List<Parcela>> getAllParcelas();

    /**
     * Obtiene los nombres de todas las parcelas.
     *
     * @return Lista de nombres de parcelas envuelta en un LiveData.
     */
    @Query("SELECT nombre FROM parcela")
    LiveData<List<String>> getAllParcelasNames();

    /**
     * Obtiene una parcela específica por su nombre.
     *
     * @param nombre Nombre de la parcela.
     * @return Parcela correspondiente envuelta en un LiveData.
     */
    @Query("SELECT * FROM parcela WHERE nombre = :nombre")
    LiveData<Parcela> getParcelaByName(String nombre);
}
