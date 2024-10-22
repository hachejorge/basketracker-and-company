package clasesDAO;

import clasesVO.EquipoVO; // Asegúrate de importar la clase desde el paquete correcto.

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EquipoDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un equipo
    public void guardarEquipo(EquipoVO equipo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(equipo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para obtener un equipo por su ID
    public EquipoVO obtenerEquipoPorId(int idEquipo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(EquipoVO.class, idEquipo);
        } finally {
            em.close();
        }
    }

    // Método para listar todos los equipos
    public List<EquipoVO> listarEquipos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Equipo", EquipoVO.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar un equipo
    public void actualizarEquipo(EquipoVO equipo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(equipo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para eliminar un equipo por su ID
    public void eliminarEquipo(int idEquipo) {
        EntityManager em = emf.createEntityManager();
        try {
            EquipoVO equipo = obtenerEquipoPorId(idEquipo);
            if (equipo != null) {
                em.getTransaction().begin();
                em.remove(em.contains(equipo) ? equipo : em.merge(equipo));
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    // Método para buscar equipos por nombre de jugador
    public List<EquipoVO> obtenerEquiposPorJugador(String nombreJugador) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Equipo e JOIN e.jugadores j WHERE j.nombreJugador = :nombreJugador", EquipoVO.class)
                    .setParameter("nombreJugador", nombreJugador)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
