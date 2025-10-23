package ficheros.basedatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Persona {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int yearNacimiento;
	private static List<String> nombres;
	private static List<String> apellidos;
	//private static Random random = new Random();
	
	private static RandomGenerator generador = RandomGenerator.getDefault();

	public Persona() {

		nombres = new ArrayList<>();
		nombre = nombreAleatorio();

		apellidos = new ArrayList<>();
		apellido1 = apellidoAleatorio();
		apellido2 = apellidoAleatorio();

		yearNacimiento = generador.nextInt(1920, 2026);

	}

	private String nombreAleatorio() {
		String nombreAleatorio = "";
		try (BufferedReader brn = new BufferedReader(new FileReader("src/ficheros/basedatos/nombres.txt"));) {

			String lineaValida;
			while ((lineaValida = brn.readLine()) != null) {
				String[] arrayNombres = lineaValida.trim().split(",");
				for (String nombre : arrayNombres) {
					nombres.add(nombre);

				}
			}

			nombreAleatorio = nombres.get(generador.nextInt(nombres.size()));

		} catch (IOException e) {
			System.out.println("Error al generar nombre aleatorio.");
		}
		return nombreAleatorio;
	}

	private String apellidoAleatorio() {

		String apellidoAleatorio = "";
		try (BufferedReader bra = new BufferedReader(new FileReader("src/ficheros/basedatos/apellidos.txt"))) {
			String lineaValida;
			while ((lineaValida = bra.readLine()) != null) {
				String[] arrayApellidos = lineaValida.trim().split(",");
				for (String apellido : arrayApellidos) {
					apellidos.add(apellido);
				}
			}
			apellidoAleatorio = apellidos.get(generador.nextInt(apellidos.size()));

		} catch (IOException e) {
			System.out.println("Error al generar apellido aleatorio.");
		}
		return apellidoAleatorio;
	}

	private static Persona[] damePersona(int cantidadPersonas) {
		Persona[] arrayPersonas = new Persona[cantidadPersonas];

		for (int i = 0; i < cantidadPersonas; i++) {
			arrayPersonas[i] = new Persona();
		}
		System.out.println();
		return arrayPersonas;
	}

	public static boolean crearSentencias(Persona[] personas) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/ficheros/basedatos/poblador.sql"));) {
			bw.append(String.format("CREATE DATABASE mundo;%n"));
			bw.append(String.format("show databases;%n"));
			bw.append(String.format("USE mundo;%n"));
			bw.append(String.format(
					"CREATE TABLE personas (nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);%n"));
			bw.append(String.format("DESCRIBE personas;%n"));
			bw.append(String.format("INSERT INTO personas (nombre, apellido1, apellido2,nacimiento) VALUES %n"));

			for (int i = 0; i < personas.length - 1; i++) {
				bw.append(String.format("('%s', '%s', '%s', %d),%n", personas[i].nombre, personas[i].apellido1,
						personas[i].apellido2, personas[i].yearNacimiento));
			}
			bw.append(String.format("('%s', '%s', '%s', %d);", personas[personas.length - 1].nombre,
					personas[personas.length - 1].apellido1, personas[personas.length - 1].apellido2,
					personas[personas.length - 1].yearNacimiento));
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("Problema creando el fichero de salida.");
			return false;
		} catch (IOException e) {

			System.out.println("Problema manejando fichero salida");
			return false;
		}
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", yearNacimiento=" + yearNacimiento + "]";
	}

	public static void main(String[] args) {
		Persona b1 = new Persona();
		System.out.println(b1.toString());

		System.out.println(Arrays.toString(damePersona(5)));
		
		System.out.printf("%nCreacion de base de datos%n");
		
		int totalPersonas = 10_000;
	    Persona[] personas = damePersona(totalPersonas);
	    boolean exito = crearSentencias(personas);

	    if (exito) {
	        System.out.printf("Archivo poblador.sql creado correctamente con %d registros ✅%n", totalPersonas);
	    } else {
	        System.out.println("No se pudo crear el archivo ❌");
	    }
		

	}

}
