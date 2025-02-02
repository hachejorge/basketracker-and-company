<%@ page import="clasesDAO.EquipoDAO" %>
<%@ page import="clasesDAO.JugadorDAO" %>
<%@ page import="clasesDAO.PartidoDAO" %>
<%@ page import="clasesDAO.PtsJugParDAO" %>
<%@ page import="clasesVO.EquipoVO" %>
<%@ page import="clasesVO.JugadorVO" %>
<%@ page import="clasesVO.PartidoVO" %>
<%@ page import="clasesVO.PtsJugParVO" %>

<%
    String partidoId = request.getParameter("partidoId");
    String nombreJugador = request.getParameter("jugador");

    // Obtener los objetos correspondientes
    PartidoVO partido = PartidoDAO.obtenerPartidoPorId(Integer.parseInt(partidoId));
    JugadorVO jugador = JugadorDAO.obtenerJugadorPorNombreUsuario(nombreJugador);
    EquipoVO equipoLocal = EquipoDAO.obtenerEquipoPorId(partido.getEquipoLocal());
    EquipoVO equipoVisitante = EquipoDAO.obtenerEquipoPorId(partido.getEquipoVisitante());
    
    PtsJugParVO datosPartidoJugador = PtsJugParDAO.obtenerPtsJugPar(Integer.parseInt(partidoId), nombreJugador);
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Añadir jugador en partido</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/admin_styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="thecontainer">
    <%@ include file="../headerAdmin.jsp" %>
    <div class="container">
    	<div class="form-section">
	            <h2> <%= jugador.getNombreJugador() %> - <%= equipoLocal.getNombreEquipo() %> VS <%= equipoVisitante.getNombreEquipo() %> </h2>
				<form action="<%= request.getContextPath() %>/AddStatsPlayerMatch" method="post">
	                <!-- Información del partido y jugador -->
	                <input type="hidden" name="partidoId" value="<%= partidoId %>">
	                <input type="hidden" name="nombreJugador" value="<%= nombreJugador %>">
	
	                <!-- Puntos Antiguos -->
	                <div class="form-group">
	                    <label for="ptsAnt">Puntos Anotados</label>
	                    <input type="number" id="ptsAnt" name="ptsAnt" value="<%= datosPartidoJugador != null ? datosPartidoJugador.getPtsAnt() : "" %>" min="0" required>
	                </div>
	
	                <!-- Triples Anteriores -->
	                <div class="form-group">
	                    <label for="trpAnt">Triples Anotados</label>
	                    <input type="number" id="trpAnt" name="trpAnt" value="<%= datosPartidoJugador != null ? datosPartidoJugador.getTrpAnt() : "" %>" min="0" required>
	                </div>
	
	                <!-- Tiros Libres Lanzados -->
	                <div class="form-group">
	                    <label for="tlbLan">Tiros Libres Lanzados</label>
	                    <input type="number" id="tlbLan" name="tlbLan" value="<%= datosPartidoJugador != null ? datosPartidoJugador.getTlbLan() : "" %>" min="0" required>
	                </div>
	
	                <!-- Tiros Libres Anotados -->
	                <div class="form-group">
	                    <label for="tlbAnt">Tiros Libres Anotados</label>
	                    <input type="number" id="tlbAnt" name="tlbAnt" value="<%= datosPartidoJugador != null ? datosPartidoJugador.getTlbAnt() : "" %>" min="0" required>
	                </div>
	
	                <!-- Faltas -->
	                <div class="form-group">
	                    <label for="faltas">Faltas</label>
	                    <input type="number" id="faltas" name="faltas" value="<%= datosPartidoJugador != null ? datosPartidoJugador.getFaltas() : "" %>" min="0" max="5" required>
	                </div>
	
	                <!-- Minutos Jugados -->
	                <div class="form-group">
	                    <label for="mntJd">Minutos Jugados</label>
	                    <input type="number" id="mntJd" name="mntJd" value="<%= datosPartidoJugador != null ? datosPartidoJugador.getMntJd() : "" %>" min="0" max="40" required>
	                </div>
	
	                <div class="form-group save-button">
	                    <button type="submit">
	                        <i class="fa fa-save"></i>
	                        Guardar Datos
	                    </button>
	                </div>
            	</form>
	        </div>
         </div>
</div>
</body>
</html>
