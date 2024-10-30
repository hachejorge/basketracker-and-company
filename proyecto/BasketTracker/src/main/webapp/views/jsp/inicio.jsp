<%@ page import="clasesDAO.JugadorFavDAO" %>
<%@ page import="clasesDAO.EquipoFavDAO" %>
<%@ page import="clasesDAO.CompeticionFavDAO" %>
<%@ page import="clasesDAO.JugadorDAO" %>
<%@ page import="clasesDAO.EquipoDAO" %>

<%@ page import="clasesVO.UsuarioVO" %>
<%@ page import="clasesVO.EquipoVO" %>
<%@ page import="clasesVO.JugadorVO" %>
<%@ page import="clasesVO.JugadorFavVO" %>
<%@ page import="clasesVO.EquipoFavVO" %>
<%@ page import="clasesVO.CompeticionVO" %>

<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
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

    // Comprobar favoritos de jugadores
    String[] jugadores = { "andrea", "jorge", "mario" };
    Map<String, Boolean> favoritosJugadores = new HashMap<>();
    for (String jugador : jugadores) {
        favoritosJugadores.put(jugador, JugadorFavDAO.esFavorito(usuario.getNombreUsuario(), jugador));
    }

    // Comprobar favoritos de equipos
    int[] equipos = { 1, 2, 3 };
    Map<Integer, Boolean> favoritosEquipos = new HashMap<>();
    for (int equipo : equipos) {
        favoritosEquipos.put(equipo, EquipoFavDAO.esFavorito(usuario.getNombreUsuario(), equipo));
    }

    // Comprobar favoritos de competiciones
    String[] competiciones = { "2a Aragonesa Femenina", "Social Plata", "3a Aragonesa Masculina" };
    Map<String, Boolean> favoritosCompeticiones = new HashMap<>();
    for (String competicion : competiciones) {
        favoritosCompeticiones.put(competicion, CompeticionFavDAO.esFavorito(usuario.getNombreUsuario(), competicion));
    }
    
    
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
	<div class="container-inicio">
		<div class="recuadro">
		    <div class="navbar">
		        <div class="navbar-item active" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/inicio.jsp'">
		        	<img src="https://img.icons8.com/?size=100&id=131&format=png&color=FFFFFF" alt="Jugadores">
		            <span><b>Buscar</b></span>
		        </div>
		        <div class="navbar-item" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/favoritos.jsp'">
		            <img src="https://img.icons8.com/?size=100&id=84925&format=png&color=000000" alt="Jugadores">
		            <span>Favoritos</span>
		        </div>
		        <div class="navbar-item" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/mensajes.jsp'">
		            <img src="https://img.icons8.com/?size=100&id=87193&format=png&color=000000" alt="Equipos">
		            <span>Mensajes</span>
		        </div>
		    </div>
	    </div>

	    <div class="info">
			<div class="info-item">
				<% for (String jugador : jugadores) { %>
					<div class="jugadores">
	                    <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Perfil">
				        
				        <% 
				            // Obtener información del jugador a partir del nombre de usuario
				            JugadorVO jugadorVO = JugadorDAO.obtenerJugadorPorNombreUsuario(jugador);
				        	if (jugadorVO != null) {
				        		EquipoVO equipoVO = EquipoDAO.obtenerEquipoPorId(jugadorVO.getEquipo());
								if (equipoVO != null) {
				 
				        %>

	                    <p><strong><%= jugadorVO.getNombreJugador() %></strong><br><%= equipoVO.getNombreEquipo() %><br><%= equipoVO.getCompeticion() %></p>
	                    <img src="https://img.icons8.com/?size=100&id=<%= favoritosJugadores.get(jugador) ? "19416" : "85784" %>&format=png&color=000000" 
                         alt="Favorito" 
                         class="icono-favorito" 
                         data-id="<%= jugador %>" 
                         data-tipo="jugador" 
                         data-favorito="<%= favoritosJugadores.get(jugador) %>">
	                </div>
				<% }}} %>
			</div>

			<div class="info-item">
				<% for (int equipo : equipos) { %>
					<div class="equipos">
						<img src="https://img.icons8.com/?size=100&id=vy6OvJYHSJ8I&format=png&color=000000" alt="Logo Equipo">
						
						<% 
				            // Obtener información del jugador a partir del nombre de usuario
				            EquipoVO equipoVO = EquipoDAO.obtenerEquipoPorId(equipo);
							if (equipoVO != null) {
				        %>
				        
					    <p><strong><%= equipoVO.getNombreEquipo() %></strong><br><%= equipoVO.getCompeticion() %></p>
					    <img src="https://img.icons8.com/?size=100&id=<%= favoritosEquipos.get(equipo) ? "19416" : "85784" %>&format=png&color=000000" 
                         alt="Favorito" 
                         class="icono-favorito" 
                         data-id="<%= equipo %>" 
                         data-tipo="equipo" 
                         data-favorito="<%= favoritosEquipos.get(equipo) %>">			
					</div>
				<% }} %>
			</div>

			<div class="info-item">
				<% for (String competicion : competiciones) { %>
					<div class="competiciones">
						<img src="https://img.icons8.com/?size=100&id=6YtrB5VnlPqY&format=png&color=000000" alt="Logo Competición">
				        
					    <p><strong><%= competicion %></strong></p>
                        <img src="https://img.icons8.com/?size=100&id=<%= favoritosCompeticiones.get(competicion) ? "19416" : "85784" %>&format=png&color=000000" 
                         alt="Favorito" 
                         class="icono-favorito" 
                         data-id="<%= competicion %>" 
                         data-tipo="competicion" 
                         data-favorito="<%= favoritosCompeticiones.get(competicion) %>">
					</div>
				<% } %>
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

</script>
