package es.unizar.eina.M35_Camping.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import es.unizar.eina.M35_Camping.database.Ocupantes;
import es.unizar.eina.M35_Camping.database.Reserva;
import es.unizar.eina.M35_Camping.database.ReservaRepository;

/**
 * ViewModel para manejar las operaciones relacionadas con las reservas.
 * Este ViewModel interactúa con el repositorio de reservas para realizar operaciones
 * como insertar, actualizar, obtener todas las reservas o buscar una reserva por su ID.
 */
public class ReservaViewModel extends AndroidViewModel {

    // Repositorio que maneja las operaciones de datos de las reservas
    private static ReservaRepository mRepository;

    // LiveData que contiene todas las reservas
    private final LiveData<List<Reserva>> mAllReservas;

    /**
     * Constructor del ViewModel. Inicializa el repositorio de reservas
     * y carga todas las reservas disponibles.
     *
     * @param application La aplicación donde se encuentra el ViewModel.
     */
    public ReservaViewModel(Application application) {
        super(application);
        mRepository = new ReservaRepository(application);
        mAllReservas = mRepository.getAllReservas();  // Obtiene todas las reservas desde el repositorio
    }

    /**
     * Obtiene una lista de todas las reservas disponibles.
     *
     * @return LiveData de una lista de todas las reservas.
     */
    public LiveData<List<Reserva>> getAllReservas() {
        return mAllReservas;
    }

    /**
     * Inserta una nueva reserva en el repositorio.
     *
     * @param reserva La reserva que se desea insertar.
     */
    public void insert(Reserva reserva) {
        mRepository.insert(reserva);
    }

    /**
     * Actualiza una reserva existente en el repositorio.
     *
     * @param reserva La reserva con los datos actualizados.
     */
    public void update(Reserva reserva) {
        mRepository.update(reserva);
    }

    /**
     * Elimina una reserva del repositorio.
     *
     * @param reserva La reserva que se desea eliminar.
     */
    public static void delete(Reserva reserva) {
        mRepository.delete(reserva);
    }

    /**
     * Elimina una reserva del repositorio utilizando su ID.
     *
     * @param id El ID de la reserva que se desea eliminar.
     */
    public static void deleteById(int id) {
        mRepository.deleteById(id);
    }

    // LiveData mediador que contiene una reserva específica obtenida por ID
    private MediatorLiveData<Reserva> getReservaById = new MediatorLiveData<>();

    /**
     * Obtiene una reserva específica por su ID.
     *
     * @param idReserva El ID de la reserva que se desea obtener.
     * @return LiveData de la reserva encontrada.
     */
    public LiveData<Reserva> singleReservaById(int idReserva) {
        // Inicializa el MediatorLiveData y obtiene el LiveData de la reserva por su ID
        getReservaById = new MediatorLiveData<>();
        LiveData<Reserva> source = getReservaById(idReserva);

        // Configura el MediatorLiveData para escuchar el LiveData fuente
        getReservaById.addSource(source, reserva -> {
            // Cuando se recibe un valor, emite la reserva y remueve la fuente
            getReservaById.setValue(reserva);
            getReservaById.removeSource(source);
        });

        return getReservaById;
    }

    /**
     * Método que obtiene una reserva por su ID desde el repositorio.
     *
     * @param id El ID de la reserva a obtener.
     * @return LiveData de la reserva encontrada.
     */
    public LiveData<Reserva> getReservaById(int id) {
        return mRepository.getReservaById(id);
    }

    // LiveData mediador que contiene las reservas filtradas por parcela y fechas
    private MediatorLiveData<List<Reserva>> singleReservasPorParcelaYFechas = new MediatorLiveData<>();

    /**
     * Obtiene una lista de reservas filtradas por parcela y fechas de entrada y salida.
     *
     * @param parcela El nombre o identificador de la parcela.
     * @param fechaEntrada La fecha de entrada en formato de texto.
     * @param fechaSalida La fecha de salida en formato de texto.
     * @return LiveData de una lista de reservas filtradas.
     */
    public LiveData<List<Reserva>> getSingleReservasPorParcelaYFechas(String parcela, String fechaEntrada, String fechaSalida) {
        // Inicializa el MediatorLiveData y obtiene las reservas filtradas
        singleReservasPorParcelaYFechas = new MediatorLiveData<>();
        LiveData<List<Reserva>> source = getReservasPorParcelaYFechas(parcela, fechaEntrada, fechaSalida);
        singleReservasPorParcelaYFechas.addSource(source, ocupantes -> {
            // Cuando se recibe un valor, emite la lista de reservas y remueve la fuente
            singleReservasPorParcelaYFechas.setValue(ocupantes);
            singleReservasPorParcelaYFechas.removeSource(source);  // Remueve la fuente después de la primera emisión
        });
        return singleReservasPorParcelaYFechas;
    }

    /**
     * Método que obtiene las reservas filtradas por parcela y fechas desde el repositorio.
     *
     * @param parcela El nombre o identificador de la parcela.
     * @param fechaEntrada La fecha de entrada en formato de texto.
     * @param fechaSalida La fecha de salida en formato de texto.
     * @return LiveData de una lista de reservas filtradas.
     */
    public LiveData<List<Reserva>> getReservasPorParcelaYFechas(String parcela, String fechaEntrada, String fechaSalida) {
        return mRepository.getReservasPorParcelaYFechas(parcela, fechaEntrada, fechaSalida);
    }
}
