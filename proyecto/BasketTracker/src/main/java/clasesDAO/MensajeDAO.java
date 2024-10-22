package clasesDAO;

import clasesVO.MensajeVO; // Asegúrate de importar la clase desde el paquete correcto.

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MensajeDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un mensaje
    public void guardarMensaje(MensajeVO mensaje) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mensaje);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener un mensaje por ID
    public MensajeVO obtenerMensajePorId(int idMensaje) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(MensajeVO.class, idMensaje);
        } finally {
            em.close();
        }
    }

    // Método para listar todos los mensajes
    public List<MensajeVO> listarMensajes() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Mensaje", MensajeVO.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar un mensaje
    public void actualizarMensaje(MensajeVO mensaje) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(mensaje);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para eliminar un mensaje por ID
    public void eliminarMensaje(int idMensaje) {
        EntityManager em = emf.createEntityManager();
        try {
            MensajeVO mensaje = obtenerMensajePorId(idMensaje);
            if (mensaje != null) {
                em.getTransaction().begin();
                em.remove(em.contains(mensaje) ? mensaje : em.merge(mensaje));
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
