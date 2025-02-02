package servlets;

import clasesDAO.UsuarioDAO;
import clasesVO.UsuarioVO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/BorrarUsuarioServlet")
public class BorrarUsuarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuario");

        if (usuario != null) {
            // Llamada al DAO para eliminar el usuario
            UsuarioDAO.eliminarUsuario(usuario.getNombreUsuario());
            session.invalidate(); // Cerrar sesión después de borrar la cuenta
            response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp"); // Redirigir a login
        } else {
            response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp");
        }
    }
}
