<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>¿Quiénes somos? | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
</head>
<body>
	<div class="thecontainer">
		<%@ include file="header.jsp" %>
		<div class="container">
	        <div class="image-section">
	            <img src="<%= request.getContextPath() %>/views/images/imagen-quienes_somos.png" alt="Imagen de Presentación">
	        </div>
	        <div class="quienes_somos-section">
	        	<h2>¿Quiénes somos?</h2>
		        <div class="quienes_somos-text">
	                <p>Empezamos este proyecto como tres amigos con una pasión común: el baloncesto. Mario, Jorge y Andrea decidimos unir nuestros talentos y crear una web dedicada a este deporte que tanto disfrutamos. Jorge aportó la visión estratégica, Andrea se encargó de la parte técnica, y Mario añadió el toque creativo para conectar con los aficionados. No fue fácil, nos encontramos con varios retos en el camino, pero siempre confiamos en lo que estábamos construyendo. Hoy, nuestra página es un punto de referencia en la comunidad del baloncesto, y nos alegra ver cómo algo que empezó como una idea entre amigos ha crecido tanto.</p>
			    </div>
			</div>
		</div>
	    <%@ include file="footer.jsp" %>
    </div>
</body>
</html>