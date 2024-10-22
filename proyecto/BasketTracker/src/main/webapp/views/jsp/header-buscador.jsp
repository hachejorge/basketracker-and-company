<!-- views/jsp/header.jsp -->
<head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
</head>
<div class="cabecera2-section">
    <div class="cabecera2-rect">
        <img class="cabecera2-image" src="<%= request.getContextPath() %>/views/images/logo.png" alt="Logo">
        <form class="cabecera-search-form" action="#" method="GET">
            <input type="text" class="cabecera-search-input" placeholder="Buscar..." name="search" required>
        </form>
    </div>
    <div class="user-account">
          <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=000000" alt="Mi Cuenta" class="user-icon">
          <span class="user-text">Mi Cuenta</span>
    </div>
</div>
