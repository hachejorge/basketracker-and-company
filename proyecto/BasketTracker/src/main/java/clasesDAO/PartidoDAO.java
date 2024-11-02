package clasesDAO;

import clasesVO.PartidoVO; // Asegúrate de importar la clase desde el paquete correcto.
import clasesVO.JugadorVO; // Importa la clase JugadorVO.
import clasesVO.EquipoRankingVO; // Importa la clase JugadorVO.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;  // Importa Time si es necesario
import java.sql.Date;  // Importa Date si es necesario
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

import utils.PoolConnectionManager;

public class PartidoDAO {

    // Método para guardar un partido
    public void guardarPartido(PartidoVO partido) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // SQL para insertar un nuevo partido
            String query = "INSERT INTO sisinf_db.partido (id_partido, equipo_local, equipo_visitante, jornada, " +
                           "pts_c1_local, pts_c2_local, pts_c3_local, pts_c4_local, " +
                           "pts_c1_visit, pts_c2_visit, pts_c3_visit, pts_c4_visit, hora, fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, partido.getIdPartido());
            ps.setInt(2, partido.getEquipoLocal());
            ps.setInt(3, partido.getEquipoVisitante());
            ps.setInt(4, partido.getJornada());
            ps.setInt(5, partido.getPtsC1Local());
            ps.setInt(6, partido.getPtsC2Local());
            ps.setInt(7, partido.getPtsC3Local());
            ps.setInt(8, partido.getPtsC4Local());
            ps.setInt(9, partido.getPtsC1Visit());
            ps.setInt(10, partido.getPtsC2Visit());
            ps.setInt(11, partido.getPtsC3Visit());
            ps.setInt(12, partido.getPtsC4Visit());
            ps.setTime(13, partido.getHora()); // Guardar la hora
            ps.setDate(14, partido.getFecha()); // Guardar la fecha
            
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
    }

    // Método para obtener un partido por ID
    public static PartidoVO obtenerPartidoPorId(int idPartido) {
        PartidoVO partido = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.partido WHERE id_partido = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            if (rs.next()) {
                partido = new PartidoVO(
                        rs.getInt("id_partido"),
                        rs.getInt("equipo_local"),
                        rs.getInt("equipo_visitante"),
                        rs.getInt("jornada"),
                        rs.getInt("pts_c1_local"),
                        rs.getInt("pts_c2_local"),
                        rs.getInt("pts_c3_local"),
                        rs.getInt("pts_c4_local"),
                        rs.getInt("pts_c1_visit"),
                        rs.getInt("pts_c2_visit"),
                        rs.getInt("pts_c3_visit"),
                        rs.getInt("pts_c4_visit"),
                        rs.getTime("hora"),   // Obtener la hora
                        rs.getDate("fecha")    // Obtener la fecha
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return partido;
    }

    // Método para listar todos los partidos
    public List<PartidoVO> listarPartidos() {
        List<PartidoVO> partidos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.partido";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                partidos.add(new PartidoVO(
                        rs.getInt("id_partido"),
                        rs.getInt("equipo_local"),
                        rs.getInt("equipo_visitante"),
                        rs.getInt("jornada"),
                        rs.getInt("pts_c1_local"),
                        rs.getInt("pts_c2_local"),
                        rs.getInt("pts_c3_local"),
                        rs.getInt("pts_c4_local"),
                        rs.getInt("pts_c1_visit"),
                        rs.getInt("pts_c2_visit"),
                        rs.getInt("pts_c3_visit"),
                        rs.getInt("pts_c4_visit"),
                        rs.getTime("hora"),   // Obtener la hora
                        rs.getDate("fecha")    // Obtener la fecha
                ));
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return partidos;
    }

    // Método para actualizar un partido
    public void actualizarPartido(PartidoVO partido) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "UPDATE sisinf_db.partido SET equipo_local = ?, equipo_visitante = ?, jornada = ?, " +
                           "pts_c1_local = ?, pts_c2_local = ?, pts_c3_local = ?, pts_c4_local = ?, " +
                           "pts_c1_visit = ?, pts_c2_visit = ?, pts_c3_visit = ?, pts_c4_visit = ?, hora = ?, fecha = ? WHERE id_partido = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, partido.getEquipoLocal());
            ps.setInt(2, partido.getEquipoVisitante());
            ps.setInt(3, partido.getJornada());
            ps.setInt(4, partido.getPtsC1Local());
            ps.setInt(5, partido.getPtsC2Local());
            ps.setInt(6, partido.getPtsC3Local());
            ps.setInt(7, partido.getPtsC4Local());
            ps.setInt(8, partido.getPtsC1Visit());
            ps.setInt(9, partido.getPtsC2Visit());
            ps.setInt(10, partido.getPtsC3Visit());
            ps.setInt(11, partido.getPtsC4Visit());
            ps.setTime(12, partido.getHora()); // Actualizar la hora
            ps.setDate(13, partido.getFecha()); // Actualizar la fecha
            ps.setInt(14, partido.getIdPartido());
            // Ejecutar la actualización
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
    }

    // Método para eliminar un partido por ID
    public void eliminarPartido(int idPartido) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf_db.partido WHERE id_partido = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idPartido);
            // Ejecutar la eliminación
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
    }

    // Método para obtener el último partido por jugador
    public static PartidoVO obtenerUltimoPartidoPorJugador(JugadorVO jugador) {
        PartidoVO ultimoPartido = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT p.id_partido, p.equipo_local, p.equipo_visitante, p.jornada, " +
                           "p.pts_c1_local, p.pts_c2_local, p.pts_c3_local, p.pts_c4_local, " +
                           "p.pts_c1_visit, p.pts_c2_visit, p.pts_c3_visit, p.pts_c4_visit, " +
                           "p.hora, p.fecha " +
                           "FROM sisinf_db.partido p " +
                           "JOIN sisinf_db.pts_jug_par pj ON p.id_partido = pj.id_partido " +
                           "WHERE pj.nombre_usuario = ? " +  // Cambiado a nombre_jugador
                           "ORDER BY p.fecha DESC, p.hora DESC LIMIT 1";
            ps = conn.prepareStatement(query);
            ps.setString(1, jugador.getNombreUsuario());  // Usa el nombre de jugador desde JugadorVO
            rs = ps.executeQuery();

            if (rs.next()) {
                ultimoPartido = new PartidoVO(
                        rs.getInt("id_partido"),
                        rs.getInt("equipo_local"),
                        rs.getInt("equipo_visitante"),
                        rs.getInt("jornada"),
                        rs.getInt("pts_c1_local"),
                        rs.getInt("pts_c2_local"),
                        rs.getInt("pts_c3_local"),
                        rs.getInt("pts_c4_local"),
                        rs.getInt("pts_c1_visit"),
                        rs.getInt("pts_c2_visit"),
                        rs.getInt("pts_c3_visit"),
                        rs.getInt("pts_c4_visit"),
                        rs.getTime("hora"),   // Obtener la hora
                        rs.getDate("fecha")    // Obtener la fecha
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return ultimoPartido;
    }
    
    public static List<EquipoRankingVO> obtenerRanking(String nombreCompeticion) {
        List<EquipoRankingVO> ranking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();

            // Primero, obtenemos todos los equipos que participan en la competición
            String queryEquipos = "SELECT id_equipo, nombre_equipo FROM sisinf_db.EQUIPO WHERE competicion = ?";
            ps = conn.prepareStatement(queryEquipos);
            ps.setString(1, nombreCompeticion);
            rs = ps.executeQuery();

            // Mapeo para almacenar cada equipo con su VO de ranking
            Map<Integer, EquipoRankingVO> equipoRankingMap = new HashMap<>();

            while (rs.next()) {
                int idEquipo = rs.getInt("id_equipo");
                String nombreEquipo = rs.getString("nombre_equipo");
                
                // Crear un objeto EquipoRankingVO para cada equipo
                EquipoRankingVO equipoRanking = new EquipoRankingVO(idEquipo, nombreEquipo, 0, 0, 0, 0, 0);
                equipoRankingMap.put(idEquipo, equipoRanking);
            }

            // Cerrar ResultSet y PreparedStatement anteriores para ejecutar una nueva consulta
            rs.close();
            ps.close();

            // Luego, obtenemos todos los partidos en los que participan estos equipos
            String queryPartidos = "SELECT equipo_local, equipo_visitante, " +
                                   "(pts_c1_local + pts_c2_local + pts_c3_local + pts_c4_local) AS puntos_local, " +
                                   "(pts_c1_visit + pts_c2_visit + pts_c3_visit + pts_c4_visit) AS puntos_visit " +
                                   "FROM sisinf_db.PARTIDO " +
                                   "WHERE equipo_local IN (SELECT id_equipo FROM sisinf_db.EQUIPO WHERE competicion = ?) " +
                                   "OR equipo_visitante IN (SELECT id_equipo FROM sisinf_db.EQUIPO WHERE competicion = ?)";
            ps = conn.prepareStatement(queryPartidos);
            ps.setString(1, nombreCompeticion);
            ps.setString(2, nombreCompeticion);
            rs = ps.executeQuery();

            // Procesamos cada partido para actualizar las estadísticas de los equipos
            while (rs.next()) {
                int equipoLocal = rs.getInt("equipo_local");
                int equipoVisitante = rs.getInt("equipo_visitante");
                int puntosLocal = rs.getInt("puntos_local");
                int puntosVisitante = rs.getInt("puntos_visit");

                EquipoRankingVO rankingLocal = equipoRankingMap.get(equipoLocal);
                EquipoRankingVO rankingVisitante = equipoRankingMap.get(equipoVisitante);

                if (rankingLocal != null && rankingVisitante != null) {
                    // Sumar puntos a favor y en contra
                    rankingLocal.setPuntosAFavor(rankingLocal.getPuntosAFavor() + puntosLocal);
                    rankingLocal.setPuntosEnContra(rankingLocal.getPuntosEnContra() + puntosVisitante);

                    rankingVisitante.setPuntosAFavor(rankingVisitante.getPuntosAFavor() + puntosVisitante);
                    rankingVisitante.setPuntosEnContra(rankingVisitante.getPuntosEnContra() + puntosLocal);

                    // Determinar el ganador y actualizar partidos ganados/perdidos
                    if (puntosLocal > puntosVisitante) {
                        rankingLocal.setPartidosGanados(rankingLocal.getPartidosGanados() + 1);
                        rankingVisitante.setPartidosPerdidos(rankingVisitante.getPartidosPerdidos() + 1);
                    } else if (puntosVisitante > puntosLocal) {
                        rankingVisitante.setPartidosGanados(rankingVisitante.getPartidosGanados() + 1);
                        rankingLocal.setPartidosPerdidos(rankingLocal.getPartidosPerdidos() + 1);
                    }
                }
            }

            // Cerrar ResultSet y PreparedStatement
            rs.close();
            ps.close();

            // Agregar cada equipo en el ranking
            ranking.addAll(equipoRankingMap.values());

            // Ordenar el ranking por puntos ganados y diferencia de puntos a favor y en contra
            ranking.sort((e1, e2) -> {
                int puntosComparacion = Integer.compare(e2.getPuntos(), e1.getPuntos());
                if (puntosComparacion != 0) return puntosComparacion;
                return Integer.compare(e2.getDiferenciaPuntos(), e1.getDiferenciaPuntos());
            });

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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return ranking;
    }
    
    public static List<PartidoVO> obtenerProximosPartidosPorEquipo(int idEquipo) {
        List<PartidoVO> proximosPartidos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // SQL para obtener los partidos futuros en los que participa el equipo (como local o visitante)
            // Se usa CURRENT_TIMESTAMP para comparar tanto la fecha como la hora actuales
            String query = "SELECT * FROM sisinf_db.partido " +
                           "WHERE (equipo_local = ? OR equipo_visitante = ?) AND " +
                           "(fecha > CURRENT_DATE OR (fecha = CURRENT_DATE AND hora > CURRENT_TIME)) " +
                           "ORDER BY fecha ASC, hora ASC"; // Cambiado a ASC para obtener partidos más cercanos primero
            ps = conn.prepareStatement(query);
            ps.setInt(1, idEquipo); // Asigna el equipo como local
            ps.setInt(2, idEquipo); // Asigna el equipo como visitante
            rs = ps.executeQuery();

            while (rs.next()) {
                proximosPartidos.add(new PartidoVO(
                        rs.getInt("id_partido"),
                        rs.getInt("equipo_local"),
                        rs.getInt("equipo_visitante"),
                        rs.getInt("jornada"),
                        rs.getInt("pts_c1_local"),
                        rs.getInt("pts_c2_local"),
                        rs.getInt("pts_c3_local"),
                        rs.getInt("pts_c4_local"),
                        rs.getInt("pts_c1_visit"),
                        rs.getInt("pts_c2_visit"),
                        rs.getInt("pts_c3_visit"),
                        rs.getInt("pts_c4_visit"),
                        rs.getTime("hora"),   // Obtener la hora del partido
                        rs.getDate("fecha")    // Obtener la fecha del partido
                ));
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return proximosPartidos;
    }
    
 // Método para obtener todos los partidos de un equipo por su ID
    public static List<PartidoVO> obtenerPartidosPorEquipo(int idEquipo) {
        List<PartidoVO> partidos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // Consulta SQL para obtener partidos donde el equipo sea local o visitante
            String query = "SELECT * FROM sisinf_db.partido " +
                           "WHERE equipo_local = ? OR equipo_visitante = ? " +
                           "ORDER BY fecha ASC, hora ASC";  // Ordena por fecha y hora para ver cronológicamente
            ps = conn.prepareStatement(query);
            ps.setInt(1, idEquipo); // Equipo como local
            ps.setInt(2, idEquipo); // Equipo como visitante
            rs = ps.executeQuery();

            // Procesa cada resultado y añade al listado de partidos
            while (rs.next()) {
                partidos.add(new PartidoVO(
                        rs.getInt("id_partido"),
                        rs.getInt("equipo_local"),
                        rs.getInt("equipo_visitante"),
                        rs.getInt("jornada"),
                        rs.getInt("pts_c1_local"),
                        rs.getInt("pts_c2_local"),
                        rs.getInt("pts_c3_local"),
                        rs.getInt("pts_c4_local"),
                        rs.getInt("pts_c1_visit"),
                        rs.getInt("pts_c2_visit"),
                        rs.getInt("pts_c3_visit"),
                        rs.getInt("pts_c4_visit"),
                        rs.getTime("hora"),   // Hora del partido
                        rs.getDate("fecha")    // Fecha del partido
                ));
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return partidos;
    }
    
 // Método para obtener todos los partidos de una competición por su nombre
    public static List<PartidoVO> obtenerPartidosPorCompeticion(String nombreCompeticion) {
        List<PartidoVO> partidos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // Consulta SQL para obtener partidos en función de la competición
            String query = "SELECT p.* FROM sisinf_db.partido p " +
                           "JOIN sisinf_db.equipo e ON p.equipo_local = e.id_equipo OR p.equipo_visitante = e.id_equipo " +
                           "WHERE e.competicion = ? " +
                           "ORDER BY p.fecha ASC, p.hora ASC";  // Ordena por fecha y hora
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreCompeticion); // Asigna el nombre de la competición
            rs = ps.executeQuery();

            // Procesa cada resultado y añade al listado de partidos
            while (rs.next()) {
                partidos.add(new PartidoVO(
                        rs.getInt("id_partido"),
                        rs.getInt("equipo_local"),
                        rs.getInt("equipo_visitante"),
                        rs.getInt("jornada"),
                        rs.getInt("pts_c1_local"),
                        rs.getInt("pts_c2_local"),
                        rs.getInt("pts_c3_local"),
                        rs.getInt("pts_c4_local"),
                        rs.getInt("pts_c1_visit"),
                        rs.getInt("pts_c2_visit"),
                        rs.getInt("pts_c3_visit"),
                        rs.getInt("pts_c4_visit"),
                        rs.getTime("hora"),   // Hora del partido
                        rs.getDate("fecha")    // Fecha del partido
                ));
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return partidos;
    }
    
 // Método para obtener los números de las jornadas de una competición por su nombre
    public static List<Integer> obtenerJornadasPorCompeticion(String nombreCompeticion) {
        List<Integer> jornadas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // Consulta SQL para obtener los números de jornada únicos
            String query = "SELECT DISTINCT p.jornada FROM sisinf_db.partido p " +
                           "JOIN sisinf_db.equipo e ON p.equipo_local = e.id_equipo OR p.equipo_visitante = e.id_equipo " +
                           "WHERE e.competicion = ? " +
                           "ORDER BY p.jornada ASC";  // Ordena por número de jornada
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreCompeticion); // Asigna el nombre de la competición
            rs = ps.executeQuery();

            // Procesa cada resultado y añade el número de jornada a la lista
            while (rs.next()) {
                jornadas.add(rs.getInt("jornada"));
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return jornadas;
    }

    public static List<PartidoVO> obtenerPartidosPorJornadaYCompeticion(String nombreCompeticion, int jornada) {
        List<PartidoVO> partidos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            
            // Consulta SQL para obtener los partidos de una competición específica y jornada
            String query = "SELECT p.* FROM sisinf_db.partido p " +
                           "JOIN sisinf_db.equipo e ON p.equipo_local = e.id_equipo OR p.equipo_visitante = e.id_equipo " +
                           "WHERE e.competicion = ? AND p.jornada = ? " +
                           "ORDER BY p.fecha ASC, p.hora ASC";  // Ordena por fecha y hora para mostrar cronológicamente
            
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreCompeticion); // Asigna el nombre de la competición
            ps.setInt(2, jornada); // Asigna el número de la jornada
            rs = ps.executeQuery();

            // Procesa cada resultado y añade al listado de partidos
            while (rs.next()) {
                partidos.add(new PartidoVO(
                        rs.getInt("id_partido"),
                        rs.getInt("equipo_local"),
                        rs.getInt("equipo_visitante"),
                        rs.getInt("jornada"),
                        rs.getInt("pts_c1_local"),
                        rs.getInt("pts_c2_local"),
                        rs.getInt("pts_c3_local"),
                        rs.getInt("pts_c4_local"),
                        rs.getInt("pts_c1_visit"),
                        rs.getInt("pts_c2_visit"),
                        rs.getInt("pts_c3_visit"),
                        rs.getInt("pts_c4_visit"),
                        rs.getTime("hora"),   // Hora del partido
                        rs.getDate("fecha")    // Fecha del partido
                ));
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
            if (conn != null) {
                PoolConnectionManager.releaseConnection(conn);
            }
        }
        return partidos;
    }


}