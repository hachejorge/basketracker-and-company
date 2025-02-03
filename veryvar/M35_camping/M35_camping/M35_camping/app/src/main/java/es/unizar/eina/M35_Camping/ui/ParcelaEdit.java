package es.unizar.eina.M35_Camping.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Parcela;

/**
 * Actividad para editar una parcela existente.
 * Permite al usuario modificar la información de una parcela y luego guardar los cambios.
 */
public class ParcelaEdit extends AppCompatActivity {

    // Declaración de las vistas utilizadas para capturar los datos de la parcela
    private EditText etMaxOcupantes, etPrecioPorPersona, etDescripcion;
    private ImageButton btnSave, btnBack;
    private ParcelaViewModel mParcelaViewModel;

    // Constantes para las claves de los extras del Intent
    public static final String PARCELA_NOMBRE = "com.example.notepad.PARCELA_NOMBRE";
    public static final String PARCELA_DESC = "com.example.notepad.PARCELA_DESC";
    public static final String PARCELA_MAX_OCUP = "com.example.notepad.PARCELA_MAX_OCUP";
    public static final String PARCELA_PREC_PER = "com.example.notepad.PARCELA_PREC_PER";
    public static final String PARCELA_PRECIO_TOTAL = "com.example.notepad.PARCELA_PRECIO_TOTAL";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela_edit);

        // Inicializar el ViewModel para manejar la lógica de negocio de las parcelas
        mParcelaViewModel = new ViewModelProvider(this).get(ParcelaViewModel.class);

        // Obtener los datos de la parcela desde el Intent
        String nombre = getIntent().getStringExtra("nombre");
        String descripcion = getIntent().getStringExtra("descripcion");
        int maxOcupantes = getIntent().getIntExtra("maxOcupantes", 0);
        float precioPorPersona = getIntent().getFloatExtra("precioPorPersona", 0.0f);

        // Rellenar los campos de texto con los valores obtenidos del Intent
        ((TextView) findViewById(R.id.et_nombre_titulo)).setText(nombre);
        ((EditText) findViewById(R.id.et_descripcion)).setText(descripcion);
        ((EditText) findViewById(R.id.et_max_ocupantes)).setText(String.valueOf(maxOcupantes));
        ((EditText) findViewById(R.id.et_precio_por_persona)).setText(String.valueOf(precioPorPersona));

        // Inicializar las vistas para editar los datos
        etMaxOcupantes = findViewById(R.id.et_max_ocupantes);
        etPrecioPorPersona = findViewById(R.id.et_precio_por_persona);
        etDescripcion = findViewById(R.id.et_descripcion);
        btnSave = findViewById(R.id.save_button);
        btnBack = findViewById(R.id.back_button);

        // Configurar el botón de guardar para actualizar la parcela
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateParcela();
            }
        });

        // Configurar el botón de retroceso para cerrar la actividad sin hacer cambios
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la anterior
            }
        });
    }

    /**
     * Método para actualizar los datos de la parcela.
     * Recibe los datos modificados de los campos de texto y los guarda en el ViewModel.
     */
    private void updateParcela() {
        // Obtener el nombre de la parcela y los valores modificados desde los campos de texto
        String nombre = getIntent().getStringExtra("nombre");
        String descripcion = etDescripcion.getText().toString().trim();
        String maxOcupantesStr = etMaxOcupantes.getText().toString().trim();
        String precioPorPersonaStr = etPrecioPorPersona.getText().toString().trim();

        // Validar que todos los campos obligatorios estén completos
        if (nombre.isEmpty() || descripcion.isEmpty() || maxOcupantesStr.isEmpty() || precioPorPersonaStr.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return; // Salir del método si hay campos vacíos
        }

        // Convertir los valores de maxOcupantes y precioPorPersona a sus respectivos tipos
        int maxOcupantes = Integer.parseInt(maxOcupantesStr);
        float precioPorPersona = Float.parseFloat(precioPorPersonaStr);

        // Crear un objeto Parcela con los nuevos datos
        Parcela updatedParcela = new Parcela(nombre, descripcion, maxOcupantes, precioPorPersona);

        // Llamar al ViewModel para actualizar la parcela en la base de datos
        mParcelaViewModel.update(updatedParcela);

        // Mostrar un mensaje de éxito y cerrar la actividad
        Toast.makeText(this, "Parcela actualizada exitosamente", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK); // Devolver el resultado de la actividad como OK
        finish(); // Terminar la actividad y regresar a la actividad anterior
    }
}
