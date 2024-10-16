package src.main.clasesDAO;

import src.main.clasesVO.PtsJugPar; // Asegúrate de importar la clase desde el paquete correcto.

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PtsJugParDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un registro de puntos de jugador en un partido
    public void guardarPtsJugPar(PtsJugPar ptsJugPar) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ptsJugPar);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener un registro por ID de partido y nombre de usuario
    public PtsJugPar obtenerPtsJugPar(int idPartido, String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM PtsJugPar p WHERE p.idPartido = :idPartido AND p.nombreUsuario = :nombreUsuario", PtsJugPar.class)
                    .setParameter("idPartido", idPartido)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // O maneja la excepción de otra manera
        } finally {
            em.close();
        }
    }

    // Método para listar todos los registros de puntos de jugadores en partidos
    public List<PtsJugPar> listarPtsJugPar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM PtsJugPar", PtsJugPar.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar un registro de puntos de jugador en un partido
    public void actualizarPtsJugPar(PtsJugPar ptsJugPar) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(ptsJugPar);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para eliminar un registro de puntos de jugador en un partido
    public void eliminarPtsJugPar(int idPartido, String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            PtsJugPar ptsJugPar = obtenerPtsJugPar(idPartido, nombreUsuario);
            if (ptsJugPar != null) {
                em.getTransaction().begin();
                em.remove(em.contains(ptsJugPar) ? ptsJugPar : em.merge(ptsJugPar));
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
