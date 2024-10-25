<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Equipo | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
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
	    <div class="info-perfiles">
		    <div class="proximos-partidos">
			    <div class="partido-container">
			        <div class="partido-proximos-partidos">
			            <h3>Próximos Partidos</h3>
			            <div class="partido-card">
			                <div class="partido-fecha">
			                    <span>28 sept</span>
			                </div>
			                <div class="partido-info">
			                    <p><strong>Boscos VS Cristo Rey</strong></p>
			                    <p>15:00h</p>
			                    <p>Social Plata</p>
			                </div>
			                <div class="partido-ubicacion">
			                    <p>Ubicación</p>
			                    <img src="https://img.icons8.com/material-outlined/24/000000/marker.png" alt="Ubicación Icono">
			                </div>
			            </div>
			            <div class="partido-card">
			                <div class="partido-fecha">
			                    <span>5 oct</span>
			                </div>
			                <div class="partido-info">
			                    <p><strong>Black Lions VS Cristo Rey</strong></p>
			                    <p>17:30h</p>
			                    <p>Social Plata</p>
			                </div>
			                <div class="partido-ubicacion">
			                    <p>Ubicación</p>
			                    <img src="https://img.icons8.com/material-outlined/24/000000/marker.png" alt="Ubicación Icono">
			                </div>
			            </div>
			            <div class="partido-card">
			                <div class="partido-fecha">
			                    <span>12 oct</span>			                </div>
			                <div class="partido-info">
			                    <p><strong>Cristo Rey VS Pepe Team</strong></p>
			                    <p>18:30h</p>
			                    <p>Social Plata</p>
			                </div>
			                <div class="partido-ubicacion">
			                    <p>Ubicación</p>
			                    <img src="https://img.icons8.com/material-outlined/24/000000/marker.png" alt="Ubicación Icono">
			                </div>
			            </div>
			        </div>
			
			        <div class="partido-clasificacion">
			            <h3>Clasificación</h3>
			            <table>
			                <thead>
			                    <tr>
			                        <th>Equipo</th>
			                        <th>PG</th>
			                        <th>PP</th>
			                    </tr>
			                </thead>
			                <tbody>
			                    <tr class="partido-primer-lugar">
			                        <td><strong>Boscos</strong></td>
			                        <td>10</td>
			                        <td>2</td>
			                    </tr>
			                    <tr>
			                        <td><strong>Cristo Rey</strong></td>
			                        <td>8</td>
			                        <td>4</td>
			                    </tr>
			                    <tr>
			                        <td><strong>Black Lions</strong></td>
			                        <td>5</td>
			                        <td>7</td>
			                    </tr>
			                </tbody>
			            </table>
			        </div>
			    </div>
		    </div>
		    <div class="datos-equipo">
                <div class="equipo-header">
                    <div class="equipo-background-img">
                        <img src="<%= request.getContextPath() %>/views/images/banner.png" alt="Background image">
                    </div>
                    <div class="equipo-info">
                        <div class="equipo-logo">
                            <div class="equipo-logo2">
                                <img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=FFFFFF" alt="Team Logo">
                            </div>
                            <div class="equipo-info">
                                <h1>Cristo Rey</h1>
                                <p>Social Plata</p>
                            </div>
                        </div>
                        <button class="equipo-follow-btn"><i class="fa fa-star"></i><strong>Seguido</strong></button>
                    </div>
                </div>
                <div class="equipo-menu">
                    <button class="equipo-team-btn active">Equipo</button>
                    <button class="equipo-calendar-btn">Calendario</button>
                </div>
                <div class="equipo-content">
                    <div class="equipo-team active"> <!-- Aquí 'active' para mostrar por defecto -->
                        <%@ include file="equipo_1.jsp" %>
                    </div>
                    <div class="equipo-calendar">
                        <%@ include file="equipo_2.jsp" %>
                    </div>
                </div>
            </div>
		    <div class="anuncio">
		    <img src="<%= request.getContextPath() %>/views/images/anuncio.png" alt="Anuncio Bianco Zavani">
	    	</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</div>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // Mostrar "Equipo" y ocultar "Calendario" al hacer clic en el botón de equipo
    document.querySelector('.equipo-team-btn').addEventListener('click', function() {
        document.querySelector('.equipo-team').classList.add('active');
        document.querySelector('.equipo-calendar').classList.remove('active');
        document.querySelector('.equipo-team-btn').classList.add('active');
        document.querySelector('.equipo-calendar-btn').classList.remove('active');
    });

    // Mostrar "Calendario" y ocultar "Equipo" al hacer clic en el botón de calendario
    document.querySelector('.equipo-calendar-btn').addEventListener('click', function() {
        document.querySelector('.equipo-team').classList.remove('active');
        document.querySelector('.equipo-calendar').classList.add('active');
        document.querySelector('.equipo-team-btn').classList.remove('active');
        document.querySelector('.equipo-calendar-btn').classList.add('active');
    });
});
</script>
</body>
</html>
