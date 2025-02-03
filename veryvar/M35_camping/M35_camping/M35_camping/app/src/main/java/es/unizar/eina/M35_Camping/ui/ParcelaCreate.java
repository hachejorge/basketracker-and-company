package es.unizar.eina.M35_Camping.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import es.unizar.eina.M35_Camping.R;

/**
 * Actividad para crear una nueva parcela. Recibe y guarda datos de la parcela,
 * como su nombre, máximo de ocupantes, precio por persona y descripción.
 */
public class ParcelaCreate extends AppCompatActivity {

    // Declaración de las vistas que se utilizarán para capturar la información
    private EditText etNombre, etMaxOcupantes, etPrecioPorPersona, etDescripcion;
    private ImageButton btnSave, btnBack;

    // Constantes para las claves de los extras del Intent
    public static final String PARCELA_NOMBRE = "com.example.notepad.PARCELA_NOMBRE";
    public static final String PARCELA_DESC = "com.example.notepad.PARCELA_DESC";
    public static final String PARCELA_MAX_OCUP = "com.example.notepad.PARCELA_MAX_OCUP";
    public static final String PARCELA_PREC_PER = "com.example.notepad.PARCELA_PREC_PER";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela_create);

        // Enlazar las vistas con los elementos del layout
        etNombre = findViewById(R.id.et_nombre);
        etMaxOcupantes = findViewById(R.id.et_max_ocupantes);
        etPrecioPorPersona = findViewById(R.id.et_precio_por_persona);
        etDescripcion = findViewById(R.id.et_descripcion);
        btnSave = findViewById(R.id.save_button);
        btnBack = findViewById(R.id.back_button);

        // Si la actividad fue invocada con un Intent, obtener los datos de la parcela
        Intent intent = getIntent();
        if (intent != null) {
            etNombre.setText(intent.getStringExtra(PARCELA_NOMBRE));
            etDescripcion.setText(intent.getStringExtra(PARCELA_DESC));
            etMaxOcupantes.setText(String.valueOf(intent.getIntExtra(PARCELA_MAX_OCUP, 0)));
            etPrecioPorPersona.setText(String.valueOf(intent.getFloatExtra(PARCELA_PREC_PER, 0)));
        }

        // Configurar el botón de guardar, para guardar los datos de la parcela
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveParcela();
            }
        });

        // Configurar el botón de retroceso, para cerrar la actividad
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la anterior
            }
        });
    }

    /**
     * Método que guarda los datos introducidos por el usuario para crear o editar una parcela.
     */
    private void saveParcela() {
        // Obtener los valores de los EditText, eliminando espacios en blanco al principio y al final
        String nombre = etNombre.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String maxOcupantesStr = etMaxOcupantes.getText().toString().trim();
        String precioPorPersonaStr = etPrecioPorPersona.getText().toString().trim();

        // Verificar si algún campo obligatorio está vacío y mostrar un mensaje si es necesario
        if (nombre.isEmpty() || descripcion.isEmpty() || maxOcupantesStr.isEmpty() || precioPorPersonaStr.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return; // Salir del método si hay campos vacíos
        }

        // Convertir los valores de maxOcupantes y precioPorPersona a sus respectivos tipos
        int maxOcupantes = Integer.parseInt(maxOcupantesStr);
        float precioPorPersona = Float.parseFloat(precioPorPersonaStr);

        // Crear un Intent para devolver los datos de la parcela
        Intent resultIntent = new Intent();
        resultIntent.putExtra(PARCELA_NOMBRE, nombre); // Poner el nombre de la parcela
        resultIntent.putExtra(PARCELA_DESC, descripcion); // Poner la descripción de la parcela
        resultIntent.putExtra(PARCELA_MAX_OCUP, maxOcupantes); // Poner el número máximo de ocupantes
        resultIntent.putExtra(PARCELA_PREC_PER, precioPorPersona); // Poner el precio por persona

        // Establecer el resultado de la actividad y cerrarla
        setResult(RESULT_OK, resultIntent);
        finish(); // Termina la actividad y regresa a la actividad anterior
    }
}
