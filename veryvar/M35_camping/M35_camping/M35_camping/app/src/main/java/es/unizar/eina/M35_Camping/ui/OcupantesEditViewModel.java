package es.unizar.eina.M35_Camping.ui;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import es.unizar.eina.M35_Camping.database.Ocupantes;
import es.unizar.eina.M35_Camping.database.OcupantesRepository;
import es.unizar.eina.M35_Camping.database.Reserva;

/**
 * ViewModel para gestionar los datos relacionados con los ocupantes de las parcelas en la interfaz de usuario.
 * Facilita la comunicación entre el repositorio de datos y la interfaz de usuario.
 */
public class OcupantesEditViewModel extends AndroidViewModel {

    // Repositorio que maneja las operaciones de datos relacionadas con los ocupantes
    private static OcupantesRepository mRepository;
    // LiveData que observa todos los ocupantes
    private final LiveData<List<Ocupantes>> mAllOcupantes;

    /**
     * Constructor que inicializa el ViewModel, creando un repositorio y obteniendo la lista de todos los ocupantes.
     *
     * @param application Contexto de la aplicación.
     */
    public OcupantesEditViewModel(Application application) {
        super(application);
        // Inicializa el repositorio con el contexto de la aplicación
        mRepository = new OcupantesRepository(application);
        // Asigna el LiveData que contiene todos los ocupantes
        mAllOcupantes = mRepository.getAllOcupantes();  // Obtiene todos los ocupantes desde el repositorio
    }

    /**
     * Devuelve el LiveData que contiene la lista de todos los ocupantes.
     *
     * @return LiveData de la lista de ocupantes.
     */
    public LiveData<List<Ocupantes>> getAllOcupantes() {
        return mAllOcupantes;
    }

    /**
     * Devuelve un LiveData que contiene la lista de ocupantes filtrada por un identificador específico.
     *
     * @param id El identificador para filtrar los ocupantes.
     * @return LiveData con la lista filtrada de ocupantes.
     */
    public LiveData<List<Ocupantes>> getAllOcupantesPorId(int id) {
        return mRepository.getAllOcupantesPorId(id);  // Llama al repositorio para obtener ocupantes por ID
    }

    /**
     * Inserta un nuevo ocupante en el repositorio.
     *
     * @param ocupantes El objeto Ocupantes a insertar.
     */
    public void insert(Ocupantes ocupantes) {
        mRepository.insert(ocupantes);  // Inserta el ocupante en el repositorio
    }

    /**
     * Actualiza un ocupante existente en el repositorio.
     *
     * @param ocupantes El objeto Ocupantes con los nuevos datos.
     */
    public void update(Ocupantes ocupantes) {
        mRepository.update(ocupantes);  // Actualiza el ocupante en el repositorio
    }

    /**
     * Elimina un ocupante del repositorio.
     *
     * @param ocupantes El ocupante a eliminar.
     */
    public static void delete(Ocupantes ocupantes) {
        mRepository.delete(ocupantes);  // Elimina el ocupante del repositorio
    }

    /**
     * Devuelve un LiveData con el valor máximo del ID de los ocupantes.
     *
     * @return LiveData con el valor máximo del ID.
     */
    public LiveData<Integer> getMaxId() {
        return mRepository.getMaxId();  // Obtiene el valor máximo de ID de los ocupantes
    }

    /**
     * Devuelve un LiveData con el ocupante filtrado por ID y nombre de parcela.
     *
     * @param id     El identificador del ocupante.
     * @param parcela El nombre de la parcela.
     * @return LiveData con el ocupante filtrado por ID y nombre de parcela.
     */
    public LiveData<Ocupantes> getOcupantesPorIdYParcela(int id, String parcela) {
        return mRepository.getOcupantesPorIdYParcela(id, parcela);  // Obtiene el ocupante por ID y parcela
    }

    /**
     * Devuelve un LiveData con la lista de ocupantes filtrada por el nombre de la parcela.
     *
     * @param parcela El nombre de la parcela.
     * @return LiveData con la lista de ocupantes filtrada por parcela.
     */
    public LiveData<List<Ocupantes>> getOcupantesPorParcela(String parcela) {
        return mRepository.getOcupantesPorParcela(parcela);  // Obtiene ocupantes filtrados por el nombre de la parcela
    }

    private MediatorLiveData<List<Ocupantes>> getOcupantesPorNombreParcela = new MediatorLiveData<>();

    /**
     * Devuelve un LiveData con un solo ocupante basado en su ID y nombre de parcela.
     * Utiliza un MediatorLiveData para gestionar la emisión del valor cuando esté disponible.
     *
     * @param parcela El nombre de la parcela.
     * @return LiveData con el ocupante correspondiente.
     */
    public LiveData<List<Ocupantes>> singleOcupantesPorNombreParcela(String parcela) {
        // Inicializa el MediatorLiveData que se utilizará para escuchar la fuente de datos
        getOcupantesPorNombreParcela = new MediatorLiveData<>();

        // Obtiene el LiveData que contiene el ocupante filtrado por ID y parcela
        LiveData<List<Ocupantes>> source = getOcupantesPorParcela(parcela);

        // Configura el MediatorLiveData para escuchar este LiveData
        getOcupantesPorNombreParcela.addSource(source, ocupantes -> {
            // Emite el valor de ocupante cuando se obtiene el dato
            getOcupantesPorNombreParcela.setValue(ocupantes);
            // Remueve la fuente después de la primera emisión para evitar cambios adicionales
            getOcupantesPorNombreParcela.removeSource(source);
        });

        return getOcupantesPorNombreParcela;  // Devuelve el LiveData con el ocupante
    }

    // MediatorLiveData para gestionar un solo ocupante por ID y nombre de parcela.
    private MediatorLiveData<Ocupantes> getsingleNumeroOcupantesPorIdYParcela = new MediatorLiveData<>();

    /**
     * Devuelve un LiveData con un solo ocupante basado en su ID y nombre de parcela.
     * Utiliza un MediatorLiveData para gestionar la emisión del valor cuando esté disponible.
     *
     * @param id     El identificador del ocupante.
     * @param parcela El nombre de la parcela.
     * @return LiveData con el ocupante correspondiente.
     */
    public LiveData<Ocupantes> singleOcupantesPorParcela(int id, String parcela) {
        // Inicializa el MediatorLiveData que se utilizará para escuchar la fuente de datos
        getsingleNumeroOcupantesPorIdYParcela = new MediatorLiveData<>();

        // Obtiene el LiveData que contiene el ocupante filtrado por ID y parcela
        LiveData<Ocupantes> source = getOcupantesPorIdYParcela(id, parcela);

        // Configura el MediatorLiveData para escuchar este LiveData
        getsingleNumeroOcupantesPorIdYParcela.addSource(source, ocupantes -> {
            // Emite el valor de ocupante cuando se obtiene el dato
            getsingleNumeroOcupantesPorIdYParcela.setValue(ocupantes);
            // Remueve la fuente después de la primera emisión para evitar cambios adicionales
            getsingleNumeroOcupantesPorIdYParcela.removeSource(source);
        });

        return getsingleNumeroOcupantesPorIdYParcela;  // Devuelve el LiveData con el ocupante
    }

    // MediatorLiveData para gestionar una lista de ocupantes por ID.
    private MediatorLiveData<List<Ocupantes>> singleOcupantesLiveData = new MediatorLiveData<>();

    /**
     * Devuelve un LiveData con la lista de ocupantes filtrada por un ID específico.
     * Utiliza un MediatorLiveData para emitir los valores solo una vez.
     *
     * @param id El identificador de los ocupantes.
     * @return LiveData con la lista de ocupantes.
     */
    public LiveData<List<Ocupantes>> getSingleOcupantesPorId(int id) {
        // Inicializa el MediatorLiveData que escuchará el LiveData de ocupantes por ID
        singleOcupantesLiveData = new MediatorLiveData<>();

        // Obtiene el LiveData con los ocupantes filtrados por ID
        LiveData<List<Ocupantes>> source = getAllOcupantesPorId(id);

        // Configura el MediatorLiveData para escuchar la fuente
        singleOcupantesLiveData.addSource(source, ocupantes -> {
            // Establece el valor del MediatorLiveData con los ocupantes obtenidos
            singleOcupantesLiveData.setValue(ocupantes);
            // Remueve la fuente después de la primera emisión para evitar actualizaciones posteriores
            singleOcupantesLiveData.removeSource(source);
        });

        return singleOcupantesLiveData;  // Devuelve el LiveData con la lista de ocupantes
    }
}
