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
    <title><%= equipoVO.getNombreEquipo() %> | Basketracker</title>
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
			            List<PartidoVO> partidos =  PartidoDAO.obtenerProximosPartidosPorEquipo(equipoVO.getIdEquipo());
			            Integer i = 0;
			            	for (PartidoVO partido : partidos) {
				            	if (partido != null && !partidos.isEmpty() && i != 3 ) {
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
			
			        <div class="partido-clasificacion">			           
						<%
						List<EquipoRankingVO> ranking = PartidoDAO.obtenerRanking(equipoVO.getCompeticion());
						%>	
		                <h3>Clasificación</h3>
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
					        int rankingSize = ranking.size(); // Obtenemos el tamaño de la lista
					        for (int j = 0; j < rankingSize && j < 4; j++) { 
					        %>
					            <tr class="favorito-<%= (j == 0 ? "primero" : (j == 1 ? "segundo" : (j == 2 ? "tercero" : "cuarto"))) %>">
					                <td><%= (j + 1) + ".<img src='https://img.icons8.com/?size=100&id=851&format=png&color=000000' alt='Escudo " + EquipoDAO.obtenerEquipoPorId(ranking.get(j).getIdEquipo()).getNombreEquipo() + "' class='favorito-icono'> " + ranking.get(j).getNombre() %></td>
					            	<td><%= ranking.get(j).getPartidosGanados() %></td>
           							<td><%= ranking.get(j).getPartidosPerdidos() %></td>
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
			        </div>
			    </div>
		    </div>
		    <div class="datos-equipo">
                <div class="equipo-header">
                    <div class="equipo-background-img">
                        <img src="<%= request.getContextPath() %>/views/images/banner.png" alt="Banner Image">
                    </div>
                    <div class="equipo-info">
                        <div class="equipo-logo">
                            <div class="equipo-logo2">
                                <img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Escudo <%= equipoVO.getNombreEquipo()%>">
                            </div>
                            <div class="equipo-info">
                                <h1><%= equipoVO.getNombreEquipo() %></h1>
                                <p onclick="verMasCompeticion('<%= equipoVO.getCompeticion() %>')"><%= equipoVO.getCompeticion() %></p>
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
                    <div class="equipo-team active"> <!-- Aquí 'active' para mostrar por defecto -->
                        <div class="equipo-container">
						    <div class="jugadores-section">
						    	<% List<JugadorVO> plantilla = EquipoDAO.obtenerEquipo(equipoVO.getIdEquipo()); %>
						        <h2>Jugadores</h2>
						        <div class="jugadores-grid">
							        <% 
							            for (JugadorVO jugador : plantilla) { 
							        %>
							            <div class="jugador-card" onclick="verMas('<%= jugador.getNombreUsuario() %>')">
							                <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Profile Picture <%=jugador.getNombreJugador() %>">
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
					           <div class="calendario-day">Mié</div>
					           <div class="calendario-day">Jue</div>
					           <div class="calendario-day">Vie</div>
					           <div class="calendario-day">Sáb</div>
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

document.addEventListener('DOMContentLoaded', function() {
    // Mostrar "Equipo" y ocultar "Calendario" al hacer clic en el botón de equipo
    document.querySelector('.equipo-team-btn').addEventListener('click', function() {
        document.querySelector('.equipo-team').classList.add('active');
        document.querySelector('.equipo-calendar').classList.remove('active');
        document.querySelector('.equipo-team-btn').classList.add('active');
        document.querySelector('.equipo-calendar-btn').classList.remove('active');
    });

    // Mostrar "Calendario" y ocultar "Equipo" al hacer clic en el botón de calendario
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

const monthYear = document.getElementById("monthYear");
const dateContainer = document.getElementById("dateContainer");
const prevMonthBtn = document.getElementById("prevMonth");
const nextMonthBtn = document.getElementById("nextMonth");

let currentDate = new Date();

const partidos = [
    <% 
        List<PartidoVO> partidosEquipo = PartidoDAO.obtenerPartidosPorEquipo(equipoVO.getIdEquipo());
        for (PartidoVO partido : partidosEquipo) {
            String fecha = partido.getFecha().toString(); // "yyyy-MM-dd"
            String hora = partido.getHora().toString();   // "HH:mm:ss"
    %>
    {
        idPartido: <%= partido.getIdPartido() %>,
        equipoLocal: <%= partido.getEquipoLocal() %>,
        equipoVisitante: <%= partido.getEquipoVisitante() %>,
        jornada: <%= partido.getJornada() %>,
        ptsC1Local: <%= partido.getPtsC1Local() %>,
        ptsC2Local: <%= partido.getPtsC2Local() %>,
        ptsC3Local: <%= partido.getPtsC3Local() %>,
        ptsC4Local: <%= partido.getPtsC4Local() %>,
        ptsC1Visit: <%= partido.getPtsC1Visit() %>,
        ptsC2Visit: <%= partido.getPtsC2Visit() %>,
        ptsC3Visit: <%= partido.getPtsC3Visit() %>,
        ptsC4Visit: <%= partido.getPtsC4Visit() %>,
        fecha: new Date("<%= fecha %>"),
        hora: "<%= hora.substring(0, 5) %>" // Solo hh:mm
    },
    <% } %>
];

function renderCalendar(date, partidos) {
    dateContainer.innerHTML = "";
    const year = date.getFullYear();
    const month = date.getMonth();

    monthYear.textContent = date.toLocaleString('default', { month: 'long', year: 'numeric' });

    const firstDay = new Date(year, month, 1).getDay();
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    // Crear espacios vacíos antes del primer día
    for (let i = 0; i < firstDay; i++) {
        const emptyDiv = document.createElement("div");
        emptyDiv.classList.add("calendario-date", "empty");
        dateContainer.appendChild(emptyDiv);
    }

    // Agregar los días del mes
    for (let day = 1; day <= daysInMonth; day++) {
        const dayDiv = document.createElement("div");
        dayDiv.classList.add("calendario-date");
        dayDiv.textContent = day;

        const currentDate = new Date(year, month, day);
        const partidosEnDia = partidos.filter(partido => 
            partido.fecha.getFullYear() === currentDate.getFullYear() &&
            partido.fecha.getMonth() === currentDate.getMonth() &&
            partido.fecha.getDate() === currentDate.getDate()
        );

        // Asignar el evento de clic a dayDiv
        if (partidosEnDia.length > 0) {
            dayDiv.classList.add("partido");

            // Evento de clic en el recuadro de la fecha
            dayDiv.onclick = () => {
                // Aquí puedes manejar múltiples partidos, elige uno según tu lógica
                // Por ejemplo, solo llamaremos al primer partido de la lista
                guardarPartido(partidosEnDia[0].idPartido);
            };

            partidosEnDia.forEach(partido => {
                const circle = document.createElement("div");
                circle.classList.add("partido-circle");
                circle.textContent = ""; // Ícono o texto para el partido
                dayDiv.appendChild(circle);
            });
        }

        dateContainer.appendChild(dayDiv);
    }
}


// Llamada inicial para renderizar el calendario

prevMonthBtn.addEventListener("click", () => {
    let currentMonth = currentDate.getMonth();
    let currentYear = currentDate.getFullYear();
    
    // Si estamos en enero, retrocede al diciembre del año anterior
    if (currentMonth === 0) {
        currentDate.setMonth(11);
        currentDate.setFullYear(currentYear - 1);
    } else {
        currentDate.setMonth(currentMonth - 1);
    }
    
    renderCalendar(currentDate, partidos);
});

nextMonthBtn.addEventListener("click", () => {
    let currentMonth = currentDate.getMonth();
    let currentYear = currentDate.getFullYear();
    
    // Si estamos en diciembre, avanza al enero del año siguiente
    if (currentMonth === 11) {
        currentDate.setMonth(0);
        currentDate.setFullYear(currentYear + 1);
    } else {
        currentDate.setMonth(currentMonth + 1);
    }
    
    renderCalendar(currentDate, partidos);
});
// Renderizar el calendario por primera vez
renderCalendar(new Date(), partidos);
</script>
</body>
</html>
