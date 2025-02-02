package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import clasesVO.CompeticionVO;
import clasesDAO.CompeticionDAO;

@WebServlet("/GuardarCompeticionServlet")
public class GuardarCompeticionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreCompeticion = request.getParameter("nombreCompeticion");
        
        // Obtener el CompeticionVO de acuerdo a su nombre
        CompeticionVO competicionVO = CompeticionDAO.obtenerCompeticionPorNombre(nombreCompeticion); // Ajusta según tu lógica
        
        HttpSession session = request.getSession();
        session.setAttribute("competicionSeleccionada", competicionVO); // Guardar la competición en la sesión

        response.setStatus(HttpServletResponse.SC_OK); // Respuesta exitosa
    }
}
