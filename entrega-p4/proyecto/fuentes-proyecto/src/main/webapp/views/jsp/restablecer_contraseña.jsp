<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Restablecer Contraseña | Basketracker</title>
    
</head>
<body>
	
	<div class="thecontainer">
	<%@ include file="header.jsp" %>	
		<div class="olvidar-container">
			<div class="olvidar-section">
				<form action="<%= request.getContextPath() %>/CambiarContraseñaServlet" method="post">
					<div class="olvidar-group">
						<label for="token">Token de Restablecimiento:</label>
                        <input type="text" name="token" id="token" placeholder="Introduce el token recibido" required>
						
						<label for="nombreUsuario">INombre de Usuario:</label>
						<input type="text" name="nombreUsuario" id="correo" name="correo" placeholder="Nombre de usuario" required>
						
						<label for="correo">Correo Electrónico:</label>
						<input type="email" name="correo" id="correo" name="correo" placeholder="Correo Electrónico" required>
						
						<label for="correo">Nueva contraseña:</label>
						<input type="text" name="nuevaContrasena" id="nuevaContrasena" name="correo" placeholder="Contraseña" required>
						
						<label for="correo">Confirmar Nueva Contraseña:</label>
						<input type="text" name="confirmarContrasena" id="confirmarContrasena" name="correo" placeholder="Contraseña" required>			
					</div>
					<div class="olvidar-group">
						<button type="submit">Enviar correo de recuperación de contraseña</button>
					</div>
				</form>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
	</div>
</body>

<script>
if (event) {
    alert(event);
}
</script>
</html>