CREATE DATABASE mundo;
show databases;
USE mundo;
CREATE TABLE personas (nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);
DESCRIBE personas;
INSERT INTO personas (nombre, apellido1, apellido2,nacimiento) VALUES 
('Silvia', 'Iglesias', 'Iglesias', 1998),
('Beatriz.', 'Rubio', 'Rubio', 1924),
('Sonia', 'Álvarez', 'Álvarez', 1947),
('Julia', 'López', 'López', 1963),
('Patricia', 'León', 'León', 1996),
('Raquel', 'Marín', 'Marín', 1993),
('Julia', 'Suárez', 'Suárez', 1957),
('Laura', 'Molina', 'Molina', 1979),
('Ana', 'Iglesias', 'Iglesias', 1991),
('Ana', 'Castillo', 'null', 2005);