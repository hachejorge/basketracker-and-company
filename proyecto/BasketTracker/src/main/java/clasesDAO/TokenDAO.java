package clasesDAO;

import java.sql.*;
import utils.PoolConnectionManager;

/**
 * Clase de acceso a datos para manejar tokens de restablecimiento de contraseña.
 * Proporciona métodos para almacenar, verificar y eliminar tokens de restablecimiento
 * de contraseña en la base de datos.
 */
public class TokenDAO {

    /**
     * Guarda un token de restablecimiento para un usuario específico en la base de datos.
     * Si el usuario ya tiene un token, este se actualiza con un nuevo valor y fecha de expiración.
     *
     * @param nombreUsuario El nombre de usuario del usuario que solicita el restablecimiento.
     * @param token El token de restablecimiento generado para el usuario.
     */
    public static void guardarTokenRestablecimiento(String nombreUsuario, String token) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();

            // Inserta o actualiza el token de restablecimiento con una fecha de expiración de una hora
            String query = "INSERT INTO sisinf.tokens_restablecimiento (nombre_usuario, token, fecha_expiracion) " +
                           "VALUES (?, ?, NOW() + INTERVAL '1 hour') " +
                           "ON CONFLICT (nombre_usuario) DO UPDATE SET token = EXCLUDED.token, " +
                           "fecha_expiracion = NOW() + INTERVAL '1 hour'";

            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, token);

            // Ejecutar la inserción o actualización
            ps.executeUpdate();
            System.out.println("Token de restablecimiento guardado correctamente para el usuario: " + nombreUsuario);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error guardando el token de restablecimiento en la base de datos", e);
        } finally {
            // Liberar PreparedStatement y la conexión
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            PoolConnectionManager.releaseConnection(conn);
        }
    }

    /**
     * Verifica si un token de restablecimiento es válido para un usuario específico y no ha expirado.
     *
     * @param nombreUsuario El nombre de usuario del usuario que intenta usar el token.
     * @param token El token de restablecimiento proporcionado por el usuario.
     * @param correo El correo electrónico del usuario, utilizado para verificar la identidad.
     * @return true si el token es válido, no ha expirado, y pertenece al usuario con el correo especificado; false en caso contrario.
     */
    public static boolean verificarTokenRestablecimiento(String nombreUsuario, String token, String correo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();

            // Consulta para verificar que el token y usuario coinciden y que el token no ha expirado
            String query = "SELECT 1 FROM sisinf.tokens_restablecimiento tr " +
                           "JOIN sisinf.USUARIO u ON tr.nombre_usuario = u.nombre_usuario " +
                           "WHERE tr.nombre_usuario = ? AND tr.token = ? AND u.correo_elec = ? AND tr.fecha_expiracion > NOW()";

            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, token);
            ps.setString(3, correo);

            rs = ps.executeQuery();

            // Retorna true si el token, el usuario, y el correo coinciden, y el token no ha expirado
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Liberar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                PoolConnectionManager.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Elimina el token de restablecimiento asociado a un usuario específico.
     *
     * @param nombreUsuario El nombre de usuario cuyo token de restablecimiento debe ser eliminado.
     */
    public static void eliminarTokenRestablecimiento(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();

            // Consulta para eliminar el token asociado al nombre de usuario
            String query = "DELETE FROM sisinf.tokens_restablecimiento WHERE nombre_usuario = ?";

            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);

            ps.executeUpdate(); // Ejecuta la eliminación

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Liberación de recursos
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            PoolConnectionManager.releaseConnection(conn);
        }
    }
}
