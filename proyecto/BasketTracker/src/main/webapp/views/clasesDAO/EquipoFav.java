package src.main.clasesDAO;

import src.main.clasesVO.EquipoFav; // Asegúrate de importar la clase desde el paquete correcto.
import src.main.clasesVO.Usuario; // Importa la clase Usuario

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EquipoFavDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un equipo favorito
    public void guardarEquipoFav(EquipoFav equipoFav) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(equipoFav);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener equipos favoritos por nombre de usuario
    public List<EquipoFav> obtenerEquiposFavPorUsuario(String nombreUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT ef FROM EquipoFav ef WHERE ef.nombreUsuario = :nombreUsuario", EquipoFav.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    // Método para listar equipos favoritos de un usuario
    public void listarEquiposFavPorUsuario(Usuario usuario) {
        List<EquipoFav> equiposFavoritos = obtenerEquiposFavPorUsuario(usuario.getNombreUsuario());
        System.out.println("Equipos favoritos de " + usuario.getNombreCompleto() + ":");
        for (EquipoFav equipoFav : equiposFavoritos) {
            System.out.println(equipoFav.toString());
        }
    }

    // Método para eliminar un equipo favorito por nombre de usuario y ID de equipo
    public void eliminarEquipoFav(String nombreUsuario, int equipoId) {
        EntityManager em = emf.createEntityManager();
        try {
            List<EquipoFav> equiposFav = obtenerEquiposFavPorUsuario(nombreUsuario);
            for (EquipoFav equipoFav : equiposFav) {
                if (equipoFav.getEquipo() == equipoId) {
                    em.getTransaction().begin();
                    em.remove(em.contains(equipoFav) ? equipoFav : em.merge(equipoFav));
                    em.getTransaction().commit();
                    break; // Salimos del bucle después de eliminar
                }
            }
        } finally {
            em.close();
        }
    }
}
