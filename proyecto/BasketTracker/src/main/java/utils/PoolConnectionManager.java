package utils;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Clase que abstrae la conexión con la base de datos utilizando un pool de conexiones.
 * Esta clase proporciona métodos estáticos para obtener y liberar conexiones de base de datos,
 * sin necesidad de abrir y cerrar manualmente las conexiones en cada operación.
 * Utiliza un contexto de JNDI (Java Naming and Directory Interface) para obtener el DataSource
 * configurado en el servidor de aplicaciones.
 */
public class PoolConnectionManager {

    /**
     * Obtiene una conexión a la base de datos desde el pool de conexiones configurado en el servidor de aplicaciones.
     * 
     * <p>Este método se conecta al pool de conexiones a través de JNDI, obteniendo la fuente de datos 
     * y luego recuperando una conexión. Si ocurre un error en la obtención de la conexión, el método 
     * devuelve {@code null}.</p>
     * 
     * @return Una conexión {@link Connection} válida o {@code null} si ocurre algún error al obtener la conexión.
     */
    public final static Connection getConnection() {
        try {
            // Obtención del contexto inicial
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            // Obtención del DataSource desde el contexto JNDI
            DataSource ds = (DataSource) envCtx.lookup("jdbc/SIDB");

            // Obtener la conexión desde el DataSource
            Connection conn = ds.getConnection();
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Libera una conexión, devolviéndola al pool de conexiones para su reutilización.
     * 
     * <p>Este método cierra la conexión de manera segura, asegurando que la conexión sea 
     * devuelta al pool y pueda ser reutilizada por otros procesos.</p>
     * 
     * @param conn La conexión {@link Connection} a liberar. Si la conexión es {@code null}, no se realiza ninguna acción.
     */
    public final static void releaseConnection(Connection conn) {
        try {
            // Si la conexión no es nula, la cerramos
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
