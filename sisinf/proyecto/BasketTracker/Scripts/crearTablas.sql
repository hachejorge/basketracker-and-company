-- Crear la tabla USUARIO
CREATE TABLE sisinf.USUARIO (
    nombre_usuario TEXT PRIMARY KEY,
    correo_elec TEXT,
    password TEXT
);

-- Crear la tabla COMPETICION
CREATE TABLE sisinf.COMPETICION (
    nombre TEXT PRIMARY KEY
);

-- Crear la tabla EQUIPO
CREATE TABLE sisinf.EQUIPO (
    id_equipo SERIAL PRIMARY KEY,  -- SERIAL genera automáticamente una secuencia y un valor único
    nombre_equipo text NOT NULL,
    ubicacion TEXT,
    competicion TEXT REFERENCES sisinf.COMPETICION(nombre) ON DELETE CASCADE
);


-- Crear la tabla JUGADOR
CREATE TABLE sisinf.JUGADOR (
    nombre_usuario TEXT PRIMARY KEY REFERENCES sisinf.USUARIO(nombre_usuario) ON DELETE CASCADE,
    nombre_jugador TEXT,
    equipo INTEGER REFERENCES sisinf.EQUIPO(id_equipo) ON DELETE CASCADE
);

-- Crear la tabla PARTIDO
CREATE TABLE sisinf.PARTIDO (
    id_partido SERIAL PRIMARY KEY,
    equipo_local INTEGER REFERENCES sisinf.EQUIPO(id_equipo) ON DELETE CASCADE,
    equipo_visitante INTEGER REFERENCES sisinf.EQUIPO(id_equipo) ON DELETE CASCADE,
    jornada INTEGER,
    pts_c1_local INTEGER CHECK (pts_c1_local >= 0),
    pts_c2_local INTEGER CHECK (pts_c2_local >= 0),
    pts_c3_local INTEGER CHECK (pts_c3_local >= 0),
    pts_c4_local INTEGER CHECK (pts_c4_local >= 0),
    pts_c1_visit INTEGER CHECK (pts_c1_visit >= 0),
    pts_c2_visit INTEGER CHECK (pts_c2_visit >= 0),
    pts_c3_visit INTEGER CHECK (pts_c3_visit >= 0),
    pts_c4_visit INTEGER CHECK (pts_c4_visit >= 0),
    hora TIME,
    fecha DATE,
    CHECK (equipo_local != equipo_visitante)
);

-- Crear la tabla PTS_JUG_PAR
CREATE TABLE sisinf.PTS_JUG_PAR (
    id_partido INTEGER REFERENCES sisinf.PARTIDO(id_partido) ON DELETE CASCADE,
    nombre_usuario TEXT REFERENCES sisinf.JUGADOR(nombre_usuario) ON DELETE CASCADE,
    pts_ant INTEGER CHECK (pts_ant >= 0 OR pts_ant IS NULL),
    trp_ant INTEGER CHECK (trp_ant >= 0 OR trp_ant IS NULL),
    tlb_lan INTEGER CHECK (tlb_lan >= 0 OR tlb_lan IS NULL),
    tlb_ant INTEGER CHECK (tlb_ant >= 0 OR tlb_ant IS NULL),
    faltas INTEGER CHECK (faltas >= 0 OR faltas IS NULL),
    mnt_jd INTEGER CHECK (mnt_jd >= 0 OR mnt_jd IS NULL),
    PRIMARY KEY(id_partido, nombre_usuario)
);

-- Crear la tabla COMENTARIO
CREATE TABLE sisinf.COMENTARIO (
    nombre_usuario TEXT REFERENCES sisinf.USUARIO(nombre_usuario) ON DELETE CASCADE,
    id_partido INTEGER REFERENCES sisinf.PARTIDO(id_partido) ON DELETE CASCADE,
    comentario TEXT NOT NULL,
    PRIMARY KEY (nombre_usuario, id_partido, comentario)
);

-- Crear la tabla JUGADOR_FAV
CREATE TABLE sisinf.JUGADOR_FAV (
    nombre_usuario TEXT REFERENCES sisinf.USUARIO(nombre_usuario) ON DELETE CASCADE,
    jugador TEXT REFERENCES sisinf.JUGADOR(nombre_usuario) ON DELETE CASCADE,
    PRIMARY KEY (nombre_usuario, jugador)
);

-- Crear la tabla EQUIPO_FAV
CREATE TABLE sisinf.EQUIPO_FAV (
    nombre_usuario TEXT REFERENCES sisinf.USUARIO(nombre_usuario) ON DELETE CASCADE,
    equipo INTEGER REFERENCES sisinf.EQUIPO(id_equipo) ON DELETE CASCADE,
    PRIMARY KEY (nombre_usuario, equipo)
);

-- Crear la tabla COMPETICION_FAV
CREATE TABLE sisinf.COMPETICION_FAV (
    nombre_usuario TEXT REFERENCES sisinf.USUARIO(nombre_usuario) ON DELETE CASCADE,
    competicion TEXT REFERENCES sisinf.COMPETICION(nombre) ON DELETE CASCADE,
    PRIMARY KEY (nombre_usuario, competicion)
);

-- Crear la tabla MENSAJE
CREATE TABLE sisinf.MENSAJE (
    id_mensaje INTEGER PRIMARY KEY,
    nombre_usuario TEXT REFERENCES sisinf.JUGADOR(nombre_usuario) ON DELETE CASCADE,
    mensaje TEXT NOT NULL,
    hora TIME,
    fecha DATE
);

CREATE TABLE sisinf.tokens_restablecimiento (
    nombre_usuario VARCHAR(255) NOT NULL,
    token VARCHAR(255) NOT NULL,
    fecha_expiracion TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (nombre_usuario),
    FOREIGN KEY (nombre_usuario) REFERENCES sisinf.usuario(nombre_usuario)
);

