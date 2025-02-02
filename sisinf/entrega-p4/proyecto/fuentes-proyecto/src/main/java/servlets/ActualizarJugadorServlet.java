package servlets;

import clasesDAO.JugadorDAO;
import clasesDAO.UsuarioDAO;
import clasesVO.JugadorVO;
import clasesVO.UsuarioVO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ActualizarJugadorServlet")
public class ActualizarJugadorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuario");

        if (usuario != null && JugadorDAO.existeJugador(usuario.getNombreUsuario())) {
            JugadorVO jugador = JugadorDAO.obtenerJugadorPorNombreUsuario(usuario.getNombreUsuario());
            String nuevoNombreJugador = request.getParameter("nombre-jugador");
            String nuevoCorreo = request.getParameter("email");

            jugador.setNombreJugador(nuevoNombreJugador);
            
            usuario.setCorreoElect(nuevoCorreo);
            JugadorDAO.actualizarJugador(jugador);
            UsuarioDAO.actualizarUsuario(usuario);
            request.getSession().setAttribute("usuario", usuario);
            response.setStatus(HttpServletResponse.SC_OK); // Respuesta exitosa            

            response.sendRedirect(request.getContextPath() + "/views/jsp/perfil_jugador.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp");
        }
    }
}
