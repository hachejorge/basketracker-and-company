package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import clasesVO.PartidoVO;
import clasesDAO.PartidoDAO;

@WebServlet("/GuardarPartidoServlet")
public class GuardarPartidoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPartido = request.getParameter("idPartido");
        Integer partido = Integer.parseInt(idPartido);
        
        // Obtener el CompeticionVO de acuerdo a su nombre
        PartidoVO partidoVO = PartidoDAO.obtenerPartidoPorId(partido); // Ajusta según tu lógica
        
        HttpSession session = request.getSession();
        session.setAttribute("partidoSeleccionado", partidoVO); // Guardar la competición en la sesión

        response.setStatus(HttpServletResponse.SC_OK); // Respuesta exitosa
    }
}
