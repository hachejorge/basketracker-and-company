<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Iniciar Sesión | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
</head>
<body>
	<div class="thecontainer">
		<%@ include file="header.jsp" %>
		<div class="container">
	        <div class="image-section">
	            <img src="<%= request.getContextPath() %>/views/images/imagen-login.png" alt="Imagen de Baloncesto">
	        </div>
	        <div class="form-section">
	            <h2>Iniciar Sesión</h2>
				<form action="<%= request.getContextPath() %>/LoginServlet" method="post">
	                <div class="form-group">
	                    <label for="usuario">Nombre de Usuario</label>
	                    <input type="text" id="usuario" name="usuario" placeholder="Usuario" required>
	                </div>
	                <div class="form-group">
	                    <label for="password">Contraseña</label>
	                    <input type="password" id="password" name="password" placeholder="Contraseña" required>
	                    <i class="fa fa-eye toggle-password" id="toggle-password" onclick="togglePassword()"></i>
	                </div>
            
	                <div class="form-group">
	                    <button type="submit">Iniciar Sesión</button>
	                </div>
	                <div class="form-links">
	                    <a href="<%= request.getContextPath() %>/views/jsp/recuperar_password.jsp">¿Has olvidado tu contraseña?</a>
	                </div>
	                <hr>
	                <div class="form-group">
	                    <label>Todavía no tienes cuenta</label>
	                    <button class="register" type="button" onclick="location.href='<%= request.getContextPath() %>/views/jsp/register.jsp'">Registrarse</button>
	                </div>
	            </form>
	        </div>
	    </div>
	    <%@ include file="footer.jsp" %>
    </div>
    
    

    <script>
	 	// Obtener el valor del parámetro success de la URL
	    const urlParams = new URLSearchParams(window.location.search);
	    const event = urlParams.get('event');
	
	    // Mostrar alerta si success no es null
	    if (event) {
	        alert(event);
	    }
    
        function togglePassword() {
            var passwordInput = document.getElementById("password");
            var toggleIcon = document.getElementById("toggle-password");

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
    </script>
    
</body>
</html>


