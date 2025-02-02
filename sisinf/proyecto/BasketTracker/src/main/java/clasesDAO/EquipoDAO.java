package clasesDAO;

import clasesVO.*;
import utils.PoolConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase EquipoDAO proporciona acceso a la base de datos para realizar operaciones CRUD
 * sobre los equipos en el sistema. Permite guardar, obtener, actualizar, listar, buscar y eliminar equipos.
 */
public class EquipoDAO {

    /**
     * Guarda un nuevo equipo en la base de datos.
     * 
     * @param equipo El objeto EquipoVO que contiene los detalles del equipo a guardar.
     */
    public void guardarEquipo(EquipoVO equipo) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "INSERT INTO sisinf.equipo (nombre_equipo, ubicacion, competicion) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, equipo.getNombreEquipo());
            ps.setString(2, equipo.getUbicacion());
            ps.setString(3, equipo.getCompeticion());

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
     * Obtiene un equipo de la base de datos por su ID.
     * 
     * @param idEquipo El ID del equipo a obtener.
     * @return El objeto EquipoVO que representa al equipo, o null si no se encuentra.
     */
    public static EquipoVO obtenerEquipoPorId(int idEquipo) {
        EquipoVO equipo = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.equipo WHERE id_equipo = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idEquipo);
            rs = ps.executeQuery();

            if (rs.next()) {
                String nombreEquipo = rs.getString("nombre_equipo");
                String ubicacion = rs.getString("ubicacion");
                String competicion = rs.getString("competicion");
                equipo = new EquipoVO(idEquipo, nombreEquipo, ubicacion, competicion);
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
        return equipo;
    }

    /**
     * Obtiene una lista de todos los equipos registrados en la base de datos.
     * 
     * @return Una lista de objetos EquipoVO representando todos los equipos.
     */
    public List<EquipoVO> listarEquipos() {
        List<EquipoVO> equipos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.equipo";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idEquipo = rs.getInt("id_equipo");
                String nombreEquipo = rs.getString("nombre_equipo");
                String ubicacion = rs.getString("ubicacion");
                String competicion = rs.getString("competicion");
                equipos.add(new EquipoVO(idEquipo, nombreEquipo, ubicacion, competicion));
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
        return equipos;
    }

    /**
     * Actualiza los detalles de un equipo en la base de datos.
     * 
     * @param equipo El objeto EquipoVO que contiene los nuevos detalles del equipo.
     */
    public void actualizarEquipo(EquipoVO equipo) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "UPDATE sisinf.equipo SET nombre_equipo = ?, ubicacion = ?, competicion = ? WHERE id_equipo = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, equipo.getNombreEquipo());
            ps.setString(2, equipo.getUbicacion());
            ps.setString(3, equipo.getCompeticion());
            ps.setInt(4, equipo.getIdEquipo());
            
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
     * Obtiene la lista de jugadores que pertenecen a un equipo específico.
     * 
     * @param idEquipo El ID del equipo cuyos jugadores se desean obtener.
     * @return Una lista de objetos JugadorVO que representan a los jugadores del equipo.
     */
    public static List<JugadorVO> obtenerEquipo(int idEquipo) {
        List<JugadorVO> jugadores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT nombre_usuario, nombre_jugador, equipo FROM sisinf.jugador WHERE equipo = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idEquipo);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String nombreJugador = rs.getString("nombre_jugador");
                int equipo = rs.getInt("equipo");

                // Crear un objeto JugadorVO y agregarlo a la lista
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
     * Obtiene todos los equipos que participan en una competición específica.
     * 
     * @param competicion El nombre de la competición.
     * @return Una lista de objetos EquipoVO que representan a los equipos en esa competición.
     */
    public static List<EquipoVO> obtenerEquiposPorCompeticion(String competicion) {
        List<EquipoVO> equipos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.equipo WHERE competicion = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, competicion);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idEquipo = rs.getInt("id_equipo");
                String nombreEquipo = rs.getString("nombre_equipo");
                String ubicacion = rs.getString("ubicacion");
                String competicionEquipo = rs.getString("competicion");
                equipos.add(new EquipoVO(idEquipo, nombreEquipo, ubicacion, competicionEquipo));
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

        return equipos;
    }

    /**
     * Realiza una búsqueda de equipos por nombre (parcial) en la base de datos.
     * 
     * @param searchTerm El término de búsqueda parcial que se usará para encontrar equipos por nombre.
     * @return Una lista de objetos EquipoVO que coinciden con el término de búsqueda.
     * @throws SQLException Si ocurre algún error en la consulta.
     */
    public static List<EquipoVO> buscarEquipos(String searchTerm) throws SQLException {
        List<EquipoVO> equipos = new ArrayList<>();
        String query = "SELECT * FROM sisinf.EQUIPO WHERE nombre_equipo ILIKE ?";

        try (Connection conn = PoolConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + searchTerm + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idEquipo = rs.getInt("id_equipo");
                String nombreEquipo = rs.getString("nombre_equipo");
                String ubicacion = rs.getString("ubicacion");
                String competicion = rs.getString("competicion");
                equipos.add(new EquipoVO(idEquipo, nombreEquipo, ubicacion, competicion));
            }
        }
        return equipos;
    }

    /**
     * Obtiene la competición asociada a un equipo por su ID.
     * 
     * @param idEquipo El ID del equipo.
     * @return El nombre de la competición del equipo, o null si no se encuentra.
     */
    public static String obtenerCompeticionPorIdEquipo(int idEquipo) {
        String competicion = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT competicion FROM sisinf.equipo WHERE id_equipo = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idEquipo);
            rs = ps.executeQuery();

            if (rs.next()) {
                competicion = rs.getString("competicion");
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

        return competicion;
    }

    /**
     * Verifica si existe un equipo con el mismo nombre en una competición específica.
     * 
     * @param nombreEquipo El nombre del equipo a buscar.
     * @param competicion El nombre de la competición en la que se buscará el equipo.
     * @return {@code true} si existe un equipo con el mismo nombre en la competición dada; {@code false} en caso contrario.
     */
    public static boolean existeEquipoConMismoNombre(String nombreEquipo, String competicion) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT COUNT(*) FROM sisinf.equipo WHERE nombre_equipo = ? AND competicion = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreEquipo);
            ps.setString(2, competicion);
            rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
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

        return existe;
    }
    
    /**
     * Busca equipos cuyo nombre contiene el término especificado, sin distinguir entre mayúsculas y minúsculas.
     *
     * @param nombreParcial El término parcial de búsqueda para el nombre del equipo.
     * @return Una lista de objetos EquipoVO que coinciden parcial o completamente con el nombre especificado.
     * @throws SQLException Si ocurre algún error en la consulta a la base de datos.
     */
    public static List<EquipoVO> buscarEquiposPorNombre(String nombreParcial) throws SQLException {
        List<EquipoVO> equipos = new ArrayList<>();
        String query = "SELECT * FROM sisinf.equipo WHERE nombre_equipo ILIKE ?";

        try (Connection conn = PoolConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Agregar comodines para la búsqueda parcial
            ps.setString(1, "%" + nombreParcial + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idEquipo = rs.getInt("id_equipo");
                    String nombreEquipo = rs.getString("nombre_equipo");
                    String ubicacion = rs.getString("ubicacion");
                    String competicion = rs.getString("competicion");

                    equipos.add(new EquipoVO(idEquipo, nombreEquipo, ubicacion, competicion));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return equipos;
    }

}


