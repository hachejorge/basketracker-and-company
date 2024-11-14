package servlets;

import clasesDAO.CompeticionDAO;
import clasesDAO.EquipoDAO;
import clasesDAO.JugadorDAO;
import clasesVO.CompeticionVO;
import clasesVO.EquipoVO;
import clasesVO.JugadorVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/BuscarJugador")
public class BuscarJugadorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String termino = request.getParameter("termino");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Obtener las competiciones similares al término de búsqueda
            List<JugadorVO> jugadores = JugadorDAO.buscarJugadores(termino);
            /*
            for(int i = 0; i < jugadores.size(); i++) {
            	System.out.println(jugadores.get(i).getNombreUsuario());
            	System.out.println(jugadores.get(i).getNombreJugador());
            	System.out.println(jugadores.get(i).getEquipo());
            }*/
            
            // Convertir la lista de competiciones a JSON
            Gson gson = new Gson();
            String json = gson.toJson(jugadores);

            out.print(json);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Error al buscar competiciones\"}");
            out.flush();
        }
    }
}
