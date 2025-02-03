package es.unizar.eina.M35_Camping.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Parcela;

import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity para gestionar las parcelas en el sistema de camping.
 * Permite visualizar, crear, editar y eliminar parcelas.
 */
public class Activity_Parcela extends AppCompatActivity implements ParcelaListAdapter.OnDeleteClickListener {

    private ParcelaViewModel mParcelaViewModel;
    private OcupantesEditViewModel mOcupantesViewModel;
    private RecyclerView mRecyclerView;
    private ParcelaListAdapter mAdapter;
    private ImageButton mFab;
    private Spinner mSpinner;
    private List<Parcela> originalParcelas = new ArrayList<>();

    // IDs para los elementos del menú contextual
    static final int INSERT_ID = Menu.FIRST;
    static final int DELETE_ID = Menu.FIRST + 1;
    static final int EDIT_ID = Menu.FIRST + 2;

    /**
     * Método llamado al crear la actividad.
     *
     * @param savedInstanceState Estado guardado de la actividad (si lo hay).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelas);

        mRecyclerView = findViewById(R.id.recyclerview);

        // Configuración del adaptador y el RecyclerView
        mAdapter = new ParcelaListAdapter(new ParcelaListAdapter.ParcelaDiff(), this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicialización del ViewModel
        mParcelaViewModel = new ViewModelProvider(this).get(ParcelaViewModel.class);
        mOcupantesViewModel = new ViewModelProvider(this).get(OcupantesEditViewModel.class);
        mParcelaViewModel.getAllParcelas().observe(this, parcelas -> {
            originalParcelas.clear();
            originalParcelas.addAll(parcelas);
            actualizarListaParcelas();
        });

        // Configuración del botón flotante para crear una nueva parcela
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(view -> createParcela());

        registerForContextMenu(mRecyclerView);

        // Configuración del spinner para ordenar parcelas
        mSpinner = findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                actualizarListaParcelas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No se necesita acción aquí
            }
        });
    }

    /**
     * Actualiza la lista de parcelas según la opción seleccionada en el Spinner.
     */
    private void actualizarListaParcelas() {
        String selectedOption = (String) mSpinner.getSelectedItem();
        List<Parcela> reservasParcelas = new ArrayList<>(originalParcelas);

        if (selectedOption != null) {
            switch (selectedOption) {
                case "Precio por Persona":
                    reservasParcelas.sort((r1, r2) -> Float.compare(r1.getPrecPer(), r2.getPrecPer()));
                    break;
                case "Nombre":
                    reservasParcelas.sort((r1, r2) -> r1.getNombre().compareToIgnoreCase(r2.getNombre()));
                    break;
                case "Nº Maximo de Ocupantes":
                    reservasParcelas.sort((r1, r2) -> Integer.compare(r1.getMaxOcup(), r2.getMaxOcup()));
                    break;
            }
        }

        mAdapter.submitList(reservasParcelas);
    }

    /**
     * Configura el menú principal de opciones.
     *
     * @param menu Menú de la actividad.
     * @return true si el menú se creó correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, INSERT_ID, Menu.NONE, R.string.add_parcela);
        return result;
    }

    /**
     * Maneja la selección de opciones del menú.
     *
     * @param item Elemento seleccionado del menú.
     * @return true si el evento fue manejado.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == INSERT_ID) {
            createParcela();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Maneja la selección de opciones del menú contextual.
     *
     * @param item Elemento seleccionado del menú contextual.
     * @return true si el evento fue manejado.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Parcela current = getCurrent();
        if (current == null) return super.onContextItemSelected(item);

        switch (item.getItemId()) {
            case DELETE_ID:
                Toast.makeText(getApplicationContext(), "Eliminando " + current.getNombre(), Toast.LENGTH_LONG).show();
                mParcelaViewModel.delete(current);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Inicia la actividad para crear una nueva parcela.
     */
    private void createParcela() {
        mStartCreateParcela.launch(new Intent(this, ParcelaCreate.class));
    }

    // Lanzador para manejar la creación de parcelas
    ActivityResultLauncher<Intent> mStartCreateParcela = registerForActivityResult(
            new StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    if (extras != null) {
                        Parcela newParcela = new Parcela(
                                extras.getString(ParcelaCreate.PARCELA_NOMBRE),
                                extras.getString(ParcelaCreate.PARCELA_DESC),
                                extras.getInt(ParcelaCreate.PARCELA_MAX_OCUP),
                                extras.getFloat(ParcelaCreate.PARCELA_PREC_PER)
                        );
                        mParcelaViewModel.insert(newParcela);
                    }
                }
            }
    );

    /**
     * Maneja la acción de eliminar una parcela a través de un cuadro de confirmación.
     *
     * @param parcela Parcela a eliminar.
     */
    @Override
    public void onDeleteClick(Parcela parcela) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de que deseas eliminar esta parcela?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    mOcupantesViewModel.singleOcupantesPorNombreParcela(parcela.getNombre()).observe(this, listOcup -> {
                        if (listOcup.isEmpty()) {
                            mParcelaViewModel.delete(parcela);
                            Toast.makeText(this, "Parcela eliminada", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "No se puede eliminar la parcela, ya que esta añadida en alguna reserva", Toast.LENGTH_LONG).show();
                        }
                    });

                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    /**
     * Obtiene la parcela actualmente seleccionada.
     *
     * @return La parcela seleccionada o null si no hay ninguna.
     */
    public Parcela getCurrent() {
        if (mAdapter.getCurrentList() != null && mAdapter.getCurrentList().size() > 0) {
            return mAdapter.getCurrentList().get(0); // O el elemento seleccionado, según tu lógica
        }
        return null;
    }

}