package clasesDAO;

import clasesVO.CompeticionVO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CompeticionDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar una competición
    public void guardarCompeticion(CompeticionVO competicion) {
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
    public CompeticionVO obtenerCompeticionPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Competicion c WHERE c.nombre = :nombre", CompeticionVO.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // Método para listar todas las competiciones
    public List<CompeticionVO> listarCompeticiones() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Competicion", CompeticionVO.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar una competición
    public void actualizarCompeticion(CompeticionVO competicion) {
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
            CompeticionVO competicion = obtenerCompeticionPorNombre(nombre);
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
