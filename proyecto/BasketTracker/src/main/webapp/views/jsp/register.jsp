<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
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
            <h2>Registrarse</h2>
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label for="usuario">Nombre de Usuario</label>
                    <input type="text" id="usuario" name="usuario" placeholder="Usuario" required>
                </div>
                <div class="form-group">
                    <label for="usuario">Correo electrónico (@)</label>
                    <input type="email" id="correo" name="correo" placeholder="Correo Electrónico" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="password" id="password" name="password" placeholder="Contraseña" required>
                    <i class="fa fa-eye toggle-password" id="toggle-password" onclick="togglePassword()"></i>
                </div>
                 <div class="form-group">
                    <label for="password">Repetir Contraseña</label>
                    <input type="password" id="password" name="password" placeholder="Contraseña" required>
                    <i class="fa fa-eye toggle-password" id="toggle-password" onclick="togglePassword()"></i>
                </div>
                
                <div class="search-container">
			        <img src="https://via.placeholder.com/20" alt="Jupyter Icon" class="icon"> <!-- You can replace this link with the actual icon -->
			        <input type="text" class="search-input" placeholder="Buscador">
			        <button class="search-btn">
			            <i class="fa fa-search"></i>
			        </button>
			    </div>
                <hr>
                <div class="form-group">
                    <button type="button" onclick="location.href='RegistroServlet'">Registrarse</button>
                </div>
            </form>
        </div>
    </div>

    <script>
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


