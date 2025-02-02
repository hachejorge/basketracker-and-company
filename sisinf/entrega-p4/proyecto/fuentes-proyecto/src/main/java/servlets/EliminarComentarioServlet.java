package servlets;

import clasesDAO.ComentarioDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EliminarComentarioServlet")
public class EliminarComentarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String idPartidoStr = request.getParameter("idPartido");
        String comentarioTexto = request.getParameter("comentario");

        try {
            int idPartido = Integer.parseInt(idPartidoStr);
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            comentarioDAO.eliminarComentario(nombreUsuario, idPartido, comentarioTexto); // Llama al nuevo método

            // Respuesta exitosa
            response.setStatus(HttpServletResponse.SC_OK);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/jsp/perfil_partido.jsp");
		    dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID de partido no válido");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al eliminar el comentario");
        }
    }
}
