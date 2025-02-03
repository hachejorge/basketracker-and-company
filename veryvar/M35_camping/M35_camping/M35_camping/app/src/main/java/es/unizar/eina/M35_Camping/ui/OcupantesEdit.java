package es.unizar.eina.M35_Camping.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Ocupantes;

/**
 * Actividad que permite editar los datos de ocupantes en una reserva.
 * Esta clase permite actualizar el número de ocupantes y la parcela asignada,
 * además de validar la disponibilidad y los límites de ocupación.
 */
public class OcupantesEdit extends AppCompatActivity {

    private TextView spinnerParcela;
    private EditText etNocup;
    private ImageButton btnSave, btnBack;
    private OcupantesEditViewModel mOcupantesEditViewModel;
    private ParcelaViewModel mParcelasViewModel;
    private ReservaViewModel mReservaViewModel;
    private ArrayAdapter<String> parcelaAdapter;
    private String parcelaIncial;

    /**
     * Método de creación de la actividad. Aquí se inicializan las vistas y ViewModels
     * necesarios para la actividad.
     *
     * @param savedInstanceState Estado previo de la actividad, si existe.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocupantes_edit);

        // Inicialización de los ViewModels
        mOcupantesEditViewModel = new ViewModelProvider(this).get(OcupantesEditViewModel.class);
        mParcelasViewModel = new ViewModelProvider(this).get(ParcelaViewModel.class);
        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);

        // Obtiene el número de ocupantes y la parcela inicial desde el Intent
        int nocup = getIntent().getIntExtra("nocup", 0);
        parcelaIncial = getIntent().getStringExtra("nombreparcela");

        // Asigna el número de ocupantes al EditText
        ((TextView) findViewById(R.id.et_nocup)).setText(String.valueOf(nocup));
        ((TextView) findViewById(R.id.spinner_parcela)).setText(parcelaIncial);

        // Inicialización de las vistas
        etNocup = findViewById(R.id.et_nocup);
        spinnerParcela = findViewById(R.id.spinner_parcela);
        btnSave = findViewById(R.id.save_button);
        btnBack = findViewById(R.id.back_button);

        // Configurar el botón de guardar para actualizar los datos de ocupantes
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOcupantes();  // Llamada al método para actualizar los datos
            }
        });

        // Configurar el botón de retroceso para cerrar la actividad
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
            }
        });
    }

    /**
     * Método que actualiza los datos de ocupantes en la base de datos.
     * Se valida si los datos ingresados son correctos, si la parcela está disponible
     * y si el número de ocupantes no excede el límite máximo.
     */
    private void updateOcupantes() {
        int id = getIntent().getIntExtra("id", 0);  // ID de la reserva
        String parcela = parcelaIncial;  // Parcela seleccionada
        int nocup = Integer.parseInt(etNocup.getText().toString().trim());  // Número de ocupantes

        // Validación de campos vacíos o valores incorrectos
        if (parcela.isEmpty() || nocup == 0) {
            Toast.makeText(this, "Por favor, completa todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtiene la información de la parcela seleccionada
        mParcelasViewModel.getParcelaByName(parcela).observe(this, parcelaInfo -> {
            if (parcelaInfo != null && nocup > parcelaInfo.getMaxOcup()) {
                String message = "El número de ocupantes excede el máximo permitido para la parcela. El máximo es " + parcelaInfo.getMaxOcup() + ".";
                // Mostrar mensaje de error si excede el límite
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

            mReservaViewModel.singleReservaById(id).observe(this, reserva -> {
                if (reserva == null) {
                    Toast.makeText(this, "No se encontró la reserva.", Toast.LENGTH_SHORT).show();
                    return;  // Salir si no se encuentra la reserva
                }

                int numNoches = calcularNumeroNoches(fechaEntrada, fechaSalida);  // Calcular el número de noches

                // Obtener los datos antiguos de la parcela y ocupantes
                String oldParcela = getIntent().getStringExtra("nombreparcela");
                Ocupantes oldOcupantes = new Ocupantes(id, oldParcela, 0, 0.0);

                mOcupantesEditViewModel.singleOcupantesPorParcela(id, parcela).observe(this, ocupantes -> {
                    double precioPorNoche = ocupantes.getPrecioparcela();
                    // Calcular el precio total de la reserva con la actualización
                    Float precioTotal = reserva.getPrecioTotal() - (float) (precioPorNoche * numNoches * ocupantes.getOcp());

                    // Eliminar los ocupantes antiguos y agregar los nuevos
                    mOcupantesEditViewModel.delete(oldOcupantes);
                    Ocupantes newOcupantes = new Ocupantes(id, parcela, nocup, precioPorNoche);
                    mOcupantesEditViewModel.insert(newOcupantes);

                    // Actualizar el precio total de la reserva
                    precioTotal = precioTotal + (float) (precioPorNoche * numNoches * nocup);
                    reserva.setPrecioTotal(precioTotal);
                    mReservaViewModel.update(reserva);

                    // Mostrar mensaje de éxito
                    Toast.makeText(this, "Parcela en reserva actualizada exitosamente", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                });
            });
        });
    }

    /**
     * Calcula el número de noches entre la fecha de entrada y la fecha de salida.
     *
     * @param fechaEntrada Fecha de entrada en formato "yyyy-MM-dd".
     * @param fechaSalida Fecha de salida en formato "yyyy-MM-dd".
     * @return Número de noches entre las dos fechas.
     * @throws IllegalArgumentException Si las fechas no son válidas.
     */
    private int calcularNumeroNoches(String fechaEntrada, String fechaSalida) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateEntrada = formatter.parse(fechaEntrada);
            Date dateSalida = formatter.parse(fechaSalida);

            long diferenciaMilisegundos = dateSalida.getTime() - dateEntrada.getTime();
            int numeroNoches = (int) (diferenciaMilisegundos / (1000 * 60 * 60 * 24));

            if (numeroNoches <= 0) {
                throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de entrada.");
            }

            return numeroNoches;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Usa el formato 'yyyy-MM-dd'.", e);
        }
    }
}
