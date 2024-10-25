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
		        	<img src="https://img.icons8.com/?size=100&id=131&format=png&color=FFFFFF" alt="Jugadores">
		            <span><b>Buscar</b></span>
		        </div>
		        <div class="navbar-item">
		            <img src="https://img.icons8.com/?size=100&id=84925&format=png&color=000000" alt="Jugadores">
		            <span>Favoritos</span>
		        </div>
		        <div class="navbar-item">
		            <img src="https://img.icons8.com/?size=100&id=87193&format=png&color=000000" alt="Equipos">
		            <span>Mensajes</span>
		        </div>
		    </div>
	    </div>
		<div class="recuadro">
		    <div class="navbar2">
		        <div class="navbar-item2 active">
		            <span><b>Todos</b></span>
		        </div>
		        <div class="navbar-item2">
		            <img src="https://img.icons8.com/?size=100&id=978&format=png&color=000000" alt="Jugadores">
		            <span>Jugadores</span>
		        </div>
		        <div class="navbar-item2">
		            <img src="https://img.icons8.com/?size=100&id=vy6OvJYHSJ8I&format=png&color=000000" alt="Equipos">
		            <span>Equipos</span>
		        </div>
		        <div class="navbar-item2">
		            <img src="https://img.icons8.com/?size=100&id=6YtrB5VnlPqY&format=png&color=000000" alt="Competiciones">
		            <span>Competiciones</span>
		        </div>
		    </div>
	    </div>
	    <div class="info">
			<div class="info-item">
				<div class="jugadores">
					<img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Perfil">
                    <p><strong>Andrea Hernández Artal</strong><br>Boscos<br>2a Aragonesa Femenina</p>
                    <img src="https://img.icons8.com/?size=100&id=85784&format=png&color=000000" alt="Favorito">
				</div>
				<div class="jugadores">
					<img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Perfil">
                    <p><strong>Jorge Clavero Agudo</strong><br>Cristo Rey<br>Social Plata</p>
                    <img src="https://img.icons8.com/?size=100&id=85784&format=png&color=000000" alt="Favorito">
				</div>
				<div class="jugadores">
					<img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Perfil">
                    <p><strong>Mario Ferradas Aznar</strong><br>Boscos<br>3a Aragonesa Masculina</p>
                    <img src="https://img.icons8.com/?size=100&id=85784&format=png&color=000000" alt="Favorito">
				</div>
			</div>
			<div class="info-item">
				<div class="equipos">
					<img src="https://img.icons8.com/?size=100&id=vy6OvJYHSJ8I&format=png&color=000000" alt="Logo Equipo">
				    <p><strong>Boscos</strong><br>2a Aragonesa Femenina</p>
				    <img src="https://img.icons8.com/?size=100&id=85784&format=png&color=000000" alt="Favorito">
				</div>
				<div class="equipos">
					<img src="https://img.icons8.com/?size=100&id=vy6OvJYHSJ8I&format=png&color=000000" alt="Logo Equipo">
				    <p><strong>Cristo Rey</strong><br>Social Plata</p>
				    <img src="https://img.icons8.com/?size=100&id=85784&format=png&color=000000" alt="Favorito">
				</div>
				<div class="equipos">
					<img src="https://img.icons8.com/?size=100&id=vy6OvJYHSJ8I&format=png&color=000000" alt="Logo Equipo">
				    <p><strong>Boscos 3</strong><br>3a Aragonesa Masculina</p>
				    <img src="https://img.icons8.com/?size=100&id=85784&format=png&color=000000" alt="Favorito">
				</div>
			</div>
			<div class="info-item">
				<div class="competiciones">
					<img src="https://img.icons8.com/?size=100&id=6YtrB5VnlPqY&format=png&color=000000" alt="Logo Competición">
				    <p><strong>2a Aragonesa Femenina</strong></p>
				    <img src="https://img.icons8.com/?size=100&id=85784&format=png&color=000000" alt="Favorito">
				</div>
				<div class="competiciones">
					<img src="https://img.icons8.com/?size=100&id=6YtrB5VnlPqY&format=png&color=000000" alt="Logo Competición">
				    <p><strong>Social Plata</strong></p>
				    <img src="https://img.icons8.com/?size=100&id=85784&format=png&color=000000" alt="Favorito">
				</div>
				<div class="competiciones">
					<img src="https://img.icons8.com/?size=100&id=6YtrB5VnlPqY&format=png&color=000000" alt="Logo Competición">
				    <p><strong>3a Aragonesa Masculina</strong></p>
				    <img src="https://img.icons8.com/?size=100&id=85784&format=png&color=000000" alt="Favorito">
				</div>
			</div>
	    </div>
	</div>
	<%@ include file="footer.jsp" %>
</div>