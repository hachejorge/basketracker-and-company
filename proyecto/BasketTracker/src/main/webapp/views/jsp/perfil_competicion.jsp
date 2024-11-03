<%@ page import="clasesDAO.*" %>
<%@ page import="clasesVO.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
    
    CompeticionVO competicionVO = (CompeticionVO) session.getAttribute("competicionSeleccionada");

    if (competicionVO == null) {
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
    <title><%=competicionVO.getNombre() %> | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="thecontainer">
	<%@ include file="header-buscador.jsp" %>
	<div class="container-perfil-equipo">
		<div class="recuadro">
		    <div class="navbar">
		        <div class="navbar-item active" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/inicio.jsp'">
		        	<img src="https://img.icons8.com/?size=100&id=131&format=png&color=FFFFFF" alt="Buscar">
		            <span><b>Buscar</b></span>
		        </div>
		        <div class="navbar-item" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/favoritos.jsp'">
		            <img src="https://img.icons8.com/?size=100&id=84925&format=png&color=000000" alt="Favoritos">
		            <span>Favoritos</span>
		        </div>
		        <div class="navbar-item" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/mensajes.jsp'">
		            <img src="https://img.icons8.com/?size=100&id=87193&format=png&color=000000" alt="Mensajes">
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
			            List<PartidoVO> partidos =  PartidoDAO.obtenerPartidosPorCompeticion(competicionVO.getNombre());
			            Integer i = 0;
			            	for (PartidoVO partido : partidos) {
				            	if (partido != null && !partidos.isEmpty() && i != 4 ) {
				 					i++;
				 					if (partido != null) {
				            			EquipoVO equipolocal = EquipoDAO.obtenerEquipoPorId(partido.getEquipoLocal());

			            %>
			            <div class="partido-card" onclick="guardarPartido('<%= partido.getIdPartido() %>')">
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
		    <div class="datos-equipo">
                <div class="competicion-header">
                    <div class="competicion-info">
                        <div class="competicion-logo">
                        	<h1><%= competicionVO.getNombre() %></h1>
                        </div>
                        <button class="competicion-follow-btn" 
						        data-id="<%= competicionVO.getNombre() %>"
						        data-tipo="competicion"
						        data-favorito="<%= CompeticionFavDAO.esFavorito(usuario.getNombreUsuario(), competicionVO.getNombre()) %>">
						    <i class="fa fa-star<%= CompeticionFavDAO.esFavorito(usuario.getNombreUsuario(), competicionVO.getNombre()) ? "" : "-o" %>"></i><strong><%= CompeticionFavDAO.esFavorito(usuario.getNombreUsuario(), competicionVO.getNombre()) ? "Seguido" : "Seguir" %></strong>
						</button>
                    </div>
                </div>
                <div class="equipo-menu">
                    <button class="equipo-jornada-btn active">Jornadas</button>
                    <button class="equipo-clasificacion-btn">Clasificación</button>
                    <button class="equipo-ranking-btn">Rankings</button>
                    <button class="equipo-equipos-btn">Equipos</button>
                </div>
                <div class="equipo-content">
                    <div class="equipo-jornada active"> <!-- Aquí 'active' para mostrar por defecto -->
                    	<div class="jornadas-btns">
                    	<%
                    	List<Integer> jornadas = PartidoDAO.obtenerJornadasPorCompeticion(competicionVO.getNombre());
                    	boolean esPrimera= true;
                    	if (!jornadas.isEmpty()) {
                    		for (Integer j : jornadas) {
                    	%>
                    	
                    		<button class="competicion-jornada-btn <%= esPrimera ? "active" : "" %>" data-id= <%= j %>>Jornada <%=j %></button>
        				                 		
                    	<% esPrimera = false; %>	
                    	<% }} %>
                    	</div>	
                    	<div class="jornadas-contents">   
                    	<%
                    	esPrimera = true;
                    	if (!jornadas.isEmpty()) {
                    		for (Integer j : jornadas) {
                    	%>
                    	
                    		<div class="jornada-content <%= esPrimera ? "active" : "" %>" data-id=<%= j %>>
                    		<% 
                    		List<PartidoVO> partidosJornada = PartidoDAO.obtenerPartidosPorJornadaYCompeticion(competicionVO.getNombre(), j);
                    		if (!partidosJornada.isEmpty()) {
                    			for (PartidoVO partidojornada : partidosJornada) {
                    		%>
                    		<div class="jornada-match-container">
							    <div class="jornada-date-time">
							        <div class="jornada-date"><p><%= partidojornada.formatFecha() %></p></div>
							        <div class="jornada-time"><%= partidojornada.formatHora() %></div>
							    </div>
							    <div class="jornada-match-info">
							        <div class="jornada-score">
							            <img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Escudo <%= EquipoDAO.obtenerEquipoPorId(partidojornada.getEquipoLocal()).getNombreEquipo() %>">
							            <div class="jornada-team-score jornada-home"><%= partidojornada.getPtsC1Local() + partidojornada.getPtsC2Local() + partidojornada.getPtsC3Local() + partidojornada.getPtsC4Local()%></div>
							            <div class="jornada-team-score jornada-away"><%= partidojornada.getPtsC1Visit() + partidojornada.getPtsC2Visit() + partidojornada.getPtsC3Visit() + partidojornada.getPtsC4Visit()%></div>
							            <img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Escudo <%= EquipoDAO.obtenerEquipoPorId(partidojornada.getEquipoVisitante()).getNombreEquipo() %>">
							        </div>
							        <div class="jornada-team-names"><%= EquipoDAO.obtenerEquipoPorId(partidojornada.getEquipoLocal()).getNombreEquipo() %> VS <%= EquipoDAO.obtenerEquipoPorId(partidojornada.getEquipoVisitante()).getNombreEquipo() %></div>
							        <div class="jornada-location">
								    	<img src="https://img.icons8.com/?size=100&id=342&format=png&color=000000" alt="Ubicación Icono">
								        <span>Ubicación</span>							       
							    	</div>
							    	<button onClick="verMasPartido('<%= partidojornada.getIdPartido() %>')" class="arrow">></button>
							    </div>							    
							</div>
                    		<% }} %>
                    		
                    		</div>
                    	
                    	<% esPrimera = false; %>	
                    	<% }} %>
                    	</div>
                    </div>
                    
                    <div class="equipo-clasificacion"> <!-- Aquí 'active' para mostrar por defecto -->
	                    <div class="competicion-clasificacion">			           
							<%
							List<EquipoRankingVO> ranking = PartidoDAO.obtenerRanking(competicionVO.getNombre());
							%>	
			                <div class="info-competicion-favorito">
							 	<table class="competicion-tabla">
						        	<thead>
								        <tr>
								            <th></th>
								            <th>PG</th>
								            <th>PP</th>
								            <th>PF</th>
								            <th>PC</th>
								        </tr>
								    </thead>
						        <% 
						        int rankingSize = ranking.size(); // Obtenemos el tamaño de la lista
						        for (int j = 0; j < rankingSize; j++) { 
						        %>
						        	<tbody>
							            <tr class="favorito-<%= (j == 0 ? "primero" : (j == 1 ? "segundo" : (j == 2 ? "tercero" : "cuarto"))) %>">
							                <td><%= (j + 1) + ".<img src='https://img.icons8.com/?size=100&id=851&format=png&color=000000' alt='Escudo " + EquipoDAO.obtenerEquipoPorId(ranking.get(j).getIdEquipo()).getNombreEquipo() + "' class='favorito-icono'> " + ranking.get(j).getNombre() %></td>
							            	<td><%= ranking.get(j).getPartidosGanados() %></td>
		           							<td><%= ranking.get(j).getPartidosPerdidos() %></td>
		           							<td><%= ranking.get(j).getPuntosAFavor() %></td>
		           							<td><%= ranking.get(j).getPuntosEnContra() %>         
		           						</tr>
	           						</tbody>
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
				        </div>
                    </div>
                    
                    <div class="equipo-ranking"> <!-- Aquí 'active' para mostrar por defecto -->
                    	<div class="section-title">Máximos anotadores</div>
                    	<div class="competicion-ranking-tabla">	        
					         <table>
					             <thead>
					                 <tr>
					                     <th></th>
					                     <th>PJ</th>
					                     <th>PTS</th>
					                     <th>PTS/PJ</th>
					                 </tr>
					             </thead>
					             <tbody>
					             <%
					             List<JugadorVO> jugadoresPTS = JugadorDAO.listarJugadoresPorCompeticion(competicionVO.getNombre());
					           	 List<HistoricoVO> historicosPTS = new ArrayList<>();					           	
					             if (!jugadoresPTS.isEmpty()) {
						           	 for (JugadorVO jugador : jugadoresPTS) {
						            	historicosPTS.add(PtsJugParDAO.calcularEstadisticasHistorico(PtsJugParDAO.obtenerHistoricoPorJugador(jugador.getNombreUsuario())));
						             }
						           	 if (!historicosPTS.isEmpty()) {
							             historicosPTS.sort((h1, h2) -> Integer.compare(h2.getPuntosTotales(), h1.getPuntosTotales()));
							             int j = 0;
							             for (HistoricoVO historico : historicosPTS) {
							            	 if (j >= 10) {
							            		 break;
							            	 }
							            	 j++;
							            	 if (historico.getPartidosJugados() > 0) {
					             %>
					                 <tr class="favorito-<%= (j == 1 ? "primero" : (j == 2 ? "segundo" : (j == 3 ? "tercero" : "cuarto"))) %>">
					                     <td><%=j %>. <%= JugadorDAO.obtenerJugadorPorNombreUsuario(historico.getNombreUsuario()).getNombreJugador() %></td>
					                     <td><%= historico.getPartidosJugados() %></td>
					                     <td><%= historico.getPuntosTotales() %></td>
					                     <td><%= historico.getPuntosTotales()/historico.getPartidosJugados()%></td>
					                 </tr>
					             <% }}}} %>
					             </tbody>
					       	</table>
					       	</div>

                    	<div class="section-title">Máximos triplistas</div>
                    	<div class="competicion-ranking-tabla">	        
					         <table>
					             <thead>
					                 <tr>
					                     <th></th>
					                     <th>PJ</th>
					                     <th>TRP</th>
					                     <th>TRP/PJ</th>
					                 </tr>
					             </thead>
					             <tbody>
					             <%
					             List<JugadorVO> jugadoresTRP = JugadorDAO.listarJugadoresPorCompeticion(competicionVO.getNombre());
					           	 List<HistoricoVO> historicosTRP = new ArrayList<>();					           	
					             if (!jugadoresTRP.isEmpty()) {
						           	 for (JugadorVO jugador : jugadoresTRP) {
						            	historicosTRP.add(PtsJugParDAO.calcularEstadisticasHistorico(PtsJugParDAO.obtenerHistoricoPorJugador(jugador.getNombreUsuario())));
						             }
						           	 if (!historicosTRP.isEmpty()) {
							             historicosTRP.sort((h1, h2) -> Integer.compare(h2.getTriplesTotales(), h1.getTirosLibresTotales()));
							             int j = 0;
							             for (HistoricoVO historico : historicosTRP) {
							            	 if (j >= 10) {
							            		 break;
							            	 }
							            	 j++;
							            	 if (historico.getPartidosJugados() > 0) {
					             %>
					                 <tr class="favorito-<%= (j == 1 ? "primero" : (j == 2 ? "segundo" : (j == 3 ? "tercero" : "cuarto"))) %>">
					                     <td><%=j %>. <%= JugadorDAO.obtenerJugadorPorNombreUsuario(historico.getNombreUsuario()).getNombreJugador() %></td>
					                     <td><%= historico.getPartidosJugados() %></td>
					                     <td><%= historico.getTriplesTotales() %></td>
					                     <td><%= historico.getTriplesTotales()/historico.getPartidosJugados()%></td>
					                 </tr>
					             <% }}}} %>
					             </tbody>
					       	</table>
					       	</div>
					       	
                    	<div class="section-title">Máximos tiros libres anotados</div>
                    	<div class="competicion-ranking-tabla">	        
					         <table>
					             <thead>
					                 <tr>
					                     <th></th>
					                     <th>PJ</th>
					                     <th>TL</th>
					                     <th>TL/PJ</th>
					                 </tr>
					             </thead>
					             <tbody>
					             <%
					             List<JugadorVO> jugadoresTL = JugadorDAO.listarJugadoresPorCompeticion(competicionVO.getNombre());
					           	 List<HistoricoVO> historicosTL = new ArrayList<>();					           	
					             if (!jugadoresTL.isEmpty()) {
						           	 for (JugadorVO jugador : jugadoresTL) {
						            	historicosTL.add(PtsJugParDAO.calcularEstadisticasHistorico(PtsJugParDAO.obtenerHistoricoPorJugador(jugador.getNombreUsuario())));
						             }
						           	 if (!historicosTL.isEmpty()) {
							             historicosTL.sort((h1, h2) -> Integer.compare(h2.getTirosLibresAnotados(), h1.getTirosLibresAnotados()));
							             int j = 0;
							             for (HistoricoVO historico : historicosTL) {
							            	 if (j >= 10) {
							            		 break;
							            	 }
							            	 j++;
							            	 if (historico.getPartidosJugados() > 0) {
					             %>
					                 <tr class="favorito-<%= (j == 1 ? "primero" : (j == 2 ? "segundo" : (j == 3 ? "tercero" : "cuarto"))) %>">
					                     <td><%=j %>. <%= JugadorDAO.obtenerJugadorPorNombreUsuario(historico.getNombreUsuario()).getNombreJugador() %></td>
					                     <td><%= historico.getPartidosJugados() %></td>
					                     <td><%= historico.getTirosLibresAnotados() %></td>
					                     <td><%= historico.getTirosLibresAnotados()/historico.getPartidosJugados()%></td>
					                 </tr>
					             <% }}}} %>
					             </tbody>
					       	</table>
					       	</div>					       						       	
                    	</div>
                    <div class="equipo-equipos"> <!-- Aquí 'active' para mostrar por defecto -->
                    	<div class="competicion-equipos">
                    		<%
                    		List<EquipoVO> equipos = EquipoDAO.obtenerEquiposPorCompeticion(competicionVO.getNombre());
                    		for (EquipoVO equipo : equipos) {
                    		%>
						    <div class="competicion-team">
						      <img src="https://img.icons8.com/?size=100&id=vy6OvJYHSJ8I&format=png&color=000000" alt="Logo <%= equipo.getCompeticion() %>">
						      <span class="name"><%= equipo.getNombreEquipo() %></span>
						      <button onclick="verMasEquipo('<%= equipo.getIdEquipo() %>')" class="arrow">></button>
						    </div>
						    <% } %>
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
document.addEventListener('DOMContentLoaded', function() {
    // Mostrar "Equipo" y ocultar "Calendario" al hacer clic en el botón de equipo
    document.querySelector('.equipo-jornada-btn').addEventListener('click', function() {
        document.querySelector('.equipo-jornada').classList.add('active');
        document.querySelector('.equipo-jornada-btn').classList.add('active');
        document.querySelector('.equipo-clasificacion').classList.remove('active');
        document.querySelector('.equipo-clasificacion-btn').classList.remove('active');
        document.querySelector('.equipo-ranking').classList.remove('active');
        document.querySelector('.equipo-ranking-btn').classList.remove('active');
        document.querySelector('.equipo-equipos').classList.remove('active');
        document.querySelector('.equipo-equipos-btn').classList.remove('active');
    });

    // Mostrar "Calendario" y ocultar "Equipo" al hacer clic en el botón de calendario
    document.querySelector('.equipo-clasificacion-btn').addEventListener('click', function() {
        document.querySelector('.equipo-jornada').classList.remove('active');
        document.querySelector('.equipo-jornada-btn').classList.remove('active');
        document.querySelector('.equipo-clasificacion').classList.add('active');
        document.querySelector('.equipo-clasificacion-btn').classList.add('active');
        document.querySelector('.equipo-ranking').classList.remove('active');
        document.querySelector('.equipo-ranking-btn').classList.remove('active');
        document.querySelector('.equipo-equipos').classList.remove('active');
        document.querySelector('.equipo-equipos-btn').classList.remove('active');
    });

    // Mostrar "Calendario" y ocultar "Equipo" al hacer clic en el botón de calendario
    document.querySelector('.equipo-ranking-btn').addEventListener('click', function() {
        document.querySelector('.equipo-jornada').classList.remove('active');
        document.querySelector('.equipo-jornada-btn').classList.remove('active');
        document.querySelector('.equipo-clasificacion').classList.remove('active');
        document.querySelector('.equipo-clasificacion-btn').classList.remove('active');
        document.querySelector('.equipo-ranking').classList.add('active');
        document.querySelector('.equipo-ranking-btn').classList.add('active');
        document.querySelector('.equipo-equipos').classList.remove('active');
        document.querySelector('.equipo-equipos-btn').classList.remove('active');
    });
    
    // Mostrar "Calendario" y ocultar "Equipo" al hacer clic en el botón de calendario
    document.querySelector('.equipo-equipos-btn').addEventListener('click', function() {
        document.querySelector('.equipo-jornada').classList.remove('active');
        document.querySelector('.equipo-jornada-btn').classList.remove('active');
        document.querySelector('.equipo-clasificacion').classList.remove('active');
        document.querySelector('.equipo-clasificacion-btn').classList.remove('active');
        document.querySelector('.equipo-ranking').classList.remove('active');
        document.querySelector('.equipo-ranking-btn').classList.remove('active');
        document.querySelector('.equipo-equipos').classList.add('active');
        document.querySelector('.equipo-equipos-btn').classList.add('active');
    });
    
});

document.addEventListener('DOMContentLoaded', function() {
    const jornadaButtons = document.querySelectorAll('.competicion-jornada-btn');
    const jornadaContents = document.querySelectorAll('.jornada-content');

    jornadaButtons.forEach(button => {
        button.addEventListener('click', () => {
            const jornadaId = button.getAttribute('data-id');

            // Remove 'active' class from all buttons and contents
            jornadaButtons.forEach(btn => btn.classList.remove('active'));
            jornadaContents.forEach(content => content.classList.remove('active'));

            // Add 'active' class to the clicked button and corresponding content
            button.classList.add('active');
            const contentElement = document.querySelector('.jornada-content[data-id="' + jornadaId + '"]');
            if (contentElement) {
                contentElement.classList.add('active');
            }
        });
    });
});


document.addEventListener('DOMContentLoaded', () => {
	 const followButtons = document.querySelectorAll('.competicion-follow-btn');

	 followButtons.forEach(button => {
        button.addEventListener('click', () => {
            const favoritoId = button.getAttribute('data-id');
            const esFavorito = button.getAttribute('data-favorito') === 'true';
            const tipoFavorito = button.getAttribute('data-tipo');
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

function verMasPartido(idPartido) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/GuardarPartidoServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Redirigir a la página del perfil del equipo
                window.location.href = "<%= request.getContextPath() %>/views/jsp/perfil_partido.jsp";
            } else {
                alert("Error al guardar el partido.");
            }
        }
    };
    xhr.send("idPartido=" + encodeURIComponent(idPartido));
}

function guardarPartido(idPartido) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/GuardarPartidoServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Redirigir a la página del perfil del partido
                window.location.href = "<%= request.getContextPath() %>/views/jsp/perfil_partido.jsp";
            } else {
                alert("Error al guardar el partido.");
            }
        }
    };
    xhr.send("idPartido=" + encodeURIComponent(idPartido));
}
</script>
</body>
</html>
