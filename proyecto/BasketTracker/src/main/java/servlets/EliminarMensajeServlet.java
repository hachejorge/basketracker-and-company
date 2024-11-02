package servlets;

import clasesDAO.MensajeDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EliminarMensajeServlet")
public class EliminarMensajeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del mensaje desde la solicitud
        String mensajeIdParam = request.getParameter("idMensaje");
        
        try {
            int mensajeId = Integer.parseInt(mensajeIdParam);
            
            // Llamar al DAO para eliminar el mensaje con el ID proporcionado
            MensajeDAO.eliminarMensaje(mensajeId);
  
            // Redirigir a la página de mensajes tras eliminar
            response.sendRedirect(request.getContextPath() + "/views/jsp/mensajes.jsp");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID de mensaje inválido");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al eliminar el mensaje");
        }
    }
}
