<%@ page import="clasesDAO.*" %>
<%@ page import="clasesVO.*" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>

<%
    // Recuperar el objeto UsuarioVO de la sesión
    UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuario");
    // Comprobar si el usuario está autenticado
    if (usuario == null) {
        // Redirigir a la página de login si no hay un usuario autenticado
        response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp");
        return;
    }
    
    JugadorVO jugadorVO = (JugadorVO) session.getAttribute("jugadorSeleccionado");

    if (jugadorVO == null) {
    	response.sendRedirect(request.getContextPath() + "/views/jsp/favoritos.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Equipo | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<div class="thecontainer">
	<%@ include file="header-buscador.jsp" %>
	<div class="container-inicio">
		<div class="recuadro">
		    <div class="navbar">
		        <div class="navbar-item active">
		        	<img src="https://img.icons8.com/?size=100&id=131&format=png&color=FFFFFF" alt="Jugadores">
		            <span><b>Buscar</b></span>
		        </div>
		        <div class="navbar-item">
		            <img src="https://img.icons8.com/?size=100&id=84925&format=png&color=000000" alt="Jugadores">
		            <span>Favoritos</span>
		        </div>
		        <div class="navbar-item">
		            <img src="https://img.icons8.com/?size=100&id=87193&format=png&color=000000" alt="Equipos">
		            <span>Mensajes</span>
		        </div>
		    </div>	    
	    </div>
	    <div class="info-perfiles">
		    <div class="proximos-partidos">
			    <div class="partido-container">
			        <div class="partido-proximos-partidos">
			            <h3>Próximos Partidos</h3>
			            <%
			            List<PartidoVO> partidos =  PartidoDAO.obtenerProximosPartidosPorEquipo(jugadorVO.getEquipo());
			            Integer i = 0;
			            	for (PartidoVO partido : partidos) {
				            	if (partido != null && !partidos.isEmpty() && i != 4 ) {
				 					i++;
				 					if (partido != null) {
				            			EquipoVO equipolocal = EquipoDAO.obtenerEquipoPorId(partido.getEquipoLocal());

			            %>
			            <div class="partido-card">
					        <div class="partido-fecha">
					            <span><%= partido.formatFecha() %></span> <!-- Muestra la fecha del partido -->
					        </div>
					        <div class="partido-info">
					            <p><strong><%= EquipoDAO.obtenerEquipoPorId(partido.getEquipoLocal()).getNombreEquipo() %> VS <%= EquipoDAO.obtenerEquipoPorId(partido.getEquipoVisitante()).getNombreEquipo() %></strong></p>
					            <p><%= partido.formatHora() %></p> <!-- Muestra la hora del partido -->
					            <p><%= equipolocal.getUbicacion() %></p> <!-- Muestra la ubicación del partido -->
					        </div>
					        <div class="partido-ubicacion">
					            <img src="https://img.icons8.com/?size=100&id=342&format=png&color=000000" alt="Ubicación Icono">
					            <p>Ubicación</p>
					        </div>
					    </div>
					    <% }}} %>
			        </div>
			    </div>
		    </div>
		   	<div class="datos-jugador">
		        <div class="profile-header">
			        <div class="banner">
			            <img src="<%= request.getContextPath() %>/views/images/banner.png" alt="Banner image">
			        </div>
			        <div class="profile-content">
			            <div class="profile-details">
			                <h1><%= jugadorVO.getNombreJugador() %></h1>
			                <p>@<%= jugadorVO.getNombreUsuario() %></p>
			            </div>
			            <div class="profile-picture">
			                <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Profile picture">
			            </div>
			            <div class="jugadores-seguidores">
			                 <button class="jugador-follow-btn" 
							        data-id="<%= jugadorVO.getNombreUsuario() %>"
							        data-tipo="jugador"
							        data-favorito="<%= JugadorFavDAO.esFavorito(usuario.getNombreUsuario(), jugadorVO.getNombreUsuario()) %>">
							    <i class="fa fa-star<%= JugadorFavDAO.esFavorito(usuario.getNombreUsuario(), jugadorVO.getNombreUsuario()) ? "" : "-o" %>"></i><strong><%= JugadorFavDAO.esFavorito(usuario.getNombreUsuario(), jugadorVO.getNombreUsuario()) ? "Seguido" : "Seguir" %></strong>
							</button>
			                 <button class="jugador-seguidores-btn"><strong><%= JugadorFavDAO.contarSeguidores(jugadorVO.getNombreUsuario()) %> seguidores</strong></button>
			            </div>
			            <div class="team">
			            	<img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Profile picture">
			                <% EquipoVO equipoVO = EquipoDAO.obtenerEquipoPorId(jugadorVO.getEquipo()); %>
			                <h2><%= equipoVO.getNombreEquipo() %></h2>
			            </div>
			        </div>
			    </div>
		        <div class="jugador-stats-container">
		            <div class="jugador-last-game">
		                <h3>Último Partido</h3>
		                <%
		                PartidoVO partidoVO = PartidoDAO.obtenerUltimoPartidoPorJugador(jugadorVO);
									if (partidoVO != null) {
										PtsJugParVO ptsjugparVO = PtsJugParDAO.obtenerPtsJugPar(partidoVO.getIdPartido(), jugadorVO.getNombreUsuario());
										if (ptsjugparVO != null) {
						%>
						<div class="jugador-last-game-info">
			                <div class="jugador-game-info">
			                    <div class="jugador-score">
			                        <span class="jugador-points">PTS</span>
			                        <span class="jugador-score-value"><%= ptsjugparVO.getPtsAnt() %></span>
			                    </div>
			                    <div class="jugador-minutes">
			                        <span>MIN</span>
			                        <span class="jugador-minutes-value"><%= ptsjugparVO.getMntJd() %></span>
			                    </div>
			                </div>
			                <div class="jugador-date-loc">
				                <div class="jugador-date">
				                    <p class="jugador-fecha"><%= partidoVO.formatFecha() %></p>
				                    <p><%= partidoVO.formatHora() %></p>
				                </div>
				                <div class="jugador-loc">
				                    <img src="https://img.icons8.com/?size=100&id=342&format=png&color=000000" alt="Ubicación Icono">
				                    <p>Ubicación</p>
			                    </div>
			                </div>
			                <div class="jugador-location">
			                	
			                    <p><%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getNombreEquipo() %> VS <%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoVisitante()).getNombreEquipo() %></p>
			                    <div class="jugador-scoreboard">
			                    	<img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Profile picture">
			                        <span><%= partidoVO.getPtsC1Local() + partidoVO.getPtsC2Local() + partidoVO.getPtsC3Local() + partidoVO.getPtsC4Local()%></span> - 
			                        <span><%= partidoVO.getPtsC1Visit() + partidoVO.getPtsC2Visit() + partidoVO.getPtsC3Visit() + partidoVO.getPtsC4Visit()%></span>
			                    	<img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Profile picture">
			                    </div>
			                </div>
			                <% }} %>
		                </div>
		            </div>
		            
					<%
					List<PtsJugParVO> historicoJugador = PtsJugParDAO.obtenerHistoricoPorJugador(jugadorVO.getNombreUsuario());
					HistoricoVO historico = PtsJugParDAO.calcularEstadisticasHistorico(historicoJugador);
					%>
					
		            <div class="jugador-historical">
		                <h3>Histórico</h3>
		                <div class="jugador-stats-grid">
		                    <div class="jugador-stat">
		                        <p><strong>P/P</strong></p>
		                        <p><%= historico.getPuntosTotales()/historico.getPartidosJugados() %></p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p><strong>MP</strong></p>
		                        <p><%= historico.getMinutosTotales()/historico.getPartidosJugados() %></p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p><strong>PJ</strong></p>
		                        <p><%= historico.getPartidosJugados() %></p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p><strong>TRP</strong></p>
		                        <p><%= historico.getTriplesTotales() %></p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p><strong>TL</strong></p>
		                        <p><%= historico.getTirosLibresAnotados() %></p>
		                        <p><%= historico.getTirosLibresAnotados()/historico.getTirosLibresTotales()*100 %>%</p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p><strong>F/P</strong></p>
		                        <p><%= historico.getFaltasTotales()/historico.getPartidosJugados() %></p>
		                    </div>
		                </div>
		            </div>
		        </div>
			</div>
		    <div class="anuncio">
		    <img src="<%= request.getContextPath() %>/views/images/anuncio.png" alt="Anuncio Bianco Zavani">
	    	</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</div>
<script>
document.addEventListener('DOMContentLoaded', () => {
    const followButtons = document.querySelectorAll('.jugador-follow-btn');

    followButtons.forEach(button => {
        button.addEventListener('click', () => {
            const favoritoId = button.getAttribute('data-id');
            const esFavorito = button.getAttribute('data-favorito') === 'true';
            const tipoFavorito = button.getAttribute('data-tipo');
            const nombreUsuario = '<%= usuario.getNombreUsuario() %>';

            fetch('<%= request.getContextPath() %>/ActualizarFavoritoServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    id: favoritoId,
                    esFavorito: esFavorito,
                    tipo: tipoFavorito,
                    nombreUsuario: nombreUsuario
                })
            })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(`Error en la red: ${response.status} - ${text}`);
                    });
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    // Cambia el estado visual del botón y el atributo data-favorito
                    button.setAttribute('data-favorito', (!esFavorito).toString());
                    button.querySelector('strong').textContent = !esFavorito ? 'Seguido' : 'Seguir';
                    button.querySelector('.fa').classList.toggle('fa-star');
                    button.querySelector('.fa').classList.toggle('fa-star-o');
                } else {
                    alert(data.message || 'No se pudo actualizar el estado del favorito.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ocurrió un error al intentar actualizar el favorito: ' + error.message);
            });
        });
    });
});
</script>
