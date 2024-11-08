package servlets;

import clasesDAO.EquipoDAO;
import clasesDAO.JugadorDAO;
import clasesDAO.PartidoDAO;
import clasesDAO.UsuarioDAO;
import clasesVO.EquipoVO;
import clasesVO.JugadorVO;
import clasesVO.PartidoVO;
import clasesVO.UsuarioVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/AddMatch")
public class AddMatch extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private JugadorDAO jugadorDAO = new JugadorDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recibir parámetros del formulario de registro
        String idEquipoLocal = request.getParameter("idEquipoJugador2");
        String idEquipoVisit = request.getParameter("idEquipoJugador3");
        // Jornada, fecha, hora
        String jornadaS = request.getParameter("jornadaPartido");
        String fechaS = request.getParameter("fecha");
        String horaS = request.getParameter("hora");
        
        System.out.println("idEquipolocal= " + idEquipoLocal);
        System.out.println("idEquipovisit= " + idEquipoVisit);

        System.out.println("Jornada: " + jornadaS);
        System.out.println("Fecha: " + fechaS);
        System.out.println("Hora: " + horaS);
        
        int jornada = Integer.parseInt(jornadaS);
        
        System.out.println("Jornada entera " + jornada);
        
        List<Integer> ptsLocal = new ArrayList<Integer>();
        List<Integer> ptsVisit = new ArrayList<Integer>();
        
        for(int i = 1; i < 5; i++) {
        	String ptLocal = "c" + i + "local";
        	String ptVisit = "c" + i + "visit";
        	ptsLocal.add(Integer.parseInt(request.getParameter(ptLocal)));
        	ptsVisit.add(Integer.parseInt(request.getParameter(ptVisit)));
        }

        int idLocal = Integer.parseInt(idEquipoLocal);
        int idVisit = Integer.parseInt(idEquipoVisit);
        
        if (idLocal != idVisit) {
	        try {
	        	// Comprobar que el equipo exista
	            EquipoVO equipoLocal = EquipoDAO.obtenerEquipoPorId(idLocal);
	            EquipoVO equipoVisit = EquipoDAO.obtenerEquipoPorId(idVisit);
	            if (equipoLocal != null && equipoVisit != null) {
	            	if(EquipoDAO.obtenerCompeticionPorIdEquipo(idLocal) != EquipoDAO.obtenerCompeticionPorIdEquipo(idVisit)) {
	            		if(jornada >= 1) {
	            			// Convertir fecha y hora a tipos `Date` y `Time`
                            Date fecha = Date.valueOf(fechaS); // Formato "yyyy-MM-dd"
                            Time hora = Time.valueOf(horaS + ":00"); // Formato "HH:mm:ss"

                            // Crear instancia de PartidoVO
                            PartidoVO partido = new PartidoVO(
                                0, idLocal, idVisit, jornada,
                                ptsLocal.get(0), ptsLocal.get(1), ptsLocal.get(2), ptsLocal.get(3),
                                ptsVisit.get(0), ptsVisit.get(1), ptsVisit.get(2), ptsVisit.get(3),
                                hora, fecha );

                            // Guardar el partido usando el DAO
                            PartidoDAO.guardarPartido(partido);

                            // Redirigir a la página de éxito
                            response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=Partido registrado correctamente");
	            			
	            			
	            			//PartidoVO partido = new PartidoVO(0, idLocal, idVisit, idVisit, idVisit, idVisit, idVisit, idVisit, idVisit, idVisit, idVisit, idVisit, null, null);
	            		
	            		} else {
	            			response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=La jornada introducida es incorrecta");
	            		}
	            	} else {
            			response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=Los equipos tienen que competir en la misma categoría");

	            	}
	            } else {
	                response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=Los equipos tienen que existir");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Hubo un problema al registrar a el/la jugador/a. Intente nuevamente.");
	            response.sendRedirect("views/jsp/admin/inicioAdmin.jsp");
	        }
        }
        else {
            response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=No puedes registrar un partido con mismo equipo local y visitante ");
        }
    }
}
