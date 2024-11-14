-- Eliminar las tablas dependientes primero
DROP TABLE IF EXISTS sisinf_db.PTS_JUG_PAR;
DROP TABLE IF EXISTS sisinf_db.COMENTARIO;
DROP TABLE IF EXISTS sisinf_db.JUGADOR_FAV;
DROP TABLE IF EXISTS sisinf_db.EQUIPO_FAV;
DROP TABLE IF EXISTS sisinf_db.COMPETICION_FAV;
DROP TABLE IF EXISTS sisinf_db.MENSAJE;

-- Eliminar las tablas que tienen dependencias en otras
DROP TABLE IF EXISTS sisinf_db.PARTIDO;
DROP TABLE IF EXISTS sisinf_db.JUGADOR;
DROP TABLE IF EXISTS sisinf_db.EQUIPO;
DROP TABLE IF EXISTS sisinf_db.COMPETICION;
DROP TABLE IF EXISTS sisinf_db.tokens_restablecimiento;

-- Finalmente, eliminar las tablas independientes
DROP TABLE IF EXISTS sisinf_db.USUARIO;