package es.unizar.eina.M35_Camping.ui;


import android.util.Log;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.unizar.eina.M35_Camping.database.Parcela;
import es.unizar.eina.M35_Camping.database.ParcelaDAO;
import es.unizar.eina.M35_Camping.database.ParcelaRoomDatabase;
import es.unizar.eina.M35_Camping.database.Reserva;
import es.unizar.eina.M35_Camping.database.ReservaDAO;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {
    private static ReservaDAO mReservaDao;
    private static ParcelaDAO mParcelaDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    // Constructor to initialize DAOs
    public UnitTests(ParcelaRoomDatabase database) {
        this.mParcelaDao = database.ParcelaDao();
        this.mReservaDao = database.ReservaDao();
    }

    public void addition_isCorrect() {
        executor.execute(() -> mParcelaDao.insert(new Parcela("titulo", "descripcion", 4, 14.0f)));

        executor.execute(() -> mReservaDao.insert(new Reserva(0, "cliente", 654251202, "2024-08-12", "2024-08-14")));
    }

    public void addition_isIncorrect() {
        List<Parcela> invalidParcelas = Arrays.asList(
                new Parcela(null, "descripcion", 4, 14.0f),
                new Parcela("", "descripcion", 4, 14.0f),
                new Parcela("titulo", null, 4, 14.0f),
                new Parcela("titulo", "", 4, 14.0f),
                new Parcela("titulo", "descripcion", null, 14.0f),
                new Parcela("titulo", "descripcion", 0, 14.0f),
                new Parcela("titulo", "descripcion", 4, null),
                new Parcela("titulo", "descripcion", 4, 0.0f)
        );

        for (Parcela parcela : invalidParcelas) {
            if (parcela.getNombre() == null || parcela.getNombre().isEmpty() ||
                    parcela.getDes() == null || parcela.getDes().isEmpty() ||
                    parcela.getMaxOcup() == null || parcela.getMaxOcup() <= 0 ||
                    parcela.getPrecPer() == null || parcela.getPrecPer() < 0) {
                continue;
            }
            executor.execute(() -> mParcelaDao.insert(parcela));
        }

        List<Reserva> invalidReservas = Arrays.asList(
                new Reserva(-1, "cliente", 654251202, "2024-08-12", "2024-08-14"),
                new Reserva(null, "cliente", 654251202, "2024-08-12", "2024-08-14"),
                new Reserva(0, null, 654251202, "2024-08-12", "2024-08-14"),
                new Reserva(0, "", 654251202, "2024-08-12", "2024-08-14"),
                new Reserva(0, "cliente", 0, "2024-08-12", "2024-08-14"),
                new Reserva(0, "cliente", 654251202, "12/08/2024", "2024-08-14"),
                new Reserva(0, "cliente", 654251202, "2024-08-30", "2024-08-14"),
                new Reserva(0, "cliente", 654251202, "null", "2024-08-14"),
                new Reserva(0, "cliente", 654251202, "2024-08-12", "14/08/2024"),
                new Reserva(0, "cliente", 654251202, "2024-08-12", "2024-02-30"),
                new Reserva(0, "cliente", 654251202, "2024-08-12", null)
        );

        for (Reserva reserva : invalidReservas) {
            executor.execute(() -> mReservaDao.insert(reserva));
        }

        Reserva Reservaprueba13 = new Reserva(0, "cliente", 654251202, "2024-08-12", "2024-08-14");
        executor.execute(() -> mReservaDao.insert(Reservaprueba13));

        Reserva Reservaprueba14 = new Reserva(0, "cliente", 654251202, "2024-08-12", "2024-08-14");
        executor.execute(() -> mReservaDao.insert(Reservaprueba14));
    }

    public void update_isCorrect() {
        executor.execute(() -> mParcelaDao.update(new Parcela("titulo", "descripcion", 4, 14.0f)));

        Reserva Reservaprueba1 = new Reserva(0, "cliente", 654251202, "2024-08-12", "2024-08-14");
        Reservaprueba1.setPrecioTotal(24.02f);
        executor.execute(() -> mReservaDao.update(Reservaprueba1));
    }

    public void update_isIncorrect() {
        List<Parcela> invalidParcelas = Arrays.asList(
                new Parcela(null, "descripcion", 4, 14.0f),
                new Parcela("", "descripcion", 4, 14.0f),
                new Parcela("titulo", null, 4, 14.0f),
                new Parcela("titulo", "", 4, 14.0f),
                new Parcela("titulo", "descripcion", null, 14.0f),
                new Parcela("titulo", "descripcion", 0, 14.0f),
                new Parcela("titulo", "descripcion", 4, null),
                new Parcela("titulo", "descripcion", 4, 0.0f)
        );

        for (Parcela parcela : invalidParcelas) {
            executor.execute(() -> mParcelaDao.update(parcela));
        }

        List<Reserva> invalidReservas = Arrays.asList(
                new Reserva(-1, "cliente", 654251202, "2024-08-12", "2024-08-14"),
                new Reserva(null, "cliente", 654251202, "2024-08-12", "2024-08-14"),
                new Reserva(0, null, 654251202, "2024-08-12", "2024-08-14"),
                new Reserva(0, "", 654251202, "2024-08-12", "2024-08-14"),
                new Reserva(0, "cliente", 0, "2024-08-12", "2024-08-14"),
                new Reserva(0, "cliente", 654251202, "12/08/2024", "2024-08-14"),
                new Reserva(0, "cliente", 654251202, "2024-08-30", "2024-08-14"),
                new Reserva(0, "cliente", 654251202, "null", "2024-08-14"),
                new Reserva(0, "cliente", 654251202, "2024-08-12", "14/08/2024"),
                new Reserva(0, "cliente", 654251202, "2024-08-12", "2024-02-30"),
                new Reserva(0, "cliente", 654251202, "2024-08-12", null)
        );

        for (Reserva reserva : invalidReservas) {
            executor.execute(() -> mReservaDao.update(reserva));
        }

        Reserva Reservaprueba13 = new Reserva(0, "cliente", 654251202, "2024-08-12", "2024-08-14");
        executor.execute(() -> mReservaDao.update(Reservaprueba13));

        Reserva Reservaprueba14 = new Reserva(0, "cliente", 654251202, "2024-08-12", "2024-08-14");
        executor.execute(() -> mReservaDao.update(Reservaprueba14));
    }

    public void delete_isCorrect() {
        executor.execute(() -> mParcelaDao.delete(new Parcela("titulo", "descripcion", 4, 14.0f)));

        Reserva Reservaprueba1 = new Reserva(0, "cliente", 654251202, "2024-08-12", "null");
        Reservaprueba1.setPrecioTotal(24.02f);
        executor.execute(() -> mReservaDao.delete(Reservaprueba1));
    }

    public void delete_isIncorrect() {
        executor.execute(() ->  mParcelaDao.delete(new Parcela(null, "descripcion", 4, 14.0f)));

        Reserva Reservaprueba1 = new Reserva(-1, "cliente", 654251202, "2024-08-12", "null");
        Reservaprueba1.setPrecioTotal(24.02f);
        executor.execute(() -> mReservaDao.delete(Reservaprueba1));
    }

    public void pruebasdeVolumen() {
        for(int i = 0; i < 100; i++) {
            Parcela finalParcela = new Parcela("parcela-" + i, "desc", 1, 1.0f);;
            executor.execute(() -> mParcelaDao.insert(finalParcela));
        }

        for(int i = 0; i < 10000; i++) {
            Reserva finalReserva = new Reserva("reserva-" + i, 648521362, "2024-08-02", "2024-08-04");;
            executor.execute(() -> mReservaDao.insert(finalReserva));
        }
    }

    public void pruebadeSobrecarga() {
        int longitudMaxima = 0;
        boolean operacionExitosa = true;

        while (operacionExitosa) {
            String descripcion = "a";
            for (int i = 0; i < longitudMaxima; i++) {
                descripcion += "a";
            }


            Parcela parcela = new Parcela("título" + longitudMaxima, descripcion, 4, 14.0f);

            try {
                executor.execute(() -> mParcelaDao.insert(parcela));
                longitudMaxima += 10000;  // Incrementar la longitud en 200 caracteres
                Log.d("Prueba", "Inserción correcta para descripción con longitud: " + descripcion.length());
            } catch (OutOfMemoryError e) {
                operacionExitosa = false;  // La inserción falla por falta de memoria
                Log.d("Prueba", "Inserción fallida para descripción con longitud: " + longitudMaxima);
            } catch (Exception e) {
                operacionExitosa = false;  // Captura otros errores generales
                Log.d("Prueba", "Inserción fallida para descripción con longitud: " + longitudMaxima);
            }
        }
        Log.d("Prueba", "Máxima longitud soportada: " + longitudMaxima);
    }
}