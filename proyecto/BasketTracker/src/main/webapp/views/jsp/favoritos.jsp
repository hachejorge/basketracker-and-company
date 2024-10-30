<%@ page import="clasesDAO.JugadorFavDAO" %>
<%@ page import="clasesDAO.EquipoFavDAO" %>
<%@ page import="clasesDAO.CompeticionFavDAO" %>
<%@ page import="clasesDAO.JugadorDAO" %>
<%@ page import="clasesDAO.EquipoDAO" %>
<%@ page import="clasesDAO.CompeticionDAO" %>

<%@ page import="clasesVO.UsuarioVO" %>
<%@ page import="clasesVO.EquipoVO" %>
<%@ page import="clasesVO.JugadorVO" %>
<%@ page import="clasesVO.JugadorFavVO" %>
<%@ page import="clasesVO.EquipoFavVO" %>
<%@ page import="clasesVO.CompeticionFavVO" %>

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
	<div class="container-inicio">
		<div class="recuadro">
		    <div class="navbar">
		        <div class="navbar-item" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/inicio.jsp'">
		        	<img src="https://img.icons8.com/?size=100&id=131&format=png&color=000000" alt="Jugadores">
		            <span><b>Buscar</b></span>
		        </div>
		        <div class="navbar-item active" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/favoritos.jsp'">
		            <img src="https://img.icons8.com/?size=100&id=84925&format=png&color=FFFFFF" alt="Jugadores">
		            <span>Favoritos</span>
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
		                    <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Perfil">
					        
					        <%
								JugadorVO jugadorVO = JugadorDAO.obtenerJugadorPorNombreUsuario(jugador.getJugador());
								if (jugadorVO != null) {
					        		EquipoVO equipoVO = EquipoDAO.obtenerEquipoPorId(jugadorVO.getEquipo());
									if (equipoVO != null) {
					 
					        %>
	
		                    <p><strong><%= jugadorVO.getNombreJugador() %></strong><br><%= equipoVO.getNombreEquipo() %><br><%= equipoVO.getCompeticion() %></p>
		                    <img src="https://img.icons8.com/?size=100&id=<%= JugadorFavDAO.esFavorito(usuario.getNombreUsuario(), jugadorVO.getNombreUsuario()) ? "19416" : "85784" %>&format=png&color=000000" 
	                         alt="Favorito" 
	                         class="icono-favorito" 
	                         data-id="<%= jugadorVO.getNombreUsuario() %>" 
	                         data-tipo="jugador" 
	                         data-favorito="<%= JugadorFavDAO.esFavorito(usuario.getNombreUsuario(), jugadorVO.getNombreJugador()) %>">
		                </div>
					<% }}} %>
				</div>
	
				<div class="info-item-favorito">
					<% for (EquipoFavVO equipo : equiposFavoritos) { %>
						<div class="equipos-favorito">
							<img src="https://img.icons8.com/?size=100&id=vy6OvJYHSJ8I&format=png&color=000000" alt="Logo Equipo">
							
							<% 
					            // Obtener información del jugador a partir del nombre de usuario
					            EquipoVO equipoVO = EquipoDAO.obtenerEquipoPorId(equipo.getEquipo());
								if (equipoVO != null) {
					        %>
					        
						    <p><strong><%= equipoVO.getNombreEquipo() %></strong><br><%= equipoVO.getCompeticion() %></p>
						    <img src="https://img.icons8.com/?size=100&id=<%= EquipoFavDAO.esFavorito(usuario.getNombreUsuario(), equipoVO.getIdEquipo()) ? "19416" : "85784" %>&format=png&color=000000" 
	                         alt="Favorito"
	                         class="icono-favorito" 
	                         data-id="<%= equipoVO.getIdEquipo() %>" 
	                         data-tipo="equipo" 
	                         data-favorito="<%= EquipoFavDAO.esFavorito(usuario.getNombreUsuario(), equipoVO.getIdEquipo()) %>">			
						</div>
					<% }} %>
				</div>
	
				<div class="info-item-favorito">
					<% for (CompeticionFavVO competicion : competicionesFavoritos) { %>
						<div class="competiciones-favorito">
							<img src="https://img.icons8.com/?size=100&id=6YtrB5VnlPqY&format=png&color=000000" alt="Logo Competición">
	
					        
						    <p><strong><%= competicion.getCompeticion() %></strong></p>
	                        <img src="https://img.icons8.com/?size=100&id=<%= CompeticionFavDAO.esFavorito(usuario.getNombreUsuario(), competicion.getCompeticion()) ? "19416" : "85784" %>&format=png&color=000000" 
	                         alt="Favorito" 
	                         class="icono-favorito" 
	                         data-id="<%= competicion.getCompeticion() %>" 
	                         data-tipo="competicion" 
	                         data-favorito="<%= CompeticionFavDAO.esFavorito(usuario.getNombreUsuario(), competicion.getCompeticion()) %>">
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

</script>
