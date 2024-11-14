<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/admin_styles.css">
    <title>Inicio Administrador | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="thecontainer">
		<%@ include file="../headerAdmin.jsp" %>
		<div class="container-inicio">
		  <div class="recuadro">
		    <div class="navbar">
		        <div id="addCompe" class="navbar-item" onclick="showSection('addCompeSection')">
                	<i class="fa fa-plus-square"></i>
                    <span>Añadir Competición</span>
				</div>
                <div id="addTeam" class="navbar-item" onclick="showSection('addTeamSection')">
                    <i class="fa fa-plus-square"></i>
                    <span>Añadir Equipo</span>
                </div>
                <div id="home" class="navbar-item active" onclick="showSection('homeSection')">
                    <i class="fa fa-home" style="font-size: 19px;"></i>
                </div>
                <div id="addPlayer" class="navbar-item" onclick="showSection('addPlayerSection')">
                    <i class="fa fa-plus-square"></i>
                    <span>Añadir Jugador</span>
                </div>
                <div id="addMatch" class="navbar-item" onclick="showSection('addMatchSection')">
                    <i class="fa fa-plus-square"></i>
                    <span>Añadir Partido</span>
                </div>
		    </div>
	      </div>
	      
	      <!-- Contenedores de contenido para cada sección -->
          <div id="addCompeSection" class="content-section">
          <div class="container">
          	<div class="form-section">
	            <h2>Añadir Competición</h2>
				<form action="<%= request.getContextPath() %>/AddCompe" method="post">
	                <div class="form-group">
	                    <label for="competicion">Competición</label>
						<input type="text" id="competicion" name="competicion" placeholder="Nombre de la competición" required>
	                </div>
	                <div class="form-group save-button">
	                    <button type="submit">
	                    	<i class="fa fa-save"></i>
	                    	Guardar Competición
	                    </button>
	                </div>
	            </form>
	        </div>
          </div>
          </div>
          <div id="addTeamSection" class="content-section">
			<div class="container">
          	<div class="form-section">
	            <h2>Añadir Equipo</h2>
				<form action="<%= request.getContextPath() %>/AddTeam" method="post">
	                <div class="form-group">
	                    <label for="equipo">Nombre del equipo</label>
						<input type="text" id="nomTeam" name="nomTeam" placeholder="Nombre del equipo" required>
	                </div>
	                <div class="form-group">
	                    <label for="equipo">Ubicación de los partidos </label>
						<input type="text" id="ubicacion" name="ubicacion" placeholder="Dirección" required>
	                </div>
	                 <div class="form-group">
                    	<label for="competicion">Competición</label>
                    	<input type="text" id="competicionEquipo" name="competicionEquipo" placeholder="Buscar competición" autocomplete="off" oninput="buscarCompeticion(this.value)">
                    	<div id="sugerencias" class="suggestions"> <!-- Contenedor para las sugerencias -->
                    	</div> 
                	</div>
	                <div class="form-group save-button">
	                    <button type="submit">
	                    	<i class="fa fa-save"></i>
	                    	Guardar Equipo
	                    </button>
	                </div>
	            </form>
	        </div>
          	</div>
          </div>
		  <div id="homeSection" class="content-section active">
			<div class="container">
			<div class="form-section">
				<h2>Añadir estadística de un jugador</h2>
				<div class="form-group">
                    	<label for="jugadorStats">Jugador</label>
                    	<input type="text" id="jugador" name="jugador" placeholder="Buscar jugador" autocomplete="off" oninput="buscarJugador(this.value,1)">
                    	<div id="sugerenciasJugador" class="suggestions"> <!-- Contenedor para las sugerencias -->
                    	</div> 
                	</div>
			</div>
			</div>
          </div>
		  <div id="addPlayerSection" class="content-section">
            <div class="container">
          	<div class="form-section">
				<h2>Añadir Jugador  <i id="infoButton" class="fa fa-info-circle" onclick="toggleInfoPanel(event)"></i></h2>
				<!-- Panel de información -->
				<div id="infoPanel" class="info-panel">
				    Al crear un jugador, también se añade un usuario nuevo asociado a este jugador. Su nombre de usuario se compone de su nombre de jugador sin espacios junto con un número aleatorio entre el 1 y el 99.
				</div>

				<form action="<%= request.getContextPath() %>/AddPlayer" method="post">
	                <div class="form-group">
	                    <label for="jugador">Nombre del jugador</label>
						<input type="text" id="nomPlayer" name="nomPlayer" placeholder="Jugador" required>
	                </div>
	                 <div class="form-group">
                    	<label for="equipoJugador">Equipo</label>
                    	<input type="text" id="equipoJugador1" name="equipoJugador" placeholder="Buscar equipo" autocomplete="off" oninput="buscarEquipo(this.value,1)">
                    	<div id="sugerenciasEquipo1" class="suggestions"> <!-- Contenedor para las sugerencias -->
                    	</div> 
                	</div>
                	<div class="form-group" hidden="true">
                		<label for="idEquipoJugador">Id equipo</label>
                		<input type="number" id="idEquipoJugador1" name="idEquipoJugador">
                	</div>
	                <div class="form-group save-button">
	                    <button type="submit">
	                    	<i class="fa fa-save"></i>
	                    	Guardar Jugador
	                    </button>
	                </div>
	            </form>
	        </div>
          	</div>
		  </div>
		  <div id="addMatchSection" class="content-section">
		  <div class="container">
			<div class="form-section">
				<h2>Añadir Partido</h2>
				<form action="<%= request.getContextPath() %>/AddMatch" method="post">
	                <div class="form-group">
	                    <label for="equipoLocal">Equipo Local</label>
						<input type="text" id="equipoJugador2" name="equipoLocal" placeholder="Buscar equipo local" autocomplete="off" oninput="buscarEquipo(this.value,2)">
                    	<div id="sugerenciasEquipo2" class="suggestions"> <!-- Contenedor para las sugerencias -->
                    	</div> 
	                </div>
	                <div class="form-group" hidden="true">
                		<label for="idEquipoJugador">Id equipo</label>
                		<input type="number" id="idEquipoJugador2" name="idEquipoJugador2">
                	</div>
	                <div class="form-group">
	                    <label for="equipoVisitante">Equipo Visitante</label>
						<input type="text" id="equipoJugador3" name="equipoVisitante" placeholder="Buscar equipo visitante" autocomplete="off" oninput="buscarEquipo(this.value,3)">
                    	<div id="sugerenciasEquipo3" class="suggestions"></div> 
	                </div>
	                <div class="form-group" hidden="true">
                		<label for="idEquipoJugador">Id equipo</label>
                		<input type="number" id="idEquipoJugador3" name="idEquipoJugador3">
                	</div>
                	<div class="form-group">
	                    <label for="jornada">Jornada</label>
						<input type="number" id="jornadaPartido" name="jornadaPartido" min=1 max=42>
	                </div>
					<div class="form-group">
						<!-- Campo para seleccionar una fecha -->
						<label for="fecha">Fecha</label>
						<input type="date" id="fecha" name="fecha" required>
					</div>
					<div class="form-group">
						<!-- Campo para seleccionar una hora -->
						<label for="hora">Hora</label>
						<input type="time" id="hora" name="hora" required>
					</div>
					<div class="form-group">
						<label>Puntos por cuarto Equipo Local</label>
						<div class="match-points">
							<label for="c1local">C1</label>
							<input type="number" id="c1local" name="c1local" min="0" value="0" required>
							<label for="c2local">C2</label>
							<input type="number" id="c2local" name="c2local" min="0" value="0" required>
							<label for="c3local">C3</label>
							<input type="number" id="c3local" name="c3local" min="0" value="0" required>
							<label for="c4local">C4</label>
							<input type="number" id="c4local" name="c4local" min="0" value="0" required>
						</div>
					</div>
					<div class="form-group">
						<label>Puntos por cuarto Equipo Visitante</label>
						<div class="match-points">
							<label for="c1visit">C1</label>
							<input type="number" id="c1visit" name="c1visit" min="0" value="0" required>
							<label for="c2visit">C2</label>
							<input type="number" id="c2visit" name="c2visit" min="0" value="0" required>
							<label for="c3visit">C3</label>
							<input type="number" id="c3visit" name="c3visit" min="0" value="0" required>
							<label for="c4visit">C4</label>
							<input type="number" id="c4visit" name="c4visit" min="0" value="0" required>
						</div>
					</div>
	                
	                <div class="form-group save-button">
	                    <button type="submit">
	                    	<i class="fa fa-save"></i>
	                    	Guardar Partido
	                    </button>
	                </div>
	            </form>
          		</div>
          	</div>
          </div>
	      
		</div>
	</div>
	
	<script>
        function showSection(sectionId) {
            // Ocultar todas las secciones de contenido
            var sections = document.querySelectorAll('.content-section');
            sections.forEach(function(section) {
                section.classList.remove('active');
            });

            // Mostrar la sección seleccionada
            document.getElementById(sectionId).classList.add('active');

            // Actualizar el estado activo en la barra de navegación
            var navbarItems = document.querySelectorAll('.navbar-item');
            navbarItems.forEach(function(item) {
                item.classList.remove('active');
            });

            // Marcar como activa la sección seleccionada en el navbar
            var selectedNavItem = document.getElementById(sectionId.replace('Section', ''));
            selectedNavItem.classList.add('active');
        }
        
        // Obtener el valor del parámetro success de la URL
        const urlParams = new URLSearchParams(window.location.search);
        const event = urlParams.get('event');

        // Mostrar alerta si success no es null
        if (event) {
            alert(event);
        }
        
        function buscarJugador(termino) {
            if (termino.length === 0) {
                document.getElementById("sugerenciasJugador").innerHTML = "";
                return;
            }

            // Llamada AJAX para obtener sugerencias
            fetch('<%= request.getContextPath() %>/BuscarJugador?termino=' + encodeURIComponent(termino))
                .then(response => response.json())
                .then(data => {
                    var sugerenciasHTML = "";
                    data.forEach(jugador => {
						console.log(jugador.nombreJugador)
                        if (jugador.nombreJugador) {
                        	sugerenciasHTML += "<div class=\"suggestion-item\" onclick=\"window.location.href='<%= request.getContextPath() %>/views/jsp/admin/seleccionarPartido.jsp?nombreJugador=" + encodeURIComponent(jugador.nombreUsuario) + "'\">" 
                        							+ jugador.nombreJugador + 
                        							"<div class=\"suggestion-desc\">" +
                    									jugador.nombreUsuario + 
                    								"</div>" +
                        						"</div>";
                        } else {
        					console.error('Nombre de la competición es nulo o indefinido', competicion);
    					}
                    });
                    document.getElementById("sugerenciasJugador").innerHTML = sugerenciasHTML;
                })
                .catch(error => console.error('Error:', error));
        }
        
        function buscarCompeticion(termino) {
            if (termino.length === 0) {
                document.getElementById("sugerencias").innerHTML = "";
                return;
            }

            // Llamada AJAX para obtener sugerencias
            fetch('<%= request.getContextPath() %>/BuscarCompeticion?termino=' + encodeURIComponent(termino))
                .then(response => response.json())
                .then(data => {
                    var sugerenciasHTML = "";
                    data.forEach(competicion => {
                        if (competicion.nombre) {
                        	sugerenciasHTML += "<div class=\"suggestion-item\" onclick=\"seleccionarCompeticion('" + competicion.nombre + "')\">" + competicion.nombre + "</div>";

                        } else {
        					console.error('Nombre de la competición es nulo o indefinido', competicion);
    					}
                    });
                    document.getElementById("sugerencias").innerHTML = sugerenciasHTML;
                })
                .catch(error => console.error('Error:', error));
        }

        function seleccionarCompeticion(nombre) {
			document.getElementById("competicionEquipo").value = nombre;
			document.getElementById("sugerencias").innerHTML = "";
        }
        
        function buscarEquipo(termino, id) {
            if (termino.length === 0) {
            	
                document.getElementById("sugerenciasEquipo" + id).innerHTML = "";
                return;
            }

            // Llamada AJAX para obtener sugerencias
            fetch('<%= request.getContextPath() %>/BuscarEquipo?termino=' + encodeURIComponent(termino))
                .then(response => response.json())
                .then(data => {
                    var sugerenciasHTML = "";
                    //console.log(equipo)
                    data.forEach(equipo => {
                    	
                        if (equipo.nombreEquipo) {
                        	sugerenciasHTML += "<div class=\"suggestion-item\" onclick=\"seleccionarEquipo('" + equipo.nombreEquipo + "', '"  + equipo.idEquipo + "','" + id + "')\">" 
                        							+ equipo.nombreEquipo + 
                        							"<div class=\"suggestion-desc\">" +
                        								equipo.competicion + 
                        							"</div>" +
                        					   "</div>";

                        } else {
        					console.error('Nombre de la competición es nulo o indefinido', equipo);
    					}
                    });
                    document.getElementById("sugerenciasEquipo" + id).innerHTML = sugerenciasHTML;
                })
                .catch(error => console.error('Error:', error));
        }
        
        function seleccionarEquipo(nombre, id, idBuscador) {
			document.getElementById("equipoJugador" + idBuscador).value = nombre;
			document.getElementById("sugerenciasEquipo" + idBuscador).innerHTML = "";
			document.getElementById("idEquipoJugador" + idBuscador).value = id;
        }
        
        function toggleInfoPanel(event) {
            event.stopPropagation();

            var infoPanel = document.getElementById("infoPanel");
            if (infoPanel.classList.contains("open")) {
                infoPanel.classList.remove("open"); // Oculta el panel con la animación
            } else {
                infoPanel.classList.add("open"); // Muestra el panel con la animación
            }
        }


        
    </script>
	
</body>