package servlets;

import clasesVO.MensajeVO;
import clasesDAO.MensajeDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EnviarMensajeServlet")
public class EnviarMensajeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String mensajeTexto = request.getParameter("comentario");

        try {
            int nuevoId = MensajeDAO.obtenerIdMaximo(); // Obtener el ID máximo + 1

            // Crear un objeto MensajeVO con el nuevo ID y detalles del mensaje
            LocalDate fechaActual = LocalDate.now();
            LocalTime horaActual = LocalTime.now();
            Date fechaSql = Date.valueOf(fechaActual);
            Time horaSql = Time.valueOf(horaActual);
            MensajeVO mensajeVO = new MensajeVO(nuevoId + 1, nombreUsuario, mensajeTexto, horaSql, fechaSql);

            	
            // Guardar el mensaje en la base de datos
            MensajeDAO.guardarMensaje(mensajeVO);
            
            response.setStatus(HttpServletResponse.SC_OK);
            // Redirigir a perfil_partido.jsp después de guardar el mensaje
            response.sendRedirect(request.getContextPath() + "/views/jsp/mensajes.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al guardar el mensaje");
        }
    }
}

