package es.unizar.eina.M35_Camping.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import es.unizar.eina.M35_Camping.database.Ocupantes;
import es.unizar.eina.M35_Camping.database.Parcela;
import es.unizar.eina.M35_Camping.database.ParcelaRepository;

/**
 * ViewModel para la gestión de datos relacionados con las parcelas.
 * Proporciona una capa de abstracción para interactuar con el repositorio de parcelas y mantener la separación entre la lógica de datos y la interfaz de usuario.
 */
public class ParcelaViewModel extends AndroidViewModel {

    private final ParcelaRepository mRepository;  // Repositorio encargado de la manipulación de datos de parcelas
    private final LiveData<List<Parcela>> mAllParcelas;  // Lista de todas las parcelas disponibles

    /**
     * Constructor del ViewModel.
     *
     * @param application El contexto de la aplicación, necesario para inicializar el repositorio.
     */
    public ParcelaViewModel(Application application) {
        super(application);
        mRepository = new ParcelaRepository(application);  // Crea una instancia del repositorio
        mAllParcelas = mRepository.getAllParcelas();  // Obtiene todas las parcelas a través del repositorio
    }

    /**
     * Devuelve una LiveData que representa una lista de todas las parcelas disponibles.
     *
     * @return Una LiveData con una lista de objetos Parcela.
     */
    public LiveData<List<Parcela>> getAllParcelas() {
        return mAllParcelas;
    }

    /**
     * Obtiene una lista de nombres de todas las parcelas disponibles.
     *
     * @return Una LiveData con una lista de nombres de parcelas.
     */
    public LiveData<List<String>> getAllParcelasNames() {
        return mRepository.getAllParcelasNames();
    }

    /**
     * Inserta una nueva parcela en el repositorio.
     *
     * @param parcela La parcela a insertar.
     */
    public void insert(Parcela parcela) {
        mRepository.insert(parcela);
    }

    /**
     * Actualiza una parcela existente en el repositorio.
     *
     * @param parcela La parcela a actualizar.
     */
    public void update(Parcela parcela) {
        mRepository.update(parcela);
    }

    /**
     * Elimina una parcela del repositorio.
     *
     * @param parcela La parcela a eliminar.
     */
    public void delete(Parcela parcela) {
        mRepository.delete(parcela);
    }

    /**
     * Obtiene una parcela específica por su nombre.
     *
     * @param nombre El nombre de la parcela que se desea obtener.
     * @return Una LiveData que representa la parcela solicitada.
     */
    public LiveData<Parcela> getParcelaByName(String nombre) {
        return mRepository.getParcelaByName(nombre);
    }

    // MediatorLiveData que se utilizará para manejar múltiples fuentes de datos
    private MediatorLiveData<Parcela> getParcelaByName = new MediatorLiveData<>();

    /**
     * Obtiene una parcela específica por su nombre con el comportamiento de LiveData.
     *
     * @param nombre El nombre de la parcela que se desea obtener.
     * @return Una LiveData que representa la parcela solicitada.
     */
    public LiveData<Parcela> getSingleParcelaByName(String nombre) {
        getParcelaByName = new MediatorLiveData<>();  // Reinicia la MediatorLiveData
        LiveData<Parcela> source = getParcelaByName(nombre);  // Obtiene la parcela desde el repositorio
        getParcelaByName.addSource(source, parcela -> {
            getParcelaByName.setValue(parcela);  // Actualiza la MediatorLiveData con el valor de la fuente
            getParcelaByName.removeSource(source);  // Remueve la fuente después de la primera emisión
        });
        return getParcelaByName;  // Devuelve la MediatorLiveData actualizada
    }
}
