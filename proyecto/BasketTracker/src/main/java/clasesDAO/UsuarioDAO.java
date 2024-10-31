package clasesDAO;

import clasesVO.UsuarioVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import utils.PoolConnectionManager;

public class UsuarioDAO {

    // Crear una fábrica de EntityManagers
    //private EntityManagerFactory emf;
    
    public UsuarioDAO() {
    	//this.emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }
    // Método para guardar un usuario
    public void guardarUsuario(UsuarioVO usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = PoolConnectionManager.getConnection();
            
            // SQL para insertar un nuevo usuario en la base de datos
            String query = "INSERT INTO sisinf_db.usuario (nombre_usuario, correo_elec, password) VALUES (?, ?, ?)";
            
            ps = conn.prepareStatement(query);
            ps.setString(1, usuario.getNombreUsuario());  // Nombre de usuario
            ps.setString(2, usuario.getCorreoElect());  // Correo electrónico
            ps.setString(3, usuario.getPassword());  // Contraseña
            
            // Ejecutar la inserción
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Liberar la conexión y el PreparedStatement
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

    // Método para obtener un usuario por su nombre de usuario
    public static UsuarioVO obtenerUsuarioPorNombre(String nombreUsuario) {
        UsuarioVO user = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
        	conn = PoolConnectionManager.getConnection();
        	String query = "SELECT * FROM sisinf_db.usuario WHERE nombre_usuario = ?";
        	
        	PreparedStatement ps = conn.prepareStatement(query);
        	ps.setString(1, nombreUsuario);
        	rs = ps.executeQuery();
        	
        	if(rs.next()) {
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

    // Método para listar todos los usuarios
    public List<UsuarioVO> listarUsuarios() {
        List<UsuarioVO> usuarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.usuario";
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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            PoolConnectionManager.releaseConnection(conn);
        }
        return usuarios;
    }
    
    // Método para actualizar la información de un usuario
    public void actualizarUsuario(UsuarioVO usuario) {
        Connection conn = null;
        PreparedStatement psCheck = null;
        PreparedStatement psUpdate = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();

            // Verificar si el usuario existe en la base de datos
            String checkQuery = "SELECT 1 FROM sisinf_db.usuario WHERE nombre_usuario = ?";
            psCheck = conn.prepareStatement(checkQuery);
            psCheck.setString(1, usuario.getNombreUsuario());
            rs = psCheck.executeQuery();

            if (rs.next()) {
                // Si el usuario existe, proceder con la actualización
                String updateQuery = "UPDATE sisinf_db.usuario SET correo_elec = ?, password = ? WHERE nombre_usuario = ?";
                psUpdate = conn.prepareStatement(updateQuery);
                psUpdate.setString(1, usuario.getCorreoElect());
                psUpdate.setString(2, usuario.getPassword());
                psUpdate.setString(3, usuario.getNombreUsuario());
                psUpdate.executeUpdate();
                System.out.println("Usuario actualizado correctamente.");
            } else {
                // Si el usuario no existe, mostrar un mensaje de advertencia o lanzar una excepción
                System.out.println("El usuario con nombre '" + usuario.getNombreUsuario() + "' no existe en la base de datos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheck != null) {
                try {
                    psCheck.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            PoolConnectionManager.releaseConnection(conn);
        }
    }
   


    // Método para eliminar un usuario por su nombre de usuario
    public void eliminarUsuario(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf_db.usuario WHERE nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
}
