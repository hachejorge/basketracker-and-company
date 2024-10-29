INSERT INTO sisinf_db.USUARIO (nombre_usuario, correo_elec, password)
VALUES ('admin', '872838@unizar.es', 'admin'),
		('andrea', 'andrea@gmail.com', 'andrea'),
		('mario', 'mario@gmail.com', 'mario'),
		('jorge', 'jorge@gmail.com', 'jorge');
		
-- Crear la competición
INSERT INTO sisinf_db.COMPETICION (nombre)
VALUES ('2a Aragonesa Femenina'),
		('Social Plata'),
		('3a Aragonesa Masculina');

-- Crear el equipo
INSERT INTO sisinf_db.EQUIPO (id_equipo, nombre_equipo, ubicacion, competicion)
VALUES (1, 'Boscos', 'Zaragoza', '2a Aragonesa Femenina'),
		(2, 'Cristo Rey', 'Zaragoza', 'Social Plata'),
		(3, 'Black Lions', 'Zaragoza', '2a Aragonesa Femenina');
		
-- Crear los jugadores
INSERT INTO sisinf_db.JUGADOR (nombre_usuario, nombre_jugador, equipo)
VALUES 
    ('andrea', 'Andrea Hernández Artal', 1),  -- Jugador 1
    ('mario', 'Mario Ferradas Aznar', 1),  -- Jugador 2
    ('jorge', 'Jorge Clavero Agudo', 2);  -- Jugador 3
		

    
INSERT INTO sisinf_db.competicion_fav (nombre_usuario, competicion)
VALUES 
    ('admin', '2a Aragonesa Femenina'),
    ('admin', '3a Aragonesa Masculina');
   
INSERT INTO sisinf_db.jugador_fav (nombre_usuario, jugador)
VALUES 
    ('admin', 'andrea'),
    ('admin', 'mario');
   
INSERT INTO sisinf_db.equipo_fav (nombre_usuario, equipo)
VALUES 
    ('admin', '1'),
    ('admin', '2');
