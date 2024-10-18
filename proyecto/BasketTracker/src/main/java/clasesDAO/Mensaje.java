package src.main.clasesDAO;

import src.main.clasesVO.Mensaje; // Asegúrate de importar la clase desde el paquete correcto.

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MensajeDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un mensaje
    public void guardarMensaje(Mensaje mensaje) {
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
    public Mensaje obtenerMensajePorId(int idMensaje) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Mensaje.class, idMensaje);
        } finally {
            em.close();
        }
    }

    // Método para listar todos los mensajes
    public List<Mensaje> listarMensajes() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Mensaje", Mensaje.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar un mensaje
    public void actualizarMensaje(Mensaje mensaje) {
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
            Mensaje mensaje = obtenerMensajePorId(idMensaje);
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
