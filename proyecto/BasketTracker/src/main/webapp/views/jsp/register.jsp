<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Registrarse | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="thecontainer">
		<%@ include file="header.jsp" %>
		<div class="container">
	        <div class="image-section">
	            <img src="<%= request.getContextPath() %>/views/images/imagen-register.png" alt="Imagen de Baloncesto">
	        </div>
	        <div class="form-section">
	            <h2>Registrarse</h2>
	            <form action="LoginServlet" method="post">
	                <div class="form-group">
	                    <label for="usuario">Nombre de Usuario</label>
	                    <input type="text" id="usuario" name="usuario" placeholder="Usuario" required>
	                </div>
	                <div class="form-group">
	                    <label for="correo">Correo electrónico (@)</label>
	                    <input type="email" id="correo" name="correo" placeholder="Correo Electrónico" required>
	                </div>
	                <div class="form-group">
	                    <label for="password">Contraseña</label>
	                    <input type="password" id="password" name="password" placeholder="Contraseña" required>
	                    <i class="fa fa-eye toggle-password" id="toggle-password1" onclick="togglePassword('password', 'toggle-password1')"></i>
	                </div>
	                 <div class="form-group">
	                    <label for="repeat-password">Repetir Contraseña</label>
	                    <input type="password" id="repeat-password" name="repeat-password" placeholder="Contraseña" required>
	                    <i class="fa fa-eye toggle-password" id="toggle-password2" onclick="togglePassword('repeat-password', 'toggle-password2')"></i>
	                </div>
	                
				    <div class="outer-container">
				        <div class="checkbox-wrapper">
				            <label class="checkbox-label">
				                <input type="checkbox" id="jugador" name="jugador">
				                Jugador
				            </label>
				        </div>
				        <div class="search-container">
				            <input type="text" class="search-input" placeholder="Buscador" id="searchInput" disabled>
				            <button class="search-btn" id="searchBtn" disabled>
				                <i class="fa fa-search"></i>
				            </button>
				        </div>
				    </div>
	
	                <hr>
	                <div class="form-group">
	                    <button type="submit">Registrarse</button> <!-- Cambiado a submit -->
	                </div>
	            </form>
	        </div>
	    </div>
	    <%@ include file="footer.jsp" %> 
	</div>

    <script>
        // Función para alternar la visibilidad de la contraseña
        function togglePassword(passwordFieldId, toggleIconId) {
            var passwordInput = document.getElementById(passwordFieldId);
            var toggleIcon = document.getElementById(toggleIconId);

            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");
            } else {
                passwordInput.type = "password";
                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");
            }
        }

        document.addEventListener('DOMContentLoaded', function() {
            const checkbox = document.getElementById('jugador');
            const searchInput = document.getElementById('searchInput');
            const searchBtn = document.getElementById('searchBtn');

            // Función para habilitar o deshabilitar el campo de búsqueda
            function toggleSearchInput() {
                if (checkbox.checked) {
                    searchInput.disabled = false; // Habilitar el campo de búsqueda
                    searchBtn.disabled = false;   // Habilitar el botón
                    searchInput.style.opacity = 1; // Hacer visible el campo de búsqueda
                    searchBtn.style.opacity = 1;   // Hacer visible el botón
                } else {
                    searchInput.disabled = true;  // Deshabilitar el campo de búsqueda
                    searchBtn.disabled = true;    // Deshabilitar el botón
                    searchInput.style.opacity = 0.5; // Reducir opacidad para indicar que está deshabilitado
                    searchBtn.style.opacity = 0.5;   // Reducir opacidad para indicar que está deshabilitado
                }
            }

            // Añadir un evento 'change' para la casilla de verificación
            checkbox.addEventListener('change', toggleSearchInput);
            
            // Inicializar el estado al cargar la página
            toggleSearchInput();
        });	
    </script>
    
</body>
</html>
