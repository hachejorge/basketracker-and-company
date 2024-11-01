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
                    <span>A�adir Competici�n</span>
				</div>
                <div id="addTeam" class="navbar-item" onclick="showSection('addTeamSection')">
                    <i class="fa fa-plus-square"></i>
                    <span>A�adir Equipo</span>
                </div>
                <div id="home" class="navbar-item active" onclick="showSection('homeSection')">
                    <i class="fa fa-home" style="font-size: 19px;"></i>
                </div>
                <div id="addPlayer" class="navbar-item" onclick="showSection('addPlayerSection')">
                    <i class="fa fa-plus-square"></i>
                    <span>A�adir Jugador</span>
                </div>
                <div id="addMatch" class="navbar-item" onclick="showSection('addMatchSection')">
                    <i class="fa fa-plus-square"></i>
                    <span>A�adir Partido</span>
                </div>
		    </div>
	      </div>
	      
	      <!-- Contenedores de contenido para cada secci�n -->
          <div id="addCompeSection" class="content-section">
          <div class="container">
          	<div class="form-section">
	            <h2>A�adir Competici�n</h2>
				<form action="<%= request.getContextPath() %>/AddCompe" method="post">
	                <div class="form-group">
	                    <label for="competicion">Competici�n</label>
						<input type="text" id="usuario" name="usuario" placeholder="Nombre de la competici�n" required>
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
          <div id="addTeamSection" class="content-section">
			<h2>A�adir Equipo</h2>
			<p>Contenido para a�adir un equipo...</p>
          </div>
		  <div id="homeSection" class="content-section active">
			<h2>Inicio</h2>
			<p>Contenido de la p�gina principal...</p>
          </div>
		  <div id="addPlayerSection" class="content-section">
            <h2>A�adir Jugador</h2>
            <p>Contenido para a�adir un jugador...</p>
		  </div>
		  <div id="addMatchSection" class="content-section">
			<h2>A�adir Partido</h2>
			<p>Contenido para a�adir un partido...</p>
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

            // Mostrar la secci�n seleccionada
            document.getElementById(sectionId).classList.add('active');

            // Actualizar el estado activo en la barra de navegaci�n
            var navbarItems = document.querySelectorAll('.navbar-item');
            navbarItems.forEach(function(item) {
                item.classList.remove('active');
            });

            // Marcar como activa la secci�n seleccionada en el navbar
            var selectedNavItem = document.getElementById(sectionId.replace('Section', ''));
            selectedNavItem.classList.add('active');
        }
    </script>
	
</body>