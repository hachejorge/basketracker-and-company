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

    JugadorFavDAO jugadorFavDAO = new JugadorFavDAO();
    List<JugadorFavVO> jugadoresFavoritos = jugadorFavDAO.obtenerJugadoresFavPorUsuario(usuario.getNombreUsuario());
    
    EquipoFavDAO equipoFavDAO = new EquipoFavDAO();
    List<EquipoFavVO> equiposFavoritos = equipoFavDAO.obtenerEquiposFavPorUsuario(usuario.getNombreUsuario());
    
    CompeticionFavDAO competicionFavDAO = new CompeticionFavDAO();
    List<CompeticionFavVO> competicionesFavoritos = competicionFavDAO.obtenerCompeticionesFavPorUsuario(usuario.getNombreUsuario());
    
    
    
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Inicio | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="thecontainer">
	<%@ include file="header-buscador.jsp" %>
	<div class="container-favorito">
		<div class="recuadro">
		    <div class="navbar">
		        <div class="navbar-item" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/inicio.jsp'">
		        	<img src="https://img.icons8.com/?size=100&id=131&format=png&color=000000" alt="Jugadores">
		            <span>Buscar</span>
		        </div>
		        <div class="navbar-item active" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/favoritos.jsp'">
		            <img src="https://img.icons8.com/?size=100&id=84925&format=png&color=FFFFFF" alt="Jugadores">
		            <span><b>Favoritos</b></span>
		        </div>
		        <div class="navbar-item" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/mensajes.jsp'">
		            <img src="https://img.icons8.com/?size=100&id=87193&format=png&color=000000" alt="Equipos">
		            <span>Mensajes</span>
		        </div>
		    </div>
	    </div>

		<div class="scroll-container-favorito">
	    	<div class="info-favorito">
				<div class="info-item-favorito">
					<% for (JugadorFavVO jugador : jugadoresFavoritos) { %>
						<div class="jugadores-favorito">
					        
					        <%
								JugadorVO jugadorVO = JugadorDAO.obtenerJugadorPorNombreUsuario(jugador.getJugador());
								if (jugadorVO != null) {
					        		EquipoVO equipoVO = EquipoDAO.obtenerEquipoPorId(jugadorVO.getEquipo());
									if (equipoVO != null) {
					 
					        %>
					        <div class="player-info-favorito">
					            <div class="avatar-favorito">
					                <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Perfil">
					            </div>
					            <div class="details-favorito">
					                <p><strong><%= jugadorVO.getNombreJugador() %></strong>
					                <br><%= equipoVO.getNombreEquipo() %>
					                <br><%= equipoVO.getCompeticion() %></p>
					            </div>
					        </div>
					        <div class="estrella-favorito">
			                    <img src="https://img.icons8.com/?size=100&id=<%= JugadorFavDAO.esFavorito(usuario.getNombreUsuario(), jugadorVO.getNombreUsuario()) ? "19416" : "85784" %>&format=png&color=000000" 
		                         alt="Favorito" 
		                         class="icono-favorito" 
		                         data-id="<%= jugadorVO.getNombreUsuario() %>" 
		                         data-tipo="jugador" 
		                         data-favorito="<%= JugadorFavDAO.esFavorito(usuario.getNombreUsuario(), jugadorVO.getNombreUsuario()) %>">
					 		</div>
					 		<div class="stats-favorito">
					 			<%
									PartidoVO partidoVO = PartidoDAO.obtenerUltimoPartidoPorJugador(jugadorVO);
									if (partidoVO != null) {
										PtsJugParVO ptsjugparVO = PtsJugParDAO.obtenerPtsJugPar(partidoVO.getIdPartido(), jugadorVO.getNombreUsuario());
										if (ptsjugparVO != null) {
						 
							        %>
					            <div class="section-favorito last-game-favorito">
					            
						            
					                <label>Último partido</label>
					                <div class="info-jugador-favorito">
						                <p class="stat-favorito">PT <span><%= ptsjugparVO.getPtsAnt() %></span></p>
						                <p class="stat-favorito">MIN <span><%= ptsjugparVO.getMntJd() %></span></p>
					                </div>
					                <button onclick="guardarPartido('<%= partidoVO.getIdPartido() %>')">Ver más</button>
					                
					            </div>
					            <div class="section-favorito history-favorito">
					            	<%
									List<PtsJugParVO> historicoJugador = PtsJugParDAO.obtenerHistoricoPorJugador(jugadorVO.getNombreUsuario());

									// Calcular y obtener las estadísticas
									HistoricoVO historico = PtsJugParDAO.calcularEstadisticasHistorico(historicoJugador);						 
							        %>
					                <label>Histórico</label>
					                <div class="info-jugador-favorito">
						                <p class="stat-favorito">P/P <span><%= historico.getPuntosTotales()/historico.getPartidosJugados() %></span></p>
						                <p class="stat-favorito">M/P <span><%= historico.getMinutosTotales()/historico.getPartidosJugados() %></span></p>
						                <p class="stat-favorito">PJ <span><%= historico.getPartidosJugados() %></span></p>
					                </div>
					                <button onclick="verMas('<%= jugadorVO.getNombreUsuario() %>')">Ver más</button>
					            </div>
					            <% }} %>
					        </div>
					        <% }} %>
		                </div>
					<% } %>
				</div>
	
				<div class="info-item-favorito">
					<% for (EquipoFavVO equipo : equiposFavoritos) { %>
						<div class="equipos-favorito">
							<% 
					            // Obtener información del jugador a partir del nombre de usuario
					            EquipoVO equipoVO = EquipoDAO.obtenerEquipoPorId(equipo.getEquipo());
								if (equipoVO != null) {
					        %>
					        <div class="player-info-favorito">
					        	<div class="avatar-favorito">
					        		<img src="https://img.icons8.com/?size=100&id=vy6OvJYHSJ8I&format=png&color=000000" alt="Logo Equipo">
						    	</div>
						    	<div class="details-favorito">
						    		<p><strong><%= equipoVO.getNombreEquipo() %></strong><br><%= equipoVO.getCompeticion() %></p>
						    	</div>
							</div>
							<div class="estrella-favorito">
							    <img src="https://img.icons8.com/?size=100&id=<%= EquipoFavDAO.esFavorito(usuario.getNombreUsuario(), equipoVO.getIdEquipo()) ? "19416" : "85784" %>&format=png&color=000000" 
		                         alt="Favorito"
		                         class="icono-favorito" 
		                         data-id="<%= equipoVO.getIdEquipo() %>" 
		                         data-tipo="equipo" 
		                         data-favorito="<%= EquipoFavDAO.esFavorito(usuario.getNombreUsuario(), equipoVO.getIdEquipo()) %>">			
							</div>
							<div class="stats-favorito">
								<div class="section2-favorito equipo-datos-favorito">	
									<%
									List<EquipoRankingVO> ranking = PartidoDAO.obtenerRanking(equipoVO.getCompeticion());
									
									EquipoRankingVO equipoEnRanking = null;
									int pos = 0;
								    // Buscar el equipo en el ranking según su id
								    for (EquipoRankingVO equipoRanking : ranking) {
								    	pos++;
								        if (equipoRanking.getIdEquipo() == equipoVO.getIdEquipo()) {
								            equipoEnRanking = equipoRanking;
								            break;
								        }
								    }
								    
								   	if (equipoEnRanking != null) {
									
									%>							
					                <label>Clasificación</label>
					                <div class="info-jugador-favorito">
						                <p class="stat-favorito">Posición <span><%= pos %></span></p>
						                <p class="stat-favorito">PG <span><%= equipoEnRanking.getPartidosGanados() %></span></p>
						                <p class="stat-favorito">PP <span><%= equipoEnRanking.getPartidosPerdidos() %></span></p>
					                </div>
					                <button onclick="verMasEquipo('<%= equipoVO.getIdEquipo() %>')">Ver más</button>
					                <% } %>
				                </div>	
				            </div>
						</div>
					<% }} %>
				</div>
	
				<div class="info-item-favorito">
					<% for (CompeticionFavVO competicion : competicionesFavoritos) { %>
						<div class="competiciones-favorito">
							<div class="player-info-favorito">
								<div class="avatar-favortio">
									<img src="https://img.icons8.com/?size=100&id=6YtrB5VnlPqY&format=png&color=000000" alt="Logo Competición">
								</div>
					        	<div class="details-favorito">
						    		<p><strong><%= competicion.getCompeticion() %></strong></p>
						    	</div>
						    </div>
						    <div class="estrella-favorito">
		                        <img src="https://img.icons8.com/?size=100&id=<%= CompeticionFavDAO.esFavorito(usuario.getNombreUsuario(), competicion.getCompeticion()) ? "19416" : "85784" %>&format=png&color=000000" 
		                         alt="Favorito" 
		                         class="icono-favorito" 
		                         data-id="<%= competicion.getCompeticion() %>" 
		                         data-tipo="competicion" 
		                         data-favorito="<%= CompeticionFavDAO.esFavorito(usuario.getNombreUsuario(), competicion.getCompeticion()) %>">
							</div>
							<div class="stats-favorito">
								<div class="section3-favorito equipo-datos-favorito">
									<%
									List<EquipoRankingVO> ranking = PartidoDAO.obtenerRanking(competicion.getCompeticion());
									%>	
					                <label>Clasificación</label>
					                <div class="info-competicion-favorito">
									 	<table class="favorito-tabla">
								        
								        <% 
								        int rankingSize = ranking.size(); // Obtenemos el tamaño de la lista
								        for (int i = 0; i < rankingSize && i < 4; i++) { 
								        %>
								            <tr class="favorito-<%= (i == 0 ? "primero" : (i == 1 ? "segundo" : (i == 2 ? "tercero" : "cuarto"))) %>">
								                <td><%= (i + 1) + ".<img src='https://img.icons8.com/?size=100&id=851&format=png&color=000000' alt='icono' class='favorito-icono'> " + ranking.get(i).getNombre() %></td>
								            </tr>
								        <% 
								        } 
								        // Si no hay equipos en el ranking, podemos mostrar un mensaje
								        if (rankingSize == 0) {
								        %>
								            <tr>
								                <td>No hay equipos en la clasificación.</td>
								            </tr>
								        <% 
								        }
								        %>
									       
									    </table>						     
					                </div>
					                <button onclick="verMasCompeticion('<%= competicion.getCompeticion() %>')">Ver más</button>
					            </div>
				            </div>
						</div>
					<% } %>
				</div>
		    </div>
	    </div>
	</div>
	<%@ include file="footer.jsp" %>
</div>

<script>
document.addEventListener('DOMContentLoaded', () => {
    const favoritos = document.querySelectorAll('.icono-favorito');

    favoritos.forEach(icono => {
        icono.addEventListener('click', () => {
            const favoritoId = icono.getAttribute('data-id');
            const esFavorito = icono.getAttribute('data-favorito') === 'true';
            const tipoFavorito = icono.getAttribute('data-tipo');
            const nombreUsuario = '<%= usuario.getNombreUsuario() %>'; // Asegúrate de que esto esté definido

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
                console.log(data);
                if (data.success) {
                    icono.setAttribute('data-favorito', (!esFavorito).toString());
                    icono.src = !esFavorito
                        ? 'https://img.icons8.com/?size=100&id=19416&format=png&color=000000'
                        : 'https://img.icons8.com/?size=100&id=85784&format=png&color=000000';
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

function verMas(nombreJugador) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/GuardarJugadorServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Redirigir a la página del perfil del jugador
                window.location.href = "<%= request.getContextPath() %>/views/jsp/perfil_jugador.jsp";
            } else {
                alert("Error al guardar el jugador.");
            }
        }
    };
    xhr.send("nombreJugador=" + encodeURIComponent(nombreJugador));
}

function verMasEquipo(idEquipo) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/GuardarEquipoServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Redirigir a la página del perfil del equipo
                window.location.href = "<%= request.getContextPath() %>/views/jsp/perfil_equipo.jsp";
            } else {
                alert("Error al guardar el equipo.");
            }
        }
    };
    xhr.send("idEquipo=" + encodeURIComponent(idEquipo));
}

function verMasCompeticion(nombreCompeticion) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/GuardarCompeticionServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Redirigir a la página del perfil de la competición
                window.location.href = "<%= request.getContextPath() %>/views/jsp/perfil_competicion.jsp";
            } else {
                alert("Error al guardar la competición.");
            }
        }
    };
    xhr.send("nombreCompeticion=" + encodeURIComponent(nombreCompeticion));
}

function guardarPartido(idPartido) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/GuardarPartidoServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Redirigir a la página del perfil del partido
                window.location.href = "<%= request.getContextPath() %>/views/jsp/partido.jsp";
            } else {
                alert("Error al guardar el partido.");
            }
        }
    };
    xhr.send("idPartido=" + encodeURIComponent(idPartido));
}

</script>
