package es.unizar.eina.M35_Camping.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import es.unizar.eina.M35_Camping.R;

/**
 * Actividad del menú principal de la aplicación. Esta actividad muestra
 * dos botones que permiten al usuario navegar a las pantallas de reservas
 * y parcelas.
 */
public class Menu extends AppCompatActivity {

    // Botones para navegar entre las diferentes actividades
    private Button btnReservas;
    private Button btnParcelas;
    private Button btnPruebas;

    /**
     * Método llamado cuando la actividad se crea.
     * Inicializa los botones y configura los listeners para manejar
     * la navegación hacia las actividades de reservas y parcelas.
     *
     * @param savedInstanceState El estado guardado de la instancia, si es que existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu); // Asegúrate de que el archivo XML se llame 'menu.xml'

        // Inicializar los botones a partir del layout
        btnReservas = findViewById(R.id.btn_reservas);
        btnParcelas = findViewById(R.id.btn_parcelas);
        btnPruebas = findViewById(R.id.btn_pruebas);

        // Configurar el botón de "Reservas" para abrir la actividad Activity_Reserva
        btnReservas.setOnClickListener(view -> {
            // Crear un Intent para navegar a la actividad Activity_Reserva
            Intent intent = new Intent(Menu.this, Activity_Reserva.class);
            startActivity(intent); // Iniciar la actividad
        });

        // Configurar el botón de "Parcelas" para abrir la actividad Activity_Parcela
        btnParcelas.setOnClickListener(view -> {
            // Crear un Intent para navegar a la actividad Activity_Parcela
            Intent intent = new Intent(Menu.this, Activity_Parcela.class);
            startActivity(intent); // Iniciar la actividad
        });

        btnPruebas.setOnClickListener(view -> {
            // Crear un Intent para navegar a la actividad Activity_Parcela
            Intent intent = new Intent(Menu.this, Activity_Pruebas.class);
            startActivity(intent); // Iniciar la actividad
        });
    }
}
