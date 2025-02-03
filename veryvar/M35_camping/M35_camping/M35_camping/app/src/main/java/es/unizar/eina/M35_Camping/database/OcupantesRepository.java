package es.unizar.eina.M35_Camping.database;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Repositorio para gestionar operaciones con la entidad "ocupantes".
 * Se encarga de interactuar con el DAO de Ocupantes y proporcionar un punto único de acceso
 * a los datos relacionados con la tabla "ocupantes".
 */
public class OcupantesRepository {

    private final OcupantesDAO mOcupantesDao; // Data Access Object (DAO) para Ocupantes
    private final LiveData<List<Ocupantes>> mAllOcupantes; // Lista en tiempo real de todos los ocupantes
    private final ExecutorService executor = Executors.newSingleThreadExecutor(); // Ejecuta operaciones en segundo plano

    /**
     * Constructor del repositorio que inicializa el DAO y la lista de ocupantes.
     *
     * @param application Contexto de la aplicación, utilizado para obtener la base de datos.
     */
    public OcupantesRepository(Application application) {
        // Obtiene la base de datos e inicializa el DAO de Ocupantes
        ParcelaRoomDatabase db = ParcelaRoomDatabase.getDatabase(application);
        mOcupantesDao = db.OcupantesDao();
        mAllOcupantes = mOcupantesDao.getAllOcupantes();
    }

    /**
     * Inserta un nuevo registro en la tabla "ocupantes" en segundo plano.
     *
     * @param ocupantes Objeto Ocupantes a insertar.
     */
    public void insert(Ocupantes ocupantes) {
        executor.execute(() -> mOcupantesDao.insert(ocupantes));
    }

    /**
     * Actualiza un registro existente en la tabla "ocupantes" en segundo plano.
     *
     * @param ocupantes Objeto Ocupantes con los datos actualizados.
     */
    public void update(Ocupantes ocupantes) {
        executor.execute(() -> mOcupantesDao.update(ocupantes));
    }

    /**
     * Elimina un registro específico de la tabla "ocupantes" en segundo plano.
     *
     * @param ocupantes Objeto Ocupantes a eliminar.
     */
    public void delete(Ocupantes ocupantes) {
        executor.execute(() -> mOcupantesDao.delete(ocupantes));
    }

    /**
     * Obtiene el valor máximo de la columna "reservaid" en la tabla "ocupantes".
     *
     * @return Un LiveData con el valor máximo de "reservaid".
     */
    public LiveData<Integer> getMaxId() {
        MutableLiveData<Integer> maxIdLiveData = new MutableLiveData<>();
        executor.execute(() -> {
            int maxId = mOcupantesDao.getMaxId();
            // Actualiza el LiveData en el hilo principal
            maxIdLiveData.postValue(maxId);
        });
        return maxIdLiveData;
    }

    /**
     * Obtiene los ocupantes asociados a un nombre de parcela específico.
     *
     * @param parcela Nombre de la parcela.
     * @return Lista de ocupantes de la parcela envuelta en un LiveData.
     */
    public LiveData<List<Ocupantes>> getOcupantesPorParcela(String parcela) {
        return mOcupantesDao.getOcupantesPorParcela(parcela);
    }

    /**
     * Obtiene un ocupante específico por ID de reserva y nombre de parcela.
     *
     * @param id      ID de la reserva.
     * @param parcela Nombre de la parcela.
     * @return El ocupante correspondiente envuelto en un LiveData.
     */
    public LiveData<Ocupantes> getOcupantesPorIdYParcela(int id, String parcela) {
        return mOcupantesDao.getOcupantesPorIdYParcela(id, parcela);
    }

    /**
     * Obtiene todos los registros de ocupantes en la tabla "ocupantes".
     *
     * @return Lista de todos los ocupantes envuelta en un LiveData.
     */
    public LiveData<List<Ocupantes>> getAllOcupantes() {
        return mAllOcupantes;
    }

    /**
     * Obtiene los registros de ocupantes que coincidan con un ID de reserva específico.
     *
     * @param id ID de la reserva.
     * @return Lista de ocupantes asociados al ID envuelta en un LiveData.
     */
    public LiveData<List<Ocupantes>> getAllOcupantesPorId(int id) {
        return mOcupantesDao.getAllOcupantesPorId(id);
    }
}
