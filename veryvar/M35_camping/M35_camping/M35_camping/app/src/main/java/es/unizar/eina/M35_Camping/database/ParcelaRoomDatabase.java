package es.unizar.eina.M35_Camping.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase que representa la base de datos principal de la aplicación.
 * Contiene las entidades: Parcela, Reserva, y Ocupantes.
 * Proporciona acceso a los DAOs correspondientes para realizar operaciones con cada entidad.
 */
@Database(entities = {Parcela.class, Reserva.class, Ocupantes.class}, version = 3, exportSchema = false)
public abstract class ParcelaRoomDatabase extends RoomDatabase {

    /**
     * Métodos abstractos para obtener los DAOs de cada entidad.
     */
    public abstract ParcelaDAO ParcelaDao();
    public abstract ReservaDAO ReservaDao();
    public abstract OcupantesDAO OcupantesDao();

    /**
     * Instancia singleton de la base de datos.
     */
    private static volatile ParcelaRoomDatabase INSTANCE;

    /**
     * Número de hilos disponibles para el executor.
     */
    private static final int NUMBER_OF_THREADS = 4;

    /**
     * ExecutorService para realizar operaciones en la base de datos en segundo plano.
     */
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Obtiene la instancia de la base de datos.
     * Si no existe, se inicializa de forma segura para hilos.
     *
     * @param context Contexto de la aplicación.
     * @return Instancia de la base de datos.
     */
    public static ParcelaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ParcelaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ParcelaRoomDatabase.class, "parcela_database")
                            .addCallback(sRoomDatabaseCallback) // Configuración adicional al crear/abrir la base de datos
                            .fallbackToDestructiveMigration() // Permitir destrucción de datos al cambiar versiones
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Callback para realizar operaciones específicas al crear o abrir la base de datos.
     */
    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // Habilitar claves foráneas al abrir la base de datos
            db.execSQL("PRAGMA foreign_keys = ON;");
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);        }
    };
}
