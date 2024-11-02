package clasesDAO;

import clasesVO.MensajeVO;
import utils.PoolConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensajeDAO {

    public MensajeDAO() {
        // Constructor vacío
    }

    // Método para obtener el ID máximo en la tabla MENSAJE
    public static int obtenerIdMaximo() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idMaximo = 0;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT COALESCE(MAX(id_mensaje), 0) FROM sisinf_db.MENSAJE";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                idMaximo = rs.getInt(1);
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

        return idMaximo;
    }

    // Método para guardar un mensaje
    public static void guardarMensaje(MensajeVO mensaje) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            int nuevoId = obtenerIdMaximo() + 1;

            String query = "INSERT INTO sisinf_db.MENSAJE (id_mensaje, nombre_usuario, mensaje, hora, fecha) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, nuevoId);
            ps.setString(2, mensaje.getNombreUsuario());
            ps.setString(3, mensaje.getMensaje());
            ps.setTime(4, mensaje.getHora());
            ps.setDate(5, mensaje.getFecha());

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

    // Método para obtener un mensaje por su ID
    public MensajeVO obtenerMensajePorId(int idMensaje) {
        MensajeVO mensaje = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.MENSAJE WHERE id_mensaje = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idMensaje);
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_mensaje");
                String nombreUsuario = rs.getString("nombre_usuario");
                String mensajeContenido = rs.getString("mensaje");
                Time hora = rs.getTime("hora");
                Date fecha = rs.getDate("fecha");

                mensaje = new MensajeVO(id, nombreUsuario, mensajeContenido, hora, fecha);
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
        return mensaje;
    }

    // Método para listar todos los mensajes
    public List<MensajeVO> listarMensajes() {
        List<MensajeVO> mensajes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.MENSAJE";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_mensaje");
                String nombreUsuario = rs.getString("nombre_usuario");
                String mensajeContenido = rs.getString("mensaje");
                Time hora = rs.getTime("hora");
                Date fecha = rs.getDate("fecha");

                mensajes.add(new MensajeVO(id, nombreUsuario, mensajeContenido, hora, fecha));
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
        return mensajes;
    }

    // Método para actualizar un mensaje
    public void actualizarMensaje(MensajeVO mensaje) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "UPDATE sisinf_db.MENSAJE SET nombre_usuario = ?, mensaje = ?, hora = ?, fecha = ? WHERE id_mensaje = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, mensaje.getNombreUsuario());
            ps.setString(2, mensaje.getMensaje());
            ps.setTime(3, mensaje.getHora());
            ps.setDate(4, mensaje.getFecha());
            ps.setInt(5, mensaje.getIdMensaje());

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

    // Método para eliminar un mensaje por ID
    public static void eliminarMensaje(int idMensaje) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf_db.MENSAJE WHERE id_mensaje = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idMensaje);

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
    
    // Método para obtener mensajes de un usuario ordenados de más antiguo a más reciente
    public static List<MensajeVO> obtenerMensajesPorUsuario(String nombreUsuario) {
        List<MensajeVO> mensajes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.MENSAJE WHERE nombre_usuario = ? ORDER BY fecha ASC, hora ASC";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_mensaje");
                String usuario = rs.getString("nombre_usuario");
                String mensajeContenido = rs.getString("mensaje");
                Time hora = rs.getTime("hora");
                Date fecha = rs.getDate("fecha");

                mensajes.add(new MensajeVO(id, usuario, mensajeContenido, hora, fecha));
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
        return mensajes;
    }
}
