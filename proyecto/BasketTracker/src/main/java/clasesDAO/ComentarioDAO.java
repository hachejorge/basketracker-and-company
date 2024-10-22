package clasesDAO;

import clasesVO.ComentarioVO;
import clasesVO.UsuarioVO;
import clasesVO.PartidoVO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ComentarioDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un comentario
    public void guardarComentario(ComentarioVO comentario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(comentario);
        em.getTransaction().commit();
        em.close();
    }

    // Método para obtener un comentario por su ID
    public ComentarioVO obtenerComentarioPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        ComentarioVO comentario = em.find(ComentarioVO.class, id);
        em.close();
        return comentario;
    }

    // Método para listar todos los comentarios
    public List<ComentarioVO> listarComentarios() {
        EntityManager em = emf.createEntityManager();
        List<ComentarioVO> comentarios = em.createQuery("from Comentario", ComentarioVO.class).getResultList();
        em.close();
        return comentarios;
    }

    // Método para listar comentarios por un usuario
    public List<ComentarioVO> listarComentariosPorUsuario(UsuarioVO usuario) {
        EntityManager em = emf.createEntityManager();
        List<ComentarioVO> comentarios = em.createQuery("SELECT c FROM Comentario c WHERE c.usuario = :usuario", ComentarioVO.class)
                .setParameter("usuario", usuario)
                .getResultList();
        em.close();
        return comentarios;
    }

    // Listar comentarios por partido
    public List<ComentarioVO> listarComentariosPorPartido(PartidoVO partido) {
        EntityManager em = emf.createEntityManager();
        List<ComentarioVO> comentarios = em.createQuery("SELECT c FROM Comentario c WHERE c.partido = :partido", ComentarioVO.class)
                .setParameter("partido", partido)
                .getResultList();
        em.close();
        return comentarios;
    }

    // Método para Eliminar un comentario
    public void eliminarComentario(Long id) {
        EntityManager em = emf.createEntityManager();
        ComentarioVO comentario = em.find(ComentarioVO.class, id);
        if (comentario != null) {
            em.getTransaction().begin();
            em.remove(comentario);
            em.getTransaction().commit();
        }
        em.close();
    }
}
