package clasesDAO;

import java.sql.*;

import utils.PoolConnectionManager;

public class TokenDAO {
	 // Método para guardar el token de restablecimiento en una tabla separada con fecha de expiración opcional
	public static void guardarTokenRestablecimiento(String nombreUsuario, String token) {
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        conn = PoolConnectionManager.getConnection();
	        
	        // Inserta o actualiza el token de restablecimiento en la tabla tokens_restablecimiento
	        String query = "INSERT INTO sisinf_db.tokens_restablecimiento (nombre_usuario, token, fecha_expiracion) " +
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
	        // Liberar el PreparedStatement y la conexión
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

    
    public static boolean verificarTokenRestablecimiento(String nombreUsuario, String token, String correo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();

            // Consulta para verificar que el token es válido y que pertenece al usuario correcto con el correo correcto
            String query = "SELECT 1 FROM sisinf_db.tokens_restablecimiento tr " +
                           "JOIN sisinf_db.USUARIO u ON tr.nombre_usuario = u.nombre_usuario " +
                           "WHERE tr.nombre_usuario = ? AND tr.token = ? AND u.correo_elec = ? AND tr.fecha_expiracion > NOW()";

            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, token);
            ps.setString(3, correo);

            rs = ps.executeQuery();

            // Retorna true si el token, el usuario y el correo coinciden, y el token no ha expirado
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

	
	public static void eliminarTokenRestablecimiento(String nombreUsuario) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	
	    try {
	        conn = PoolConnectionManager.getConnection();
	        
	        // Consulta para eliminar el token asociado al nombre de usuario
	        String query = "DELETE FROM sisinf_db.tokens_restablecimiento WHERE nombre_usuario = ?";
	        
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

