package servlets;

import clasesDAO.UsuarioDAO;
import clasesVO.UsuarioVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recibir parámetros del formulario de registro
        String nombreUsuario = request.getParameter("usuario");
        String correoElect = request.getParameter("correo");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");

        // Validar que las contraseñas coincidan
        if (!password.equals(repeatPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Crear un objeto UsuarioVO
        UsuarioVO usuario = new UsuarioVO(nombreUsuario, correoElect, password);

        // Validar si el usuario ya existe
        try {
            UsuarioVO usuarioExistente = usuarioDAO.obtenerUsuarioPorNombre(nombreUsuario);
            if (usuarioExistente != null) {
                request.setAttribute("error", "El nombre de usuario ya está en uso.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Guardar el usuario en la base de datos
            usuarioDAO.guardarUsuario(usuario);

            // Redirigir al usuario a la página de inicio o login después del registro
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Hubo un problema al registrar el usuario. Intente nuevamente.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
