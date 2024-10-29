package clasesDAO;

import clasesVO.EquipoFavVO; // Asegúrate de importar la clase desde el paquete correcto.
import clasesVO.UsuarioVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.PoolConnectionManager;

public class EquipoFavDAO {

    // Método para guardar un equipo favorito
    public void guardarEquipoFav(EquipoFavVO equipoFav) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // SQL para insertar un nuevo equipo favorito
            String query = "INSERT INTO sisinf_db.equipo_fav (nombre_usuario, equipo) VALUES (?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, equipoFav.getNombreUsuario());  // Nombre de usuario
            ps.setInt(2, equipoFav.getEquipo());  // ID del equipo
            
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

    // Método para obtener equipos favoritos por nombre de usuario
    public List<EquipoFavVO> obtenerEquiposFavPorUsuario(String nombreUsuario) {
        List<EquipoFavVO> equiposFavoritos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.equipo_fav WHERE nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                int equipo = rs.getInt("equipo");
                // Crear un nuevo objeto EquipoFavVO y añadirlo a la lista
                equiposFavoritos.add(new EquipoFavVO(nombreUsuario, equipo));
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
        return equiposFavoritos;
    }

    // Método para listar equipos favoritos de un usuario
    public void listarEquiposFavPorUsuario(UsuarioVO usuario) {
        List<EquipoFavVO> equiposFavoritos = obtenerEquiposFavPorUsuario(usuario.getNombreUsuario());
        System.out.println("Equipos favoritos de " + usuario.getNombreUsuario() + ":");
        for (EquipoFavVO equipoFav : equiposFavoritos) {
            System.out.println(equipoFav.toString());
        }
    }

    // Método para eliminar un equipo favorito por nombre de usuario y ID del equipo
    public void eliminarEquipoFav(String nombreUsuario, int equipo) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf_db.equipo_fav WHERE nombre_usuario = ? AND equipo = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setInt(2, equipo);
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

    // Método para verificar si un equipo es favorito para un usuario específico
    public static boolean esFavorito(String nombreUsuario, int equipo) {
        boolean esFavorito = false;
        String query = "SELECT COUNT(*) FROM sisinf_db.equipo_fav WHERE nombre_usuario = ? AND equipo = ?";

        try (Connection conn = PoolConnectionManager.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setInt(2, equipo);
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