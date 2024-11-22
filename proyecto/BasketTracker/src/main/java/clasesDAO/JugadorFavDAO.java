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

/**
 * Clase que maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) relacionadas con los jugadores favoritos
 * de los usuarios en la base de datos.
 * Utiliza el PoolConnectionManager para manejar las conexiones a la base de datos de manera eficiente.
 */
public class JugadorFavDAO {

    /**
     * Guarda un jugador como favorito de un usuario en la base de datos.
     * 
     * @param jugadorFav Objeto JugadorFavVO que contiene la información del jugador y el usuario.
     */
    public void guardarJugadorFav(JugadorFavVO jugadorFav) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            // SQL para insertar un nuevo jugador favorito
            String query = "INSERT INTO sisinf.jugador_fav (nombre_usuario, jugador) VALUES (?, ?)";
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

    /**
     * Obtiene una lista de jugadores favoritos de un usuario desde la base de datos.
     * 
     * @param nombreUsuario El nombre de usuario para obtener sus jugadores favoritos.
     * @return Una lista de objetos JugadorFavVO que representan los jugadores favoritos del usuario.
     */
    public static List<JugadorFavVO> obtenerJugadoresFavPorUsuario(String nombreUsuario) {
        List<JugadorFavVO> jugadoresFavoritos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.jugador_fav WHERE nombre_usuario = ?";
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

    /**
     * Lista los jugadores favoritos de un usuario.
     * 
     * @param usuario El objeto UsuarioVO que representa al usuario cuyos jugadores favoritos se quieren listar.
     */
    public void listarJugadoresFavPorUsuario(UsuarioVO usuario) {
        List<JugadorFavVO> jugadoresFavoritos = obtenerJugadoresFavPorUsuario(usuario.getNombreUsuario());
        System.out.println("Jugadores favoritos de " + usuario.getNombreUsuario() + ":");
        for (JugadorFavVO jugadorFav : jugadoresFavoritos) {
            System.out.println(jugadorFav.toString());
        }
    }

    /**
     * Elimina un jugador favorito de un usuario específico en la base de datos.
     * 
     * @param nombreUsuario El nombre de usuario del que se desea eliminar el jugador favorito.
     * @param jugador El nombre del jugador que se desea eliminar como favorito.
     */
    public void eliminarJugadorFav(String nombreUsuario, String jugador) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf.jugador_fav WHERE nombre_usuario = ? AND jugador = ?";
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

    /**
     * Verifica si un jugador es favorito de un usuario específico.
     * 
     * @param nombreUsuario El nombre de usuario que se desea verificar.
     * @param jugador El nombre del jugador que se desea verificar si es favorito.
     * @return true si el jugador es favorito del usuario, false en caso contrario.
     */
    public static boolean esFavorito(String nombreUsuario, String jugador) {
        boolean esFavorito = false;
        String query = "SELECT COUNT(*) FROM sisinf.jugador_fav WHERE nombre_usuario = ? AND jugador = ?";

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

    /**
     * Cuenta cuántos seguidores tiene un jugador (es decir, cuántos usuarios lo han marcado como favorito).
     * 
     * @param nombreJugador El nombre del jugador cuyo número de seguidores se desea contar.
     * @return El número de seguidores del jugador.
     */
    public static int contarSeguidores(String nombreJugador) {
        int seguidores = 0;
        String query = "SELECT COUNT(*) FROM sisinf.jugador_fav WHERE jugador = ?";

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
