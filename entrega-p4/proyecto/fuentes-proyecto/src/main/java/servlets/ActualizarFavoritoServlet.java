package servlets;

import clasesDAO.EquipoFavDAO;
import clasesDAO.JugadorFavDAO;
import clasesDAO.CompeticionFavDAO;
import clasesVO.EquipoFavVO;
import clasesVO.JugadorFavVO;
import clasesVO.CompeticionFavVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ActualizarFavoritoServlet")
public class ActualizarFavoritoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ActualizarFavoritoServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Petición recibida en ActualizarFavoritoServlet"); // Para debug
    	// Configurar la respuesta para que sea JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String tipoFavorito = request.getParameter("tipo");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String id = request.getParameter("id");
        boolean esFavorito = Boolean.parseBoolean(request.getParameter("esFavorito"));
    	System.out.println("Tipo: " + tipoFavorito);
    	System.out.println("Usuario: " + nombreUsuario);
    	System.out.println("ID: " + id);
    	System.out.println("Es favorito: " + esFavorito);

        try {
            // Validación básica de parámetros
            if (tipoFavorito == null || nombreUsuario == null || id == null || tipoFavorito.trim().isEmpty() || nombreUsuario.trim().isEmpty()) {
                response.getWriter().write("{\"success\": false, \"message\": \"Error: Parámetros inválidos.\"}");
                return;
            }

            // Manejar la actualización según el tipo de favorito
            switch (tipoFavorito) {
                case "jugador":
                    JugadorFavDAO jugadorFavDAO = new JugadorFavDAO();
                    if (esFavorito) {
                        jugadorFavDAO.eliminarJugadorFav(nombreUsuario, id);
                    } else {
                        jugadorFavDAO.guardarJugadorFav(new JugadorFavVO(nombreUsuario, id));
                    }
                    break;

                case "equipo":
                    EquipoFavDAO equipoFavDAO = new EquipoFavDAO();
                    int equipoId = Integer.parseInt(id);
                    if (esFavorito) {
                        equipoFavDAO.eliminarEquipoFav(nombreUsuario, equipoId);
                    } else {
                        equipoFavDAO.guardarEquipoFav(new EquipoFavVO(nombreUsuario, equipoId));
                    }
                    break;

                case "competicion":
                    CompeticionFavDAO competicionFavDAO = new CompeticionFavDAO();
                    if (esFavorito) {
                        competicionFavDAO.eliminarCompeticionFav(nombreUsuario, id);
                    } else {
                        competicionFavDAO.guardarCompeticionFav(new CompeticionFavVO(nombreUsuario, id));
                    }
                    break;

                default:
                    response.getWriter().write("{\"success\": false, \"message\": \"Error: Tipo de favorito no válido.\"}");
                    return;
            }

            // Responder con un JSON indicando que la operación fue exitosa
            response.getWriter().write("{\"success\": true}");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"success\": false, \"message\": \"Error en el servidor al actualizar el favorito.\"}");
        }
    }
}