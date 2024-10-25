<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Equipo | Basketracker</title>
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
			    </div>
		    </div>
		   	<div class="datos-jugador">
		        <div class="profile-header">
			        <div class="banner">
			            <img src="<%= request.getContextPath() %>/views/images/banner.png" alt="Banner image">
			        </div>
			        <div class="profile-content">
			            <div class="profile-details">
			                <h1>Jorge Clavero Agudo</h1>
			                <p>@jorgeclagu_22</p>
			            </div>
			            <div class="profile-picture">
			                <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Profile picture">
			            </div>
			            <div class="jugadores-seguidores">
			                 <button class="jugador-follow-btn"><i class="fa fa-star"></i><strong>Seguido</strong></button>
			                 <button class="jugador-seguidores-btn"><strong>22 seguidores</strong></button>
			            </div>
			            <div class="team">
			            	<img src="https://img.icons8.com/?size=100&id=t7crGJINSAvv&format=png&color=000000" alt="Profile picture">
			                <h2>Cristo Rey</h2>
			            </div>
			        </div>
			    </div>
		        <div class="jugador-stats-container">
		            <div class="jugador-last-game">
		                <h3>Último Partido</h3>
		                <div class="jugador-game-info">
		                    <div class="jugador-score">
		                        <span class="jugador-points">PTS</span>
		                        <span class="jugador-score-value">12</span>
		                    </div>
		                    <div class="jugador-minutes">
		                        <span>MIN</span>
		                        <span class="jugador-minutes-value">22:23</span>
		                    </div>
		                </div>
		                <div class="jugador-date">
		                    <p>28 sept</p>
		                    <p>15:00h</p>
		                </div>
		                <div class="jugador-location">
		                    <p>Boscos VS Cristo Rey</p>
		                    <div class="jugador-scoreboard">
		                        <span>52</span> - <span>45</span>
		                    </div>
		                </div>
		            </div>
		
		            <div class="jugador-historical">
		                <h3>Histórico</h3>
		                <div class="jugador-stats-grid">
		                    <div class="jugador-stat">
		                        <p>P/P</p>
		                        <p>9.3</p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p>MP</p>
		                        <p>19:12</p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p>PJ</p>
		                        <p>12</p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p>TL</p>
		                        <p>23</p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p>TRP</p>
		                        <p>7</p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p>RB</p>
		                        <p>15</p>
		                    </div>
		                    <div class="jugador-stat">
		                        <p>82%</p>
		                        <p>F/P</p>
		                        <p>2.7</p>
		                    </div>
		                </div>
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
