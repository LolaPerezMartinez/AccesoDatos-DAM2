CREATE DATABASE mundo;
show databases;
USE mundo;
CREATE TABLE personas (nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);
DESCRIBE personas;
INSERT INTO personas (nombre, apellido1, apellido2,nacimiento) VALUES 
('Carolina', 'Romero', 'Romero', 1996),
('María', 'García', 'García', 1965),
('Carolina', 'Hernández', 'Hernández', 1991),
('Alicia', 'Ruiz', 'Ruiz', 1989),
('Marta', 'Castillo', 'Castillo', 1995),
('Inés', 'Navarro', 'Navarro', 1984),
('Marta', 'Flores', 'Flores', 1971),
('Patricia', 'Moreno', 'Moreno', 1954),
('María', 'Santos', 'Santos', 2010),
('Beatriz.', 'Hernández', 'null', 1973);