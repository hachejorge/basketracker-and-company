package src.main.servlets;

import src.main.clasesDAO.UsuarioDAO;
import src.main.clasesVO.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        // Inicializamos el DAO de Usuario
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtenemos los datos del formulario
        String nombreUsuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        try {
            // Buscamos el usuario en la base de datos por el nombre de usuario
            Usuario usuario = usuarioDAO.obtenerUsuarioPorNombre(nombreUsuario);

            if (usuario != null && usuario.getPassword().equals(password)) {
                // Credenciales correctas
                request.getSession().setAttribute("usuario", usuario);
                response.sendRedirect("views/jsp/home.jsp"); // Página a la que redirigir después de iniciar sesión
            } else {
                // Credenciales incorrectas
                request.setAttribute("error", "Nombre de usuario o contraseña incorrectos.");
                request.getRequestDispatcher("views/jsp/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al procesar el inicio de sesión.");
            request.getRequestDispatcher("views/jsp/login.jsp").forward(request, response);
        }
    }
}
