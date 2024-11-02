package clasesDAO;

import clasesVO.ComentarioVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.PoolConnectionManager;

public class ComentarioDAO {

    // Método para guardar un comentario
    public void guardarComentario(ComentarioVO comentario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "INSERT INTO sisinf_db.comentario (nombre_usuario, id_partido, comentario) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, comentario.getNombreUsuario());
            ps.setInt(2, comentario.getIdPartido());
            ps.setString(3, comentario.getComentario());

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

    // Método para obtener un comentario por usuario y partido
    public ComentarioVO obtenerComentarioPorUsuarioYPartido(String nombreUsuario, int idPartido) {
        ComentarioVO comentario = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.comentario WHERE nombre_usuario = ? AND id_partido = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setInt(2, idPartido);
            rs = ps.executeQuery();

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

    // Método para listar todos los comentarios
    public List<ComentarioVO> listarComentarios() {
        List<ComentarioVO> comentarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.comentario";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

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

    // Método para listar comentarios por usuario
    public List<ComentarioVO> listarComentariosPorUsuario(String nombreUsuario) {
        List<ComentarioVO> comentarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.comentario WHERE nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

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

    // Método para listar comentarios por partido
    public static List<ComentarioVO> listarComentariosPorPartido(int idPartido) {
        List<ComentarioVO> comentarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.comentario WHERE id_partido = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

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

    // Método para eliminar un comentario
    public void eliminarComentario(String nombreUsuario, int idPartido, String comentario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf_db.comentario WHERE nombre_usuario = ? AND id_partido = ? AND comentario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setInt(2, idPartido);
            ps.setString(3, comentario);
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
