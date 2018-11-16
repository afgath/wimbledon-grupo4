/*CREATE DATABASE wimbledon
  WITH ENCODING='UTF8'
       OWNER=postgres
       CONNECTION LIMIT=2;

USE wimbliden;*/

DROP TABLE drawIntegrantes CASCADE;
DROP TABLE draw CASCADE;
DROP TABLE torneo CASCADE;
DROP TABLE tipoDraw CASCADE;
DROP TABLE torneo CASCADE;
DROP TABLE equipo CASCADE;
DROP TABLE jugador CASCADE;
DROP TABLE usuario CASCADE;
DROP TABLE tipoUsuario CASCADE;
DROP TABLE tipoCancha CASCADE;
DROP TABLE cancha CASCADE;
DROP TABLE ronda CASCADE;
DROP TABLE reservaCancha CASCADE;
DROP TABLE partido CASCADE;

CREATE TABLE IF NOT EXISTs tipoCancha (
	idTipoCancha BIGSERIAL NOT NULL PRIMARY KEY,
	tipo VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS cancha (
	idCancha BIGSERIAL NOT NULL PRIMARY KEY,
	nombre	VARCHAR(30) NOT NULL,
	tipo	INT REFERENCES tipoCancha (idTipoCancha),	
	disponible BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS tipoUsuario (
	idTipoUsuario BIGSERIAL NOT NULL PRIMARY KEY,
	tipoUsuario VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
	idUsuario BIGSERIAL NOT NULL PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	apellido VARCHAR(30) NOT NULL,
	tipo INT REFERENCES tipoUsuario (idTipoUsuario),
	login VARCHAR(15) NOT NULL,
	contrasena VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS jugador (
	idJugador BIGSERIAL NOT NULL PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	apellido VARCHAR(30) NOT NULL,
	rankeado BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS equipo (
	idEquipo BIGSERIAL NOT NULL PRIMARY KEY,
	jugador1 INT REFERENCES jugador (idJugador),
	jugador2 INT REFERENCES jugador (idJugador)
);

CREATE TABLE IF NOT EXISTS torneo (
	idTorneo BIGSERIAL NOT NULL PRIMARY KEY,
	anio VARCHAR(4) NOT NULL
);

CREATE TABLE IF NOT EXISTS tipoDraw (
	idTipo BIGSERIAL NOT NULL PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS draw (
	idDraw BIGSERIAL NOT NULL PRIMARY KEY,
	torneo INT REFERENCES torneo (idTorneo),
	tipo INT REFERENCES tipoDraw (idTipo)
);

CREATE TABLE IF NOT EXISTS drawIntegrantes (
	idDrawIntegrantes BIGSERIAL NOT NULL PRIMARY KEY,
	jugador INT REFERENCES jugador (idJugador),
	equipo INT REFERENCES equipo (idEquipo),
	draw INT REFERENCES draw (idDraw),
	puntos INT,
	premio NUMERIC
	
);

CREATE TABLE IF NOT EXISTS ronda (
	idRonda BIGSERIAL NOT NULL PRIMARY KEY,
	numeroRonda INT NOT NULL,
	premioDinero NUMERIC NOT NULL,
	premioPuntos INT NOT NULL,
	draw INT REFERENCES draw (idDraw),
	unique(numeroRonda, draw)
);

CREATE TABLE IF NOT EXISTS partido (
	idPartido BIGSERIAL NOT NULL PRIMARY KEY,
	fecha TIMESTAMP,
	cancha INT REFERENCES cancha (idCancha),
	jugador1 INT REFERENCES jugador (idJugador),
	jugador2 INT REFERENCES jugador (idJugador),
	equipo1 INT REFERENCES equipo (idEquipo),
	equipo2 INT REFERENCES equipo (idEquipo),
	draw INT REFERENCES draw (idDraw),
	set1 VARCHAR(10),
	set2 VARCHAR(10),
	set3 VARCHAR(10),
	jugadorGanador INT REFERENCES jugador (idJugador),
	equipoGanador INT REFERENCES equipo (idEquipo),
	ronda INT REFERENCES ronda (idRonda)
);

CREATE TABLE IF NOT EXISTS reservaCancha (
	idReservaCancha BIGSERIAL NOT NULL PRIMARY KEY,
	cancha INT REFERENCES cancha (idCancha),
	partido INT REFERENCES partido (idPartido),
	fechaInicio TIMESTAMP NOT NULL,
	fechaFin TIMESTAMP NOT NULL
);

INSERT INTO tipoUsuario (tipoUsuario) VALUES ('REFERI');
INSERT INTO tipoUsuario (tipoUsuario) VALUES ('DIRECTOR');

INSERT INTO usuario (nombre, apellido, tipo, login, contrasena) VALUES ('DARIO', 'GONZALEZ', 1, 'DGONZALES', '12345');
INSERT INTO usuario (nombre, apellido, tipo, login, contrasena) VALUES ('JUAN', 'PEREZ', 1, 'JPEREZ', '12345');
INSERT INTO usuario (nombre, apellido, tipo, login, contrasena) VALUES ('DANIEL', 'BUITRAGO', 2, 'DBUITRAGO', '12345');

INSERT INTO tipoCancha (tipo) VALUES ('ARENA');
INSERT INTO tipoCancha (tipo) VALUES ('GRAVA');
INSERT INTO tipoCancha (tipo) VALUES ('CESPED');
INSERT INTO tipoCancha (tipo) VALUES ('ASFALTO');

INSERT INTO cancha (nombre, tipo, disponible) VALUES ('SIN ASIGNAR', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 1', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 2', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 3', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 4', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 5', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 6', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 7', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 8', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 9', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 10', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 11', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 12', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 13', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 14', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 15', 1, true);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 16', 1, false);
INSERT INTO cancha (nombre, tipo, disponible) VALUES ('TENNIS ESTADIO 17', 1, true);

--24
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('FERNANDO', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('DANIEL', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('JOSE', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('JOHAN', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('PEDRO', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('JULIAN', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('HUGO', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('FABIO', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('ALIRIO', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('GABRIEL', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('EDUARDO', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('JUAN', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('INGRID', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('VANESSA', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('LUISA', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('FERNANDA', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('MARIA', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('GABRIELA', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('JULIANA', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('DANIELA', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('DIANA', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('RAMIRO', 'GARCIA', true);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('JOSEFINA', 'GARCIA', false);
INSERT INTO jugador (nombre, apellido, rankeado) VALUES ('YURI', 'GARCIA', false);

INSERT INTO torneo (anio) VALUES ('2018');
INSERT INTO torneo (anio) VALUES ('2017');
INSERT INTO torneo (anio) VALUES ('2016');

INSERT INTO tipoDraw (nombre) VALUES ('DRAW SOLO HOMBRES'); --1
INSERT INTO tipoDraw (nombre) VALUES ('DRAW SOLO MUJERES'); --2
INSERT INTO tipoDraw (nombre) VALUES ('DRAW EQUIPO HOMBRES'); --3
INSERT INTO tipoDraw (nombre) VALUES ('DRAW EQUIPO MUJERES'); --4
INSERT INTO tipoDraw (nombre) VALUES ('DRAW EQUIPO MIXTO'); --5

INSERT INTO draw (torneo, tipo) VALUES (1, 1);
INSERT INTO draw (torneo, tipo) VALUES (1, 2);
INSERT INTO draw (torneo, tipo) VALUES (2, 3);
INSERT INTO draw (torneo, tipo) VALUES (2, 4);
INSERT INTO draw (torneo, tipo) VALUES (2, 1);
INSERT INTO draw (torneo, tipo) VALUES (3, 1);

INSERT INTO drawIntegrantes (jugador, draw, puntos, premio) VALUES (1, 1, 5, 0);
INSERT INTO drawIntegrantes (jugador, draw, puntos, premio) VALUES (1, 3, 5, 1300);
INSERT INTO drawIntegrantes (jugador, draw, puntos, premio) VALUES (1, 2, 5, 0);
INSERT INTO drawIntegrantes (jugador, draw, puntos, premio) VALUES (1, 5, 5, 300);

--INSERT INTO drawIntegrantes (equipo, draw) VALUES ();

INSERT INTO ronda (numeroRonda, premioDinero, premioPuntos, draw) VALUES (1, 300, 5, 1);
INSERT INTO ronda (numeroRonda, premioDinero, premioPuntos, draw) VALUES (2, 1000, 0, 1);

INSERT INTO partido (fecha, cancha, jugador1, jugador2, draw, set1, set2, set3, jugadorGanador, ronda) VALUES ('12/04/2018', 2, 1, 3, 1, '15:5', '30:20', '32:18', 3, 1);
INSERT INTO partido (fecha, cancha, jugador1, jugador2, draw, set1, set2, set3, jugadorGanador, ronda) VALUES ('13/04/2018', 3, 2, 5, 1, '15:5', '30:20', '32:18', 2, 1);
INSERT INTO partido (fecha, cancha, jugador1, jugador2, draw, set1, set2, set3, jugadorGanador, ronda) VALUES ('16/04/2018', 1, 2, 3, 1, '', '', '', 3, 2);

--INSERT INTO partido (fecha, cancha, equipo1, equipo2, draw, set1, set2, set3, equipoGanador, ronda) VALUES ('', 1, 1, 3, );

--INSERT INTO reservaCancha () VALUES ();
