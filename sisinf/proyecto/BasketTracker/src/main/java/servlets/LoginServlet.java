package servlets;

import clasesVO.UsuarioVO;
import clasesDAO.UsuarioDAO;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        try { 
            if ((usuario != null) && (!usuario.trim().isEmpty()) && 
             (password != null) && (!password.trim().isEmpty()) ) {

                // Busca el usuario en la base de datos
                UsuarioVO usuarioEncontrado = UsuarioDAO.obtenerUsuarioPorNombre(usuario);

                if (usuarioEncontrado == null) {
                    response.getWriter().write("<script>alert('Usuario no encontrado'); window.location.href = '" + request.getContextPath() + "/views/jsp/login.jsp';</script>");
                    return;
                }

                // Si el usuario es "admin" con contraseña "admin" encriptada
                if (usuarioEncontrado.getNombreUsuario().equals("admin") && BCrypt.checkpw(password, usuarioEncontrado.getPassword())) {
                    request.getSession().setAttribute("usuario", usuarioEncontrado);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/jsp/admin/inicioAdmin.jsp");
                    dispatcher.forward(request, response);
                } 
                // Verifica la contraseña ingresada con el hash usando JBCrypt
                else if (BCrypt.checkpw(password, usuarioEncontrado.getPassword())) {
                    // Usuario y contraseña son correctos
                    request.getSession().setAttribute("usuario", usuarioEncontrado);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/jsp/inicio.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.getWriter().write("<script>alert('Usuario o contraseña incorrectos');window.location.href = '" + request.getContextPath() + "/views/jsp/login.jsp';</script>");
                }
            } else {
                response.getWriter().write("<script>alert('Nombre de usuario o contraseña no pueden estar vacíos');window.location.href = '" + request.getContextPath() + "/views/jsp/login.jsp';</script>");
            }
        } catch (Exception e) { 
            e.printStackTrace();
            response.getWriter().write("<script>alert('Error en el servidor'); window.location.href = '" + request.getContextPath() + "/views/jsp/login.jsp';</script>");
        }
    }
}
