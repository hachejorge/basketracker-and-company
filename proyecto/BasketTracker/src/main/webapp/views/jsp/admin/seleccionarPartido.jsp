<%@ page import="java.util.List" %>
<%@ page import="clasesDAO.EquipoDAO" %>
<%@ page import="clasesDAO.JugadorDAO" %>
<%@ page import="clasesDAO.PartidoDAO" %>
<%@ page import="clasesVO.EquipoVO" %>
<%@ page import="clasesVO.JugadorVO" %>
<%@ page import="clasesVO.PartidoVO" %>

<%
    String nombreJugador = request.getParameter("nombreJugador");

    // Obtener el equipo del jugador
    JugadorVO jugador = JugadorDAO.obtenerJugadorPorNombreUsuario(nombreJugador);
    EquipoVO equipo = EquipoDAO.obtenerEquipoPorId(jugador.getEquipo());

    // Obtener los partidos donde el equipo juega como local o visitante
    List<PartidoVO> partidos = PartidoDAO.obtenerPartidosPorEquipo(equipo.getIdEquipo());
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Partidos de Equipo de <%= nombreJugador %></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/admin_styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="thecontainer">
    <%@ include file="../headerAdmin.jsp" %>
	<div class="container">
    
		<div class="container-partidos">
    		<h1 styles="padding-left: 20px;">Partidos del equipo de <%= nombreJugador %> - <%= equipo.getNombreEquipo() %></h1>

        <%
            for (PartidoVO partido : partidos) {
                // Obtener los equipos local y visitante usando el ID del equipo en el partido
                EquipoVO equipoLocal = EquipoDAO.obtenerEquipoPorId(partido.getEquipoLocal());
                EquipoVO equipoVisitante = EquipoDAO.obtenerEquipoPorId(partido.getEquipoVisitante());

                // Calcular el puntaje total de cada equipo sumando puntos de los 4 cuartos
                int puntosTotalesLocal = partido.getPtsC1Local() + partido.getPtsC2Local() + partido.getPtsC3Local() + partido.getPtsC4Local();
                int puntosTotalesVisitante = partido.getPtsC1Visit() + partido.getPtsC2Visit() + partido.getPtsC3Visit() + partido.getPtsC4Visit();

                // Obtener la fecha y hora del partido
                String fecha = partido.getFecha().toString(); // Convertir fecha a String si es necesario
                String hora = partido.getHora().toString();   // Convertir hora a String si es necesario
        %>
            	<div class="partido" onclick="window.location.href='<%= request.getContextPath() %>/views/jsp/admin/addStatsMatch.jsp?partidoId=<%= partido.getIdPartido() %>&jugador=<%= nombreJugador %>'">
                    <!-- Fecha -->
                    <div class="partido-fecha">
                        <div class="dia"><%= partido.formatFecha() %></div> <!-- Día -->
                        <div class="partido-hora"><%= hora.substring(0,5) %>h</div>
                    </div>

                    <!-- Equipos y puntajes -->
                    <div class="equipos-container">
                        <div class="equipo">
							<i class="fa fa-shield" aria-hidden="true"></i>
                            <div class="puntaje puntaje-local"><%= puntosTotalesLocal %></div>
                        </div>
                        <div class="equipos-nombres"><%= equipoLocal.getNombreEquipo() %> VS <%= equipoVisitante.getNombreEquipo() %></div>
                        <div class="equipo">
                            <div class="puntaje puntaje-visitante"><%= puntosTotalesVisitante %></div>
							<i class="fa fa-shield" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
        
        <%
            }
        %>
        </div>
	</div>
</div>
</body>
</html>
