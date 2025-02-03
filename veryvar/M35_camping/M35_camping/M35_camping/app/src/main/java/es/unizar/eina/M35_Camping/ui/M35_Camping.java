package es.unizar.eina.M35_Camping.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import es.unizar.eina.M35_Camping.R;

/**
 * Actividad principal de la aplicación que muestra una pantalla de carga al inicio.
 * Después de un retraso de 3 segundos, la actividad cambia a la pantalla del menú principal.
 */
public class M35_Camping extends AppCompatActivity {

    /**
     * Método llamado cuando la actividad se crea.
     * Aquí se configura el layout de la pantalla de carga y se inicia un retraso
     * para luego cambiar a la siguiente actividad (Menú).
     *
     * @param savedInstanceState El estado guardado de la instancia, si es que existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campify_pantalla_carga); // Asegúrate de que el layout 'campify_pantalla_carga' esté creado

        // Se lanza una tarea en segundo plano que esperará 3 segundos antes de cambiar a la siguiente actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Después de 3 segundos, se inicia la actividad del menú
                Intent intent = new Intent(M35_Camping.this, Menu.class);
                startActivity(intent);

                // Finaliza la actividad de la pantalla de carga para que no quede en el backstack
                finish();
            }
        }, 3000); // El retraso de 3000 milisegundos (3 segundos) antes de cambiar de actividad
    }
}
