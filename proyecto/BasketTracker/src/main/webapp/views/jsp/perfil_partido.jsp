<%@ page import="clasesDAO.*" %>
<%@ page import="clasesVO.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.time.LocalDateTime" %>

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
    
    PartidoVO partidoVO = (PartidoVO) session.getAttribute("partidoSeleccionado");

    if (partidoVO == null) {
    	response.sendRedirect(request.getContextPath() + "/views/jsp/favoritos.jsp");
        return;
    }
    
    Date fechaPartido = partidoVO.getFecha();
    Time horaPartido = partidoVO.getHora();
    LocalDateTime fechaHoraPartido = LocalDateTime.of(fechaPartido.toLocalDate(), horaPartido.toLocalTime());
    LocalDateTime fechaActual = LocalDateTime.now();
    
    String esJugado = "proximamente";
    
    if (fechaActual.isBefore(fechaHoraPartido)) {
    	esJugado = "proximamente";
    } else if (fechaActual.isAfter(fechaHoraPartido)) {
    	esJugado = "jugado";
    } else {
    	esJugado = "proximamente";
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title><%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getNombreEquipo() %> VS <%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoVisitante()).getNombreEquipo() %> | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<div class="thecontainer">
	<%@ include file="header-buscador.jsp" %>
	<div class="container-partido">
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
		<div class="datos-partidoselec <%= esJugado.equals("jugado") ? "active" : "" %>">
			<div class="partidoselec-datos">
		        <div class="partidoselec-header">
		        	<div class="partidoselec-header-score">
			       		<img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Escudo <%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getNombreEquipo() %>" onclick="verMasEquipo('<%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getIdEquipo() %>')">
			        	<div class="partidoselec-score-home"><%= partidoVO.getPtsC1Local() + partidoVO.getPtsC2Local() + partidoVO.getPtsC3Local() + partidoVO.getPtsC4Local()%></div>
			            <div class="partidoselec-score-away"><%= partidoVO.getPtsC1Visit() + partidoVO.getPtsC2Visit() + partidoVO.getPtsC3Visit() + partidoVO.getPtsC4Visit()%></div>
			            <img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Escudo <%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoVisitante()).getNombreEquipo() %>" onclick="verMasEquipo('<%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoVisitante()).getIdEquipo() %>')">
		            </div>
		            <div class="partidoselec-teams">
		                <div class="partidoselec-date"><%= partidoVO.formatFecha() %></div>
		                <div class="partidoselec-date-info">			                
			                <div class="partidoselec-vs"><%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getNombreEquipo() %> VS <%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoVisitante()).getNombreEquipo() %></div>
			                <div class="partidoselec-time-competicion">
				                <div class="partidoselec-competicion"><%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getCompeticion() %></div>
				                <div class="partidoselec-time"><%= partidoVO.formatHora() %></div>
		                	</div>
		                </div>
		        	</div>
		        	<div class="partidoselec-status">Finalizado</div>
		        </div>
		
		        <!-- Tabla de puntuación por cuarto -->
		        <div class="partidoselec-quarters">
		            <table>
		                <thead>
		                    <tr>
		                        <th></th>
		                        <th>C1</th>
		                        <th>C2</th>
		                        <th>C3</th>
		                        <th>C4</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <tr>
		                        <td><%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getNombreEquipo() %></td>
		                        <td><%= partidoVO.getPtsC1Local() %></td>
		                        <td><%= partidoVO.getPtsC2Local() %></td>
		                        <td><%= partidoVO.getPtsC3Local() %></td>
		                        <td><%= partidoVO.getPtsC4Local() %></td>
		                    </tr>
		                    <tr>
		                        <td><%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoVisitante()).getNombreEquipo() %></td>
		                        <td><%= partidoVO.getPtsC1Visit() %></td>
		                        <td><%= partidoVO.getPtsC2Visit() %></td>
		                        <td><%= partidoVO.getPtsC3Visit() %></td>
		                        <td><%= partidoVO.getPtsC4Visit() %></td>
		                    </tr>
		                </tbody>
		            </table>
		        </div>
		
		        <!-- Mayores anotadores -->
		        <div class="competicion-ranking-tabla">
		        	<%
		            List<PtsJugParVO> listajug = PtsJugParDAO.obtenerDatosJugadoresPorPartido(partidoVO.getIdPartido());
		        	listajug.sort((jugador1, jugador2) -> jugador2.getPtsAnt().compareTo(jugador1.getPtsAnt()));
		        	
		            %>
		            <h3>Mayores Anotadores</h3>
		           	<table>	          
		            <%
		            Integer j = 0;
		            for (PtsJugParVO datos : listajug) {
		            	if (j >= 5) {
		            		break;
		            	}
		            	j++;
		            
		            %>
		            
		                <tr class="favorito-<%= (j == 1 ? "primero" : (j == 2 ? "segundo" : (j == 3 ? "tercero" : "cuarto"))) %>">
		                     <td><%=j %>. <%= JugadorDAO.obtenerJugadorPorNombreUsuario(datos.getNombreUsuario()).getNombreJugador() %></td>
		                     <td><%= datos.getPtsAnt() %></td>		                    
		                 </tr>
		            
		            <% } %>
		            </table>
		            <h3>Mayores Triplistas</h3>
		           	<table>	          
		            <%
		            listajug.sort((jugador1, jugador2) -> jugador2.getTrpAnt().compareTo(jugador1.getTrpAnt()));
		            j = 0;
		            for (PtsJugParVO datos : listajug) {
		            	if (j >= 5) {
		            		break;
		            	}
		            	j++;
		            
		            %>
		            
		                <tr class="favorito-<%= (j == 1 ? "primero" : (j == 2 ? "segundo" : (j == 3 ? "tercero" : "cuarto"))) %>">
		                     <td><%=j %>. <%= JugadorDAO.obtenerJugadorPorNombreUsuario(datos.getNombreUsuario()).getNombreJugador() %></td>
		                     <td><%= datos.getTrpAnt() %></td>		                    
		                 </tr>
		            
		            <% } %>
		            </table> 
		            <h3>Mayores Anotadores</h3>
		           	<table>	          
		            <%
		            listajug.sort((jugador1, jugador2) -> jugador2.getTlbAnt().compareTo(jugador1.getTlbAnt()));
		            j = 0;
		            for (PtsJugParVO datos : listajug) {
		            	if (j >= 5) {
		            		break;
		            	}
		            	j++;
		            
		            %>
		            
		                <tr class="favorito-<%= (j == 1 ? "primero" : (j == 2 ? "segundo" : (j == 3 ? "tercero" : "cuarto"))) %>">
		                     <td><%=j %>. <%= JugadorDAO.obtenerJugadorPorNombreUsuario(datos.getNombreUsuario()).getNombreJugador() %></td>
		                     <td><%= datos.getTlbAnt() %></td>		                    
		                 </tr>
		            
		            <% } %>
		            </table>  		            
		        </div>
	        </div>
			<div class="partidoselec-comentarios">
		        <div class="partidoselec-comments">
		            <h3>Comentarios</h3>
		            <%
		            List<ComentarioVO> comentarios = ComentarioDAO.listarComentariosPorPartido(partidoVO.getIdPartido());
		           	
		            if (!comentarios.isEmpty()) {
		            	for (ComentarioVO comentario : comentarios) {
		            %>
		            <div class="partidoselec-comment">
		            	<div class="comentarios-header">
		                <span class="partidoselec-comment-user">@<%= comentario.getNombreUsuario() %></span>
		                <% if (comentario.getNombreUsuario().equals(usuario.getNombreUsuario())) { %>
		                	<form action="<%= request.getContextPath() %>/EliminarComentarioServlet" method="post" style="display:inline;" onsubmit="return confirmarEliminacion();">
                				<input type="hidden" name="nombreUsuario" value="<%= comentario.getNombreUsuario() %>"> <!-- Enviar nombre de usuario -->
				                <input type="hidden" name="idPartido" value="<%= partidoVO.getIdPartido() %>"> <!-- Enviar ID del partido -->
				                <input type="hidden" name="comentario" value="<%= comentario.getComentario() %>"> <!-- Enviar texto del comentario -->
				                <button type="submit" style="border: none; background: transparent; cursor: pointer;">
				                    <img src="https://img.icons8.com/?size=100&id=1941&format=png&color=000000" alt="Eliminar Comentario">
				                </button>
				            </form>
		                <% } %>
		                </div>
		                <p><%= comentario.getComentario() %></p>
		                
		            </div>
		            <% }} %>
		            <div class="partidoselec-comment-box">
		               	<form action="<%= request.getContextPath() %>/SubirComentarioServlet" method="POST" class="partidoselec-comment-box">
					        <input type="text" name="comentario" placeholder="Escribir Comentario" required>
					        <input type="hidden" name="nombreUsuario" value="<%= usuario.getNombreUsuario() %>">
					        <input type="hidden" name="idPartido" value="<%= partidoVO.getIdPartido() %>">
					        <button type="submit">Enviar</button>
					    </form>
		            </div>
		        </div>
	       </div> 
		</div>
		<div class="datos-partidoselec <%= esJugado.equals("proximamente") ? "active" : "" %>">
			<div class="partidoselec-datos-prox">
		        <div class="partidoselec-header">
		        	<div class="partidoselec-header-score-prox">
			       		<img src="https://img.icons8.com/?size=200&id=t7crGJINSAvv&format=png&color=000000" alt="Escudo <%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getNombreEquipo() %>" onclick="verMasEquipo('<%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getIdEquipo() %>')">
			            <img src="https://img.icons8.com/?size=200&id=t7crGJINSAvv&format=png&color=000000" alt="Escudo <%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoVisitante()).getNombreEquipo() %>" onclick="verMasEquipo('<%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoVisitante()).getIdEquipo() %>')">
		            </div>
		            <div class="partidoselec-teams">
		                <div class="partidoselec-date"><%= partidoVO.formatFecha() %></div>
		                <div class="partidoselec-date-info">			                
			                <div class="partidoselec-vs"><%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getNombreEquipo() %> VS <%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoVisitante()).getNombreEquipo() %></div>
			                <div class="partidoselec-time-competicion">
				                <div class="partidoselec-competicion"><%= EquipoDAO.obtenerEquipoPorId(partidoVO.getEquipoLocal()).getCompeticion() %></div>
				                <div class="partidoselec-time"><%= partidoVO.formatHora() %></div>
		                	</div>
		                </div>
		        	</div>
		        	<div class="partidoselec-status-prox">Proximamente</div>
		        </div>
	        </div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</div>

<script>
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

function confirmarEliminacion() {
    return confirm("¿Estás seguro de que deseas eliminar este mensaje?");
}
</script>
