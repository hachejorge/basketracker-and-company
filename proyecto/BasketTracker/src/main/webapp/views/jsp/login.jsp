<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de sesión</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
</head>
<body>
	<%@ include file="header.jsp" %>
    <div class="container">
        <div class="image-section">
			<img src="<%= request.getContextPath() %>/views/images/imagen-login.png" alt="Imagen de Baloncesto">
        </div>
        <div class="form-section">
            <h2>Iniciar Sesión</h2>
            <!-- El formulario envía los datos a un servlet llamado LoginServlet -->
            <form action="LoginServlet" method="post">
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
                    <a href="#">¿Has olvidado tu contraseña?</a>
                </div>
                <hr>
                <div class="form-group">
					<label>Todavía no tienes cuenta</label>
                    <button class ="register" type="submit">Registrarse</button>
                </div>
            </form>
        </div>
    </div>
	 <script>
        function togglePassword() {
            // Obtener el campo de contraseña y el icono
            var passwordInput = document.getElementById("password");
            var toggleIcon = document.getElementById("toggle-password");

            // Comprobar el tipo actual del campo de contraseña
            if (passwordInput.type === "password") {
                // Cambiar a texto y modificar el icono
                passwordInput.type = "text";
                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");
            } else {
                // Cambiar de vuelta a contraseña y restaurar el icono
                passwordInput.type = "password";
                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");
            }
        }
    </script>
</body>
</html>

