package es.unizar.eina.M35_Camping.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M35_Camping.database.Ocupantes;
import es.unizar.eina.M35_Camping.database.OcupantesRepository;

/**
 * ViewModel para manejar la lógica de negocio y datos de los ocupantes.
 * Se comunica con el repositorio de ocupantes para obtener o modificar datos.
 */
public class OcupantesViewModel extends AndroidViewModel {

    // Repositorio de ocupantes para interactuar con los datos de la base de datos
    private static OcupantesRepository mRepository;

    // LiveData que contiene una lista de todos los ocupantes
    private final LiveData<List<Ocupantes>> mAllOcupantes;

    /**
     * Constructor del ViewModel. Inicializa el repositorio y la lista de ocupantes.
     *
     * @param application La aplicación que se pasa al ViewModel para acceder a los recursos globales.
     */
    public OcupantesViewModel(Application application) {
        super(application);
        // Inicialización del repositorio
        mRepository = new OcupantesRepository(application);

        // Se obtiene la lista de todos los ocupantes desde el repositorio
        mAllOcupantes = mRepository.getAllOcupantes();  // Usar getAllOcupantes() para obtener los ocupantes
    }

    /**
     * Devuelve un LiveData con una lista de todos los ocupantes.
     *
     * @return LiveData que contiene una lista de todos los ocupantes
     */
    public LiveData<List<Ocupantes>> getAllOcupantes() {
        return mAllOcupantes;
    }

    /**
     * Devuelve un LiveData con una lista de ocupantes filtrados por su ID.
     *
     * @param id El ID para filtrar los ocupantes.
     * @return LiveData que contiene la lista de ocupantes con el ID proporcionado
     */
    public LiveData<List<Ocupantes>> getAllOcupantesPorId(int id) {
        return mRepository.getAllOcupantesPorId(id);
    }

    /**
     * Inserta un nuevo ocupante en el repositorio.
     *
     * @param ocupantes El objeto Ocupantes que se va a insertar en la base de datos.
     */
    public void insert(Ocupantes ocupantes) {
        mRepository.insert(ocupantes);
    }

    /**
     * Actualiza un ocupante existente en el repositorio.
     *
     * @param ocupantes El objeto Ocupantes con los nuevos datos a actualizar.
     */
    public void update(Ocupantes ocupantes) {
        mRepository.update(ocupantes);
    }

    /**
     * Elimina un ocupante del repositorio.
     *
     * @param ocupantes El objeto Ocupantes que se va a eliminar.
     */
    public static void delete(Ocupantes ocupantes) {
        mRepository.delete(ocupantes);
    }
}
