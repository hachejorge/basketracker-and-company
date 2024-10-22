<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Inicio | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<div class="thecontainer">
	<%@ include file="header-buscador.jsp" %>
	<div class="container-inicio">
		<div class="recuadro">
		    <div class="navbar">
		        <div class="navbar-item active">
		            <span><b>Todos</b></span>
		        </div>
		        <div class="navbar-item">
		            <img src="https://img.icons8.com/?size=100&id=978&format=png&color=000000" alt="Jugadores">
		            <span>Jugadores</span>
		        </div>
		        <div class="navbar-item">
		            <img src="https://img.icons8.com/?size=100&id=vy6OvJYHSJ8I&format=png&color=000000" alt="Equipos">
		            <span>Equipos</span>
		        </div>
		        <div class="navbar-item">
		            <img src="https://img.icons8.com/?size=100&id=6YtrB5VnlPqY&format=png&color=000000" alt="Competiciones">
		            <span>Competiciones</span>
		        </div>
		    </div>
	    </div>
	    <div class="info">
			<div class="info-item">
			</div>
			<div class="info-item">
			</div>
			<div class="info-item">
			</div>
	    </div>
	</div>
	<%@ include file="footer.jsp" %>
</div>