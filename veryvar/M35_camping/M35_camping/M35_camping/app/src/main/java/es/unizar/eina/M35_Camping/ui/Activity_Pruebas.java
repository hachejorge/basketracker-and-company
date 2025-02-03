package es.unizar.eina.M35_Camping.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.ParcelaRoomDatabase;

public class Activity_Pruebas extends AppCompatActivity {

    private Button btnPruebasCajaNegra, btnPruebasVolumen, btnPruebasSobrecarga;
    private ProgressBar progressBar;
    private UnitTests mUnitTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);

        ParcelaRoomDatabase database = ParcelaRoomDatabase.getDatabase(this);
        mUnitTests = new UnitTests(database);

        btnPruebasCajaNegra = findViewById(R.id.btn_pruebascajanegra);
        btnPruebasVolumen = findViewById(R.id.btn_pruebasdevolumen);
        btnPruebasSobrecarga = findViewById(R.id.btn_pruebassobrecarga);
        progressBar = findViewById(R.id.progress_dialog);

        // Asegurarse de que el ProgressBar está inicialmente oculto
        progressBar.setVisibility(View.GONE);

        btnPruebasCajaNegra.setOnClickListener(view -> {
            toggleProgressBar(true);
            new Thread(() -> {
                mUnitTests.addition_isCorrect();
                mUnitTests.update_isCorrect();
                mUnitTests.delete_isCorrect();
                mUnitTests.addition_isIncorrect();
                mUnitTests.update_isIncorrect();
                mUnitTests.delete_isIncorrect();
                runOnUiThread(() -> {
                    toggleProgressBar(false);
                    Toast.makeText(this, "Las pruebas se han lanzado con éxito", Toast.LENGTH_SHORT).show();
                });
            }).start();
        });

        btnPruebasVolumen.setOnClickListener(view -> {
            toggleProgressBar(true);
            new Thread(() -> {
                mUnitTests.pruebasdeVolumen();
                runOnUiThread(() -> {
                    toggleProgressBar(false);
                    Toast.makeText(this, "Las pruebas se han lanzado con éxito", Toast.LENGTH_SHORT).show();
                });
            }).start();
        });

        btnPruebasSobrecarga.setOnClickListener(view -> {
            toggleProgressBar(true);
            new Thread(() -> {
                mUnitTests.pruebadeSobrecarga();
                runOnUiThread(() -> {
                    toggleProgressBar(false);
                    Toast.makeText(this, "Las pruebas se han lanzado con éxito", Toast.LENGTH_SHORT).show();
                });
            }).start();
        });
    }

    private void toggleProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
