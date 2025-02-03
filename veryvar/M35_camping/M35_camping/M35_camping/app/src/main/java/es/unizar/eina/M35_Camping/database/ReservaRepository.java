package es.unizar.eina.M35_Camping.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Esta clase es responsable de gestionar las operaciones de base de datos relacionadas con las reservas.
 * Utiliza un patrón de repositorio para separar la lógica de acceso a datos de la lógica de negocio.
 * La clase se comunica con la base de datos a través de la interfaz {@link ReservaDAO}.
 *
 * La clase también maneja operaciones asíncronas utilizando un {@link ExecutorService} para
 * evitar bloquear el hilo principal de la aplicación.
 */
public class ReservaRepository {
    private final ReservaDAO mReservaDao;
    private final LiveData<List<Reserva>> mAllReservas;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final long TIMEOUT = 15000;

    /**
     * Constructor que inicializa el repositorio. Obtiene la instancia de la base de datos
     * y el DAO para interactuar con las reservas.
     *
     * @param application El contexto de la aplicación.
     */
    public ReservaRepository(Application application) {
        // Usa ParcelaRoomDatabase para obtener el DAO de reservas
        ParcelaRoomDatabase db = ParcelaRoomDatabase.getDatabase(application);
        mReservaDao = db.ReservaDao();
        mAllReservas = mReservaDao.getAllReservas();
    }

    /**
     * Inserta una nueva reserva en la base de datos de manera asíncrona.
     *
     * @param reserva La reserva que se quiere insertar.
     * @return Si la nota se ha insertado correctamente, devuelve el identificador de la nota que se ha creado. En caso contrario, devuelve -1 para indicar el fallo.
     */
    public long insert(Reserva reserva) {
        Future<Long> future = ParcelaRoomDatabase.databaseWriteExecutor.submit(
                () -> mReservaDao.insert(reserva));
        try {
            return future.get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException ex) {
            Log.d("ReservaRepository", ex.getClass().getSimpleName() + ex.getMessage());
            return -1;
        }
    }

    /**
     * Actualiza una reserva existente en la base de datos de manera asíncrona.
     *
     * @param reserva La reserva con los datos actualizados.
     * @return Un valor entero con el número de filas eliminadas:
     *   - 1 si el identificador se corresponde con una nota previamente insertada.
     *   - 0 si no existe previamente una nota con ese identificado o el identificador no es un valor aceptable.
     */
    public int update(Reserva reserva) {
        Future<Integer> future = ParcelaRoomDatabase.databaseWriteExecutor.submit(
                () -> mReservaDao.update(reserva));
        try {
            return future.get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException ex) {
            Log.d("ReservaRepository", ex.getClass().getSimpleName() + ex.getMessage());
            return -1;
        }
    }

    /**
     * Elimina una reserva de la base de datos de manera asíncrona.
     *
     * @param reserva La reserva que se desea eliminar.
     * @return Un valor entero con el número de filas eliminadas:
     *   - 1 si el identificador se corresponde con una nota previamente insertada.
     *   - 0 si no existe previamente una nota con ese identificado o el identificador no es un valor aceptable.
     */
    public int delete(Reserva reserva) {
        Future<Integer> future = ParcelaRoomDatabase.databaseWriteExecutor.submit(
                () -> mReservaDao.delete(reserva));
        try {
            return future.get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException ex) {
            Log.d("ReservaRepository", ex.getClass().getSimpleName() + ex.getMessage());
            return -1;
        }
    }

    /**
     * Elimina una reserva de la base de datos utilizando su ID de manera asíncrona.
     *
     * @param id El ID de la reserva que se desea eliminar.
     */
    public void deleteById(int id) {
        executor.execute(() -> mReservaDao.deleteById(id));
    }

    /**
     * Obtiene todas las reservas de la base de datos.
     * Devuelve un {@link LiveData} para observar los cambios en la lista de reservas.
     *
     * @return Un objeto {@link LiveData} que contiene una lista de todas las reservas.
     */
    public LiveData<List<Reserva>> getAllReservas() {
        return mAllReservas;
    }

    /**
     * Obtiene las reservas que están asociadas con una parcela y un rango de fechas especificado.
     * Devuelve un {@link LiveData} para observar los cambios en las reservas.
     *
     * @param parcela El nombre de la parcela que se desea consultar.
     * @param fechaEntrada La fecha de entrada para el rango de reservas.
     * @param fechaSalida La fecha de salida para el rango de reservas.
     * @return Un objeto {@link LiveData} que contiene una lista de reservas filtradas por parcela y fechas.
     */
    public LiveData<List<Reserva>> getReservasPorParcelaYFechas(String parcela, String fechaEntrada, String fechaSalida) {
        return mReservaDao.getReservasPorParcelaYFechas(parcela, fechaEntrada, fechaSalida);
    }

    /**
     * Obtiene una reserva específica por su ID.
     *
     * @param id El ID de la reserva que se desea obtener.
     * @return Un objeto {@link LiveData} que contiene la reserva correspondiente al ID.
     */
    public LiveData<Reserva> getReservaById(int id) {
        return mReservaDao.getReservaById(id);
    }
}
