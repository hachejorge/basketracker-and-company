<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Ayudas y preguntas frecuentes | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
</head>
<body>
	<div class="thecontainer">
		<%@ include file="header.jsp" %>
		<div class="container-ayudas">
			<div class="ayudas-section">
				<h1>Ayudas y Preguntas frecuentes</h1>
				<h2>Si necesitas ayuda:</h2>
				<div class="ayudas-text">
					<p>Puedes mandar un correo a:</p>
					<p>El horario de respuesta es 10:00-19:00 en días laborables.</p>
				</div>
				<h2>Preguntas frecuentes:</h2>
				<div class="ayudas-text">
					<p>1</p>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
	</div>
</body>