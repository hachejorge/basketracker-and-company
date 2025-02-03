package es.unizar.eina.M35_Camping.ui;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import es.unizar.eina.M35_Camping.R;

/**
 * Actividad que muestra los detalles de una parcela.
 * Se utiliza para mostrar información como el nombre, descripción, capacidad máxima y precio por persona de la parcela seleccionada.
 */
public class ParcelaShow extends AppCompatActivity {

    private ImageButton btnBack;  // Botón de retroceso para volver a la actividad anterior

    /**
     * Método llamado cuando la actividad es creada. Este método inicializa la interfaz de usuario
     * y configura los valores recibidos a través del Intent.
     *
     * @param savedInstanceState Información del estado anterior de la actividad (si existe)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela_show);  // Establece el layout de la actividad

        // Obtener los datos pasados a través del Intent
        String nombre = getIntent().getStringExtra("nombre");  // Obtiene el nombre de la parcela
        String descripcion = getIntent().getStringExtra("descripcion");  // Obtiene la descripción de la parcela
        int maxOcupantes = getIntent().getIntExtra("maxOcupantes", 0);  // Obtiene el número máximo de ocupantes
        float precioPorPersona = getIntent().getFloatExtra("precioPorPersona", 0.0f);  // Obtiene el precio por persona

        // Asignar los valores a los TextViews
        ((TextView) findViewById(R.id.et_nombre_titulo)).setText(nombre);  // Muestra el nombre en el título
        ((TextView) findViewById(R.id.et_nombre)).setText(nombre);  // Muestra el nombre de la parcela
        ((TextView) findViewById(R.id.et_descripcion)).setText(descripcion);  // Muestra la descripción de la parcela
        ((TextView) findViewById(R.id.et_max_ocupantes)).setText(String.valueOf(maxOcupantes));  // Muestra el número máximo de ocupantes
        ((TextView) findViewById(R.id.et_precio_por_persona)).setText(String.format("%.2f€", precioPorPersona));  // Muestra el precio por persona con formato

        // Configurar el botón de retroceso
        btnBack = findViewById(R.id.back_button);
        btnBack.setOnClickListener(v -> finish());  // Al hacer clic, cierra la actividad y vuelve a la anterior
    }
}
