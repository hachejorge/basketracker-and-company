package es.unizar.eina.M35_Camping.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Ocupantes;
import es.unizar.eina.M35_Camping.database.Parcela;
import es.unizar.eina.M35_Camping.database.Reserva;

/**
 * Actividad que permite crear una nueva reserva en el sistema de camping.
 * El usuario puede ingresar los detalles de la reserva, como el nombre del cliente,
 * número de teléfono y fechas de entrada y salida.
 */
public class ReservaCreate extends AppCompatActivity {

    private EditText etCliente, etTelefono, etFechEnt, etFechSal;  // EditText para ingresar los datos de la reserva
    private ImageButton btnSave, btnBack;  // Botones de guardar y retroceder
    private ReservaViewModel mReservaViewModel;  // ViewModel para gestionar las reservas

    // Constantes que representan las claves para los datos de la reserva
    public static final String RESERVA_CLIENTE = "reserva_cliente";
    public static final String RESERVA_TELEFONO = "reserva_telefono";
    public static final String RESERVA_FECHENT = "reserva_fechEnt";
    public static final String RESERVA_FECHSAL = "reserva_fechSal";


    /**
     * Este método es llamado cuando la actividad se crea.
     * Inicializa las vistas, configura los botones y gestiona la selección de fechas.
     *
     * @param savedInstanceState el estado guardado de la actividad, si existe
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_edit);

        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);

        // Enlazar las vistas con sus correspondientes elementos de la interfaz de usuario
        etCliente = findViewById(R.id.et_cliente);
        etTelefono = findViewById(R.id.et_telefono);
        etFechEnt = findViewById(R.id.et_fechent);
        etFechSal = findViewById(R.id.et_fechsal);
        btnSave = findViewById(R.id.save_button);
        btnBack = findViewById(R.id.back_button);

        // Configuración para el campo de la fecha de entrada
        etFechEnt.setOnClickListener(v -> {
            // Obtener la fecha actual para establecer el mínimo en el selector de fechas
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Crear un selector de fecha para la fecha de entrada
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Formatear la fecha seleccionada para mostrarla en el campo de texto
                        String formattedMonth = String.format("%02d", selectedMonth + 1); // +1 porque los meses empiezan en 0
                        String formattedDay = String.format("%02d", selectedDay);
                        String date = selectedYear + "-" + formattedMonth + "-" + formattedDay;
                        etFechEnt.setText(date);  // Establecer la fecha seleccionada en el campo de entrada
                    }, year, month, day);
            datePickerDialog.show();  // Mostrar el selector de fechas
        });

        // Configuración para el campo de la fecha de salida
        etFechSal.setOnClickListener(v -> {
            // Obtener la fecha actual para establecer el mínimo en el selector de fechas
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Crear un selector de fecha para la fecha de salida
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Formatear la fecha seleccionada para mostrarla en el campo de texto
                        String formattedMonth = String.format("%02d", selectedMonth + 1); // +1 porque los meses empiezan en 0
                        String formattedDay = String.format("%02d", selectedDay);
                        String date = selectedYear + "-" + formattedMonth + "-" + formattedDay;
                        etFechSal.setText(date);  // Establecer la fecha seleccionada en el campo de salida
                    }, year, month, day);
            datePickerDialog.show();  // Mostrar el selector de fechas
        });

        // Configuración del botón de guardar
        btnSave.setOnClickListener(v -> saveReserva());  // Llamar al método para guardar la reserva

        // Configuración del botón de retroceso
        btnBack.setOnClickListener(v -> finish());  // Cerrar la actividad y regresar a la pantalla anterior
    }

    /**
     * Método para guardar la reserva.
     * Valida que los campos no estén vacíos, que el teléfono sea un número válido y que la fecha de salida sea posterior a la de entrada.
     * Si todo es válido, guarda la reserva en el sistema.
     */
    private void saveReserva() {
        // Obtener los valores ingresados por el usuario
        String cliente = etCliente.getText().toString().trim();
        String telefonoStr = etTelefono.getText().toString().trim();
        String fechent = etFechEnt.getText().toString().trim();
        String fechsal = etFechSal.getText().toString().trim();

        // Validar que todos los campos estén llenos
        if (cliente.isEmpty() || telefonoStr.isEmpty() || fechent.isEmpty() || fechsal.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return;  // Si algún campo está vacío, no se guarda la reserva
        }

        // Validar que el número de teléfono sea un número válido
        int telefono;
        try {
            telefono = Integer.parseInt(telefonoStr);  // Intentar convertir el teléfono a número
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Número de teléfono no válido.", Toast.LENGTH_SHORT).show();
            return;  // Si el teléfono no es válido, mostrar un mensaje de error
        }

        // Validar que la fecha de salida sea posterior a la fecha de entrada
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaEntrada = dateFormat.parse(fechent);  // Convertir la fecha de entrada en un objeto Date
            Date fechaSalida = dateFormat.parse(fechsal);  // Convertir la fecha de salida en un objeto Date

            // Comprobar que la fecha de salida sea posterior a la fecha de entrada
            if (fechaSalida == null || fechaEntrada == null || fechaSalida.before(fechaEntrada)) {
                Toast.makeText(this, "La fecha de salida debe ser posterior a la fecha de entrada.", Toast.LENGTH_SHORT).show();
                return;  // Si la fecha de salida no es válida, no se guarda la reserva
            }
        } catch (ParseException e) {
            Toast.makeText(this, "Formato de fecha no válido.", Toast.LENGTH_SHORT).show();
            return;  // Si las fechas no tienen el formato adecuado, mostrar un error
        }

        // Crear un objeto Reserva con los datos válidos
        Reserva reserva = new Reserva(cliente, telefono, fechent, fechsal);
        mReservaViewModel.insert(reserva);  // Guardar la reserva utilizando el ViewModel

        // Establecer el resultado y finalizar la actividad
        setResult(RESULT_OK);  // Indicar que la actividad terminó correctamente
        finish();  // Cerrar la actividad y regresar a la pantalla anterior
    }

}
