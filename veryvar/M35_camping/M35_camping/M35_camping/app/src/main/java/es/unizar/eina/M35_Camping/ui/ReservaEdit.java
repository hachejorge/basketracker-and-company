package es.unizar.eina.M35_Camping.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Ocupantes;
import es.unizar.eina.M35_Camping.database.ParcelaDAO;
import es.unizar.eina.M35_Camping.database.Reserva;

/**
 * Actividad para editar una reserva.
 * Esta clase maneja la edición de los datos de una reserva, incluyendo fechas, cliente y teléfono.
 * También permite gestionar los ocupantes asociados a la reserva y sus parcelas.
 */
public class ReservaEdit extends AppCompatActivity implements OcupantesEditListAdapter.OnDeleteClickListener, OcupantesEditListAdapter.OnEditClickListener {

    private TextView etId;
    private EditText etCliente, etTelefono, etFechEnt, etFechSal;
    private ImageButton btnSave, btnBack, btnAddParcela;
    private OcupantesEditViewModel mOcupantesEditViewModel;
    private RecyclerView mRecyclerView;
    private OcupantesEditListAdapter mAdapter;
    private ReservaViewModel mReservaViewModel;
    private ParcelaViewModel mParcelaViewModel;

    private String fechaentOG;
    private String fechasalOG;
    private ParcelaDAO mParcelaDao;

    public static final String RESERVA_ID = "com.example.notepad.PARCELA_NOMBRE";
    public static final String RESERVA_CLIENTE = "com.example.notepad.PARCELA_DESC";
    public static final String RESERVA_TELEFONO = "com.example.notepad.PARCELA_MAX_OCUP";
    public static final String RESERVA_FECHENT = "com.example.notepad.PARCELA_PREC_PER";
    public static final String RESERVA_FECHSAL = "com.example.notepad.PARCELA_PRECIO_TOTAL";

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_edit_con_parcelas);

        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);
        mParcelaViewModel = new ViewModelProvider(this).get(ParcelaViewModel.class);

        int id = getIntent().getIntExtra("id", 0);
        String cliente = getIntent().getStringExtra("cliente");
        int telefono = getIntent().getIntExtra("telefono", 0);
        String fechent = getIntent().getStringExtra("fechent");
        String fechsal = getIntent().getStringExtra("fechsal");
        fechaentOG = fechent;
        fechasalOG = fechsal;

        ((TextView) findViewById(R.id.et_id)).setText("#" + String.valueOf(id));
        ((EditText) findViewById(R.id.et_cliente)).setText(cliente);
        ((EditText) findViewById(R.id.et_telefono)).setText(String.valueOf(telefono));
        ((EditText) findViewById(R.id.et_fechent)).setText(fechent);
        ((EditText) findViewById(R.id.et_fechsal)).setText(fechsal);

        etId = findViewById(R.id.et_id);
        etCliente = findViewById(R.id.et_cliente);
        etTelefono = findViewById(R.id.et_telefono);
        etFechEnt = findViewById(R.id.et_fechent);
        etFechSal = findViewById(R.id.et_fechsal);
        btnSave = findViewById(R.id.save_button);
        btnBack = findViewById(R.id.back_button);

        mRecyclerView = findViewById(R.id.recyclerparcelaview);
        mAdapter = new OcupantesEditListAdapter(new OcupantesEditListAdapter.OcupantesEditDiff(), this, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        etFechEnt.setOnClickListener(v -> showDatePickerDialog(etFechEnt, true));
        etFechSal.setOnClickListener(v -> showDatePickerDialog(etFechSal, false));

        mOcupantesEditViewModel = new ViewModelProvider(this).get(OcupantesEditViewModel.class);
        mOcupantesEditViewModel.getAllOcupantesPorId(id).observe(this, ocupantes -> {
            mAdapter.submitList(ocupantes);
        });

        btnAddParcela = findViewById(R.id.fabparcela);
        btnAddParcela.setOnClickListener(view -> {
            // Validar las fechas
            if (fechaentOG != null && fechasalOG != null) {
                // Convertir las fechas a un formato que permita compararlas (por ejemplo, `LocalDate`)
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                try {
                    Date fechaEntrada = dateFormat.parse(fechaentOG);
                    Date fechaSalida = dateFormat.parse(fechasalOG);

                    if (fechaEntrada != null && fechaSalida != null && !fechaSalida.after(fechaEntrada)) {
                        // Mostrar mensaje si la fecha de salida no es posterior a la de entrada
                        Toast.makeText(this, "La fecha de salida debe ser posterior a la fecha de entrada.", Toast.LENGTH_SHORT).show();
                        return; // No continuar con el inicio de la actividad
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error al procesar las fechas.", Toast.LENGTH_SHORT).show();
                    return; // No continuar con el inicio de la actividad
                }
            }

            // Continuar con la creación de la intención y el inicio de la actividad
            Intent intent = new Intent(this, OcupantesCreate.class);
            intent.putExtra("id", id);
            intent.putExtra("fechent", fechaentOG);
            intent.putExtra("fechsal", fechasalOG);
            startActivity(intent);
        });


        etFechEnt.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String formattedMonth = String.format("%02d", selectedMonth + 1); // +1 porque los meses empiezan en 0
                    String formattedDay = String.format("%02d", selectedDay);
                    String newFechEnt = selectedYear + "-" + formattedMonth + "-" + formattedDay;
                    etFechEnt.setText(newFechEnt);

                    checkDateChange(newFechEnt, etFechSal.getText().toString().trim());
                }, year, month, day);
            datePickerDialog.show();
        });

        etFechSal.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String formattedMonth = String.format("%02d", selectedMonth + 1); // +1 porque los meses empiezan en 0
                        String formattedDay = String.format("%02d", selectedDay);
                        String newFechSal = selectedYear + "-" + formattedMonth + "-" + formattedDay;
                        etFechSal.setText(newFechSal);

                        checkDateChange(etFechEnt.getText().toString().trim(), newFechSal);
                    }, year, month, day);
            datePickerDialog.show();
        });

        btnSave.setOnClickListener(v -> updateReserva());

        btnBack.setOnClickListener(v -> finish());
    }

    /**
     *
     * @param ocupantes El objeto Ocupantes que debe ser eliminado.
     */
    @Override
    public void onDeleteClick(Ocupantes ocupantes) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de que deseas eliminar esta parcela?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    mReservaViewModel.singleReservaById(ocupantes.getReservaid()).observe(this, reserva -> {
                        if (reserva == null) {
                            // Muestra mensaje si no se encuentra la reserva
                            Toast.makeText(this, "No se encontró la reserva.", Toast.LENGTH_SHORT).show();
                            return; // Salir del método si la reserva no existe
                        }
                        double precioPorNoche = ocupantes.getPrecioparcela();
                        int numNoches = calcularNumeroNoches(reserva.getFechEnt(), reserva.getFechSal());
                        Float precioTotal = reserva.getPrecioTotal() - (float) (precioPorNoche * numNoches * ocupantes.getOcp());
                        // Actualizar la reserva con el nuevo precio total
                        mOcupantesEditViewModel.delete(ocupantes);
                        reserva.setPrecioTotal(precioTotal);
                        mReservaViewModel.update(reserva);
                        Toast.makeText(this, "Parcela eliminada", Toast.LENGTH_SHORT).show();
                    });
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    /**
     *
     * @param fechaEntrada
     * @param fechaSalida
     * @return
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

    /**
     *
     * @param ocupantes El objeto Ocupantes que debe ser editado.
     */
    @Override
    public void onEditClick(Ocupantes ocupantes) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaEntrada = dateFormat.parse(etFechEnt.getText().toString().trim());
            Date fechaSalida = dateFormat.parse(etFechSal.getText().toString().trim());

            if (fechaSalida == null || fechaEntrada == null || fechaSalida.before(fechaEntrada)) {
                Toast.makeText(this, "La fecha de salida debe ser posterior a la fecha de entrada.", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = getIntent().getIntExtra("id", 0);
            Intent intent = new Intent(this, OcupantesEdit.class);
            intent.putExtra("id", id);
            intent.putExtra("fechent", etFechEnt.getText().toString().trim());
            intent.putExtra("fechsal", etFechSal.getText().toString().trim());
            intent.putExtra("nocup", ocupantes.getOcp());
            intent.putExtra("nombreparcela", ocupantes.getParcelanombre());
            startActivity(intent);
        } catch (ParseException e) {
            Toast.makeText(this, "Formato de fecha inválido. Usa el formato 'yyyy-MM-dd'.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void updateReserva() {
        try {
            String cliente = etCliente.getText().toString().trim();
            int telefono = Integer.parseInt(etTelefono.getText().toString().trim());
            String fechent = etFechEnt.getText().toString().trim();
            String fechsal = etFechSal.getText().toString().trim();

            if (cliente.isEmpty() || telefono == 0 || fechent.isEmpty() || fechsal.isEmpty()) {
                Toast.makeText(this, "Todos los campos deben ser completados.", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaEntrada = dateFormat.parse(fechent);
            Date fechaSalida = dateFormat.parse(fechsal);

            if (fechaSalida.before(fechaEntrada)) {
                Toast.makeText(this, "La fecha de salida debe ser posterior a la fecha de entrada.", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(etId.getText().toString().replace("#", "").trim());
            mReservaViewModel.singleReservaById(id).observe(this, reserva -> {

                Reserva updatedReserva = new Reserva(id, cliente, telefono, fechent, fechsal);
                Float precioTotal = reserva.getPrecioTotal();
                updatedReserva.setPrecioTotal(precioTotal);
                mReservaViewModel.update(updatedReserva);
                Toast.makeText(this, "Reserva actualizada. El precio total de la reserva: " + updatedReserva.getPrecioTotal() + "€", Toast.LENGTH_SHORT).show();
                finish();

            });
        } catch (ParseException | NumberFormatException e) {
            Toast.makeText(this, "Error al actualizar la reserva.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     *
     * @param editText
     * @param isEntrada
     */
    private void showDatePickerDialog(EditText editText, boolean isEntrada) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String date = year1 + "-" + (month1 + 1) + "-" + dayOfMonth;
            editText.setText(date);

            if (isEntrada) {
                fechaentOG = date;
            } else {
                fechasalOG = date;
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void checkDateChange(String newFechent, String newFechsal) {

        String originalFechent = fechaentOG != null ? fechaentOG : "";
        String originalFechsal = fechasalOG != null ? fechasalOG : "";

        // Compara las fechas actuales con las nuevas fechas
        if (!originalFechent.equals(newFechent) || !originalFechsal.equals(newFechsal)) {
            int id = getIntent().getIntExtra("id", 0);

            mOcupantesEditViewModel.getSingleOcupantesPorId(id).observe(this, ocupantes -> {
                if (!ocupantes.isEmpty()) {
                    // Mostrar el cuadro de diálogo de confirmación
                    new AlertDialog.Builder(ReservaEdit.this)
                            .setTitle("Confirmar cambio de fechas")
                            .setMessage("¿Estás seguro de que deseas cambiar las fechas? Los ocupantes actuales serán eliminados.")
                            .setPositiveButton("Sí", (dialog, which) -> {
                                // Borrar ocupantes y permitir el cambio de fechas
                                deleteOcupantes(ocupantes);
                                etFechEnt.setText(newFechent);
                                etFechSal.setText(newFechsal);
                                fechaentOG = newFechent;
                                fechasalOG = newFechsal;
                                // Actualizar la reserva con el nuevo precio total
                                mReservaViewModel.singleReservaById(id).observe(this, reserva -> {
                                    if (reserva == null) {
                                        // Muestra mensaje si no se encuentra la reserva
                                        Toast.makeText(this, "No se encontró la reserva.", Toast.LENGTH_SHORT).show();
                                        return; // Salir del método si la reserva no existe
                                    }

                                    // Actualizar la reserva con el nuevo precio total
                                    reserva.setFechEnt(fechaentOG);
                                    reserva.setFechSal(fechasalOG);
                                    reserva.setPrecioTotal(0.0f);
                                    mReservaViewModel.update(reserva);

                                });
                            })
                            .setNegativeButton("No", (dialog, which) -> {
                                // Revertir el cambio de fecha
                                etFechEnt.setText(originalFechent);
                                etFechSal.setText(originalFechsal);
                            })
                            .show();
                } else {
                    // No hay ocupantes, por lo que solo se actualizan las fechas
                    etFechEnt.setText(newFechent);
                    etFechSal.setText(newFechsal);
                    fechaentOG = newFechent;
                    fechasalOG = newFechsal;
                    mReservaViewModel.singleReservaById(id).observe(this, reserva -> {
                        reserva.setFechEnt(fechaentOG);
                        reserva.setFechSal(fechasalOG);
                        reserva.setPrecioTotal(0.0f);
                        mReservaViewModel.update(reserva);
                    });
                }
            });
        }
    }

    /**
     * Elimina un conjunto de ocupantes de la reserva.
     * Este método se encarga de eliminar todos los ocupantes asociados a una reserva
     * y actualizar el precio total de la reserva en consecuencia.
     *
     * @param ocupantes Lista de ocupantes que se desean eliminar de la reserva.
     */
    private void deleteOcupantes(List<Ocupantes> ocupantes) {
        // Iterar sobre todos los ocupantes y eliminarlos de la base de datos
        for (Ocupantes ocupante : ocupantes) {
            mOcupantesEditViewModel.delete(ocupante); // Elimina el ocupante de la base de datos
        }

        // Muestra un mensaje indicando que los ocupantes fueron eliminados
        Toast.makeText(this, "Ocupantes eliminados.", Toast.LENGTH_SHORT).show();
    }
}
