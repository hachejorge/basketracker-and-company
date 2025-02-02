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

@WebServlet("/ActualizarUsuarioServlet")
public class ActualizarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuario");

        if (usuario != null) {
            String nuevoCorreo = request.getParameter("email");
            if (nuevoCorreo != null) {
            	usuario.setCorreoElect(nuevoCorreo);
            }
            
            UsuarioDAO.actualizarUsuario(usuario);
            request.getSession().setAttribute("usuario", usuario);
            response.setStatus(HttpServletResponse.SC_OK); // Respuesta exitosa
            response.sendRedirect(request.getContextPath() + "/views/jsp/perfil_usuario.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp");
        }
    }
}
