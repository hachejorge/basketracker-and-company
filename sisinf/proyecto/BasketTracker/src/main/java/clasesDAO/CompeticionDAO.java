package clasesDAO;

import clasesVO.CompeticionVO;
import utils.PoolConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase CompeticionDAO maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre la tabla de competiciones en la base de datos. Permite realizar inserciones,
 * consultas y eliminaciones de competiciones, así como realizar búsquedas basadas en un término.
 */
public class CompeticionDAO {

    /**
     * Guarda una nueva competición en la base de datos.
     * 
     * @param competicion El objeto CompeticionVO que contiene los datos de la competición a guardar.
     */
    public void guardarCompeticion(CompeticionVO competicion) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "INSERT INTO sisinf.competicion (nombre) VALUES (?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, competicion.getNombre());
            
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
     * Obtiene una competición por su nombre de la base de datos.
     * 
     * @param nombre El nombre de la competición que se desea obtener.
     * @return El objeto CompeticionVO correspondiente al nombre de la competición, o null si no se encuentra.
     */
    public static CompeticionVO obtenerCompeticionPorNombre(String nombre) {
        CompeticionVO competicion = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.competicion WHERE nombre = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            // Si la competición existe, se crea un objeto CompeticionVO
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
    
    /**
     * Busca competiciones cuyo nombre contiene un término específico.
     * 
     * @param termino El término a buscar en el nombre de las competiciones.
     * @param limite El número máximo de resultados a devolver.
     * @return Una lista de objetos CompeticionVO que contienen competiciones cuyo nombre contiene el término proporcionado.
     */
    public List<CompeticionVO> buscarCompeticiones(String termino, int limite) {
        List<CompeticionVO> competiciones = new ArrayList<>();
        String sql = "SELECT nombre FROM sisinf.competicion WHERE nombre LIKE ? LIMIT ?";

        try (
        	Connection conn = PoolConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + termino + "%");  // Termino de búsqueda en formato LIKE
            stmt.setInt(2, limite);  // Limitar resultados

            ResultSet rs = stmt.executeQuery();

            // Crear CompeticionVO para cada resultado encontrado
            while (rs.next()) {
                String nombreCompeticion = rs.getString("nombre");
                CompeticionVO competicion = new CompeticionVO(nombreCompeticion);
                competiciones.add(competicion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return competiciones;
    }

    /**
     * Lista todas las competiciones disponibles en la base de datos.
     * 
     * @return Una lista de objetos CompeticionVO con todas las competiciones registradas en la base de datos.
     */
    public List<CompeticionVO> listarCompeticiones() {
        List<CompeticionVO> competiciones = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "SELECT * FROM sisinf.competicion";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            // Crear un objeto CompeticionVO por cada fila obtenida
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

    /**
     * Actualiza el nombre de una competición en la base de datos.
     * 
     * @param competicion El objeto CompeticionVO que contiene los datos actuales de la competición.
     * @param nuevoNombre El nuevo nombre para la competición.
     */
    public void actualizarCompeticion(CompeticionVO competicion, String nuevoNombre) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "UPDATE sisinf.competicion SET nombre = ? WHERE nombre = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, nuevoNombre);
            ps.setString(2, competicion.getNombre());

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
            PoolConnectionManager.releaseConnection(conn);
        }
    }

    /**
     * Elimina una competición por su nombre de la base de datos.
     * 
     * @param nombre El nombre de la competición que se desea eliminar.
     */
    public void eliminarCompeticion(String nombre) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = PoolConnectionManager.getConnection();
            String query = "DELETE FROM sisinf.competicion WHERE nombre = ?";
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

    /**
     * Busca competiciones por su nombre utilizando un término de búsqueda.
     * 
     * @param searchTerm El término que se buscará en los nombres de las competiciones.
     * @return Una lista de objetos CompeticionVO que contienen las competiciones encontradas.
     * @throws SQLException Si ocurre un error durante la consulta a la base de datos.
     */
    public static List<CompeticionVO> buscarCompeticiones(String searchTerm) throws SQLException {
        List<CompeticionVO> competiciones = new ArrayList<>();
        String query = "SELECT * FROM sisinf.COMPETICION WHERE nombre ILIKE ?";
        
        try (Connection conn = PoolConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + searchTerm + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                competiciones.add(new CompeticionVO(nombre));
            }
        }
        return competiciones;
    }

    /**
     * Busca competiciones por nombre utilizando un término de búsqueda con insensibilidad a mayúsculas y minúsculas.
     * 
     * @param nombre El nombre que se buscará en las competiciones.
     * @return Una lista de objetos CompeticionVO que representan las competiciones encontradas.
     */
    public static List<CompeticionVO> buscarCompeticionesPorNombre(String nombre) {
        List<CompeticionVO> competiciones = new ArrayList<>();
        String sql = "SELECT nombre FROM sisinf.COMPETICION WHERE nombre ILIKE ?";

        try (Connection conn = PoolConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreCompeticion = rs.getString("nombre");
                // Crear un nuevo objeto CompeticionVO y agregarlo a la lista
                competiciones.add(new CompeticionVO(nombreCompeticion));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }

        return competiciones;
    }

}
