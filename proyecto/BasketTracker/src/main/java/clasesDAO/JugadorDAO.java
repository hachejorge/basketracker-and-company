package clasesDAO;

import clasesVO.JugadorVO; // Asegúrate de importar la clase desde el paquete correcto.

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JugadorDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un jugador
    public void guardarJugador(JugadorVO jugador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(jugador);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener un jugador por su nombre de usuario
    public JugadorVO obtenerJugadorPorNombreUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT j FROM Jugador j WHERE j.nombreUsuario = :nombreUsuario", JugadorVO.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // Método para listar todos los jugadores
    public List<JugadorVO> listarJugadores() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Jugador", JugadorVO.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar un jugador
    public void actualizarJugador(JugadorVO jugador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(jugador);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para eliminar un jugador por nombre de usuario
    public void eliminarJugador(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            JugadorVO jugador = obtenerJugadorPorNombreUsuario(nombreUsuario);
            if (jugador != null) {
                em.getTransaction().begin();
                em.remove(em.contains(jugador) ? jugador : em.merge(jugador));
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
