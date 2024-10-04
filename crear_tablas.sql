CREATE TABLE USUARIO (
    nombre_usuario TEXT PRIMARY KEY,
    correo_elec TEXT NOT NULL,
    contrasenya TEXT NOT NULL,
);

CREATE TABLE JUGADOR (
    nombre_usuario REFERENCES USUARIO(nombre_usuario) PRIMARY KEY ON DELETE CASCADE,
    nombre_jugador TEXT,
    equipo REFERENCES EQUIPO(id_equipo) ON DELETE CASCADE
);

CREATE TABLE COMPETICION (
    nombre TEXT PRIMARY KEY
);

CREATE TABLE EQUIPO (
    id_equipo INTEGER PRIMARY KEY,
    nombre_equipo   TEXT,
    ubicacion TEXT,
    competicion REFERENCES COMPETICION(nombre) ON DELETE CASCADE
);

CREATE TABLE PARTIDO (
    id_partido INTEGER PRIMARY KEY,
    equipo_local REFERENCES EQUIPO(id_equipo) ON DELETE CASCADE,
    equipo_visitante REFERENCES EQUIPO(id_equipo) ON DELETE CASCADE,
    jornada INTEGER,
    pts_c1_local INTEGER CHECK (pts_c1_local >= 0),
    pts_c2_local INTEGER CHECK (pts_c2_local >= 0),
    pts_c3_local INTEGER CHECK (pts_c3_local >= 0),
    pts_c3_local INTEGER CHECK (pts_c4_local >= 0),
    pts_c1_visit INTEGER CHECK (pts_c1_visit >= 0),
    pts_c2_visit INTEGER CHECK (pts_c2_visit >= 0),
    pts_c3_visit INTEGER CHECK (pts_c3_visit >= 0),
    pts_c3_visit INTEGER CHECK (pts_c4_visit >= 0),
    CHECK (equipo_local != equipo_visitante)
);

CREATE TABLE PTS_JUG_PAR (
    id_partido REFERENCES PARTIDO(id_partido) ON DELETE CASCADE,
    id_jugador REFERENCES JUGADOR(id_jugador) ON DELETE CASCADE,
    pts_ant INTEGER CHECK (pts_ant >= 0 OR pts_ant IS NULL),
    trp_ant INTEGER CHECK (trp_ant >= 0 OR trp_ant IS NULL),
    tlb_lan INTEGER CHECK (tlb_lan >= 0 OR tlb_lan IS NULL),
    tlb_ant INTEGER CHECK (tlb_ant >= 0 OR tlb_ant IS NULL),
    faltas INTEGER CHECK (faltas >= 0 OR faltas IS NULL),
    mnt_jd INTEGER CHECK (mnt_jd >= 0 OR mnt_jd IS NULL),
    PRIMARY KEY(id_partido, id_jugador)
);

CREATE TABLE COMENTARIO (
    nombre_usuario REFERENCES USUARIO(nombre_usuario) ON DELETE CASCADE,
    id_partido REFERENCES PARTIDO(id_partido) ON DELETE CASCADE,
    comentario TEXT NOT NULL,
    PRIMARY KEY (nombre_usuario, id_partido, comentario)
);

CREATE TABLE JUGADOR_FAV (
    nombre_usuario REFERENCES USUARIO(nombre_usuario) ON DELETE CASCADE,
    jugador REFERENCES JUGADOR(nombre_usuario) ON DELETE CASCADE,
    PRIMARY KEY (nombre_usuario, jugador)
);

CREATE TABLE EQUIPO_FAV (
    nombre_usuario REFERENCES USUARIO(nombre_usuario) ON DELETE CASCADE,
    equipo REFERENCES EQUIPO(id_equipo) ON DELETE CASCADE,
    PRIMARY KEY (nombre_usuario, equipo)
);

CREATE TABLE COMPETICION_FAV (
    nombre_usuario REFERENCES USUARIO(nombre_usuario) ON DELETE CASCADE,
    competicion REFERENCES COMPETICION(nombre) ON DELETE CASCADE,
    PRIMARY KEY (nombre_usuario, equipo)
);

CREATE TABLE MENSAJE (
    id_mensaje INTEGER PRIMARY KEY,
    nombre_usuario REFERENCES JUGADOR(nombre_usuario) ON DELETE CASCADE,
    mensaje TEXT NOT NULL,
    hora TIME,
    fecha DATE
);
