package servlets;

import clasesDAO.PartidoDAO; // Asegúrate de que este DAO esté correctamente importado
import clasesVO.PartidoVO; // Asegúrate de que este VO esté correctamente importado

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GuardarPartidoServlet")
public class GuardarPartidoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPartidoStr = request.getParameter("idPartido");
        
        // Aquí deberías obtener el partido desde la base de datos utilizando el ID
        PartidoVO partido = PartidoDAO.obtenerPartidoPorId(Integer.parseInt(idPartidoStr));
        
        // Guardar el partido en la sesión o hacer lo que sea necesario
        if (partido != null) {
            request.getSession().setAttribute("partido", partido);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
