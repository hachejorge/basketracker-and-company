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

    boolean esJugador = JugadorDAO.existeJugador(usuario.getNombreUsuario());
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Mensajes | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<div class="thecontainer">
	<%@ include file="header-buscador.jsp" %>
	<div class="container-mensajes">
		<div class="recuadro">
		    <div class="navbar">
		        <div class="navbar-item" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/inicio.jsp'">
		        	<img src="https://img.icons8.com/?size=100&id=131&format=png&color=000000" alt="Buscar" >
		            <span><b>Buscar</b></span>
		        </div>
		        <div class="navbar-item" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/favoritos.jsp'">
		            <img src="https://img.icons8.com/?size=100&id=84925&format=png&color=000000" alt="Favoritos">
		            <span>Favoritos</span>
		        </div>
		        <div class="navbar-item active" onClick="window.location.href='<%= request.getContextPath() %>/views/jsp/mensajes.jsp'">
		            <img src="https://img.icons8.com/?size=100&id=87193&format=png&color=FFFFFF" alt="Mensajes">
		            <span>Mensajes</span>
		        </div>
		    </div>	    
		</div> 
		<div class="mensajes-info">
				<div class="chats">
					<div class="chat-scroll-container">
					<% if (esJugador) { %>
					
						<div class="michat" data-id=<%= usuario.getNombreUsuario() %> onclick="showChat('<%= usuario.getNombreUsuario() %>')">
							<p class="michatcanal">Mi canal</p>
							<div class="chat-organizado">
								<img src="https://img.icons8.com/?size=100&id=68&format=png&color=000000" alt="Icono Comunidad <%= usuario.getNombreUsuario() %>">
								<div class="chat-organizado-texto">
									<p class="community"><%= usuario.getNombreUsuario() %> community</p>
									<p class="propietario">Tú</p>
								</div>
							</div>
						</div>
						<p class="michatcanal">Mis canales seguidos</p>
						<% } 
							List<JugadorFavVO> jugfav = JugadorFavDAO.obtenerJugadoresFavPorUsuario(usuario.getNombreUsuario());
							for (JugadorFavVO jf : jugfav) {
								if (!jf.getJugador().equals(usuario.getNombreUsuario())) {
						%>
						<div class="chat" data-id=<%= usuario.getNombreUsuario() %> onclick="showChat('<%= jf.getJugador() %>')">
							<div class="chat-organizado">
								<img src="https://img.icons8.com/?size=100&id=68&format=png&color=000000" alt="Icono Comunidad <%= jf.getJugador()  %>">
								<div class="chat-organizado-texto">
									<p class="community"><%= jf.getJugador() %> community</p>
									<p class="propietario"><%= JugadorDAO.obtenerJugadorPorNombreUsuario(jf.getJugador()).getNombreJugador()  %></p>
								</div>
							</div>
						</div>
					<% }} %>
				</div>
			</div>
			<div class="chatsabiertos">
				<% if (esJugador) { %>
				<div class="michatabierto" data-id="<%= usuario.getNombreUsuario() %>-chatabierto">
					<div class="chatabierto-organizado">
						<img src="https://img.icons8.com/?size=100&id=68&format=png&color=000000" alt="Icono Comunidad <%= usuario.getNombreUsuario() %>">
						<div class="chat-organizado-texto">
							<p class="community"><%= usuario.getNombreUsuario() %> community</p>
						</div>					
					</div>
					<div class="chatcompleto">
						<div class="chatcompleto-scroll-container" id="chatScrollContainer">
							<div class="mensajes">
							<%
							List<MensajeVO> mensajes = MensajeDAO.obtenerMensajesPorUsuario(usuario.getNombreUsuario());
								for (MensajeVO mensaje : mensajes) {
							%>
							<div class="mensaje-borrar-mensaje">
								<form action="<%= request.getContextPath() %>/EliminarMensajeServlet" method="post" style="display:inline;">
	                				<input type="hidden" name="nombreUsuario" value="<%= mensaje.getNombreUsuario() %>"> <!-- Enviar nombre de usuario -->
					                <input type="hidden" name="idMensaje" value="<%= mensaje.getIdMensaje() %>"> <!-- Enviar ID del partido -->
					                <input type="hidden" name="mensaje" value="<%= mensaje.getMensaje() %>"> <!-- Enviar texto del comentario -->
					                <button type="submit" style="border: none; background: transparent; cursor: pointer;">
					                    <img src="https://img.icons8.com/?size=100&id=1941&format=png&color=000000" alt="Eliminar Mensaje">
					                </button>
				           		</form>
								<div class="message-bubble">
								    <span class="message-text"><%= mensaje.getMensaje() %></span>
								    <span class="timestamp"><%=mensaje.formatHora() %> <%=mensaje.formatFecha() %></span>
								</div>
							</div>
							<% } %>
							</div>
						</div>
					</div>
					<div class="mensajes-box">
		               	<form action="<%= request.getContextPath() %>/EnviarMensajeServlet" method="POST" class="partidoselec-comment-box">
					        <input type="text" name="comentario" placeholder="Escribir Mensaje" required>
					        <input type="hidden" name="nombreUsuario" value="<%= usuario.getNombreUsuario() %>">
					        <button type="submit">Enviar</button>
					    </form>
		            </div>
				</div>
				<% }
					List<JugadorFavVO> jugfavo = JugadorFavDAO.obtenerJugadoresFavPorUsuario(usuario.getNombreUsuario());
					for (JugadorFavVO jf : jugfavo) {
						if (!jf.getJugador().equals(usuario.getNombreUsuario())) {
				%>
				<div class="otrochatabierto hidden" data-id="<%= jf.getJugador()%>-chatabierto">
					<div class="michatabierto">
						<div class="chatabierto-organizado">
							<img src="https://img.icons8.com/?size=100&id=68&format=png&color=000000" alt="Icono Comunidad <%= jf.getJugador()  %>">
							<div class="chat-organizado-texto">
								<p class="community"><%= jf.getJugador() %> community</p>
							</div>					
						</div>
						<div class="chatcompleto">
							<div class="chatcompleto-scroll-container" id="chatScrollContainer">
								<div class="mensajes">
								<%
								List<MensajeVO> mensajes = MensajeDAO.obtenerMensajesPorUsuario(jf.getJugador());
									for (MensajeVO mensaje : mensajes) {
								%>
								<div class="mensaje-borrar-mensaje">
									<div class="message-bubble">
									    <span class="message-text"><%= mensaje.getMensaje() %></span>
									    <span class="timestamp"><%=mensaje.formatHora() %> <%=mensaje.formatFecha() %></span>
									</div>
								</div>
								<% } %>
								</div>
							</div>
						</div>
					</div>
				</div>
				<% }} %>
				
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</div>

<script>

function showChat(chatId) {
    // Ocultar todos los chats abiertos
    const openChats = document.querySelectorAll('.chatsabiertos > div');
    openChats.forEach(chat => chat.classList.add('hidden'));

    // Mostrar el chat abierto correspondiente, asegurándose de usar el ID correcto
    const activeChat = document.querySelector('div[data-id="' + chatId + '-chatabierto"]');
    if (activeChat) {
        activeChat.classList.remove('hidden');
    }
}

    document.addEventListener("DOMContentLoaded", function() {
        var chatScrollContainer = document.getElementById("chatScrollContainer");
        chatScrollContainer.scrollTop = chatScrollContainer.scrollHeight;
    });
</script>

