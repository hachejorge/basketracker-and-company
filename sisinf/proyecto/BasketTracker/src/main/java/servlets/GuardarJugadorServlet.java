package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import clasesVO.JugadorVO;
import clasesDAO.JugadorDAO;

@WebServlet("/GuardarJugadorServlet")
public class GuardarJugadorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreJugador = request.getParameter("nombreJugador");
        
        // Aquí deberías obtener el JugadorVO de alguna manera (por nombre de usuario o similar)
        JugadorVO jugadorVO = JugadorDAO.obtenerJugadorPorNombreUsuario(nombreJugador); // Ajusta esto según tu lógica
        
        HttpSession session = request.getSession();
        session.setAttribute("jugadorSeleccionado", jugadorVO); // Guardar el jugador en la sesión

        response.setStatus(HttpServletResponse.SC_OK); // Respuesta exitosa
    }
}
