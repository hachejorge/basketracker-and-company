<%@ page import="clasesDAO.*" %>
<%@ page import="clasesVO.*" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>

<%
    // Recuperar el objeto UsuarioVO de la sesi�n
    UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuario");
    // Comprobar si el usuario est� autenticado
    if (usuario == null) {
        // Redirigir a la p�gina de login si no hay un usuario autenticado
        response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp");
        return;
    }
    
    EquipoVO equipoVO = (EquipoVO) session.getAttribute("equipoSeleccionado");

    if (equipoVO == null) {
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
<body>
<div class="thecontainer">
	<%@ include file="header-buscador.jsp" %>
	<div class="container-perfil-equipo">
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
			            <h3>Pr�ximos Partidos</h3>
			            <%
			            List<PartidoVO> partidos =  PartidoDAO.obtenerProximosPartidosPorEquipo(equipoVO.getIdEquipo());
			            Integer i = 0;
			            	for (PartidoVO partido : partidos) {
				            	if (partido != null && !partidos.isEmpty() && i != 3 ) {
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
					            <p><%= equipolocal.getUbicacion() %></p> <!-- Muestra la ubicaci�n del partido -->
					        </div>
					        <div class="partido-ubicacion">
					            <img src="https://img.icons8.com/?size=100&id=342&format=png&color=000000" alt="Ubicaci�n Icono">
					            <p>Ubicaci�n</p>
					        </div>
					    </div>
					    <% }}} %>
			        </div>
			
			        <div class="partido-clasificacion">			           
						<%
						List<EquipoRankingVO> ranking = PartidoDAO.obtenerRanking(equipoVO.getCompeticion());
						%>	
		                <h3>Clasificaci�n</h3>
		                <div class="info-competicion-favorito">
						 	<table class="favorito-tabla">
					        	<thead>
							        <tr>
							            <th></th>
							            <th>PG</th>
							            <th>PP</th>
							        </tr>
							    </thead>
					        <% 
					        int rankingSize = ranking.size(); // Obtenemos el tama�o de la lista
					        for (int j = 0; j < rankingSize && j < 4; j++) { 
					        %>
					            <tr class="favorito-<%= (j == 0 ? "primero" : (j == 1 ? "segundo" : (j == 2 ? "tercero" : "cuarto"))) %>">
					                <td><%= (j + 1) + ".<img src='https://img.icons8.com/?size=100&id=851&format=png&color=000000' alt='icono' class='favorito-icono'> " + ranking.get(j).getNombre() %></td>
					            	<td><%= ranking.get(j).getPartidosGanados() %></td>
           							<td><%= ranking.get(j).getPartidosPerdidos() %></td>
					            </tr>
					        <% 
					        } 
					        // Si no hay equipos en el ranking, podemos mostrar un mensaje
					        if (rankingSize == 0) {
					        %>
					            <tr>
					                <td>No hay equipos en la clasificaci�n.</td>
					            </tr>
					        <% 
					        }
					        %>
						    </table>						     
		                </div>
			        </div>
			    </div>
		    </div>
		    <div class="datos-equipo">
                <div class="equipo-header">
                    <div class="equipo-background-img">
                        <img src="<%= request.getContextPath() %>/views/images/banner.png" alt="Background image">
                    </div>
                    <div class="equipo-info">
                        <div class="equipo-logo">
                            <div class="equipo-logo2">
                                <img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Team Logo">
                            </div>
                            <div class="equipo-info">
                                <h1><%= equipoVO.getNombreEquipo() %></h1>
                                <p><%= equipoVO.getCompeticion() %></p>
                            </div>
                        </div>
                        <button class="equipo-follow-btn" 
						        data-id="<%= equipoVO.getIdEquipo() %>"
						        data-tipo="equipo"
						        data-favorito="<%= EquipoFavDAO.esFavorito(usuario.getNombreUsuario(), equipoVO.getIdEquipo()) %>">
						    <i class="fa fa-star<%= EquipoFavDAO.esFavorito(usuario.getNombreUsuario(), equipoVO.getIdEquipo()) ? "" : "-o" %>"></i><strong><%= EquipoFavDAO.esFavorito(usuario.getNombreUsuario(), equipoVO.getIdEquipo()) ? "Seguido" : "Seguir" %></strong>
						</button>
                    </div>
                </div>
                <div class="equipo-menu">
                    <button class="equipo-team-btn active">Equipo</button>
                    <button class="equipo-calendar-btn">Calendario</button>
                </div>
                <div class="equipo-content">
                    <div class="equipo-team active"> <!-- Aqu� 'active' para mostrar por defecto -->
                        <div class="equipo-container">
						    <div class="jugadores-section">
						    	<% List<JugadorVO> plantilla = EquipoDAO.obtenerEquipo(equipoVO.getIdEquipo()); %>
						        <h2>Jugadores</h2>
						        <div class="jugadores-grid">
							        <% 
							            for (JugadorVO jugador : plantilla) { 
							        %>
							            <div class="jugador-card">
							                <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Jugador Icono">
							                <p><%= jugador.getNombreJugador() %></p>
							            </div>
							        <% 
							            } 
							        %>
							    </div>
							</div>
						</div>
                    </div>
                    <div class="equipo-calendar">
					   <div class="calendario-calendar">
					       <div class="calendario-header">
					           <button id="prevMonth" class="calendario-button">&lt;</button>
					           <h1 id="monthYear" class="calendario-month-year"></h1>
					           <button id="nextMonth" class="calendario-button">&gt;</button>
					       </div>
					       <div class="calendario-days">
					           <div class="calendario-day">Dom</div>
					           <div class="calendario-day">Lun</div>
					           <div class="calendario-day">Mar</div>
					           <div class="calendario-day">Mi�</div>
					           <div class="calendario-day">Jue</div>
					           <div class="calendario-day">Vie</div>
					           <div class="calendario-day">S�b</div>
					       </div>
					       <div class="calendario-dates" id="dateContainer"></div>
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
    // Mostrar "Equipo" y ocultar "Calendario" al hacer clic en el bot�n de equipo
    document.querySelector('.equipo-team-btn').addEventListener('click', function() {
        document.querySelector('.equipo-team').classList.add('active');
        document.querySelector('.equipo-calendar').classList.remove('active');
        document.querySelector('.equipo-team-btn').classList.add('active');
        document.querySelector('.equipo-calendar-btn').classList.remove('active');
    });

    // Mostrar "Calendario" y ocultar "Equipo" al hacer clic en el bot�n de calendario
    document.querySelector('.equipo-calendar-btn').addEventListener('click', function() {
        document.querySelector('.equipo-team').classList.remove('active');
        document.querySelector('.equipo-calendar').classList.add('active');
        document.querySelector('.equipo-team-btn').classList.remove('active');
        document.querySelector('.equipo-calendar-btn').classList.add('active');
    });
});

document.addEventListener('DOMContentLoaded', () => {
	 const followButtons = document.querySelectorAll('.equipo-follow-btn');

	 followButtons.forEach(button => {
        button.addEventListener('click', () => {
            const favoritoId = button.getAttribute('data-id');
            const esFavorito = button.getAttribute('data-favorito') === 'true';
            const tipoFavorito = button.getAttribute('data-tipo');
            const nombreUsuario = '<%= usuario.getNombreUsuario() %>'; // Aseg�rate de que esto est� definido

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
                    // Cambia el estado visual del bot�n y el atributo data-favorito
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
                alert('Ocurri� un error al intentar actualizar el favorito: ' + error.message);
            });
        });
    });
});

const monthYear = document.getElementById("monthYear");
const dateContainer = document.getElementById("dateContainer");
const prevMonthBtn = document.getElementById("prevMonth");
const nextMonthBtn = document.getElementById("nextMonth");

let currentDate = new Date();

function renderCalendar(date) {
    dateContainer.innerHTML = "";
    const year = date.getFullYear();
    const month = date.getMonth();

    monthYear.textContent = date.toLocaleString('default', { month: 'long', year: 'numeric' });

    const firstDay = new Date(year, month, 1).getDay();
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    for (let i = 0; i < firstDay; i++) {
        const emptyDiv = document.createElement("div");
        emptyDiv.classList.add("calendario-date", "empty");
        dateContainer.appendChild(emptyDiv);
    }

    for (let day = 1; day <= daysInMonth; day++) {
        const dayDiv = document.createElement("div");
        dayDiv.classList.add("calendario-date");
        dayDiv.textContent = day;
        dateContainer.appendChild(dayDiv);
    }
}

prevMonthBtn.addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    renderCalendar(currentDate);
});

nextMonthBtn.addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    renderCalendar(currentDate);
});

// Renderizar el calendario por primera vez
renderCalendar(currentDate);
</script>
</body>
</html>
