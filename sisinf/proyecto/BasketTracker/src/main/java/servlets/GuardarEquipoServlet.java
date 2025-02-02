package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import clasesVO.EquipoVO;
import clasesDAO.EquipoDAO;

@WebServlet("/GuardarEquipoServlet")
public class GuardarEquipoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idEquipo = request.getParameter("idEquipo");
        
        // Obtener el EquipoVO de acuerdo a su ID
        EquipoVO equipoVO = EquipoDAO.obtenerEquipoPorId(Integer.parseInt(idEquipo)); // Ajusta según tu lógica
        
        HttpSession session = request.getSession();
        session.setAttribute("equipoSeleccionado", equipoVO); // Guardar el equipo en la sesión

        response.setStatus(HttpServletResponse.SC_OK); // Respuesta exitosa
    }
}
