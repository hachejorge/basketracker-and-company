package clasesDAO;

import clasesVO.CompeticionFavVO; // Asegúrate de importar la clase desde el paquete correcto.
import clasesVO.UsuarioVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.PoolConnectionManager;

public class CompeticionFavDAO {

    // Método para guardar una competición favorita
    public void guardarCompeticionFav(CompeticionFavVO competicionFav) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // SQL para insertar una nueva competición favorita
            String query = "INSERT INTO sisinf_db.competicion_fav (nombre_usuario, competicion) VALUES (?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, competicionFav.getNombreUsuario());  // Nombre de usuario
            ps.setString(2, competicionFav.getCompeticion());  // Nombre de la competición
            
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

    // Método para obtener competiciones favoritas por nombre de usuario
    public List<CompeticionFavVO> obtenerCompeticionesFavPorUsuario(String nombreUsuario) {
        List<CompeticionFavVO> competicionesFavoritas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.competicion_fav WHERE nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                String competicion = rs.getString("competicion");
                // Crear un nuevo objeto CompeticionFavVO y añadirlo a la lista
                competicionesFavoritas.add(new CompeticionFavVO(nombreUsuario, competicion));
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
        return competicionesFavoritas;
    }

    // Método para listar competiciones favoritas de un usuario
    public void listarCompeticionesFavPorUsuario(UsuarioVO usuario) {
        List<CompeticionFavVO> competicionesFavoritas = obtenerCompeticionesFavPorUsuario(usuario.getNombreUsuario());
        System.out.println("Competiciones favoritas de " + usuario.getNombreUsuario() + ":");
        for (CompeticionFavVO competicionFav : competicionesFavoritas) {
            System.out.println(competicionFav.toString());
        }
    }

    // Método para eliminar una competición favorita por nombre de usuario y nombre de la competición
    public void eliminarCompeticionFav(String nombreUsuario, String competicion) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf_db.competicion_fav WHERE nombre_usuario = ? AND competicion = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, competicion);
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

    // Método para verificar si una competición es favorita para un usuario específico
    public static boolean esFavorito(String nombreUsuario, String competicion) {
        boolean esFavorito = false;
        String query = "SELECT COUNT(*) FROM sisinf_db.competicion_fav WHERE nombre_usuario = ? AND competicion = ?";

        try (Connection conn = PoolConnectionManager.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, competicion);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                esFavorito = rs.getInt(1) > 0; // Si hay al menos un registro, es favorito
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return esFavorito;
    }
}