package es.unizar.eina.M35_Camping.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Reserva;

/**
 * Actividad que gestiona la lista de Reservas. Permite la creación, edición, eliminación
 * y ordenación de las reservas. También integra un RecyclerView para mostrar la lista y un Spinner
 * para ordenar las reservas según diferentes criterios.
 */
public class Activity_Reserva extends AppCompatActivity implements ReservaListAdapter.OnDeleteClickListener {

    private ReservaViewModel mReservaViewModel; /**< ViewModel que maneja los datos de las reservas. */
    private RecyclerView mRecyclerView; /**< RecyclerView para mostrar la lista de reservas. */
    private ReservaListAdapter mAdapter; /**< Adaptador que vincula los datos de las reservas al RecyclerView. */
    private ImageButton mFab; /**< Botón flotante de acción para crear una nueva reserva. */
    private Spinner mSpinner, mSpinnerFiltar; /**< Spinner para ordenar la lista de reservas según un criterio seleccionado. */
    private List<Reserva> originalReservas = new ArrayList<>(); /**< Lista original de reservas para aplicar ordenación. */

    static final int INSERT_ID = Menu.FIRST; /**< ID del elemento del menú para insertar una nueva reserva. */
    static final int DELETE_ID = Menu.FIRST + 1; /**< ID del elemento del menú para eliminar una reserva. */
    static final int EDIT_ID = Menu.FIRST + 2; /**< ID del elemento del menú para editar una reserva. */

    /**
     * Método que se llama cuando la actividad se crea. Inicializa el RecyclerView, el ViewModel,
     * el botón flotante de acción y el Spinner para ordenar las reservas. También configura el observador
     * que se activa cuando los datos de las reservas cambian.
     *
     * @param savedInstanceState El estado guardado de la instancia.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        // Inicialización del RecyclerView y su adaptador
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new ReservaListAdapter(new ReservaListAdapter.ReservaDiff(), this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicialización del ViewModel para las reservas
        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);
        mReservaViewModel.getAllReservas().observe(this, reservas -> {
            if (reservas != null) {
                originalReservas.clear();
                originalReservas.addAll(reservas);
                actualizarListaReservas(); // Actualiza la lista de reservas cuando los datos cambian
            }
        });

        // Configuración del botón flotante de acción para crear una nueva reserva
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(view -> createReserva());

        // Registrar el RecyclerView para usar el menú contextual
        registerForContextMenu(mRecyclerView);

        // Configuración del Spinner para ordenar las reservas
        mSpinner = findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                actualizarListaReservas(); // Actualiza la lista cuando se selecciona un nuevo filtro
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No se realiza acción cuando no se selecciona nada
            }
        });

        mSpinnerFiltar = findViewById(R.id.filtar);
        mSpinnerFiltar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                actualizarListaReservas(); // Actualiza la lista cuando se selecciona un nuevo filtro
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No se realiza acción cuando no se selecciona nada
            }
        });
    }

    /**
     * Actualiza la lista de reservas según el criterio seleccionado en el Spinner.
     * La lista se ordena según el filtro elegido: ID, Nombre del Cliente o Fecha de Entrada.
     */
    private void actualizarListaReservas() {
        String selectedSortOption = (String) mSpinner.getSelectedItem();
        String selectedFilterOption = (String) mSpinnerFiltar.getSelectedItem();

        List<Reserva> reservasFiltradas = new ArrayList<>();

        // Formato de fecha (ajustar según el formato real de tus fechas)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date today = new Date(); // Fecha actual

        // Aplicar el filtro según la selección
        for (Reserva reserva : originalReservas) {
            try {
                Date fechaEntrada = dateFormat.parse(reserva.getFechEnt());
                Date fechaSalida = dateFormat.parse(reserva.getFechSal());

                switch (selectedFilterOption) {
                    case "Vigentes":
                        if (!today.before(fechaEntrada) && !today.after(fechaSalida)) {
                            reservasFiltradas.add(reserva);
                        }
                        break;
                    case "Previstas":
                        if (today.before(fechaEntrada)) {
                            reservasFiltradas.add(reserva);
                        }
                        break;
                    case "Caducadas":
                        if (today.after(fechaSalida)) {
                            reservasFiltradas.add(reserva);
                        }
                        break;
                    case "Todas":
                    default:
                        reservasFiltradas.add(reserva);
                        break;
                }
            } catch (ParseException e) {
                e.printStackTrace(); // Manejo de error si la fecha no se puede convertir
            }
        }

        // Ordenar la lista filtrada según la opción de ordenamiento seleccionada
        if (selectedSortOption != null) {
            switch (selectedSortOption) {
                case "ID":
                    reservasFiltradas.sort(Comparator.comparingInt(Reserva::getId));
                    break;
                case "Nombre Cliente":
                    reservasFiltradas.sort((r1, r2) -> {
                        if (r1.getCliente() == null && r2.getCliente() == null) return 0;
                        if (r1.getCliente() == null) return 1;
                        if (r2.getCliente() == null) return -1;
                        return r1.getCliente().compareToIgnoreCase(r2.getCliente());
                    });
                    break;
                case "Fecha de Entrada":
                    reservasFiltradas.sort((r1, r2) -> {
                        try {
                            Date date1 = dateFormat.parse(r1.getFechEnt());
                            Date date2 = dateFormat.parse(r2.getFechEnt());
                            return date1.compareTo(date2);
                        } catch (ParseException e) {
                            return 0; // Si hay un error, no cambia el orden
                        }
                    });
                    break;
            }
        }

        // Actualizar el adaptador con la lista filtrada y ordenada
        mAdapter.submitList(reservasFiltradas);
    }


    /**
     * Crea el menú de opciones en la barra de acción.
     *
     * @param menu El menú que se va a crear.
     * @return Retorna verdadero si el menú fue creado correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, INSERT_ID, Menu.NONE, R.string.add_reserva); // Agregar opción para añadir una nueva reserva
        return result;
    }

    /**
     * Maneja las opciones seleccionadas en el menú de opciones.
     *
     * @param item El elemento del menú que se seleccionó.
     * @return Retorna verdadero si la opción fue manejada correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == INSERT_ID) {
            createReserva(); // Crear una nueva reserva
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Maneja las opciones seleccionadas en el menú contextual de los elementos de la lista.
     *
     * @param item El elemento del menú contextual que se seleccionó.
     * @return Retorna verdadero si la opción fue manejada correctamente.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Reserva current = mAdapter.getCurrent();
        if (current == null) return super.onContextItemSelected(item); // Evitar puntero nulo

        switch (item.getItemId()) {
            case DELETE_ID:
                Toast.makeText(getApplicationContext(), "Eliminando " + current.getId(), Toast.LENGTH_LONG).show();
                mReservaViewModel.delete(current); // Eliminar la reserva seleccionada
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Inicia la actividad para crear una nueva reserva.
     */
    private void createReserva() {
        mStartCreateReserva.launch(new Intent(this, ReservaCreate.class)); // Lanzar actividad para crear reserva
    }

    /**
     * Lanza la actividad para crear una nueva reserva y maneja el resultado.
     */
    ActivityResultLauncher<Intent> mStartCreateReserva = registerForActivityResult(
            new StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    if (extras != null) {
                        // Crear la nueva reserva con los datos obtenidos y guardarla
                        Reserva newReserva = new Reserva(
                                extras.getString(ReservaCreate.RESERVA_CLIENTE),
                                extras.getInt(ReservaCreate.RESERVA_TELEFONO),
                                extras.getString(ReservaCreate.RESERVA_FECHENT),
                                extras.getString(ReservaCreate.RESERVA_FECHSAL)
                        );
                        mReservaViewModel.insert(newReserva);
                    }
                }
            }
    );

    /**
     * Método que se llama cuando se hace clic en el botón de eliminar de un item en la lista.
     * Muestra un cuadro de confirmación para eliminar la reserva seleccionada.
     *
     * @param reserva La reserva seleccionada para eliminar.
     */
    @Override
    public void onDeleteClick(Reserva reserva) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de que deseas eliminar esta reserva?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    mReservaViewModel.delete(reserva); // Eliminar la reserva
                    Toast.makeText(this, "Reserva eliminada", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
