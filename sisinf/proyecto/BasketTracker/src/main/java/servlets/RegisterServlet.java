package servlets;

import clasesDAO.UsuarioDAO;
import clasesVO.UsuarioVO;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
            response.getWriter().write("<script>alert('Las contraseñas no coinciden.');</script>");
            request.getRequestDispatcher("/views/jsp/register.jsp").forward(request, response);
            return;
        }

        // Encriptar la contraseña usando BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Crear un objeto UsuarioVO con la contraseña encriptada
        UsuarioVO usuario = new UsuarioVO(nombreUsuario, correoElect, hashedPassword);

        // Validar si el usuario ya existe
        try {
            UsuarioVO usuarioExistente = UsuarioDAO.obtenerUsuarioPorNombre(nombreUsuario);
            if (usuarioExistente != null) {
                response.sendRedirect("views/jsp/register.jsp?event=El nombre de usuario " + nombreUsuario + " ya está en uso");
                return;
            }

            // Guardar el usuario en la base de datos
            UsuarioDAO.guardarUsuario(usuario);
            
            // Redirigir al usuario a la página de login después del registro exitoso
            response.sendRedirect("views/jsp/login.jsp?event=El usuario " + nombreUsuario + " ha sido registrado correctamente");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Hubo un problema al registrar el usuario. Intente nuevamente.");
            response.getWriter().write("<script>alert('Hubo un problema al registrar el usuario. Intente nuevamente.');</script>");
            request.getRequestDispatcher("views/jsp/register.jsp").forward(request, response);
        }
    }
}
