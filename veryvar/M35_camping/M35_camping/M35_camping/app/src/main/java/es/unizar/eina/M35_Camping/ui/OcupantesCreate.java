package es.unizar.eina.M35_Camping.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Ocupantes;
import es.unizar.eina.M35_Camping.database.Reserva;

/**
 * Actividad que permite crear y editar los ocupantes de una parcela en una reserva.
 * Permite seleccionar una parcela, ingresar el número de ocupantes y calcular el precio
 * total en base a la fecha de la reserva y el precio por noche de la parcela.
 */
public class OcupantesCreate extends AppCompatActivity {

    // Elementos de la interfaz de usuario
    private EditText etNocup;
    private ImageButton btnSave, btnBack;
    private OcupantesEditViewModel mOcupantesEditViewModel;
    private ParcelaViewModel mParcelasViewModel;
    private ReservaViewModel mReservaViewModel;
    private Spinner spinnerParcela;
    private ArrayAdapter<String> parcelaAdapter;
    private List<String> nombreParcelasList;

    /**
     * Método llamado cuando la actividad se crea.
     * Inicializa los elementos de la interfaz de usuario y configura los listeners de los botones.
     *
     * @param savedInstanceState El estado guardado de la instancia, si existe.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocupantes_create);

        // Inicializar los ViewModels para interactuar con los datos
        mOcupantesEditViewModel = new ViewModelProvider(this).get(OcupantesEditViewModel.class);
        mParcelasViewModel = new ViewModelProvider(this).get(ParcelaViewModel.class);
        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);

        // Inicializar los elementos de la interfaz
        etNocup = findViewById(R.id.et_nocup);
        btnSave = findViewById(R.id.save_button);
        btnBack = findViewById(R.id.back_button);
        spinnerParcela = findViewById(R.id.spinner_parcela);

        // Cargar las parcelas disponibles en el spinner
        cargarParcelasEnSpinner();

        // Configurar el botón de guardar para actualizar los ocupantes
        btnSave.setOnClickListener(v -> updateOcupantes());

        // Configurar el botón de retroceso para cerrar la actividad
        btnBack.setOnClickListener(v -> finish());
    }

    /**
     * Carga las parcelas disponibles en el Spinner.
     * Observa el ViewModel que contiene los nombres de las parcelas y los muestra en el Spinner.
     */
    private void cargarParcelasEnSpinner() {
        // Observa los nombres de parcelas desde el ViewModel
        mParcelasViewModel.getAllParcelasNames().observe(this, nombreParcelasList -> {
            if (nombreParcelasList != null) {
                // Configura el ArrayAdapter con la lista de nombres de parcelas
                parcelaAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, nombreParcelasList);
                parcelaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerParcela.setAdapter(parcelaAdapter);

                // Opcional: seleccionar una parcela previamente elegida (si se pasa desde el Intent)
                String nombreparcelaSeleccionada = getIntent().getStringExtra("nombreparcela");
                if (nombreparcelaSeleccionada != null) {
                    int position = nombreParcelasList.indexOf(nombreparcelaSeleccionada);
                    if (position >= 0) {
                        spinnerParcela.setSelection(position);
                    }
                }
            }
        });
    }

    /**
     * Actualiza los ocupantes de una reserva.
     * Realiza las siguientes acciones:
     * 1. Verifica que los campos no estén vacíos.
     * 2. Verifica que el número de ocupantes no exceda el máximo permitido por la parcela.
     * 3. Crea un nuevo objeto de tipo Ocupantes y lo guarda en la base de datos.
     * 4. Calcula el precio total de la reserva basado en los ocupantes y actualiza la reserva.
     */
    private void updateOcupantes() {
        // Obtener el ID de la reserva y los valores de la interfaz de usuario
        int idReserva = getIntent().getIntExtra("id", 0);
        String parcela = (String) spinnerParcela.getSelectedItem();
        int nocup = Integer.parseInt(etNocup.getText().toString().trim());

        // Validación de campos
        if (parcela.isEmpty() || nocup == 0) {
            Toast.makeText(this, "Por favor, completa todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener información de la parcela seleccionada y verificar ocupación
        mParcelasViewModel.getSingleParcelaByName(parcela).removeObservers(this);
        mParcelasViewModel.getSingleParcelaByName(parcela).observe(this, parcelaInfo -> {
            if (parcelaInfo != null && nocup > parcelaInfo.getMaxOcup()) {
                String message = "El número de ocupantes excede el máximo permitido para la parcela. El máximo es " + parcelaInfo.getMaxOcup() + ".";
                new AlertDialog.Builder(this)
                        .setTitle("Error de ocupación")
                        .setMessage(message)
                        .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                        .show();
                return;
            }

            // Obtener las fechas de la reserva
            String fechaEntrada = getIntent().getStringExtra("fechent");
            String fechaSalida = getIntent().getStringExtra("fechsal");

            // Verificar la disponibilidad de la parcela en las fechas seleccionadas
            mReservaViewModel.getSingleReservasPorParcelaYFechas(parcela, fechaEntrada, fechaSalida).observe(this, reservasConflicto -> {
                if (reservasConflicto != null && !reservasConflicto.isEmpty()) {
                    // Si la parcela ya está ocupada en las fechas, mostrar un mensaje de error
                    Toast.makeText(this, "La parcela seleccionada ya está ocupada en las fechas especificadas.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si no hay conflictos, proceder con la actualización
                    mReservaViewModel.singleReservaById(idReserva).observe(this, reserva -> {
                        if (reserva == null) {
                            Toast.makeText(this, "No se encontró la reserva.", Toast.LENGTH_SHORT).show();
                            return;  // Salir si no se encuentra la reserva
                        }

                        double precioPorNoche = parcelaInfo.getPrecPer();

                        // Crear un nuevo objeto Ocupantes y guardarlo en la base de datos
                        Ocupantes newOcupantes = new Ocupantes(idReserva, parcela, nocup, precioPorNoche);
                        mOcupantesEditViewModel.insert(newOcupantes);

                        // Calcular el precio total de la reserva considerando el número de noches y ocupantes
                        int numNoches = calcularNumeroNoches(reserva.getFechEnt(), reserva.getFechSal());
                        Float precioTotal = (float) (precioPorNoche * numNoches * nocup + reserva.getPrecioTotal());

                        // Actualizar la reserva con el nuevo precio total
                        reserva.setPrecioTotal(precioTotal);
                        mReservaViewModel.update(reserva);

                        // Mostrar mensaje de éxito
                        Toast.makeText(this, "Parcela en reserva actualizada exitosamente", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    });
                }
            });
        });
    }

    /**
     * Calcula el número de noches entre la fecha de entrada y la fecha de salida.
     *
     * @param fechaEntrada Fecha de entrada en formato "yyyy-MM-dd".
     * @param fechaSalida Fecha de salida en formato "yyyy-MM-dd".
     * @return El número de noches entre las dos fechas.
     * @throws IllegalArgumentException Si la fecha de salida no es posterior a la fecha de entrada.
     */
    private int calcularNumeroNoches(String fechaEntrada, String fechaSalida) {
        // Definir el formato de las fechas como "yyyy-MM-dd"
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Parsear las fechas de entrada y salida a objetos Date
            Date dateEntrada = formatter.parse(fechaEntrada);
            Date dateSalida = formatter.parse(fechaSalida);

            // Calcular la diferencia en milisegundos y convertirla a días
            long diferenciaMilisegundos = dateSalida.getTime() - dateEntrada.getTime();
            int numeroNoches = (int) (diferenciaMilisegundos / (1000 * 60 * 60 * 24));

            // Verificar si el número de noches es válido (mayor que 0)
            if (numeroNoches <= 0) {
                throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de entrada.");
            }

            return numeroNoches;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Usa el formato 'yyyy-MM-dd'.", e);
        }
    }
}
