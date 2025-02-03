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
 * Repositorio para gestionar operaciones con la entidad "parcela".
 * Actúa como un punto único de acceso a los datos relacionados con la tabla "parcela"
 * e interactúa con el DAO correspondiente.
 */
public class ParcelaRepository {

    private final ParcelaDAO mParcelaDao; // DAO para la tabla "parcela"
    private final LiveData<List<Parcela>> mAllParcelas; // LiveData con todas las parcelas
    private final ExecutorService executor = Executors.newSingleThreadExecutor(); // Executor para operaciones en segundo plano
    private final long TIMEOUT = 15000;

    /**
     * Constructor del repositorio que inicializa el DAO y obtiene la lista de parcelas.
     *
     * @param application Contexto de la aplicación, utilizado para inicializar la base de datos.
     * @return Si la nota se ha insertado correctamente, devuelve el identificador de la nota que se ha creado. En caso contrario, devuelve -1 para indicar el fallo.
     */
    public ParcelaRepository(Application application) {
        // Obtiene la base de datos e inicializa el DAO de Parcelas
        ParcelaRoomDatabase db = ParcelaRoomDatabase.getDatabase(application);
        mParcelaDao = db.ParcelaDao();
        mAllParcelas = mParcelaDao.getAllParcelas();
    }

    /**
     * Inserta una nueva parcela en la base de datos en segundo plano.
     *
     * @param parcela Parcela a insertar.
     */
    public long insert(Parcela parcela) {
        Future<Long> future = ParcelaRoomDatabase.databaseWriteExecutor.submit(
                () -> mParcelaDao.insert(parcela));
        try {
            return future.get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException ex) {
            Log.d("ParcelaRepository", ex.getClass().getSimpleName() + ex.getMessage());
            return -1;
        }
    }

    /**
     * Actualiza una parcela existente en la base de datos en segundo plano.
     *
     * @param parcela Parcela con los datos actualizados.
     * @return Un valor entero con el número de filas eliminadas:
     *   - 1 si el identificador se corresponde con una nota previamente insertada.
     *   - 0 si no existe previamente una nota con ese identificado o el identificador no es un valor aceptable.
     */
    public int update(Parcela parcela) {
        Future<Integer> future = ParcelaRoomDatabase.databaseWriteExecutor.submit(
                () -> mParcelaDao.update(parcela));
        try {
            return future.get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException ex) {
            Log.d("ParcelaRepository", ex.getClass().getSimpleName() + ex.getMessage());
            return -1;
        }
    }

    /**
     * Elimina una parcela específica de la base de datos en segundo plano.
     *
     * @param parcela Parcela a eliminar.
     * @return Un valor entero con el número de filas eliminadas:
     *   - 1 si el identificador se corresponde con una nota previamente insertada.
     *   - 0 si no existe previamente una nota con ese identificado o el identificador no es un valor aceptable.
     */
    public int delete(Parcela parcela) {
        Future<Integer> future = ParcelaRoomDatabase.databaseWriteExecutor.submit(
                () -> mParcelaDao.delete(parcela));
        try {
            return future.get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException ex) {
            Log.d("ParcelaRepository", ex.getClass().getSimpleName() + ex.getMessage());
            return -1;
        }
    }

    /**
     * Obtiene todas las parcelas de la base de datos.
     *
     * @return LiveData con la lista de todas las parcelas.
     */
    public LiveData<List<Parcela>> getAllParcelas() {
        return mAllParcelas;
    }

    /**
     * Obtiene una parcela específica por su nombre.
     *
     * @param nombre Nombre de la parcela.
     * @return LiveData con la parcela correspondiente.
     */
    public LiveData<Parcela> getParcelaByName(String nombre) {
        return mParcelaDao.getParcelaByName(nombre);
    }

    /**
     * Obtiene los nombres de todas las parcelas.
     *
     * @return LiveData con la lista de nombres de todas las parcelas.
     */
    public LiveData<List<String>> getAllParcelasNames() {
        return mParcelaDao.getAllParcelasNames();
    }
}
