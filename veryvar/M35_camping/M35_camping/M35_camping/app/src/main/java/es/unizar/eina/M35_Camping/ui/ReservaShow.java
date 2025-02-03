package es.unizar.eina.M35_Camping.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import  es.unizar.eina.send.SendAbstraction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.send.SendAbstractionImpl;

/**
 * Actividad que muestra los detalles de una reserva, incluyendo la información del cliente
 * y una lista de ocupantes asociados a esa reserva.
 *
 * Esta actividad también permite navegar hacia atrás al presionar un botón de retroceso.
 */
public class ReservaShow extends AppCompatActivity {

    private ReservaViewModel mReservaViewModel; // ViewModel para manejar los datos de reserva
    private OcupantesViewModel mOcupantesViewModel; // ViewModel para manejar los datos de ocupantes
    private RecyclerView mRecyclerView; // RecyclerView para mostrar los ocupantes
    private OcupantesListAdapter mAdapter; // Adaptador para la lista de ocupantes
    private ImageButton btnBack, mSend; // Botón para retroceder a la actividad anterior

    /**
     * Método que se ejecuta cuando se crea la actividad.
     * Aquí se obtienen los datos pasados desde el Intent y se configuran las vistas.
     *
     * @param savedInstanceState Estado de la actividad guardado previamente (si existe).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_show); // Establecer el layout de la actividad

        // Inicializar el ViewModel para manejar la lógica de las reservas
        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);

        // Obtener los datos de la reserva desde el Intent
        int id = getIntent().getIntExtra("id", 0); // ID de la reserva
        String cliente = getIntent().getStringExtra("cliente"); // Nombre del cliente
        int telefono = getIntent().getIntExtra("telefono", 0); // Teléfono del cliente
        String fechent = getIntent().getStringExtra("fechent"); // Fecha de entrada
        String fechsal = getIntent().getStringExtra("fechsal"); // Fecha de salida

        // Observar los datos de la reserva con el ViewModel
        mReservaViewModel.singleReservaById(id).observe(this, reserva -> {
            // Obtener el precio total de la reserva
            Float precioTotal = reserva.getPrecioTotal();

            // Asignar los valores de los datos a los campos de texto (TextViews)
            ((TextView) findViewById(R.id.et_id)).setText("#" + String.valueOf(id));
            ((TextView) findViewById(R.id.et_cliente)).setText(cliente);
            ((TextView) findViewById(R.id.et_telefono)).setText(String.valueOf(telefono));
            ((TextView) findViewById(R.id.et_fechent)).setText(fechent);
            ((TextView) findViewById(R.id.et_fechsal)).setText(fechsal);
            ((TextView) findViewById(R.id.et_preciototal)).setText(precioTotal + "€");

            // Configurar el RecyclerView para mostrar los ocupantes
            mRecyclerView = findViewById(R.id.recyclerparcelaview);
            mAdapter = new OcupantesListAdapter(new OcupantesListAdapter.OcupantesDiff()); // Adaptador para la lista
            mRecyclerView.setAdapter(mAdapter); // Asignar el adaptador al RecyclerView
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // Usar un layout manager lineal

            // Obtener el ViewModel para los ocupantes
            mOcupantesViewModel = new ViewModelProvider(this).get(OcupantesViewModel.class);

            // Observar los datos de ocupantes para la reserva especificada
            mOcupantesViewModel.getAllOcupantesPorId(id).observe(this, ocupantes -> {
                // Actualizar el adaptador con los ocupantes obtenidos
                mAdapter.submitList(ocupantes);
            });

            // Configurar el botón de retroceso
            btnBack = findViewById(R.id.back_button);
            btnBack.setOnClickListener(v -> finish()); // Finalizar la actividad al presionar el botón

            ImageButton mSend = findViewById(R.id.send);
            mSend.setOnClickListener(view -> {
                if (reserva.getPrecioTotal() > 0) {
                    // Crear el AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Selecciona una opción")
                            .setMessage("¿Cómo deseas enviar la información?")
                            .setPositiveButton("WhatsApp", (dialog, which) -> {
                                // Selección de WhatsApp
                                SendAbstractionImpl mSendAbstractionImpl = new SendAbstractionImpl(this, "whatsapp");
                                mSendAbstractionImpl.send(
                                        String.valueOf(telefono),
                                        "Hola " + cliente + " aqui tiene la información de su reserva:\n" +
                                                "Fecha de entrada: " + fechent + "\n" +
                                                "Fecha de salida: " + fechsal + "\n" +
                                                "Precio total: " + precioTotal
                                );
                            })
                            .setNegativeButton("SMS", (dialog, which) -> {
                                // Selección de SMS
                                SendAbstractionImpl mSendAbstractionImpl = new SendAbstractionImpl(this, "SMS");
                                mSendAbstractionImpl.send(
                                        String.valueOf(telefono),
                                        "Hola " + cliente + " aqui tiene la información de su reserva:\n" +
                                                "Fecha de entrada: " + fechent + "\n" +
                                                "Fecha de salida: " + fechsal + "\n" +
                                                "Precio total: " + precioTotal
                                );
                            })
                            .setNeutralButton("Cancelar", (dialog, which) -> {
                                // Cerrar el diálogo sin hacer nada
                                dialog.dismiss();
                            });
                    builder.create().show();
                }
                else {
                    Toast.makeText(this, "Añade alguna parcela a la reserva", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
