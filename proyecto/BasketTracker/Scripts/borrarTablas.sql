-- Eliminar las tablas dependientes primero
DROP TABLE IF EXISTS sisinf.PTS_JUG_PAR;
DROP TABLE IF EXISTS sisinf.COMENTARIO;
DROP TABLE IF EXISTS sisinf.JUGADOR_FAV;
DROP TABLE IF EXISTS sisinf.EQUIPO_FAV;
DROP TABLE IF EXISTS sisinf.COMPETICION_FAV;
DROP TABLE IF EXISTS sisinf.MENSAJE;

-- Eliminar las tablas que tienen dependencias en otras
DROP TABLE IF EXISTS sisinf.PARTIDO;
DROP TABLE IF EXISTS sisinf.JUGADOR;
DROP TABLE IF EXISTS sisinf.EQUIPO;
DROP TABLE IF EXISTS sisinf.COMPETICION;
DROP TABLE IF EXISTS sisinf.tokens_restablecimiento;

-- Finalmente, eliminar las tablas independientes
DROP TABLE IF EXISTS sisinf.USUARIO;