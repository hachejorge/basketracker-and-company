package servlets;

import clasesDAO.CompeticionDAO;
import clasesVO.CompeticionVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/RegisterServlet")
public class AddCompe extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CompeticionDAO compeDAO = new CompeticionDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recibir parámetros del formulario de registro
        String competicion = request.getParameter("competicion");

        // Crear un objeto UsuarioVO
        CompeticionVO compe = new CompeticionVO(competicion);

        // Validar si el usuario ya existe
        try {
        	CompeticionVO competicionExistente = compeDAO.obtenerCompeticionPorNombre(competicion);
            if (competicionExistente != null) {
                request.setAttribute("error", "El nombre de usuario ya está en uso.");
                //request.getRequestDispatcher("views/jsp/register.jsp").forward(request, response);
                return;
            }

            // Guardar el usuario en la base de datos
            compeDAO.guardarCompeticion(compe);

            // Redirigir al usuario a la página de inicio o login después del registro
            //response.sendRedirect("views/jsp/login.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Hubo un problema al registrar el usuario. Intente nuevamente.");
            //request.getRequestDispatcher("views/jsp/register.jsp").forward(request, response);
        }
    }
}
