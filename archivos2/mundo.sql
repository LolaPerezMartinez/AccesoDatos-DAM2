
 CREATE DATABASE mundo;
 USE mundo;
 CREATE TABLE personas(nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);
 DESCRIBE personas;
INSERT INTO personas (nombre, apellido1, apellido2, nacimiento) VALUES ('Sara', 'Vega', 'Ramos', '2007'),
 VALUES ('Raquel', 'León', 'Sánchez', '2006'),
 VALUES ('Cristina', 'Cortés', 'Rodríguez', '2019'),
 VALUES ('Sofía', 'Cano', 'Díaz', '2004'),
 VALUES ('Inés', 'Álvarez', 'Pérez', '1963'),
 VALUES ('Noelia', 'Díaz', 'Blanco', '2018'),
 VALUES ('Paula', 'Gómez', 'Cruz', '2000'),
 VALUES ('Alicia', 'García', 'González', '1920'),
 VALUES ('Cristina', 'Guerrero', 'Guerrero', '1960'),
 VALUES ('Julia', 'Vázquez', 'García', '1969');