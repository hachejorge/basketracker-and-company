package src.main.clasesDAO;

import src.main.clasesVO.JugadorFav; // Asegúrate de importar la clase desde el paquete correcto.
import src.main.clasesVO.Usuario; // Importa la clase Usuario

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JugadorFavDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un jugador favorito
    public void guardarJugadorFav(JugadorFav jugadorFav) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(jugadorFav);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener jugadores favoritos por nombre de usuario
    public List<JugadorFav> obtenerJugadoresFavPorUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT jf FROM JugadorFav jf WHERE jf.nombreUsuario = :nombreUsuario", JugadorFav.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    // Método para listar jugadores favoritos de un usuario
    public void listarJugadoresFavPorUsuario(Usuario usuario) {
        List<JugadorFav> jugadoresFavoritos = obtenerJugadoresFavPorUsuario(usuario.getNombreUsuario());
        System.out.println("Jugadores favoritos de " + usuario.getNombreCompleto() + ":");
        for (JugadorFav jugadorFav : jugadoresFavoritos) {
            System.out.println(jugadorFav.toString());
        }
    }

    // Método para eliminar un jugador favorito por nombre de usuario y nombre del jugador
    public void eliminarJugadorFav(String nombreUsuario, String nombreJugador) {
        EntityManager em = emf.createEntityManager();
        try {
            List<JugadorFav> jugadoresFav = obtenerJugadoresFavPorUsuario(nombreUsuario);
            for (JugadorFav jugadorFav : jugadoresFav) {
                if (jugadorFav.getJugador().equals(nombreJugador)) {
                    em.getTransaction().begin();
                    em.remove(em.contains(jugadorFav) ? jugadorFav : em.merge(jugadorFav));
                    em.getTransaction().commit();
                    break; // Salimos del bucle después de eliminar
                }
            }
        } finally {
            em.close();
        }
    }
}
