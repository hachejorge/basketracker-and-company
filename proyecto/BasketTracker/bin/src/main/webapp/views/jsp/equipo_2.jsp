<!-- views/jsp/equipo_2.jsp -->
<head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/stylesheets/stylesheet.css">
</head>
   <div class="calendario-calendar">
       <div class="calendario-header">
           <button id="prevMonth" class="calendario-button">&lt;</button>
           <h1 id="monthYear" class="calendario-month-year"></h1>
           <button id="nextMonth" class="calendario-button">&gt;</button>
       </div>
       <div class="calendario-days">
           <div class="calendario-day">Dom</div>
           <div class="calendario-day">Lun</div>
           <div class="calendario-day">Mar</div>
           <div class="calendario-day">Mié</div>
           <div class="calendario-day">Jue</div>
           <div class="calendario-day">Vie</div>
           <div class="calendario-day">Sáb</div>
       </div>
       <div class="calendario-dates" id="dateContainer"></div>
   </div>

   <script>
   const monthYear = document.getElementById("monthYear");
   const dateContainer = document.getElementById("dateContainer");
   const prevMonthBtn = document.getElementById("prevMonth");
   const nextMonthBtn = document.getElementById("nextMonth");

   let currentDate = new Date();

   function renderCalendar(date) {
       dateContainer.innerHTML = "";
       const year = date.getFullYear();
       const month = date.getMonth();

       monthYear.textContent = date.toLocaleString('default', { month: 'long', year: 'numeric' });

       const firstDay = new Date(year, month, 1).getDay();
       const daysInMonth = new Date(year, month + 1, 0).getDate();

       for (let i = 0; i < firstDay; i++) {
           const emptyDiv = document.createElement("div");
           emptyDiv.classList.add("calendario-date", "empty");
           dateContainer.appendChild(emptyDiv);
       }

       for (let day = 1; day <= daysInMonth; day++) {
           const dayDiv = document.createElement("div");
           dayDiv.classList.add("calendario-date");
           dayDiv.textContent = day;
           dateContainer.appendChild(dayDiv);
       }
   }

   prevMonthBtn.addEventListener("click", () => {
       currentDate.setMonth(currentDate.getMonth() - 1);
       renderCalendar(currentDate);
   });

   nextMonthBtn.addEventListener("click", () => {
       currentDate.setMonth(currentDate.getMonth() + 1);
       renderCalendar(currentDate);
   });

   // Renderizar el calendario por primera vez
   renderCalendar(currentDate);
   </script>