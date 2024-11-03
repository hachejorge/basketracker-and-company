package servlets;

import clasesDAO.EquipoDAO;
import clasesDAO.JugadorDAO;
import clasesDAO.CompeticionDAO;
import clasesVO.JugadorVO;  // Asegúrate de importar la clase correcta
import clasesVO.EquipoVO;   // Asegúrate de importar la clase correcta
import clasesVO.CompeticionVO; // Asegúrate de importar la clase correcta

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/BuscarSugerenciasServlet")
public class BuscarSugerenciasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String query = request.getParameter("query");
        if (query == null || query.trim().isEmpty()) {
            response.getWriter().write(""); // Retornar un string vacío si no hay consulta
            return;
        }

        try {
            // Obtener sugerencias de jugadores, equipos y competiciones
            List<JugadorVO> jugadores = JugadorDAO.buscarJugadoresPorNombre(query);
            List<EquipoVO> equipos = EquipoDAO.buscarEquiposPorNombre(query);
            List<CompeticionVO> competiciones = CompeticionDAO.buscarCompeticionesPorNombre(query);

            // Generar el HTML con las sugerencias
            StringBuilder suggestionsHtml = new StringBuilder();

            // Añadir jugadores al HTML
            for (JugadorVO jugador : jugadores) {
                suggestionsHtml.append("<div class='suggestion' data-type='jugador' onclick='verMasHeader(\"").append(jugador.getNombreUsuario()).append("\")'>")
                               .append("Jugador: @").append(jugador.getNombreUsuario()).append(' ').append(jugador.getNombreJugador()).append("</div>");
            }

            // Añadir equipos al HTML
            for (EquipoVO equipo : equipos) {
                suggestionsHtml.append("<div class='suggestion' data-type='equipo' onclick='verMasEquipoHeader(\"").append(equipo.getIdEquipo()).append("\")'>")
                               .append("Equipo: ").append(equipo.getNombreEquipo()).append("</div>"); // Asumiendo que tienes un método getNombre()
            }

            // Añadir competiciones al HTML
            for (CompeticionVO competicion : competiciones) {
                suggestionsHtml.append("<div class='suggestion' data-type='competicion' onclick='verMasCompeticionHeader(\"").append(competicion.getNombre()).append("\")'>")
                               .append("Competición: ").append(competicion.getNombre()).append("</div>"); // Asumiendo que tienes un método getNombre()
            }

            // Escribir el HTML generado en la respuesta
            response.getWriter().write(suggestionsHtml.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("");  // En caso de error, enviar un string vacío
        }
    }
}
