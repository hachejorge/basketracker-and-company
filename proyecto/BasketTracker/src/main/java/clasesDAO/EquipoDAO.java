package clasesDAO;

import clasesVO.*;
import utils.PoolConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

    // Método para guardar un equipo
    public void guardarEquipo(EquipoVO equipo) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "INSERT INTO sisinf_db.equipo (id_equipo, nombre_equipo, ubicacion, competicion) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, equipo.getIdEquipo());
            ps.setString(2, equipo.getNombreEquipo());
            ps.setString(3, equipo.getUbicacion());
            ps.setString(4, equipo.getCompeticion());
            
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

    // Método para obtener un equipo por su ID
    public static EquipoVO obtenerEquipoPorId(int idEquipo) {
        EquipoVO equipo = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.equipo WHERE id_equipo = ?";
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

    // Método para listar todos los equipos
    public List<EquipoVO> listarEquipos() {
        List<EquipoVO> equipos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.equipo";
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

    // Método para actualizar un equipo
    public void actualizarEquipo(EquipoVO equipo) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "UPDATE sisinf_db.equipo SET nombre_equipo = ?, ubicacion = ?, competicion = ? WHERE id_equipo = ?";
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
    
    // Método para obtener la lista de jugadores de un equipo específico
    public static List<JugadorVO> obtenerEquipo(int idEquipo) {
        List<JugadorVO> jugadores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT nombre_usuario, nombre_jugador, equipo FROM sisinf_db.jugador WHERE equipo = ?";
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
            // Cerrar ResultSet, PreparedStatement y liberar la conexión
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
