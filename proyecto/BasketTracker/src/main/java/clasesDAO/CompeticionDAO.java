package clasesDAO;

import clasesVO.CompeticionVO;
import utils.PoolConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompeticionDAO {

    // Método para guardar una competición
    public void guardarCompeticion(CompeticionVO competicion) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "INSERT INTO sisinf_db.competicion (nombre) VALUES (?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, competicion.getNombre());
            
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

    // Método para obtener una competición por su nombre
    public static CompeticionVO obtenerCompeticionPorNombre(String nombre) {
        CompeticionVO competicion = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.competicion WHERE nombre = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                competicion = new CompeticionVO(rs.getString("nombre"));
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

    // Método para listar todas las competiciones
    public List<CompeticionVO> listarCompeticiones() {
        List<CompeticionVO> competiciones = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.competicion";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                competiciones.add(new CompeticionVO(rs.getString("nombre")));
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
        return competiciones;
    }

    // Método para actualizar una competición
    public void actualizarCompeticion(CompeticionVO competicion, String nuevoNombre) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "UPDATE sisinf_db.competicion SET nombre = ? WHERE nombre = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nuevoNombre);
            ps.setString(2, competicion.getNombre());

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

    // Método para eliminar una competición por su nombre
    public void eliminarCompeticion(String nombre) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf_db.competicion WHERE nombre = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombre);
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
