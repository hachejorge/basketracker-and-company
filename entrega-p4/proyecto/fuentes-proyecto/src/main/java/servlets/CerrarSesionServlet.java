package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Define la ruta de este servlet, por ejemplo "/CerrarSesionServlet"
@WebServlet("/CerrarSesionServlet")
public class CerrarSesionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CerrarSesionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la sesión actual
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Invalidar la sesión para cerrar sesión
            session.invalidate();
        }

        // Redirigir al usuario a la página de inicio de sesión o página principal
        response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Para manejar tanto GET como POST
    }
}
