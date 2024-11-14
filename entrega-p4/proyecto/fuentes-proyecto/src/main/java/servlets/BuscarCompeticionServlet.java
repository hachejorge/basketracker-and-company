package servlets;

import clasesDAO.CompeticionDAO;
import clasesVO.CompeticionVO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/BuscarCompeticion")
public class BuscarCompeticionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CompeticionDAO compeDAO = new CompeticionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String termino = request.getParameter("termino");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Obtener las competiciones similares al término de búsqueda
            List<CompeticionVO> competiciones = compeDAO.buscarCompeticiones(termino, 3);
            
            for(int i = 0; i < competiciones.size(); i++) {
            	//System.out.println(competiciones.get(i).getNombre());
            }
            
            // Convertir la lista de competiciones a JSON
            Gson gson = new Gson();
            String json = gson.toJson(competiciones);

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
