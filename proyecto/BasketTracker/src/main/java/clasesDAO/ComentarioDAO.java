package clasesDAO;

import clasesVO.ComentarioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.PoolConnectionManager;

/**
 * La clase ComentarioDAO proporciona métodos para interactuar con la base de datos
 * en relación a los comentarios de los usuarios en los partidos. Permite realizar
 * operaciones CRUD sobre los comentarios, como guardarlos, obtenerlos, listarlos y eliminarlos.
 */
public class ComentarioDAO {

    /**
     * Guarda un nuevo comentario en la base de datos.
     * 
     * @param comentario El objeto ComentarioVO que contiene los datos del comentario a guardar.
     */
    public void guardarComentario(ComentarioVO comentario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Obtener la conexión a la base de datos
            conn = PoolConnectionManager.getConnection();
            // Consulta SQL para insertar el comentario
            String query = "INSERT INTO sisinf.comentario (nombre_usuario, id_partido, comentario) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, comentario.getNombreUsuario());
            ps.setInt(2, comentario.getIdPartido());
            ps.setString(3, comentario.getComentario());

            // Ejecutar la actualización (insertar el comentario)
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Capturar y mostrar errores SQL
        } finally {
            // Cerrar el PreparedStatement y liberar la conexión
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
     * Obtiene un comentario específico de la base de datos, dado un usuario y un partido.
     * 
     * @param nombreUsuario El nombre de usuario del que se quiere obtener el comentario.
     * @param idPartido El ID del partido asociado al comentario.
     * @return El objeto ComentarioVO que contiene los datos del comentario, o null si no se encuentra.
     */
    public ComentarioVO obtenerComentarioPorUsuarioYPartido(String nombreUsuario, int idPartido) {
        ComentarioVO comentario = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.comentario WHERE nombre_usuario = ? AND id_partido = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setInt(2, idPartido);
            rs = ps.executeQuery();

            // Si existe el comentario, se crea el objeto ComentarioVO
            if (rs.next()) {
                comentario = new ComentarioVO(
                    rs.getString("nombre_usuario"),
                    rs.getInt("id_partido"),
                    rs.getString("comentario")
                );
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
        return comentario;
    }

    /**
     * Obtiene una lista de todos los comentarios almacenados en la base de datos.
     * 
     * @return Una lista de objetos ComentarioVO, cada uno representando un comentario.
     */
    public List<ComentarioVO> listarComentarios() {
        List<ComentarioVO> comentarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.comentario";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            // Crear objetos ComentarioVO para cada fila de la consulta
            while (rs.next()) {
                ComentarioVO comentario = new ComentarioVO(
                    rs.getString("nombre_usuario"),
                    rs.getInt("id_partido"),
                    rs.getString("comentario")
                );
                comentarios.add(comentario);
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
        return comentarios;
    }

    /**
     * Obtiene una lista de comentarios de un usuario específico.
     * 
     * @param nombreUsuario El nombre del usuario para el que se desean obtener los comentarios.
     * @return Una lista de objetos ComentarioVO que representan los comentarios de ese usuario.
     */
    public List<ComentarioVO> listarComentariosPorUsuario(String nombreUsuario) {
        List<ComentarioVO> comentarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.comentario WHERE nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

            // Crear objetos ComentarioVO para cada comentario del usuario
            while (rs.next()) {
                ComentarioVO comentario = new ComentarioVO(
                    rs.getString("nombre_usuario"),
                    rs.getInt("id_partido"),
                    rs.getString("comentario")
                );
                comentarios.add(comentario);
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
        return comentarios;
    }

    /**
     * Obtiene una lista de comentarios asociados a un partido específico.
     * 
     * @param idPartido El ID del partido para el que se desean obtener los comentarios.
     * @return Una lista de objetos ComentarioVO que representan los comentarios de ese partido.
     */
    public static List<ComentarioVO> listarComentariosPorPartido(int idPartido) {
        List<ComentarioVO> comentarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.comentario WHERE id_partido = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            // Crear objetos ComentarioVO para cada comentario del partido
            while (rs.next()) {
                ComentarioVO comentario = new ComentarioVO(
                    rs.getString("nombre_usuario"),
                    rs.getInt("id_partido"),
                    rs.getString("comentario")
                );
                comentarios.add(comentario);
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
        return comentarios;
    }

    /**
     * Elimina un comentario específico de la base de datos.
     * 
     * @param nombreUsuario El nombre de usuario del que se eliminará el comentario.
     * @param idPartido El ID del partido asociado al comentario que se eliminará.
     * @param comentario El texto del comentario a eliminar.
     */
    public void eliminarComentario(String nombreUsuario, int idPartido, String comentario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf.comentario WHERE nombre_usuario = ? AND id_partido = ? AND comentario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setInt(2, idPartido);
            ps.setString(3, comentario);
            // Ejecutar la actualización (eliminar el comentario)
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
