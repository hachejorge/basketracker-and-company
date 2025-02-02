package clasesDAO;

import clasesVO.UsuarioVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.PoolConnectionManager;

/**
 * Clase de acceso a datos para la entidad Usuario. Proporciona métodos para realizar operaciones
 * CRUD en la base de datos para la tabla `usuario`, tales como guardar, actualizar, listar, 
 * eliminar y buscar usuarios por nombre o correo electrónico.
 */
public class UsuarioDAO {

    public UsuarioDAO() {
        // Constructor vacío, se puede inicializar EntityManagerFactory aquí si se usa JPA en el futuro.
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto UsuarioVO que contiene la información del usuario a guardar.
     */
    public static void guardarUsuario(UsuarioVO usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = PoolConnectionManager.getConnection();
            String query = "INSERT INTO sisinf.usuario (nombre_usuario, correo_elec, password) VALUES (?, ?, ?)";
            
            ps = conn.prepareStatement(query);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getCorreoElect());
            ps.setString(3, usuario.getPassword());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            PoolConnectionManager.releaseConnection(conn);
        }
    }

    /**
     * Busca un usuario en la base de datos utilizando su nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario del usuario que se desea buscar.
     * @return UsuarioVO El objeto UsuarioVO si se encuentra, o null si no se encuentra.
     */
    public static UsuarioVO obtenerUsuarioPorNombre(String nombreUsuario) {
        UsuarioVO user = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.usuario WHERE nombre_usuario = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String nameuser = rs.getString("nombre_usuario");
                String email = rs.getString("correo_elec");
                String password = rs.getString("password");
                user = new UsuarioVO(nameuser, email, password);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PoolConnectionManager.releaseConnection(conn);
        }
        return user;
    }

    /**
     * Lista todos los usuarios registrados en la base de datos.
     *
     * @return List<UsuarioVO> Lista de todos los usuarios.
     */
    public static List<UsuarioVO> listarUsuarios() {
        List<UsuarioVO> usuarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.usuario";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String correoElec = rs.getString("correo_elec");
                String password = rs.getString("password");
                usuarios.add(new UsuarioVO(nombreUsuario, correoElec, password));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            PoolConnectionManager.releaseConnection(conn);
        }
        return usuarios;
    }
    
    /**
     * Actualiza la información de un usuario en la base de datos.
     *
     * @param usuario El objeto UsuarioVO que contiene los nuevos datos del usuario.
     */
    public static void actualizarUsuario(UsuarioVO usuario) {
        Connection conn = null;
        PreparedStatement psCheck = null;
        PreparedStatement psUpdate = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String checkQuery = "SELECT 1 FROM sisinf.usuario WHERE nombre_usuario = ?";
            psCheck = conn.prepareStatement(checkQuery);
            psCheck.setString(1, usuario.getNombreUsuario());
            rs = psCheck.executeQuery();

            if (rs.next()) {
                String updateQuery = "UPDATE sisinf.usuario SET correo_elec = ?, password = ? WHERE nombre_usuario = ?";
                psUpdate = conn.prepareStatement(updateQuery);
                psUpdate.setString(1, usuario.getCorreoElect());
                psUpdate.setString(2, usuario.getPassword());
                psUpdate.setString(3, usuario.getNombreUsuario());
                psUpdate.executeUpdate();
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("El usuario con nombre '" + usuario.getNombreUsuario() + "' no existe en la base de datos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error actualizando el usuario en la base de datos", e);
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (psCheck != null) try { psCheck.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (psUpdate != null) try { psUpdate.close(); } catch (SQLException e) { e.printStackTrace(); }
            PoolConnectionManager.releaseConnection(conn);
        }
    }

    /**
     * Elimina un usuario de la base de datos basado en su nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario del usuario a eliminar.
     */
    public static void eliminarUsuario(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf.usuario WHERE nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            PoolConnectionManager.releaseConnection(conn);
        }
    }
    
    /**
     * Busca un usuario en la base de datos utilizando su correo electrónico.
     *
     * @param correo El correo electrónico del usuario que se desea buscar.
     * @return UsuarioVO El objeto UsuarioVO si se encuentra, o null si no se encuentra.
     */
    public static UsuarioVO obtenerUsuarioPorCorreo(String correo) {
        UsuarioVO usuario = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.usuario WHERE correo_elec = ?";
            
            ps = conn.prepareStatement(query);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String correoElec = rs.getString("correo_elec");
                String password = rs.getString("password");
                usuario = new UsuarioVO(nombreUsuario, correoElec, password);
            } 
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            PoolConnectionManager.releaseConnection(conn);
        }
        
        return usuario;
    }
}
