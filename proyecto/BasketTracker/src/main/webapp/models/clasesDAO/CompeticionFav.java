package src.main.clasesDAO;

import src.main.clasesVO.CompeticionFav; // Asegúrate de importar la clase desde el paquete correcto.
import src.main.clasesVO.Usuario; // Importa la clase Usuario

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CompeticionFavDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar una competición favorita
    public void guardarCompeticionFav(CompeticionFav competicionFav) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(competicionFav);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener una competición favorita por nombre de usuario
    public CompeticionFav obtenerCompeticionFavPorUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT cf FROM CompeticionFav cf WHERE cf.nombreUsuario = :nombreUsuario", CompeticionFav.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // Método para listar todas las competiciones favoritas de un usuario
    public List<CompeticionFav> listarCompeticionesFavPorUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT cf FROM CompeticionFav cf WHERE cf.nombreUsuario = :nombreUsuario", CompeticionFav.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    // Método para listar competiciones favoritas de un usuario
    public void listarCompeticionesFavPorUsuario(Usuario usuario) {
        List<CompeticionFav> competicionesFavoritas = listarCompeticionesFavPorUsuario(usuario.getNombreUsuario());
        System.out.println("Competiciones favoritas de " + usuario.getNombreCompleto() + ":");
        for (CompeticionFav competicionFav : competicionesFavoritas) {
            System.out.println(competicionFav.toString());
        }
    }

    // Método para eliminar una competición favorita por nombre de usuario y competición
    public void eliminarCompeticionFav(String nombreUsuario, String competicion) {
        EntityManager em = emf.createEntityManager();
        try {
            // Busca todas las competiciones favoritas del usuario
            List<CompeticionFav> competicionesFav = listarCompeticionesFavPorUsuario(nombreUsuario);
            for (CompeticionFav competicionFav : competicionesFav) {
                if (competicionFav.getCompeticion().equals(competicion)) {
                    em.getTransaction().begin();
                    em.remove(em.contains(competicionFav) ? competicionFav : em.merge(competicionFav));
                    em.getTransaction().commit();
                    break; // Salimos del bucle después de eliminar
                }
            }
        } finally {
            em.close();
        }
    }
}
