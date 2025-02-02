<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
    <title>Ayuda y contacto | Basketracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <div class="thecontainer">
        <%@ include file="header.jsp" %>
        <div class="olvidar-container">
            <div class="ayudas-section">
                <h1>Ayuda y Contacto</h1>
                <h2>Si necesitas ayuda:</h2>
                <div class="ayudas-text">
                    <p>Puedes llamar al teléfono de contacto: 976 0123 456</p>
                    <p>El horario de respuesta es 10:00-19:00 en días laborables.</p>
                </div>
                <h2>Envíanos un mensaje:</h2>
                <form action="<%= request.getContextPath() %>/MandarCorreoAyudaContactoServlet" method="post">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" placeholder="Nombre" required>
                    </div>
                    <div class="form-group">    
                        <label for="correo">Correo electrónico:</label>
                        <input type="email" id="correo" name="correo" placeholder="Correo" required>
                    </div>
                    <div class="form-group">    
                        <label for="mensaje">Mensaje:</label>
                        <textarea id="mensaje" name="mensaje" placeholder="Escribe tu mensaje aquí" required></textarea>
                    </div>
                    <div class="form-group">
                        <button type="submit">Enviar</button>
                    </div>
                </form>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
    </div>
</body>
<script>
	if (event) {
		alert(event);
	}

</script>
</html>
