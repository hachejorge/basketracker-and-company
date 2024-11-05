-- Insertar usuarios en la tabla USUARIO
INSERT INTO sisinf_db.USUARIO (nombre_usuario, correo_elec, password) VALUES 
    ('admin', 'admin@unizar.es', 'admin'),
    ('andrea', 'andrea@gmail.com', 'andrea123'),
    ('mario', 'mario@gmail.com', 'mario123'),
    ('jorge', 'jorge@gmail.com', 'jorge123'),
    ('lucia', 'lucia@gmail.com', 'lucia123'),
    ('pablo', 'pablo@gmail.com', 'pablo123'),
    ('sara', 'sara@gmail.com', 'sara123'),
    ('rafael', 'rafael@gmail.com', 'rafael123'),
    ('carla', 'carla@gmail.com', 'carla123'),
    ('andrea2', 'andrea2@gmail.com', 'andrea123'),
    ('mario2', 'mario2@gmail.com', 'mario123'),
    ('jose', 'jose@gmail.com', 'jose123'),
    ('alba', 'alba@gmail.com', 'alba123'),
    ('carlos', 'carlos@gmail.com', 'carlos123'),
    ('marina', 'marina@gmail.com', 'marina123');

-- Insertar competición
INSERT INTO sisinf_db.COMPETICION (nombre) VALUES 
    ('2a Aragonesa Femenina'),
    ('Social Plata'),
    ('3a Aragonesa Masculina');

-- Insertar equipos
INSERT INTO sisinf_db.EQUIPO (nombre_equipo, ubicacion, competicion) VALUES 
    ('Boscos', 'Zaragoza', '2a Aragonesa Femenina'),
    ('Cristo Rey', 'Zaragoza', '2a Aragonesa Femenina'),
    ('Black Lions', 'Zaragoza', '2a Aragonesa Femenina'),
    ('Valencia Flames', 'Valencia', '2a Aragonesa Femenina'),
    ('Madrid Titans', 'Madrid', '2a Aragonesa Femenina'),
    ('Barcelona Warriors', 'Barcelona', '2a Aragonesa Femenina'),
    ('Sevilla Sharks', 'Sevilla', '2a Aragonesa Femenina'),
    ('Bilbao Giants', 'Bilbao', '2a Aragonesa Femenina');

-- Insertar jugadores
INSERT INTO sisinf_db.JUGADOR (nombre_usuario, nombre_jugador, equipo) VALUES 
    ('andrea', 'Andrea Hernández Artal', 1),
    ('mario', 'Mario Ferradas Aznar', 1),
    ('jorge', 'Jorge Clavero Agudo', 2),
    ('lucia', 'Lucía García Pérez', 3),
    ('pablo', 'Pablo Martínez López', 4),
    ('sara', 'Sara Ruiz Martínez', 5),
    ('rafael', 'Rafael Torres Romero', 6),
    ('carla', 'Carla García Álvarez', 7),
    ('mario2', 'Mario López García', 3),
    ('andrea2', 'Andrea Fernández Torres', 2),
    ('jose', 'José Pérez López', 1),
    ('alba', 'Alba Torres Ramírez', 1),
    ('carlos', 'Carlos Díaz Martínez', 1),
    ('marina', 'Marina González Ruiz', 1);

INSERT INTO sisinf_db.PARTIDO (id_partido, equipo_local, equipo_visitante, jornada, 
    pts_c1_local, pts_c2_local, pts_c3_local, pts_c4_local, 
    pts_c1_visit, pts_c2_visit, pts_c3_visit, pts_c4_visit, hora, fecha) VALUES 
    (1, 1, 3, 1, 25, 30, 28, 20, 18, 20, 22, 30, '18:00', '2024-10-30'),
    (2, 2, 5, 1, 30, 28, 25, 23, 22, 20, 25, 24, '20:00', '2024-10-30'),
    (3, 4, 3, 1, 28, 30, 22, 24, 26, 24, 21, 22, '17:00', '2024-10-31'),
    (4, 6, 1, 2, 26, 22, 24, 20, 25, 27, 21, 20, '19:00', '2024-11-01'),
    (5, 5, 7, 2, 22, 30, 27, 29, 26, 20, 24, 22, '15:30', '2024-11-02'),
    (6, 8, 4, 2, 24, 25, 21, 20, 20, 22, 28, 24, '16:00', '2024-11-02'),
    (7, 3, 6, 3, 29, 25, 28, 26, 21, 23, 20, 27, '14:30', '2024-11-05'),
    (8, 1, 5, 3, 27, 29, 24, 22, 23, 21, 25, 30, '17:00', '2024-11-06'),
    (9, 7, 2, 3, 24, 26, 23, 25, 22, 24, 28, 22, '19:00', '2024-11-07'),
    (10, 8, 6, 4, 25, 27, 21, 24, 29, 26, 20, 21, '18:30', '2024-11-08'),
    (11, 2, 1, 4, 30, 25, 22, 28, 23, 29, 26, 24, '15:00', '2024-11-09'),
    (12, 4, 7, 4, 28, 24, 27, 30, 25, 22, 24, 21, '16:00', '2024-11-10'),
    (13, 5, 8, 5, 29, 30, 25, 27, 20, 22, 28, 25, '17:30', '2024-11-11'),
    (14, 3, 2, 5, 27, 23, 26, 28, 24, 25, 22, 20, '19:00', '2024-11-12'),
    (15, 1, 7, 5, 30, 28, 22, 29, 21, 20, 23, 26, '20:00', '2024-11-13'),
    (16, 6, 4, 6, 23, 25, 30, 27, 24, 28, 25, 20, '18:00', '2024-11-14'),
    (17, 8, 2, 6, 26, 24, 28, 25, 23, 21, 27, 24, '15:30', '2024-11-15'),
    (18, 5, 1, 6, 28, 27, 29, 23, 20, 25, 22, 24, '14:00', '2024-11-16'),
    (19, 7, 3, 7, 30, 23, 25, 27, 26, 28, 24, 22, '16:30', '2024-11-17'),
    (20, 4, 6, 7, 25, 24, 22, 30, 28, 20, 25, 26, '17:00', '2024-11-18'),
    (21, 2, 8, 7, 26, 27, 30, 28, 24, 25, 22, 21, '19:30', '2024-11-19'),
    (22, 1, 5, 8, 28, 26, 24, 27, 25, 23, 26, 30, '15:00', '2024-11-20'),
    (23, 3, 7, 8, 27, 28, 23, 25, 24, 26, 22, 20, '18:00', '2024-11-21'),
    (24, 6, 8, 9, 29, 25, 26, 22, 28, 24, 23, 21, '16:00', '2024-11-22'),
    (25, 2, 4, 9, 30, 23, 27, 24, 25, 28, 20, 22, '14:30', '2024-11-23'),
    (26, 5, 3, 9, 24, 27, 30, 26, 22, 25, 28, 21, '15:00', '2024-11-24'),
    (27, 8, 7, 10, 26, 28, 22, 24, 23, 25, 27, 29, '17:30', '2024-11-25'),
    (28, 1, 4, 10, 30, 22, 27, 29, 25, 24, 21, 26, '20:00', '2024-11-26'),
    (29, 6, 5, 10, 28, 30, 26, 24, 23, 22, 27, 25, '18:00', '2024-11-27'),
    (30, 3, 2, 10, 29, 23, 25, 28, 27, 26, 24, 20, '16:30', '2024-11-28');


-- Insertar estadísticas de jugadores en partidos
INSERT INTO sisinf_db.PTS_JUG_PAR (id_partido, nombre_usuario, pts_ant, trp_ant, tlb_lan, tlb_ant, faltas, mnt_jd) VALUES 
    (1, 'andrea', 10, 2, 5, 3, 1, 30),
    (1, 'mario', 15, 3, 4, 1, 2, 35),
    (2, 'jorge', 12, 4, 2, 0, 0, 28),
    (2, 'lucia', 8, 1, 6, 3, 1, 20),
    (3, 'pablo', 20, 5, 2, 2, 1, 25),
    (4, 'sara', 18, 3, 5, 1, 0, 22),
    (5, 'rafael', 22, 6, 3, 0, 1, 33),
    (6, 'carla', 14, 2, 7, 2, 0, 21);

-- Insertar jugadores favoritos de usuarios
INSERT INTO sisinf_db.JUGADOR_FAV (nombre_usuario, jugador) VALUES 
    ('andrea', 'andrea'),
    ('andrea', 'mario'),
    ('andrea', 'jorge'),
    ('andrea', 'lucia'),
    ('andrea', 'pablo'),
    ('andrea', 'rafael'),
    ('andrea', 'carla'),
    ('mario', 'lucia'),
    ('jorge', 'rafael'),
    ('lucia', 'sara'),
    ('pablo', 'carla');

-- Insertar equipos favoritos de usuarios
INSERT INTO sisinf_db.EQUIPO_FAV (nombre_usuario, equipo) VALUES 
    ('andrea', 1),
    ('andrea', 2),
    ('andrea', 3),
    ('andrea', 4),
    ('andrea', 5),
    ('mario', 4),
    ('jorge', 5),
    ('lucia', 6),
    ('pablo', 7),
    ('sara', 8);

-- Insertar competiciones favoritas de usuarios
INSERT INTO sisinf_db.COMPETICION_FAV (nombre_usuario, competicion) VALUES 
    ('andrea', '2a Aragonesa Femenina'),
    ('mario', '2a Aragonesa Femenina'),
    ('jorge', '2a Aragonesa Femenina'),
    ('lucia', '2a Aragonesa Femenina'),
    ('pablo', '2a Aragonesa Femenina');