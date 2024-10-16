package src.main.clasesDAO;

import src.main.clasesVO.Comentario;
import src.main.clasesVO.Usuario;
import src.main.clasesVO.Partido;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ComentarioDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un comentario
    public void guardarComentario(Comentario comentario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(comentario);
        em.getTransaction().commit();
        em.close();
    }

    // Método para obtener un comentario por su ID
    public Comentario obtenerComentarioPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Comentario comentario = em.find(Comentario.class, id);
        em.close();
        return comentario;
    }

    // Método para listar todos los comentarios
    public List<Comentario> listarComentarios() {
        EntityManager em = emf.createEntityManager();
        List<Comentario> comentarios = em.createQuery("from Comentario", Comentario.class).getResultList();
        em.close();
        return comentarios;
    }

    // Método para listar comentarios por un usuario
    public List<Comentario> listarComentariosPorUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        List<Comentario> comentarios = em.createQuery("SELECT c FROM Comentario c WHERE c.usuario = :usuario", Comentario.class)
                .setParameter("usuario", usuario)
                .getResultList();
        em.close();
        return comentarios;
    }

    // Listar comentarios por partido
    public List<Comentario> listarComentariosPorPartido(Partido partido) {
        EntityManager em = emf.createEntityManager();
        List<Comentario> comentarios = em.createQuery("SELECT c FROM Comentario c WHERE c.partido = :partido", Comentario.class)
                .setParameter("partido", partido)
                .getResultList();
        em.close();
        return comentarios;
    }

    // Método para Eliminar un comentario
    public void eliminarComentario(Long id) {
        EntityManager em = emf.createEntityManager();
        Comentario comentario = em.find(Comentario.class, id);
        if (comentario != null) {
            em.getTransaction().begin();
            em.remove(comentario);
            em.getTransaction().commit();
        }
        em.close();
    }
}
