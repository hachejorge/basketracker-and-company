<!-- views/jsp/header.jsp -->
<head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/basketracker.css">
</head>
<div class="cabecera2-section">
    <div class="cabecera2-rect">
        <img class="cabecera2-image" src="<%= request.getContextPath() %>/views/images/logo.png" alt="Logo" onclick="window.location.href='<%= request.getContextPath() %>/views/jsp/inicio.jsp'">
        <form class="cabecera-search-form" action="#" method="GET">
            <input type="text" class="cabecera-search-input" placeholder="Buscar..." name="search" required>
        </form>
    </div>
    <div class="user-account">
          <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=FFFFFF" alt="Mi Cuenta" class="user-icon">
          <span class="user-text"><b>Mi Cuenta</b></span>
    </div>
</div>
