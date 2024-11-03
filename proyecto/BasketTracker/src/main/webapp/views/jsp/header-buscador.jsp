<head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/basketracker.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<%
    UsuarioVO usuarioheader = (UsuarioVO) session.getAttribute("usuario");
    boolean esJugadorHeader = JugadorDAO.existeJugador(usuarioheader.getNombreUsuario());
    String perfilUrl = esJugadorHeader ? "/views/jsp/perfil_jugador.jsp" : "/views/jsp/perfil_usuario.jsp";
%>
<div class="cabecera2-section">
    <div class="cabecera2-rect">
        <img class="cabecera2-image" src="<%= request.getContextPath() %>/views/images/logo.png" alt="Logo" onclick="window.location.href='<%= request.getContextPath() %>/views/jsp/inicio.jsp'">
        <form class="cabecera-search-form" action="<%= request.getContextPath() %>/SearchServlet" method="GET" onsubmit="return searchFunction(event)">
            <input type="text" id="search-input" class="cabecera-search-input" placeholder="Buscar..." name="search" onkeyup="buscarSugerencias()" required>
            <div class="suggestions-box" id="suggestions-box" style="display: none;"></div>
        </form>
    </div>
    <div class="user-account" onclick="toggleDropdown()">
        <img src="https://img.icons8.com/?size=100&id=11795&format=png&color=FFFFFF" alt="Mi Cuenta" class="user-icon">
        <span class="user-text"><b>Mi Cuenta</b></span>
        <div class="dropdown-menu" id="dropdownMenu">
            <% if (esJugadorHeader) { %>
                <a href="<%= request.getContextPath() %>/GuardarJugadorServlet?nombreJugador=<%= usuarioheader.getNombreUsuario() %>">Ver Mi Perfil</a>
            <% } else { %>
                <a href="<%= request.getContextPath() %>/views/jsp/perfil_usuario.jsp">Ver Mi Perfil</a>
            <% } %>
            <a href="<%= request.getContextPath() %>/CerrarSesionServlet">Cerrar Sesión</a>
        </div>
    </div>
</div>


<script>
// Función para abrir o cerrar el menú desplegable
function toggleDropdown() {
    const dropdown = document.getElementById("dropdownMenu");
    dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
}

// Cerrar el menú cuando se hace clic fuera de él
window.onclick = function(event) {
    const dropdown = document.getElementById("dropdownMenu");
    const userAccount = document.querySelector(".user-account");
    
    if (!userAccount.contains(event.target)) {
        dropdown.style.display = "none";
    }
}

// Enviar la búsqueda cuando se presiona Enter
function searchFunction(event) {
    event.preventDefault();
    const searchInput = document.getElementById("search-input").value;
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "<%= request.getContextPath() %>/SearchServlet?search=" + encodeURIComponent(searchInput), true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                displaySearchResults(xhr.responseText); // Mostrar los resultados de búsqueda como HTML
            } else {
                alert("Error en la búsqueda.");
            }
        }
    };
    xhr.send();
}

// Mostrar los resultados de búsqueda en la página
function displaySearchResults(results) {
    // Crear o actualizar una sección de resultados
    let resultsContainer = document.getElementById("searchResultsContainer");
    if (!resultsContainer) {
        resultsContainer = document.createElement("div");
        resultsContainer.id = "searchResultsContainer";
        document.body.appendChild(resultsContainer);
    }
    resultsContainer.innerHTML = "<h2>Resultados de búsqueda:</h2>";
    
    // Añadir cada resultado como un elemento en la lista y asignar su tipo
    results.forEach(result => {
        const resultItem = document.createElement("div");
        resultItem.classList.add("search-result-item");

        // Comprobar el tipo del resultado y añadir el evento `click`
        if (result.type === "jugador") {
            resultItem.textContent = `Jugador: ${result.name}`;
            resultItem.onclick = () => verMasHeader(result.name);
        } else if (result.type === "equipo") {
            resultItem.textContent = `Equipo: ${result.name}`;
            resultItem.onclick = () => verMasEquipoHeader(result.id);
        } else if (result.type === "competicion") {
            resultItem.textContent = `Competición: ${result.name}`;
            resultItem.onclick = () => verMasCompeticionHeader(result.name);
        }
        
        resultsContainer.appendChild(resultItem);
    });
}
// Buscar sugerencias en tiempo real mientras se escribe
function buscarSugerencias() {
    const query = document.getElementById("search-input").value;
    
    if (query.length === 0) {
        $("#suggestions-box").hide();
        return;
    }

    $.ajax({
        url: "<%= request.getContextPath() %>/BuscarSugerenciasServlet",
        type: "GET",
        data: { query: query },
        success: function(response) {
            let suggestionsBox = $("#suggestions-box");
            suggestionsBox.html(response); // Aquí se coloca el HTML que devuelve el servlet
            suggestionsBox.show();
        }
    });
}



// Seleccionar una sugerencia y enviar el formulario con esa sugerencia
function seleccionarSugerencia(text) {
    $("#search-input").val(text);
    $("#suggestions-box").hide();
    document.querySelector(".cabecera-search-form").submit();
}

// Cerrar el cuadro de sugerencias cuando se hace clic fuera de él
$(document).click(function(event) {
    if (!$(event.target).closest("#search-input, #suggestions-box").length) {
        $("#suggestions-box").hide();
    }
});

function verMasHeader(nombreJugador) { 
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/GuardarJugadorServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Redirigir a la página del perfil del jugador
                window.location.href = "<%= request.getContextPath() %>/views/jsp/perfil_jugador.jsp";
            } else {
                alert("Error al guardar el jugador.");
            }
        }
    };
    xhr.send("nombreJugador=" + encodeURIComponent(nombreJugador));
}

function verMasEquipoHeader(idEquipo) { 
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/GuardarEquipoServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Redirigir a la página del perfil del equipo
                window.location.href = "<%= request.getContextPath() %>/views/jsp/perfil_equipo.jsp";
            } else {
                alert("Error al guardar el equipo.");
            }
        }
    };
    xhr.send("idEquipo=" + encodeURIComponent(idEquipo));
}

function verMasCompeticionHeader(nombreCompeticion) { 
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%= request.getContextPath() %>/GuardarCompeticionServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Redirigir a la página del perfil de la competición
                window.location.href = "<%= request.getContextPath() %>/views/jsp/perfil_competicion.jsp";
            } else {
                alert("Error al guardar la competición.");
            }
        }
    };
    xhr.send("nombreCompeticion=" + encodeURIComponent(nombreCompeticion));
}
</script>
