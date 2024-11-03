package servlets;

import clasesDAO.CompeticionDAO;
import clasesDAO.EquipoDAO;
import clasesVO.CompeticionVO;
import clasesVO.EquipoVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/BuscarEquipo")
public class BuscarEquipoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EquipoDAO equipoDAO = new EquipoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String termino = request.getParameter("termino");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Obtener las competiciones similares al término de búsqueda
            List<EquipoVO> equipos = equipoDAO.buscarEquipos(termino, 3);
            
            /*for(int i = 0; i < equipos.size(); i++) {
            	System.out.println(equipos.get(i).getIdEquipo());
            	System.out.println(equipos.get(i).getNombreEquipo());
            	System.out.println(equipos.get(i).getUbicacion());
            	System.out.println(equipos.get(i).getCompeticion());
            }*/
            
            // Convertir la lista de competiciones a JSON
            Gson gson = new Gson();
            String json = gson.toJson(equipos);

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
