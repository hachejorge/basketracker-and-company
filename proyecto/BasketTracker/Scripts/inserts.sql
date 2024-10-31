-- Insertar usuarios en la tabla USUARIO
INSERT INTO sisinf_db.USUARIO (nombre_usuario, correo_elec, password)
VALUES 
    ('admin', '872838@unizar.es', 'admin'),
    ('andrea', 'andrea@gmail.com', 'andrea'),
    ('mario', 'mario@gmail.com', 'mario'),
    ('jorge', 'jorge@gmail.com', 'jorge'),
    ('lucia', 'lucia@gmail.com', 'lucia'),
    ('pablo', 'pablo@gmail.com', 'pablo'),
    ('sara', 'sara@gmail.com', 'sara'),
    ('rafael', 'rafael@gmail.com', 'rafael'),   -- Nuevo usuario
    ('carla', 'carla@gmail.com', 'carla'),
    ('andrea2', 'andrea2@gmail.com', 'andrea'),
    ('mario2', 'mario2@gmail.com', 'mario');       -- Nuevo usuario

-- Crear competiciones
INSERT INTO sisinf_db.COMPETICION (nombre)
VALUES 
    ('2a Aragonesa Femenina'),
    ('Social Plata'),
    ('3a Aragonesa Masculina'),
    ('Copa Aragonesa Femenina'),
    ('Liga Nacional Masculina'),
    ('Torneo de Primavera');                       -- Nueva competición

-- Crear equipos
INSERT INTO sisinf_db.EQUIPO (id_equipo, nombre_equipo, ubicacion, competicion)
VALUES 
    (1, 'Boscos', 'Zaragoza', '2a Aragonesa Femenina'),
    (2, 'Cristo Rey', 'Zaragoza', 'Social Plata'),
    (3, 'Black Lions', 'Zaragoza', '2a Aragonesa Femenina'),
    (4, 'Valencia Flames', 'Valencia', 'Copa Aragonesa Femenina'),
    (5, 'Madrid Titans', 'Madrid', 'Liga Nacional Masculina'),
    (6, 'Barcelona Warriors', 'Barcelona', 'Torneo de Primavera'),  -- Nuevo equipo
    (7, 'Sevilla Sharks', 'Sevilla', 'Liga Nacional Masculina'),      -- Nuevo equipo
    (8, 'Bilbao Giants', 'Bilbao', 'Copa Aragonesa Femenina');       -- Nuevo equipo

-- Crear jugadores
INSERT INTO sisinf_db.JUGADOR (nombre_usuario, nombre_jugador, equipo)
VALUES 
    ('andrea', 'Andrea Hernández Artal', 1),  
    ('mario', 'Mario Ferradas Aznar', 1),     
    ('jorge', 'Jorge Clavero Agudo', 2),      
    ('lucia', 'Lucía García Pérez', 3),       
    ('pablo', 'Pablo Martínez López', 4),     
    ('sara', 'Sara Ruiz Martínez', 5),        
    ('rafael', 'Rafael Torres Romero', 6),    -- Nuevo jugador
    ('carla', 'Carla García Álvarez', 7),      -- Nuevo jugador
    ('mario2', 'Mario López García', 3), 
    ('andrea2', 'Andrea Fernández Torres', 2); 

-- Crear partidos (matches) con hora y fecha
INSERT INTO sisinf_db.PARTIDO (id_partido, equipo_local, equipo_visitante, jornada, 
    pts_c1_local, pts_c2_local, pts_c3_local, pts_c4_local, 
    pts_c1_visit, pts_c2_visit, pts_c3_visit, pts_c4_visit, hora, fecha)
VALUES 
    (1, 1, 3, 1, 25, 30, 28, 20, 18, 20, 22, 30, '18:00', '2024-10-30'),  -- Partido 1
    (2, 2, 5, 1, 30, 28, 25, 23, 22, 20, 25, 24, '20:00', '2024-10-30'),  -- Partido 2
    (3, 4, 3, 1, 28, 30, 22, 24, 26, 24, 21, 22, '17:00', '2024-10-31'),  -- Partido 3
    (4, 6, 1, 2, 26, 22, 24, 20, 25, 27, 21, 20, '19:00', '2024-11-01'),  -- Partido 4
    (5, 5, 7, 2, 22, 30, 27, 29, 26, 20, 24, 22, '15:30', '2024-11-02'),  -- Partido 5
    (6, 8, 4, 2, 24, 25, 21, 20, 20, 22, 28, 24, '16:00', '2024-11-02');  -- Partido 6


-- Crear estadísticas de jugadores en partidos
INSERT INTO sisinf_db.PTS_JUG_PAR (id_partido, nombre_usuario, pts_ant, trp_ant, tlb_lan, tlb_ant, faltas, mnt_jd)
VALUES 
    (1, 'andrea', 10, 2, 5, 3, 1, 30), 
    (2, 'andrea', 2, 2, 5, 3, 1, 10),
    (1, 'mario', 15, 3, 4, 1, 2, 35),   
    (2, 'jorge', 12, 4, 2, 0, 0, 28),    
    (2, 'lucia', 8, 1, 6, 3, 1, 20),      
    (3, 'pablo', 20, 5, 2, 2, 1, 25),      
    (4, 'sara', 18, 3, 5, 1, 0, 22),       
    (5, 'rafael', 22, 6, 3, 0, 1, 33),     
    (6, 'carla', 14, 2, 7, 2, 0, 21);      

-- Crear comentarios de usuarios en partidos
INSERT INTO sisinf_db.COMENTARIO (nombre_usuario, id_partido, comentario)
VALUES 
    ('admin', 1, 'Gran partido de Boscos, muy bien jugado.'),
    ('andrea', 1, 'Me gustó la estrategia del equipo.'),
    ('mario', 2, '¡Un partido emocionante!'),
    ('jorge', 3, 'El tercer cuarto fue decisivo.'),
    ('lucia', 4, 'Necesitamos mejorar la defensa.'),
    ('pablo', 5, 'Gran actuación de los jugadores.'),
    ('sara', 6, 'Increíble remontada del equipo.'),
    ('rafael', 4, 'El arbitraje fue muy cuestionable.'); 

-- Crear jugadores favoritos de usuarios
INSERT INTO sisinf_db.JUGADOR_FAV (nombre_usuario, jugador)
VALUES 
    ('andrea', 'andrea'),
    ('andrea', 'mario'),
    ('andrea', 'jorge'),
    ('andrea', 'lucia'),
    ('andrea', 'pablo'),
    ('andrea', 'rafael'),
    ('andrea', 'carla'),
    ('andrea', 'sara'), 
    ('mario', 'lucia'),     
    ('jorge', 'rafael'),    
    ('lucia', 'sara'),      
    ('pablo', 'carla');     
    
-- Crear equipos favoritos de usuarios
INSERT INTO sisinf_db.EQUIPO_FAV (nombre_usuario, equipo)
VALUES 
    ('andrea', 1),
    ('andrea', 2),
    ('andrea', 3),
    ('andrea', 4),
    ('andrea', 5),
    ('andrea', 6),
    ('andrea', 7),
    ('andrea', 8), 
    ('mario', 4),          
    ('jorge', 5),          
    ('lucia', 6),          
    ('pablo', 7),          
    ('sara', 8);          

-- Crear competiciones favoritas de usuarios
INSERT INTO sisinf_db.COMPETICION_FAV (nombre_usuario, competicion)
VALUES 
    ('andrea', '2a Aragonesa Femenina'),
    ('andrea', '3a Aragonesa Masculina'),
    ('andrea', 'Liga Nacional Masculina'),
    ('andrea', 'Torneo de Primavera'),
    ('andrea', 'Social Plata'),
    ('andrea', 'Copa Aragonesa Femenina'),
    ('mario', 'Liga Nacional Masculina'),
    ('jorge', 'Social Plata'),
    ('lucia', 'Torneo de Primavera'),
    ('pablo', '2a Aragonesa Femenina');      

-- Crear mensajes de usuarios
INSERT INTO sisinf_db.MENSAJE (id_mensaje, nombre_usuario, mensaje, hora, fecha)
VALUES 
    (1, 'andrea', '¡Vamos a ganar el próximo partido!', '10:00', '2024-10-30'),
    (2, 'mario', '¿Quién se apunta para el entrenamiento mañana?', '12:30', '2024-10-30'),
    (3, 'jorge', 'No olviden traer el equipo de entrenamiento.', '15:45', '2024-10-30'),
    (4, 'lucia', 'Gran partido ayer, sigamos así.', '09:15', '2024-10-30'),
    (5, 'pablo', 'Hay que mejorar la defensa en el próximo partido.', '11:00', '2024-10-30'),
    (6, 'sara', 'Estoy lista para el próximo partido.', '08:30', '2024-10-30'),
    (7, 'rafael', '¿Dónde nos reunimos para el próximo entrenamiento?', '14:00', '2024-10-30'),
    (8, 'carla', 'No olviden la estrategia para el próximo partido.', '18:00', '2024-10-30');
