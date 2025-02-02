package clasesDAO;

import clasesVO.PtsJugParVO; 
import clasesVO.HistoricoVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.PoolConnectionManager;

/**
 * Clase de acceso a datos para manejar los registros de puntos de jugadores en partidos.
 * Proporciona métodos para almacenar, actualizar, obtener y eliminar los registros de puntos,
 * además de métodos adicionales para el análisis histórico y estadísticas.
 */
public class PtsJugParDAO {

	  /**
     * Guarda un registro de puntos de un jugador en un partido.
     *
     * @param ptsJugPar Objeto PtsJugParVO que contiene los datos de puntos del jugador en el partido.
     */    
	public static void guardarPtsJugPar(PtsJugParVO ptsJugPar) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "INSERT INTO sisinf.pts_jug_par (id_partido, nombre_usuario, pts_ant, trp_ant, " +
                           "tlb_lan, tlb_ant, faltas, mnt_jd) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, ptsJugPar.getIdPartido());
            ps.setString(2, ptsJugPar.getNombreUsuario());
            ps.setObject(3, ptsJugPar.getPtsAnt());
            ps.setObject(4, ptsJugPar.getTrpAnt());
            ps.setObject(5, ptsJugPar.getTlbLan());
            ps.setObject(6, ptsJugPar.getTlbAnt());
            ps.setObject(7, ptsJugPar.getFaltas());
            ps.setObject(8, ptsJugPar.getMntJd());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos directamente aquí
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
    }

	/**
     * Obtiene un registro de puntos de un jugador en un partido específico.
     *
     * @param idPartido ID del partido.
     * @param nombreUsuario Nombre del usuario.
     * @return Objeto PtsJugParVO que contiene los datos de puntos del jugador en el partido, o null si no se encuentra.
     */
    public static PtsJugParVO obtenerPtsJugPar(int idPartido, String nombreUsuario) {
        PtsJugParVO ptsJugPar = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.pts_jug_par WHERE id_partido = ? AND nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPartido);
            ps.setString(2, nombreUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                ptsJugPar = new PtsJugParVO(
                        rs.getInt("id_partido"),
                        rs.getString("nombre_usuario"),
                        (Integer) rs.getObject("pts_ant"),
                        (Integer) rs.getObject("trp_ant"),
                        (Integer) rs.getObject("tlb_lan"),
                        (Integer) rs.getObject("tlb_ant"),
                        (Integer) rs.getObject("faltas"),
                        (Integer) rs.getObject("mnt_jd")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos directamente aquí
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return ptsJugPar;
    }

    /**
     * Lista todos los registros de puntos de jugadores en partidos.
     *
     * @return Lista de objetos PtsJugParVO que contiene los datos de todos los jugadores en todos los partidos.
     */
    public List<PtsJugParVO> listarPtsJugPar() {
        List<PtsJugParVO> ptsJugParList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.pts_jug_par";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                ptsJugParList.add(new PtsJugParVO(
                        rs.getInt("id_partido"),
                        rs.getString("nombre_usuario"),
                        (Integer) rs.getObject("pts_ant"),
                        (Integer) rs.getObject("trp_ant"),
                        (Integer) rs.getObject("tlb_lan"),
                        (Integer) rs.getObject("tlb_ant"),
                        (Integer) rs.getObject("faltas"),
                        (Integer) rs.getObject("mnt_jd")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos directamente aquí
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return ptsJugParList;
    }

    /**
     * Actualiza un registro de puntos de un jugador en un partido.
     *
     * @param ptsJugPar Objeto PtsJugParVO con los datos actualizados del jugador.
     */    
    public static void actualizarPtsJugPar(PtsJugParVO ptsJugPar) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "UPDATE sisinf.pts_jug_par SET pts_ant = ?, trp_ant = ?, tlb_lan = ?, tlb_ant = ?, " +
                           "faltas = ?, mnt_jd = ? WHERE id_partido = ? AND nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setObject(1, ptsJugPar.getPtsAnt());
            ps.setObject(2, ptsJugPar.getTrpAnt());
            ps.setObject(3, ptsJugPar.getTlbLan());
            ps.setObject(4, ptsJugPar.getTlbAnt());
            ps.setObject(5, ptsJugPar.getFaltas());
            ps.setObject(6, ptsJugPar.getMntJd());
            ps.setInt(7, ptsJugPar.getIdPartido());
            ps.setString(8, ptsJugPar.getNombreUsuario());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos directamente aquí
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
    }

    /**
     * Elimina un registro de puntos de un jugador en un partido.
     *
     * @param idPartido ID del partido.
     * @param nombreUsuario Nombre del usuario.
     */
    public void eliminarPtsJugPar(int idPartido, String nombreUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf.pts_jug_par WHERE id_partido = ? AND nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPartido);
            ps.setString(2, nombreUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos directamente aquí
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
    }

    /**
     * Obtiene el histórico de puntos de un jugador en todos los partidos.
     *
     * @param nombreUsuario Nombre del usuario.
     * @return Lista de objetos PtsJugParVO que contiene el histórico de puntos del jugador.
     */
    public static List<PtsJugParVO> obtenerHistoricoPorJugador(String nombreUsuario) {
        List<PtsJugParVO> historico = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT id_partido, nombre_usuario, pts_ant, mnt_jd, tlb_ant, trp_ant, tlb_lan, faltas " +
                           "FROM sisinf.pts_jug_par WHERE nombre_usuario = ? ORDER BY id_partido";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                historico.add(new PtsJugParVO(
                        rs.getInt("id_partido"),
                        rs.getString("nombre_usuario"),
                        (Integer) rs.getObject("pts_ant"),
                        (Integer) rs.getObject("trp_ant"),
                        (Integer) rs.getObject("tlb_lan"),
                        (Integer) rs.getObject("tlb_ant"), // tlb_ant no es necesario, así que lo dejamos como null
                        (Integer) rs.getObject("faltas"),
                        (Integer) rs.getObject("mnt_jd")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos directamente aquí
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return historico;
    }

 // Método para obtener el historial de puntos del jugador para un partido específico
    public HistoricoVO obtenerHistorial(int idPartido, String nombreUsuario) {
        int partidosJugados = 0;
        int totalPts = 0;
        int totalMinutos = 0;
        int totalTirosLibres = 0;
        int tirosLibresAnotados = 0; // Nueva variable para tiros libres anotados
        int totalTriples = 0;
        int totalFaltas = 0;

        List<PtsJugParVO> ptsList = obtenerPtsPorPartidoYUsuario(idPartido, nombreUsuario);

        for (PtsJugParVO pts : ptsList) {
            if (pts != null) {
                partidosJugados++;
                totalPts += pts.getPtsAnt() != null ? pts.getPtsAnt() : 0;
                totalMinutos += pts.getMntJd() != null ? pts.getMntJd() : 0;
                totalTirosLibres += pts.getTlbLan() != null ? pts.getTlbLan() : 0;
                tirosLibresAnotados += pts.getTlbAnt() != null ? pts.getTlbAnt() : 0; // Acumula tiros libres anotados
                totalTriples += pts.getTrpAnt() != null ? pts.getTrpAnt() : 0;
                totalFaltas += pts.getFaltas() != null ? pts.getFaltas() : 0;
            }
        }

        return new HistoricoVO(nombreUsuario, partidosJugados, totalPts, totalMinutos, totalTirosLibres, tirosLibresAnotados, totalTriples, totalFaltas);
    }

    // Método auxiliar para obtener los puntos por partido y usuario
    private List<PtsJugParVO> obtenerPtsPorPartidoYUsuario(int idPartido, String nombreUsuario) {
        List<PtsJugParVO> ptsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.pts_jug_par WHERE id_partido = ? AND nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPartido);
            ps.setString(2, nombreUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                ptsList.add(new PtsJugParVO(
                        rs.getInt("id_partido"),
                        rs.getString("nombre_usuario"),
                        (Integer) rs.getObject("pts_ant"),
                        (Integer) rs.getObject("trp_ant"),
                        (Integer) rs.getObject("tlb_lan"),
                        (Integer) rs.getObject("tlb_ant"),
                        (Integer) rs.getObject("faltas"),
                        (Integer) rs.getObject("mnt_jd")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos directamente aquí
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return ptsList;
    }
    
    // Método para calcular las estadísticas del histórico
    public static HistoricoVO calcularEstadisticasHistorico(List<PtsJugParVO> historico) {
        int totalPts = 0;
        int totalMinutos = 0;
        int partidosJugados = historico.size();
        int totalTirosLibres = 0;
        int tirosLibresAnotados = 0; // Nueva variable para tiros libres anotados
        int totalTriples = 0;
        int totalFaltas = 0;
        String nombreUsuario = null;
        if(!historico.isEmpty()) {
	         nombreUsuario = historico.get(0).getNombreUsuario();
	        for (PtsJugParVO pts : historico) {
	            if (pts.getPtsAnt() != null) {
	                totalPts += pts.getPtsAnt();
	            }
	            if (pts.getMntJd() != null) {
	                totalMinutos += pts.getMntJd();
	            }
	            if (pts.getTlbLan() != null) {
	                totalTirosLibres += pts.getTlbLan();
	            }
	            if (pts.getTlbAnt() != null) { // Suponiendo que `TlbAnt` almacena los tiros libres anotados
	                tirosLibresAnotados += pts.getTlbAnt();
	            }
	            if (pts.getTrpAnt() != null) {
	                totalTriples += pts.getTrpAnt();
	            }
	            if (pts.getFaltas() != null) {
	                totalFaltas += pts.getFaltas();
	            }
	        }
        }

        return new HistoricoVO(nombreUsuario, partidosJugados, totalPts, totalMinutos, totalTirosLibres, tirosLibresAnotados, totalTriples, totalFaltas);
    }
    
    public static List<PtsJugParVO> obtenerDatosJugadoresPorPartido(int idPartido) {
        List<PtsJugParVO> jugadoresList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.pts_jug_par WHERE id_partido = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            while (rs.next()) {
                jugadoresList.add(new PtsJugParVO(
                        rs.getInt("id_partido"),
                        rs.getString("nombre_usuario"),
                        (Integer) rs.getObject("pts_ant"),
                        (Integer) rs.getObject("trp_ant"),
                        (Integer) rs.getObject("tlb_lan"),
                        (Integer) rs.getObject("tlb_ant"),
                        (Integer) rs.getObject("faltas"),
                        (Integer) rs.getObject("mnt_jd")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos directamente aquí
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return jugadoresList;
    }
}
