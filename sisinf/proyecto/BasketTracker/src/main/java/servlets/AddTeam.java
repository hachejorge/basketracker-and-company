package servlets;

import clasesDAO.CompeticionDAO;
import clasesVO.CompeticionVO;
import clasesDAO.EquipoDAO;
import clasesVO.EquipoVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddTeam")
public class AddTeam extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EquipoDAO equipoDAO = new EquipoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recibir parámetros del formulario de registro
        String nombreTeam = request.getParameter("nomTeam");
        String ubicacion = request.getParameter("ubicacion");
        String competicion = request.getParameter("competicionEquipo");
        
        System.out.println("Equipo: " + nombreTeam);
        System.out.println("Ubicac: " + ubicacion);
        System.out.println("Compet: " + competicion);

        if ((nombreTeam != null)  && (!nombreTeam.trim().equals("")) &&
        	(ubicacion != null)   && (!ubicacion.trim().equals("")) && 
        	(competicion != null) && (!competicion.trim().equals("")) ){
	        	        
	        try {
	        	// Comprobar que la competición exista
	            CompeticionVO competicionExistente = CompeticionDAO.obtenerCompeticionPorNombre(competicion);
	            if (competicionExistente != null) {
	            	// Comprobar que no hay un equipo en esa categoría con el mismo nombre
	                if (!equipoDAO.existeEquipoConMismoNombre(nombreTeam, competicion)) {
	                    EquipoVO equipo = new EquipoVO(0, nombreTeam, ubicacion, competicion);
	                    equipoDAO.guardarEquipo(equipo);
	                    response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=" + nombreTeam + " ha sido añadido correctamente");
	                } else {
	                    response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=Ya existe un equipo con el nombre " + nombreTeam + " en la competición " + competicion);
	                }
	            } else {
	                response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=La competicion " + competicion + " no existe");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Hubo un problema al registrar el equipo. Intente nuevamente.");
	            response.sendRedirect("views/jsp/admin/inicioAdmin.jsp");
	        }
        }
        else {
            response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=No puedes registrar una equipo con parámetros nulos");
        }
    }
}
