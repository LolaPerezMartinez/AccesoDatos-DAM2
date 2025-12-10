package camisetas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Camiseta {
	private long id;
	private int cantidad;
	private String color;
	private String marca;
	private String modelo;
	private String talla;

	private static long nextId = 1;
	
	private static final String pathArchivo = "archivos_camiseta/camisetas.txt";
	private static final String pathArchivoSinErrores = "archivos_camiseta/camisetas_sin_errores_de_linea.txt";
	private static final String pathArchivoConErrores = "archivos_camiseta/camisetas_con_errores_de_linea.log";
	private static final String pathFrecuenciasAntes = "archivos_camiseta/camisetas_frecuencias_antes.log";
	private static final String pathFrecuenciasDespues = "archivos_camiseta/camisetas_frecuencias_despues.log";
	private static final String pathCamisetasFinales = "archivos_camiseta/camisetas_finales.txt";
	private static final String pathCamisetasSQL = "archivos_camiseta/camisetas.sql";
	
	//Ver si constructor es necesario
	public Camiseta(int cantidad, String color, String marca, String modelo, String talla) {
		id = nextId++;
		this.cantidad = cantidad;
		this.color = color;
		this.marca = marca;
		this.modelo = modelo;
		this.talla = talla;
	}

	private static void leer() {
		List<String> listaConErrores = new ArrayList<>();

		int lineasEliminadas = 0;
		int lineasTotales = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(pathArchivo));
				BufferedWriter bw = new BufferedWriter(new FileWriter(pathArchivoSinErrores));
				BufferedWriter bwE = new BufferedWriter(new FileWriter(pathArchivoConErrores))) {

			String linea = br.readLine();
			while (linea != null) {
				lineasTotales++;

				int numeroComas = 0;
				char[] lineaChar = linea.toCharArray();
				for (int i = 0; i < lineaChar.length; i++) {
					if (lineaChar[i] == ',') {
						numeroComas++;
					}
				}
				if (numeroComas == 5) {
					bw.write(linea + "\n");
				} else {
					lineasEliminadas++;
					listaConErrores.add(linea);
				}

				linea = br.readLine();
			}
			bwE.write("Total de líneas analizadas: " + lineasTotales + "\n");
			bwE.write("Total de líneas eliminadas: " + lineasEliminadas + "\n" + "\n");
			bwE.write("Las líneas eliminadas son: " + "\n");

			for (String line : listaConErrores) {
				bwE.write(line + "\n");
			}

		} catch (IOException e) {
			System.out.printf("Problemas con el procesamiento del archivo de ruta: %s%n", pathArchivo);
		}
	}

	private static void frecuencias() {
		try (BufferedReader br = new BufferedReader(new FileReader(pathArchivoSinErrores));
			 BufferedWriter bwA = new BufferedWriter(new FileWriter(pathFrecuenciasAntes));
			 BufferedWriter bwD = new BufferedWriter(new FileWriter(pathFrecuenciasDespues));) {
			Map<Integer, Integer> mapCantidad = new TreeMap<>();
			Map<String, Integer> mapColor = new TreeMap<>();
			Map<String, Integer> mapMarca = new TreeMap<>();
			Map<String, Integer> mapModelo = new TreeMap<>();
			Map<String, Integer> mapTalla = new TreeMap<>();

			String linea = br.readLine();
			while (linea != null) {

				String[] datosLinea = linea.split(",");

				for (int i = 1; i < datosLinea.length; i++) {
					switch (i) {
					case 1: {
						int cantidadNum = Integer.valueOf(datosLinea[i]);
						mapCantidad.put(cantidadNum, mapCantidad.getOrDefault(cantidadNum, 0) + 1);
						break;
					}
					case 2: {
						mapColor.put(datosLinea[i], mapColor.getOrDefault(datosLinea[i], 0) + 1);
						break;
					}
					case 3: {
						mapMarca.put(datosLinea[i], mapMarca.getOrDefault(datosLinea[i], 0) + 1);
						break;
					}
					case 4:
						mapModelo.put(datosLinea[i], mapModelo.getOrDefault(datosLinea[i], 0) + 1);
						break;
					case 5:
						mapTalla.put(datosLinea[i], mapTalla.getOrDefault(datosLinea[i], 0) + 1);
						break;

					}
				}
				linea = br.readLine();
			}

			bwA.write(addEnunciado("cantidad"));
			bwD.write(addEnunciado("cantidad"));
			for (Integer cantidad : mapCantidad.keySet()) {
				bwA.write("Cantidad: " + cantidad + " | Frecuencia: " + mapCantidad.get(cantidad) + "\n");
				bwD.write("Cantidad: " + cantidad + " | Frecuencia: " + mapCantidad.get(cantidad) + "\n");
			}
			// AQUI TENGO QUE COMPROBAR SI FUNCIONA
			addTexto("color", mapColor, bwA, bwD);
			addTexto("marca", mapMarca, bwA, bwD);
			addTexto("modelo", mapModelo, bwA, bwD);
			addTexto("talla", mapTalla, bwA, bwD);

//			bwA.write("\n" + "-COLOR-" + "\n");
//			bwD.write("\n" + "-COLOR-" + "\n");
//			for (String color : mapColor.keySet()) {
//				bwA.write("Color: " + color + " | Frecuencia: " + mapColor.get(color) + "\n");
//			}
//			for (String color : depurar(mapColor).keySet()) {
//				bwD.write("Color: " + color + " | Frecuencia: " + depurar(mapColor).get(color) + "\n");
//			}
//			
//			bwA.write(addEnunciado("marca"));
//			bwD.write("\n" + "-MARCA-" + "\n");
//			for (String marca : mapMarca.keySet()) {
//				bwA.write("Marca: " + marca + " | Frecuencia: " + mapMarca.get(marca) + "\n");
//			}
//			for (String marca : depurar(mapMarca).keySet()) {
//				bwD.write("Marca: " + marca + " | Frecuencia: " + depurar(mapMarca).get(marca) + "\n");
//			}
//			
//			bwA.write("\n" + "-MODELO-" + "\n");
//			bwD.write("\n" + "-MODELO-" + "\n");
//			for (String modelo : mapModelo.keySet()) {
//				bwA.write("Modelo: " + modelo + " | Frecuencia: " + mapModelo.get(modelo) + "\n");
//			}
//			for (String  modelo : depurar(mapModelo).keySet()) {
//				bwD.write("Modelo: " + modelo + " | Frecuencia: " + depurar(mapModelo).get(modelo) + "\n");
//			}
//			
//			bwA.write("\n" + "-TALLA-" + "\n");
//			bwD.write("\n" + "-TALLA-" + "\n");
//			for (String talla : mapTalla.keySet()) {
//				bwA.write("Talla: " + talla + " | Frecuencia: " + mapTalla.get(talla) + "\n");
//			}
//			for (String talla : depurar(mapTalla).keySet()) {
//				bwD.write("Talla: " + talla + " | Frecuencia: " + depurar(mapTalla).get(talla) + "\n");
//			}

		} catch (IOException e) {
			System.out.printf("%nProblemas con el procesamiento del archivo.%n");
		}
	}

	private static String addEnunciado(String enunciado) {
		return String.format("%n-%S-%n", enunciado);
	}

	private static void addTexto(String enunciado, Map<String, Integer> mapOriginal, BufferedWriter bwA,
			BufferedWriter bwD) {
		try {
			bwA.write(addEnunciado(enunciado));
			bwD.write(addEnunciado(enunciado));
			for (String clave : mapOriginal.keySet()) {
				bwA.write(enunciado + ": " + clave + " | Frecuencia: " + mapOriginal.get(clave) + "\n");
			}
			
			Map<String, Integer> mapDepurado = depurar(mapOriginal);
			for (String clave : mapDepurado.keySet()) {
				bwD.write(enunciado + ": " + clave + " | Frecuencia: " + mapDepurado.get(clave) + "\n");
			}
		} catch (IOException e) {
			System.out.printf("%nProblemas en el proceso de depuración.%n");
		}
	}

	private static Map<String, Integer> depurar(Map<String, Integer> map) {
		Map<String, Integer> mapDepurado = new TreeMap<>();
		String palabraDepurada;

		for (String claveOriginal : map.keySet()) {
			palabraDepurada = Normalizer.normalize(claveOriginal, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
					.toLowerCase();

			int valorClave = map.get(claveOriginal);

			mapDepurado.put(palabraDepurada, mapDepurado.getOrDefault(palabraDepurada, 0) + valorClave);
		}
		return mapDepurado;
	}

	private static void archivoFinal() {
		try (BufferedReader br = new BufferedReader(new FileReader(pathArchivoSinErrores));
				BufferedWriter bw = new BufferedWriter(new FileWriter(pathCamisetasFinales));) {

			String linea = br.readLine();

			while (linea != null) {

				String[] arrayLinea = linea.split(",");
				String campoNormalizado;
				for (int i = 1; i < arrayLinea.length; i++) {
					if (i == 1) {
						bw.write(arrayLinea[i]);
					} else {
						campoNormalizado = Normalizer.normalize(arrayLinea[i], Normalizer.Form.NFD)
								.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
						bw.write(campoNormalizado);
					}
					if (i < arrayLinea.length - 1) {
						bw.write(",");
					}
				}
				bw.write("\n");
				linea = br.readLine();
			}

		} catch (IOException e) {
			System.out.printf("%nError durante la creación del archivo final.%n");
		}
	}
	
	private static boolean generarSQL() {
		try (BufferedReader br = new BufferedReader(new FileReader(pathCamisetasFinales));
			 BufferedWriter bw = new BufferedWriter(new FileWriter(pathCamisetasSQL))){
			bw.write(String.format("CREATE DATABASE camisetas;%n"));
			bw.write(String.format("show databases;%n"));
			bw.write(String.format("USE camisetas;%n"));
			bw.write(String.format("CREATE TABLE camisetas (id INT AUTO_INCREMENT PRIMARY KEY, cantidad INT, color VARCHAR(50), marca VARCHAR(50), modelo VARCHAR(50), talla VARCHAR(30));%n"));
			bw.write(String.format("DESCRIBE camisetas;%n"));
			
			
			String linea = br.readLine();			
			while(linea != null) {
				String[] datos = linea.split(",");
	            bw.write(String.format("INSERT INTO camisetas (cantidad, color, marca, modelo, talla) VALUES (%s, '%s', '%s', '%s', '%s');\n",
	                datos[0], datos[1], datos[2], datos[3], datos[4]));
				linea = br.readLine();
			}
			return true;
		} catch (IOException e) {
			System.out.printf("%nNo se pudo realizar la importación al archivo SQL.%n");
		}
		return false;
	}

	public static void main(String[] args) {
		leer();
		frecuencias();
		archivoFinal();
		System.out.printf("Se generó el archivo SQL : %s%n", generarSQL() ? "Sí" : "No");
	}

}
