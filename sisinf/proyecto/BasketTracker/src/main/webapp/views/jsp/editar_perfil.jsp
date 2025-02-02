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
    <title>Editar Perfil | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <div class="thecontainer">
        <%@ include file="header.jsp" %>
        <div class="container-editar">
            <div class="quienes_somos-section">
                 <h1 class="editar-titulo">Editar Perfil</h1>
                <!-- Formulario para guardar cambios -->
                <form class="editar-form" id="editar-form" method="POST" action="<%= request.getContextPath() %>/EnviarPeticionServlet">
                    <label for="usuario" class="editar-label">Nombre de Usuario: @<%=usuario.getNombreUsuario() %> </label>
                    <div class="editar-campo">
                        <label for="email" class="editar-label">Correo Electrónico</label>
                        <input type="email" name="email" id="email" class="editar-input" value="<%= usuario.getCorreoElect() %>">
                    </div>
                    <div id="ocultar-usuario">
                    <% if (esJugador) { %>
                        <%
                        JugadorVO jugador = JugadorDAO.obtenerJugadorPorNombreUsuario(usuario.getNombreUsuario());
                        %>
                        <hr class="editar-separador">
                        <div class="editar-campo">
                            <label for="nombre-jugador" class="editar-label">Nombre Jugador</label>
                            <input type="text" id="nombre-jugador" name="nombre-jugador" class="editar-input" value="<%= jugador.getNombreJugador() %>">
                        </div>
                        <div class="editar-campo">
                            <label for="cambio-equipo" class="editar-label">Solicitar cambio o eliminar cuenta</label>
                            <div class="editar-cambio-equipo">
                                <textarea id="cambio-equipo" name="sugerencia-cambio" class="editar-textarea" placeholder="Escribe un mensaje aquí"></textarea>
                                <button type="submit" class="editar-btn-enviar">Enviar</button>
                            </div>
                        </div>
                        <% } %>
                    </div>
                    <div class="editar-botones">
                        <button type="button" id="boton-eliminar" class="editar-btn eliminar-cuenta" onclick="confirmarEliminacion()">
                            <img src="https://img.icons8.com/?size=100&id=1941&format=png&color=FFFFFF" alt="Eliminar" class="editar-icono"> Eliminar Cuenta
                        </button>
                        <button type="button" class="editar-btn guardar-cambios" onclick="guardarCambios()">
                            <img src="https://img.icons8.com/?size=100&id=129&format=png&color=FFFFFF" alt="Guardar" class="editar-icono"> Guardar Cambios
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
    </div>
</body>
<script>
    // Pasar el valor de esJugador al lado del cliente
    var esJugador = <%= esJugador ? "true" : "false" %>;

    // Función para redirigir al servlet BorrarUsuarioServlet
    function eliminarCuenta() {
        window.location.href = "<%= request.getContextPath() %>/BorrarUsuarioServlet";
    }

    // Función para redirigir al servlet GuardarUsuario o GuardarJugador al guardar cambios
    function guardarCambios() {
        var form = document.getElementById("editar-form");
        var confirmacion = confirm("¿Está seguro de que desea cambiar los datos de su cuenta?");
        if (confirmacion) {
            if (esJugador) {
                form.action = "<%= request.getContextPath() %>/ActualizarJugadorServlet";
            } else {
                form.action = "<%= request.getContextPath() %>/ActualizarUsuarioServlet";
            }
            form.submit();
        }
    }

    // Ocultar sección de jugador si no es jugador
    if (!esJugador) {
        document.getElementById("ocultar-usuario").style.display = "none";
    } else {
    	document.getElementById("boton-eliminar").style.display = "none";
    }
    
    function confirmarEliminacion() {
        var confirmacion = confirm("¿Está seguro de que desea eliminar su cuenta? Esta acción no se puede deshacer.");
        if (confirmacion) {
            // Si el usuario confirma, redirige al servlet para borrar la cuenta
            window.location.href = "<%= request.getContextPath() %>/BorrarUsuarioServlet";
        }
    }
</script>
</html>
