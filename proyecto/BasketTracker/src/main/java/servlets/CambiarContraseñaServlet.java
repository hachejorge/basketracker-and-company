package servlets;

import clasesDAO.UsuarioDAO;
import clasesVO.UsuarioVO;
import clasesDAO.TokenDAO;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CambiarContraseñaServlet")
public class CambiarContraseñaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String correo = request.getParameter("correo");
        String nuevaContrasena = request.getParameter("nuevaContrasena");
        String confirmarContrasena = request.getParameter("confirmarContrasena");

        // Validar que las contraseñas coincidan
        if (!nuevaContrasena.equals(confirmarContrasena)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("/views/jsp/restablecer_contraseñat.jsp").forward(request, response);
            return;
        }

        try {
            // Verificar si el token es válido para el usuario y no ha expirado
            if (TokenDAO.verificarTokenRestablecimiento(nombreUsuario, token, correo)) {
                // Token válido, proceder a actualizar la contraseña
                UsuarioVO usuario = UsuarioDAO.obtenerUsuarioPorNombre(nombreUsuario);

                // Si se encontró el usuario y el correo coincide, actualizar la contraseña
                if (usuario != null && usuario.getCorreoElect().equals(correo)) {
                    // Encriptar la nueva contraseña con BCrypt
                    String nuevaContrasenaEncriptada = BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt());

                    // Establecer la nueva contraseña encriptada
                    usuario.setPassword(nuevaContrasenaEncriptada); 
                    UsuarioDAO.actualizarUsuario(usuario); // Guardar en la base de datos

                    // Eliminar el token después del uso
                    TokenDAO.eliminarTokenRestablecimiento(nombreUsuario);

                    // Redirigir al usuario con un mensaje de éxito
                    response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp?event=Contraseña actualizada correctamente");

                } else {
                    // Usuario no encontrado o correo incorrecto
                    response.sendRedirect(request.getContextPath() + "views/jsp/restablecer_contraseña?event=Usuario o correo incorrecto.");
                }
            } else {
                // Token inválido o expirado
                response.sendRedirect(request.getContextPath() + "views/jsp/restablecer_contraseña?event=Token inválido o expirado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Hubo un error procesando la solicitud.");
            request.getRequestDispatcher("/views/jsp/restablecer_contraseña.jsp").forward(request, response);
        }
    }
}
