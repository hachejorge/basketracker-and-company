package clasesDAO;

import clasesVO.JugadorFavVO; // Asegúrate de importar la clase desde el paquete correcto.
import clasesVO.UsuarioVO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.PoolConnectionManager;

public class JugadorFavDAO {

    // Método para guardar un jugador favorito
    public void guardarJugadorFav(JugadorFavVO jugadorFav) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // SQL para insertar un nuevo jugador favorito
            String query = "INSERT INTO sisinf_db.jugador_fav (nombre_usuario, jugador) VALUES (?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, jugadorFav.getNombreUsuario());  // Nombre de usuario
            ps.setString(2, jugadorFav.getJugador());  // Nombre del jugador
            
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

    // Método para obtener jugadores favoritos por nombre de usuario
    public static List<JugadorFavVO> obtenerJugadoresFavPorUsuario(String nombreUsuario) {
        List<JugadorFavVO> jugadoresFavoritos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf_db.jugador_fav WHERE nombre_usuario = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                String jugador = rs.getString("jugador");
                // Crear un nuevo objeto JugadorFavVO y añadirlo a la lista
                jugadoresFavoritos.add(new JugadorFavVO(nombreUsuario, jugador));
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
        return jugadoresFavoritos;
    }

    // Método para listar jugadores favoritos de un usuario
    public void listarJugadoresFavPorUsuario(UsuarioVO usuario) {
        List<JugadorFavVO> jugadoresFavoritos = obtenerJugadoresFavPorUsuario(usuario.getNombreUsuario());
        System.out.println("Jugadores favoritos de " + usuario.getNombreUsuario() + ":");
        for (JugadorFavVO jugadorFav : jugadoresFavoritos) {
            System.out.println(jugadorFav.toString());
        }
    }

    // Método para eliminar un jugador favorito por nombre de usuario y nombre del jugador
    public void eliminarJugadorFav(String nombreUsuario, String jugador) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf_db.jugador_fav WHERE nombre_usuario = ? AND jugador = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, jugador);
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

    // Método para verificar si un jugador es favorito para un usuario específico
    public static boolean esFavorito(String nombreUsuario, String jugador) {
        boolean esFavorito = false;
        String query = "SELECT COUNT(*) FROM sisinf_db.jugador_fav WHERE nombre_usuario = ? AND jugador = ?";

        try (Connection conn = PoolConnectionManager.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, jugador);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                esFavorito = rs.getInt(1) > 0; // Si hay al menos un registro, es favorito
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return esFavorito;
    }
    
 // Método para contar cuántos seguidores tiene un jugador
    public static int contarSeguidores(String nombreJugador) {
        int seguidores = 0;
        String query = "SELECT COUNT(*) FROM sisinf_db.jugador_fav WHERE jugador = ?";

        try (Connection conn = PoolConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nombreJugador);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                seguidores = rs.getInt(1); // Número de seguidores
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seguidores;
    }

}
