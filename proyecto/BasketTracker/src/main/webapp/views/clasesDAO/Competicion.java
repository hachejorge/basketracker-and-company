package src.main.clasesDAO;

import src.main.clasesVO.Competicion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CompeticionDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar una competición
    public void guardarCompeticion(Competicion competicion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(competicion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener una competición por su nombre
    public Competicion obtenerCompeticionPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Competicion c WHERE c.nombre = :nombre", Competicion.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // Método para listar todas las competiciones
    public List<Competicion> listarCompeticiones() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Competicion", Competicion.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar una competición
    public void actualizarCompeticion(Competicion competicion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(competicion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para eliminar una competición por su nombre
    public void eliminarCompeticion(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            Competicion competicion = obtenerCompeticionPorNombre(nombre);
            if (competicion != null) {
                em.getTransaction().begin();
                em.remove(competicion);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
