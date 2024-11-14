package servlets;

import clasesDAO.PtsJugParDAO;
import clasesVO.PtsJugParVO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddStatsPlayerMatch")
public class AddStatsPlayerMatch extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PtsJugParDAO ptsJugParDAO = new PtsJugParDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recibir los parámetros del formulario
        String partidoId = request.getParameter("partidoId");
        String nombreJugador = request.getParameter("nombreJugador");
        String ptsAnt = request.getParameter("ptsAnt");
        String trpAnt = request.getParameter("trpAnt");
        String tlbLan = request.getParameter("tlbLan");
        String tlbAnt = request.getParameter("tlbAnt");
        String faltas = request.getParameter("faltas");
        String mntJd = request.getParameter("mntJd");

        // Validación de los parámetros
        if (partidoId != null && !partidoId.trim().equals("") && nombreJugador != null && !nombreJugador.trim().equals("")) {
            try {
                // Convertir los valores a enteros (o a null si no son válidos)
                Integer ptsAntValue = (ptsAnt != null && !ptsAnt.trim().equals("")) ? Integer.parseInt(ptsAnt) : null;
                Integer trpAntValue = (trpAnt != null && !trpAnt.trim().equals("")) ? Integer.parseInt(trpAnt) : null;
                Integer tlbLanValue = (tlbLan != null && !tlbLan.trim().equals("")) ? Integer.parseInt(tlbLan) : null;
                Integer tlbAntValue = (tlbAnt != null && !tlbAnt.trim().equals("")) ? Integer.parseInt(tlbAnt) : null;
                Integer faltasValue = (faltas != null && !faltas.trim().equals("")) ? Integer.parseInt(faltas) : null;
                Integer mntJdValue = (mntJd != null && !mntJd.trim().equals("")) ? Integer.parseInt(mntJd) : null;

                // Validación de los tiros libres
                if (tlbAntValue != null && tlbLanValue != null && tlbAntValue > tlbLanValue) {
                    response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=Los tiros libres anotados no pueden ser mayores que los lanzados.");
                    return;
                }

                // Validación de los puntos anotados
                if (ptsAntValue != null) {
                    // Calculamos el mínimo posible de puntos basado en los triples y tiros libres
                    int puntosMinimos = (trpAntValue != null ? trpAntValue * 3 : 0) + (tlbAntValue != null ? tlbAntValue : 0);

                    // Aseguramos que los puntos anotados sean lógicos
                    if (ptsAntValue < puntosMinimos) {
                        response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=El número de puntos anotados es menor que los puntos mínimos basados en triples y tiros libres.");
                        return;
                    }

                    // Aseguramos que los puntos anotados sean múltiplos de 2 después de contar triples y tiros libres
                    int puntosRestantes = ptsAntValue - puntosMinimos;
                    if (puntosRestantes % 2 != 0) {
                        response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=El número de puntos anotados debe ser múltiplo de 2 después de contar triples y tiros libres.");
                        return;
                    }
                }

                // Crear un objeto PtsJugParVO con los valores recibidos del formulario
                PtsJugParVO datosJugadorPartido = new PtsJugParVO();
                datosJugadorPartido.setIdPartido(Integer.parseInt(partidoId));
                datosJugadorPartido.setNombreUsuario(nombreJugador);
                datosJugadorPartido.setPtsAnt(ptsAntValue);
                datosJugadorPartido.setTrpAnt(trpAntValue);
                datosJugadorPartido.setTlbLan(tlbLanValue);
                datosJugadorPartido.setTlbAnt(tlbAntValue);
                datosJugadorPartido.setFaltas(faltasValue);
                datosJugadorPartido.setMntJd(mntJdValue);

                // Verificar si ya existen estadísticas para el jugador en este partido
                PtsJugParVO existingStats = PtsJugParDAO.obtenerPtsJugPar(Integer.parseInt(partidoId), nombreJugador);

                if (existingStats != null) {
                    // Si existen estadísticas, actualizarlas
                    PtsJugParDAO.actualizarPtsJugPar(datosJugadorPartido);
                    response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=Estadísticas del jugador " + nombreJugador + " actualizadas correctamente en el partido.");
                } else {
                    // Si no existen, insertarlas
                    PtsJugParDAO.guardarPtsJugPar(datosJugadorPartido);
                    response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=Estadísticas del jugador " + nombreJugador + " añadidas correctamente al partido.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Hubo un problema al registrar o actualizar las estadísticas del jugador. Intente nuevamente.");
                response.sendRedirect("views/jsp/admin/inicioAdmin.jsp");
            }
        } else {
            response.sendRedirect("views/jsp/admin/inicioAdmin.jsp?event=Los parámetros del formulario son inválidos.");
        }
    }
}
