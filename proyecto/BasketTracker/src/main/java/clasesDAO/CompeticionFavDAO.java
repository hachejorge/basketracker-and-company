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

/**
 * La clase CompeticionFavDAO proporciona acceso a la base de datos para realizar operaciones CRUD
 * sobre las competiciones favoritas de los usuarios. Permite guardar, obtener, listar, eliminar y verificar
 * las competiciones favoritas de un usuario.
 */
public class CompeticionFavDAO {

    /**
     * Guarda una competición como favorita para un usuario.
     * 
     * @param competicionFav El objeto CompeticionFavVO que contiene el nombre del usuario y la competición.
     */
    public void guardarCompeticionFav(CompeticionFavVO competicionFav) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // SQL para insertar una nueva competición favorita
            String query = "INSERT INTO sisinf.competicion_fav (nombre_usuario, competicion) VALUES (?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, competicionFav.getNombreUsuario());  // Nombre de usuario
            ps.setString(2, competicionFav.getCompeticion());  // Nombre de la competición
            
            // Ejecutar la inserción
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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
     * Obtiene una lista de las competiciones favoritas de un usuario específico.
     * 
     * @param nombreUsuario El nombre del usuario cuyas competiciones favoritas se desean obtener.
     * @return Una lista de objetos CompeticionFavVO que representan las competiciones favoritas del usuario.
     */
    public List<CompeticionFavVO> obtenerCompeticionesFavPorUsuario(String nombreUsuario) {
        List<CompeticionFavVO> competicionesFavoritas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.competicion_fav WHERE nombre_usuario = ?";
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

    /**
     * Muestra las competiciones favoritas de un usuario en la consola.
     * 
     * @param usuario El objeto UsuarioVO que contiene la información del usuario.
     */
    public void listarCompeticionesFavPorUsuario(UsuarioVO usuario) {
        List<CompeticionFavVO> competicionesFavoritas = obtenerCompeticionesFavPorUsuario(usuario.getNombreUsuario());
        System.out.println("Competiciones favoritas de " + usuario.getNombreUsuario() + ":");
        for (CompeticionFavVO competicionFav : competicionesFavoritas) {
            System.out.println(competicionFav.toString());
        }
    }

    /**
     * Elimina una competición favorita de un usuario específico.
     * 
     * @param nombreUsuario El nombre del usuario cuya competición favorita se desea eliminar.
     * @param competicion El nombre de la competición que se desea eliminar de las favoritas del usuario.
     */
    public void eliminarCompeticionFav(String nombreUsuario, String competicion) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf.competicion_fav WHERE nombre_usuario = ? AND competicion = ?";
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

    /**
     * Verifica si una competición es una de las favoritas de un usuario.
     * 
     * @param nombreUsuario El nombre del usuario que tiene la competición favorita.
     * @param competicion El nombre de la competición que se desea verificar.
     * @return true si la competición es una de las favoritas del usuario, false en caso contrario.
     */
    public static boolean esFavorito(String nombreUsuario, String competicion) {
        boolean esFavorito = false;
        String query = "SELECT COUNT(*) FROM sisinf.competicion_fav WHERE nombre_usuario = ? AND competicion = ?";

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
