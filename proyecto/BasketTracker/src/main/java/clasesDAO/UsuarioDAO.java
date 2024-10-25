package clasesDAO;

import clasesVO.UsuarioVO;
import conectDB.PoolConnectionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.*;
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
    public UsuarioVO obtenerUsuarioPorNombre(String nombreUsuario) {
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
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Usuario", UsuarioVO.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar la información de un usuario
    public void actualizarUsuario(UsuarioVO usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para eliminar un usuario por su nombre de usuario
    public void eliminarUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            UsuarioVO usuario = obtenerUsuarioPorNombre(nombreUsuario);
            if (usuario != null) {
                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
