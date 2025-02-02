package servlets;

import clasesDAO.JugadorDAO;
import clasesDAO.EquipoDAO;
import clasesDAO.CompeticionDAO;
import clasesVO.JugadorVO;
import clasesVO.EquipoVO;
import clasesVO.CompeticionVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Petición recibida en SearchServlet"); // Para debug

        // Configuración de respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String searchTerm = request.getParameter("search");
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            response.getWriter().write("{\"success\": false, \"message\": \"Parámetro de búsqueda inválido.\"}");
            return;
        }

        List<String> results = new ArrayList<>();

        try {
            // Búsqueda en jugadores
            List<JugadorVO> jugadores = JugadorDAO.buscarJugadores(searchTerm);
            for (JugadorVO jugador : jugadores) {
                results.add("Jugador: " + jugador.getNombreJugador());
            }

            // Búsqueda en competiciones
            List<CompeticionVO> competiciones = CompeticionDAO.buscarCompeticiones(searchTerm);
            for (CompeticionVO competicion : competiciones) {
                results.add("Competición: " + competicion.getNombre());
            }

            // Búsqueda en equipos
            List<EquipoVO> equipos = EquipoDAO.buscarEquipos(searchTerm);
            for (EquipoVO equipo : equipos) {
                results.add("Equipo: " + equipo.getNombreEquipo());
            }

            // Generar respuesta JSON con los resultados de la búsqueda
            PrintWriter out = response.getWriter();
            out.print("[");
            for (int i = 0; i < results.size(); i++) {
                out.print("\"" + results.get(i) + "\"");
                if (i < results.size() - 1) out.print(", ");
            }
            out.print("]");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"success\": false, \"message\": \"Error en el servidor al realizar la búsqueda.\"}");
        }
    }
}
