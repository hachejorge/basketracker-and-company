import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String adminCode = request.getParameter("adminCode");

        // Lógica de autenticación básica (por ejemplo, validando usuario y contraseña)
        if ("admin".equals(usuario) && "1234".equals(password)) {
            response.getWriter().println("Bienvenido, " + usuario);
        } else {
            response.getWriter().println("Usuario o contraseña incorrectos.");
        }
        
        // Puedes agregar lógica para validar si el código de administrador es correcto
        if (adminCode != null && !adminCode.isEmpty()) {
            // Validar el código de administrador
        }
    }
}
