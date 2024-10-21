package clasesDAO;

import clasesVO.UsuarioVO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UsuarioDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un usuario
    public void guardarUsuario(UsuarioVO usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener un usuario por su nombre de usuario
    public UsuarioVO obtenerUsuarioPorNombre(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario", UsuarioVO.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // Método para listar todos los usuarios
    public List<UsuarioVO> listarUsuarios() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Usuario", UsuarioVO.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar la información de un usuario
    public void actualizarUsuario(UsuarioVO usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para eliminar un usuario por su nombre de usuario
    public void eliminarUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            UsuarioVO usuario = obtenerUsuarioPorNombre(nombreUsuario);
            if (usuario != null) {
                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
