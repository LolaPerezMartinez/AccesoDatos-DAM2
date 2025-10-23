package ficheros.basedatosjavi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Persona {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int nacido;
	
	private final static String pathNombres = "archivos/nombres.txt";
	private final static String pathApellidos = "archivos/apellidos.txt";
	private final static String pathPoblador = "archivos/poblador.sql";
	
	//la lista sera solo una por eso las ponemos estaticos
	//si ponemos final apunta a una misma lista aunque esta lista cambie
	private final static List <String> nombres = importarLista(pathNombres);
	private final static List <String> apellidos = importarLista(pathApellidos);
	
	//permite el multihilo
	private final static RandomGenerator generador = RandomGenerator.getDefault();
	
	//lo ideal es crear un método estatico para cuando se cree la clase se creen las listas de nombres 
	//y apellidos
	private static List<String> importarLista(String file){
		List <String> listaDatos = null;
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
		//list.of la lista es inmutable pero permite modificarla
		String [] arrayDatos = br.readLine().trim().split(",");
		for (int i = 0; i < arrayDatos.length; i++) {
			arrayDatos[i] = arrayDatos[i].trim();
		}
		
		listaDatos = List.of(arrayDatos);
		
		}catch(IOException e) {
			System.out.println("Error al leer el fichero");
		}
		
		return listaDatos;
	}
	
	//generar un nombre estatico tiene sentido que sea de la clase y no dependa de instancias aunque tambien
	//seria valido
	private static String elementoAleatorio (List <String> lista) {
		return lista.get(generador.nextInt(lista.size()));
	}
	
	public Persona () {
		nombre = elementoAleatorio(nombres);
		apellido1 = elementoAleatorio(apellidos);
		apellido1 = elementoAleatorio(apellidos);
		nacido = generador.nextInt(1920, 2026);
	}
	
	private static boolean generarSalida(String file, List<Persona> lista) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
			bw.write(String.format("CREATE DATABASE mundo;%n"));
			bw.write(String.format("show databases;%n"));
			bw.write(String.format("USE mundo;%n"));
			bw.write(String.format("CREATE TABLE personas (nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);%n"));
			bw.write(String.format("DESCRIBE personas;%n"));
			bw.write(String.format("INSERT INTO personas (nombre, apellido1, apellido2,nacimiento) VALUES %n"));
			
			for(int i=0; i < lista.size() - 1; i++) {
				bw.write(String.format("('%s', '%s', '%s', %d),%n", 
					lista.get(i).nombre, lista.get(i).apellido1, 
					lista.get(i).apellido1, lista.get(i).nacido));
			}
			bw.write(String.format("('%s', '%s', '%s', %d);", 
				lista.get(lista.size()-1).nombre, lista.get(lista.size()-1).apellido1, 
				lista.get(lista.size()-1).apellido2, lista.get(lista.size()-1).nacido));
			
		}catch(IOException e) {
			System.out.printf("Error escribiendo fichero %s%n", file);
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		int cantidadPersonas = 10;
		List<Persona> personas = new ArrayList<>();
		for (int i = 0; i < cantidadPersonas; i++) {
			personas.add(new Persona());
			
		}
		System.out.println(generarSalida(pathPoblador, personas) ? "Salida generada correctamente."
				: "No se ha podido generar salida."
					);
		;
		
	}
}
