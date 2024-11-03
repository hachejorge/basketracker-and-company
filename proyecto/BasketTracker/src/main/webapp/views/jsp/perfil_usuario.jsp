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
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title><%= usuario.getNombreUsuario() %> | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<div class="thecontainer">
	<%@ include file="header-buscador.jsp" %>
	<div class="container-jugador">
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
		    <div class="proximos-partidos-usuario">
			    <div class="partido-container">
			        <div class="partido-proximos-partidos">
			            <h3>Próximos Partidos De Mis Jugadores Fav</h3>
			            <%
			            List<JugadorFavVO> jugadoresfav = JugadorFavDAO.obtenerJugadoresFavPorUsuario(usuario.getNombreUsuario());
			            Integer totalpartidos = 0;
			            for (JugadorFavVO jf : jugadoresfav) {
			            	if (jf != null && !jugadoresfav.isEmpty() && totalpartidos != 4 ) {
					            List<PartidoVO> partidos =  PartidoDAO.obtenerProximosPartidosPorEquipo(EquipoDAO.obtenerEquipoPorId(JugadorDAO.obtenerJugadorPorNombreUsuario(jf.getJugador()).getEquipo()).getIdEquipo());
					            	for (PartidoVO partido : partidos) {
						            	if (partido != null && !partidos.isEmpty() && totalpartidos != 4 ) {
						 					if (partido != null) {
						            			EquipoVO equipolocal = EquipoDAO.obtenerEquipoPorId(partido.getEquipoLocal());
						            			totalpartidos++;

			            %>
			            <div class="partido-card" onclick="guardarPartido('<%= partido.getIdPartido() %>')">
					        <div class="partido-fecha">
					            <span><%= partido.formatFecha() %></span> <!-- Muestra la fecha del partido -->
					        </div>
					        <div class="partido-info">
					        	<p><%= JugadorDAO.obtenerJugadorPorNombreUsuario(jf.getJugador()).getNombreJugador() %></p>
					            <p><strong><%= EquipoDAO.obtenerEquipoPorId(partido.getEquipoLocal()).getNombreEquipo() %> VS <%= EquipoDAO.obtenerEquipoPorId(partido.getEquipoVisitante()).getNombreEquipo() %></strong></p>
					            <p><%= partido.formatHora() %></p> <!-- Muestra la hora del partido -->
					            <p><%= equipolocal.getUbicacion() %></p> <!-- Muestra la ubicación del partido -->
					        </div>
					        <div class="partido-ubicacion">
					            <img src="https://img.icons8.com/?size=100&id=342&format=png&color=000000" alt="Ubicación Icono">
					            <p>Ubicación</p>
					        </div>
					    </div>
					    <% }}}}} %>
			        </div>
			    </div>
		    </div>
		   	<div class="datos-jugador-usuario">
		        <div class="profile-header">
			        <div class="banner">
			            <img src="<%= request.getContextPath() %>/views/images/banner.png" alt="Banner image">
			        </div>
			        <div class="profile-content">
			            <div class="profile-details-usuario">
			                <p>@<%= usuario.getNombreUsuario() %></p>
			            </div>
			            <div class="profile-picture-usuario">
			                <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Profile picture">
			            </div>
			        </div>
			    </div>
		       	<div class="usuario-info">
					<p class="usuario-titulo">Jugadores Favoritos</p>
					<div class="usuario-jugadores-fav">
					<%
					List<JugadorFavVO> jugfav = JugadorFavDAO.obtenerJugadoresFavPorUsuario(usuario.getNombreUsuario());
					for (JugadorFavVO jugador : jugfav) { 
				    %>
				            <div class="jugador-card-usuario" onclick="verMas('<%= jugador.getJugador() %>')">
				                <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Jugador Icono">
				                <p><%= JugadorDAO.obtenerJugadorPorNombreUsuario(jugador.getJugador()).getNombreJugador() %></p>
				                <p><%= EquipoDAO.obtenerEquipoPorId(JugadorDAO.obtenerJugadorPorNombreUsuario(jugador.getJugador()).getEquipo()).getNombreEquipo() %></p>
				                <p><%= EquipoDAO.obtenerEquipoPorId(JugadorDAO.obtenerJugadorPorNombreUsuario(jugador.getJugador()).getEquipo()).getCompeticion() %></p>
				            </div>
				        <% 
				            } 
				        %>
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
</script>
