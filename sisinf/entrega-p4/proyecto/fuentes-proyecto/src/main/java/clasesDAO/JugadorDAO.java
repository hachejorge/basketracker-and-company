package clasesDAO;

import clasesVO.JugadorVO;
import utils.PoolConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) relacionadas con los jugadores en la base de datos.
 * Utiliza el PoolConnectionManager para manejar las conexiones a la base de datos de manera eficiente.
 */
public class JugadorDAO {

    /**
     * Constructor vacío para la clase JugadorDAO.
     */
    public JugadorDAO() {
        // Constructor vacío
    }

    /**
     * Guarda un jugador en la base de datos.
     * 
     * @param jugador Objeto JugadorVO que contiene la información del jugador a guardar.
     */
    public static void guardarJugador(JugadorVO jugador) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();

            // SQL para insertar un nuevo jugador en la base de datos
            String query = "INSERT INTO sisinf_db.jugador (nombre_usuario, nombre_jugador, equipo) VALUES (?, ?, ?)";

            ps = conn.prepareStatement(query);
            ps.setString(1, jugador.getNombreUsuario());  // Nombre de usuario
            ps.setString(2, jugador.getNombreJugador());  // Nombre del jugador
            ps.setInt(3, jugador.getEquipo());            // ID del equipo

            // Ejecutar la inserción
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

    /**
     * Obtiene un jugador desde la base de datos utilizando su nombre de usuario.
     * 
     * @param nombreUsuario El nombre de usuario del jugador a buscar.
     * @return Un objeto JugadorVO con la información del jugador encontrado, o null si no se encuentra.
     */
    public static JugadorVO obtenerJugadorPorNombreUsuario(String nombreUsuario) {
        JugadorVO jugador = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.jugador WHERE nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                String nombreUsuarioDb = rs.getString("nombre_usuario");
                String nombreJugador = rs.getString("nombre_jugador");
                int equipo = rs.getInt("equipo");
                jugador = new JugadorVO(nombreUsuarioDb, nombreJugador, equipo);
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
        return jugador;
    }

    /**
     * Obtiene una lista de todos los jugadores registrados en la base de datos.
     * 
     * @return Una lista de objetos JugadorVO con la información de todos los jugadores.
     */
    public List<JugadorVO> listarJugadores() {
        List<JugadorVO> jugadores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.jugador";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String nombreJugador = rs.getString("nombre_jugador");
                int equipo = rs.getInt("equipo");
                jugadores.add(new JugadorVO(nombreUsuario, nombreJugador, equipo));
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
        return jugadores;
    }

    /**
     * Actualiza los datos de un jugador en la base de datos.
     * Verifica si el jugador existe antes de intentar la actualización.
     * 
     * @param jugador El objeto JugadorVO con los nuevos datos del jugador.
     */
    public static void actualizarJugador(JugadorVO jugador) {
        Connection conn = null;
        PreparedStatement psCheck = null;
        PreparedStatement psUpdate = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();

            // Verificar si el jugador existe en la base de datos
            String checkQuery = "SELECT 1 FROM sisinf_db.jugador WHERE nombre_usuario = ?";
            psCheck = conn.prepareStatement(checkQuery);
            psCheck.setString(1, jugador.getNombreUsuario());
            rs = psCheck.executeQuery();

            if (rs.next()) {
                // Si el jugador existe, proceder con la actualización
                String updateQuery = "UPDATE sisinf_db.jugador SET nombre_jugador = ?, equipo = ? WHERE nombre_usuario = ?";
                psUpdate = conn.prepareStatement(updateQuery);
                psUpdate.setString(1, jugador.getNombreJugador());
                psUpdate.setInt(2, jugador.getEquipo());
                psUpdate.setString(3, jugador.getNombreUsuario());
                psUpdate.executeUpdate();
                System.out.println("Jugador actualizado correctamente.");
            } else {
                System.out.println("El jugador con nombre '" + jugador.getNombreUsuario() + "' no existe en la base de datos.");
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

    /**
     * Elimina un jugador de la base de datos dado su nombre de usuario.
     * 
     * @param nombreUsuario El nombre de usuario del jugador a eliminar.
     */
    public void eliminarJugador(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf_db.jugador WHERE nombre_usuario = ?";
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
    
    /**
     * Obtiene una lista de jugadores que pertenecen a un equipo en una competición específica.
     * 
     * @param nombreCompeticion El nombre de la competición para filtrar los jugadores.
     * @return Una lista de objetos JugadorVO que pertenecen a equipos en la competición especificada.
     */
    public static List<JugadorVO> listarJugadoresPorCompeticion(String nombreCompeticion) {
        List<JugadorVO> jugadores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT j.nombre_usuario, j.nombre_jugador, j.equipo " +
                           "FROM sisinf_db.JUGADOR j " +
                           "JOIN sisinf_db.EQUIPO e ON j.equipo = e.id_equipo " +
                           "WHERE e.competicion = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreCompeticion);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String nombreJugador = rs.getString("nombre_jugador");
                int equipoId = rs.getInt("equipo");
                jugadores.add(new JugadorVO(nombreUsuario, nombreJugador, equipoId));
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
        return jugadores;
    }

    /**
     * Verifica si un jugador con el nombre de usuario especificado existe en la base de datos.
     * 
     * @param nombreUsuario El nombre de usuario del jugador a verificar.
     * @return true si el jugador existe, false en caso contrario.
     */
    public static boolean existeJugador(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT 1 FROM sisinf_db.jugador WHERE nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

            return rs.next();

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
        return false;
    }

    /**
     * Verifica si un jugador con el nombre y equipo especificados existe en la base de datos.
     * 
     * @param nombreJugador El nombre del jugador a verificar.
     * @param equipo El ID del equipo donde se verifica la existencia del jugador.
     * @return true si el jugador existe en el equipo, false en caso contrario.
     */
    public static boolean existeJugadorEnEquipo(String nombreJugador, int equipo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT 1 FROM sisinf_db.jugador WHERE nombre_jugador = ? AND equipo = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreJugador);
            ps.setInt(2, equipo);
            rs = ps.executeQuery();

            return rs.next();

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
        return false;
    }

    /**
     * Busca jugadores por un término parcial en su nombre de usuario o nombre de jugador.
     * 
     * @param termino El término de búsqueda para nombre de usuario o nombre de jugador.
     * @return Una lista de jugadores cuyo nombre de usuario o nombre de jugador coincidan parcialmente con el término dado.
     */
    public static List<JugadorVO> buscarJugadores(String termino) {
        List<JugadorVO> jugadores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.jugador WHERE nombre_usuario ILIKE ? OR nombre_jugador ILIKE ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + termino + "%");
            ps.setString(2, "%" + termino + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String nombreJugador = rs.getString("nombre_jugador");
                int equipo = rs.getInt("equipo");
                jugadores.add(new JugadorVO(nombreUsuario, nombreJugador, equipo));
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
        return jugadores;
    }

    /**
     * Busca jugadores por su nombre de jugador.
     * 
     * @param nombre El nombre del jugador a buscar.
     * @return Una lista de jugadores cuyo nombre coincida parcialmente con el nombre dado.
     */
    public static List<JugadorVO> buscarJugadoresPorNombre(String nombre) {
        List<JugadorVO> jugadores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.jugador WHERE nombre_jugador ILIKE ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String nombreJugador = rs.getString("nombre_jugador");
                int equipo = rs.getInt("equipo");
                jugadores.add(new JugadorVO(nombreUsuario, nombreJugador, equipo));
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
        return jugadores;
    }
}
