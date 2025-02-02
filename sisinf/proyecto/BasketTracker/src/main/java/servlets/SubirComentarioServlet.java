package servlets;

import clasesVO.ComentarioVO;
import clasesDAO.ComentarioDAO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SubirComentarioServlet")
public class SubirComentarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del comentario
        String nombreUsuario = request.getParameter("nombreUsuario");
        String idPartidoStr = request.getParameter("idPartido");
        String comentarioTexto = request.getParameter("comentario");

        try {
            // Validar y convertir el ID de partido a entero
            int idPartido = Integer.parseInt(idPartidoStr);

            // Crear objeto ComentarioVO y almacenar el comentario
            ComentarioVO comentarioVO = new ComentarioVO(nombreUsuario, idPartido, comentarioTexto);
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            comentarioDAO.guardarComentario(comentarioVO);

            // Respuesta exitosa
            response.setStatus(HttpServletResponse.SC_OK);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/jsp/perfil_partido.jsp");
		    dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            // ID de partido no válido
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID de partido no válido");
        } catch (Exception e) {
            // Error al guardar el comentario
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al guardar el comentario");
        }
    }
}
