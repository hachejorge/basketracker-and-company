package servlets;


import clasesDAO.EquipoDAO;
import clasesDAO.JugadorDAO;
import clasesDAO.UsuarioDAO;
import clasesVO.EquipoVO;
import clasesVO.JugadorVO;
import clasesVO.UsuarioVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

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

        int idEquipo = Integer.parseInt(idEquipoJugador);
        
        if ((nombreJugador != null)  && (!nombreJugador.trim().equals("")) &&
        	(idEquipoJugador != null)   && (!idEquipoJugador.trim().equals("")) && idEquipo > 0) {
        	
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
	                     
	                		// Generar un número aleatorio entre 1 y 99
	                		Random random = new Random();
	                		int numeroAleatorio = random.nextInt(99) + 1; // `+1` asegura que el número es entre 1 y 99
	                     
	                		// Concatenar el número al nombre formateado
	                		cuentaGenerada = nombreFormateado + numeroAleatorio;
	                	} while(UsuarioDAO.obtenerUsuarioPorNombre(cuentaGenerada) != null);
	                    
	                	
	                    UsuarioVO usuario = new UsuarioVO(cuentaGenerada,"","");
	                    UsuarioDAO.guardarUsuario(usuario);
	                    
	                    JugadorVO jugador = new JugadorVO(cuentaGenerada,nombreJugador,idEquipo);
	                    JugadorDAO.guardarJugador(jugador);
	                    
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
        }
        else {
            response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=No puedes registrar un/a jugador/a con parámetros nulos");
        }
    }
}
