package src.main.clasesDAO;

import src.main.clasesVO.Partido; // Asegúrate de importar la clase desde el paquete correcto.

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PartidoDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un partido
    public void guardarPartido(Partido partido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(partido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener un partido por ID
    public Partido obtenerPartidoPorId(int idPartido) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Partido.class, idPartido);
        } finally {
            em.close();
        }
    }

    // Método para listar todos los partidos
    public List<Partido> listarPartidos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Partido", Partido.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar un partido
    public void actualizarPartido(Partido partido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(partido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para eliminar un partido por ID
    public void eliminarPartido(int idPartido) {
        EntityManager em = emf.createEntityManager();
        try {
            Partido partido = obtenerPartidoPorId(idPartido);
            if (partido != null) {
                em.getTransaction().begin();
                em.remove(em.contains(partido) ? partido : em.merge(partido));
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
