<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="container">
		<div class="form-section">
			<div class="form-group">
				<label for="correo">Introduce tu correo electrónico:</label>
				<input type="email" id="correo" name="correo" placeholder="Correo Electrónico" required>			
			</div>
			<div class="form-group">
				<button type="submit">Enviar correo de recuperación de contraseña</button>
			</div>
		</div>
	</div>
</body>
</html>