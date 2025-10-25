package ficheros.basedatos2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class Persona {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int yearNacimiento;
	
	private static RandomGenerator generador = RandomGenerator.getDefault();
	
	private static String pathNombres = "archivos2/nombres.txt";
	private static String pathApellidos = "archivos2/apellidos.txt";
	private static String pathPoblador = "archivos2/mundo.sql";
	
	private static List<String> nombres = crearListas(pathNombres);
	private static List<String> apellidos = crearListas(pathApellidos);
	public Persona () {
		
		nombre = generarRandom(nombres);
		apellido1 = generarRandom(apellidos);
		apellido2 = generarRandom(apellidos);
		yearNacimiento = generador.nextInt(1920, 2026);
	}
	
	public static List <String> crearListas(String pathfile){
		List <String> listaDatos = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathfile))){
			String [] arrayDatos = br.readLine().trim().split(",");
			
			for (String palabra : arrayDatos) {
				listaDatos.add(palabra.trim());	
			}
			
		} catch (IOException e) {
			System.out.println("ERROR al crear la lista.");
		}
		return listaDatos;
	}
	
	private static String generarRandom(List<String> lista) {
		return lista.get(generador.nextInt(lista.size()));
		
	}
	
	private static boolean crearBBDD (String fileCreado, List <Persona> lista) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileCreado));){
			bw.write(String.format("%n CREATE DATABASE mundo;%n USE mundo;%n CREATE TABLE personas(nombre VARCHAR(30), apellido1 VARCHAR(30), "
									+ "apellido2 VARCHAR(30), nacimiento INT);%n DESCRIBE personas;%n"
									+ "INSERT INTO personas (nombre, apellido1, apellido2, nacimiento)"));
			
			for (int i = 0; i < lista.size(); i++) {
				if(i < lista.size() -1) {
					bw.write(String.format(" VALUES ('%s', '%s', '%s', %d),%n", 
							lista.get(i).nombre, lista.get(i).apellido1, lista.get(i).apellido2, lista.get(i).yearNacimiento));
			}else {
				bw.write(String.format(" VALUES ('%s', '%s', '%s', %d);", 
						lista.get(i).nombre, lista.get(i).apellido1, lista.get(i).apellido2, lista.get(i).yearNacimiento));
			}
			
		  }
			return true;
			
		} catch (IOException e) {
			System.out.printf("Error escribiendo fichero %s%n", fileCreado);
			return false;
		}
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", yearNacimiento=" + yearNacimiento + "]";
	}

	public static void main(String[] args) {
		//System.out.println(generarListas(pathNombres));	
		
		Persona p1 = new Persona();
		System.out.println(p1);
		
		List<Persona> personas = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			personas.add(new Persona());
		}
		
		System.out.print(crearBBDD(pathPoblador,personas) ? "BBDD creada correctamente": "Error al crear BBDD");
	}
}
