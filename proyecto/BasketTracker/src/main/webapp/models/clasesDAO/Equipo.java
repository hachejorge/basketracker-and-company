package src.main.clasesDAO;

import src.main.clasesVO.Equipo; // Asegúrate de importar la clase desde el paquete correcto.
import src.main.clasesVO.Jugador; // Asegúrate de importar la clase Jugador

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EquipoDAO {

    // Crear una fábrica de EntityManagers
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    // Método para guardar un equipo
    public void guardarEquipo(Equipo equipo) {
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
    public Equipo obtenerEquipoPorId(int idEquipo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Equipo.class, idEquipo);
        } finally {
            em.close();
        }
    }

    // Método para listar todos los equipos
    public List<Equipo> listarEquipos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Equipo", Equipo.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar un equipo
    public void actualizarEquipo(Equipo equipo) {
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
            Equipo equipo = obtenerEquipoPorId(idEquipo);
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
    public List<Equipo> obtenerEquiposPorJugador(String nombreJugador) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Equipo e JOIN e.jugadores j WHERE j.nombreJugador = :nombreJugador", Equipo.class)
                    .setParameter("nombreJugador", nombreJugador)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
