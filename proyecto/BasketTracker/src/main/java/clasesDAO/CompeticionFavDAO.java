package clasesDAO;

import clasesVO.CompeticionFavVO; // Asegúrate de importar la clase desde el paquete correcto.
import clasesVO.UsuarioVO; // Importa la clase Usuario

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CompeticionFavDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar una competición favorita
    public void guardarCompeticionFav(CompeticionFavVO competicionFav) {
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
    public CompeticionFavVO obtenerCompeticionFavPorUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT cf FROM CompeticionFav cf WHERE cf.nombreUsuario = :nombreUsuario", CompeticionFavVO.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // Método para listar todas las competiciones favoritas de un usuario
    public List<CompeticionFavVO> listarCompeticionesFavPorUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT cf FROM CompeticionFav cf WHERE cf.nombreUsuario = :nombreUsuario", CompeticionFavVO.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    // Método para listar competiciones favoritas de un usuario
    public void listarCompeticionesFavPorUsuario(UsuarioVO usuario) {
        List<CompeticionFavVO> competicionesFavoritas = listarCompeticionesFavPorUsuario(usuario.getNombreUsuario());
        System.out.println("Competiciones favoritas de " + usuario.getNombreUsuario() + ":");
        for (CompeticionFavVO competicionFav : competicionesFavoritas) {
            System.out.println(competicionFav.toString());
        }
    }

    // Método para eliminar una competición favorita por nombre de usuario y competición
    public void eliminarCompeticionFav(String nombreUsuario, String competicion) {
        EntityManager em = emf.createEntityManager();
        try {
            // Busca todas las competiciones favoritas del usuario
            List<CompeticionFavVO> competicionesFav = listarCompeticionesFavPorUsuario(nombreUsuario);
            for (CompeticionFavVO competicionFav : competicionesFav) {
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
