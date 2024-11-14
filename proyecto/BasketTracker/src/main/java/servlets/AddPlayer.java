package servlets;

import clasesDAO.EquipoDAO;
import clasesDAO.JugadorDAO;
import clasesDAO.UsuarioDAO;
import clasesVO.EquipoVO;
import clasesVO.JugadorVO;
import clasesVO.UsuarioVO;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddPlayer")
public class AddPlayer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private JugadorDAO jugadorDAO = new JugadorDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recibir parámetros del formulario de registro
        String nombreJugador = request.getParameter("nomPlayer");
        String idEquipoJugador = request.getParameter("idEquipoJugador");
        String contrasenaJugador = request.getParameter("password");  // La contraseña que viene del formulario

        int idEquipo = Integer.parseInt(idEquipoJugador);

        if ((nombreJugador != null) && (!nombreJugador.trim().equals("")) &&
            (idEquipoJugador != null) && (!idEquipoJugador.trim().equals("")) && idEquipo > 0) {

            try {
                // Comprobar que el equipo exista
                EquipoVO equipoExistente = EquipoDAO.obtenerEquipoPorId(idEquipo);
                if (equipoExistente != null) {
                    // Comprobar que no hay ningún jugador con ese nombre en ese equipo
                    if (!JugadorDAO.existeJugadorEnEquipo(nombreJugador, idEquipo)) {
                        String cuentaGenerada;

                        // Se crea un usuario asociado a ese jugador
                        do {
                            String nombreFormateado = nombreJugador.replaceAll("\\s", "");

                            // Concatenar un número aleatorio al nombre para evitar colisiones
                            cuentaGenerada = nombreFormateado + (int)(Math.random() * 99) + 1;  // Puedes ajustarlo si deseas más control sobre los números generados.

                        } while (UsuarioDAO.obtenerUsuarioPorNombre(cuentaGenerada) != null);

                        // Encriptar la contraseña que viene del formulario
                        String contrasenaEncriptada = BCrypt.hashpw(contrasenaJugador, BCrypt.gensalt());

                        // Crear el objeto UsuarioVO con la contraseña encriptada
                        UsuarioVO usuario = new UsuarioVO(cuentaGenerada, "", contrasenaEncriptada);
                        UsuarioDAO.guardarUsuario(usuario);

                        // Crear el jugador y asociarlo al equipo
                        JugadorVO jugador = new JugadorVO(cuentaGenerada, nombreJugador, idEquipo);
                        JugadorDAO.guardarJugador(jugador);

                        // Redirigir al administrador con un mensaje de éxito
                        response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=" + nombreJugador + " ha sido añadido correctamente");
                    } else {
                        response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=Ya existe un jugador con el nombre " + nombreJugador + " en el equipo seleccionado");
                    }
                } else {
                    response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=El equipo seleccionado no existe");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Hubo un problema al registrar a el/la jugador/a. Intente nuevamente.");
                response.sendRedirect("views/jsp/admin/inicioAdmin.jsp");
            }
        } else {
            response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=No puedes registrar un/a jugador/a con parámetros nulos");
        }
    }
}
