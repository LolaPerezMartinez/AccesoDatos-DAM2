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


	private static final String pathArchivo = "archivos_camiseta/camisetas.txt";
	private static final String pathArchivoSinErrores = "archivos_camiseta/camisetas_sin_errores_de_linea.txt";
	private static final String pathArchivoConErrores = "archivos_camiseta/camisetas_con_errores_de_linea.log";
	private static final String pathFrecuenciasAntes = "archivos_camiseta/camisetas_frecuencias_antes.log";
	private static final String pathFrecuenciasDespues = "archivos_camiseta/camisetas_frecuencias_despues.log";
	private static final String pathCamisetasFinales = "archivos_camiseta/camisetas_finales.txt";
	private static final String pathCamisetasSQL = "archivos_camiseta/camisetas.sql";
	
	
	private static void leer() {
	    List<String> listaConErrores = new ArrayList<>();

	    int lineasTotales = 0;

	    try (BufferedReader br = new BufferedReader(new FileReader(pathArchivo));
	         BufferedWriter bw = new BufferedWriter(new FileWriter(pathArchivoSinErrores, false));
	         BufferedWriter bwE = new BufferedWriter(new FileWriter(pathArchivoConErrores, false))) {

	    	String linea;
			while ((linea = br.readLine()) != null) {
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
					listaConErrores.add(linea);
				}

			}
			bwE.write("Total de líneas analizadas: " + lineasTotales + "\n");
			bwE.write("Total de líneas eliminadas: " + listaConErrores.size() + "\n" + "\n");
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
				BufferedWriter bwA = new BufferedWriter(new FileWriter(pathFrecuenciasAntes, false));
				BufferedWriter bwD = new BufferedWriter(new FileWriter(pathFrecuenciasDespues, false));) {
			Map<String, Integer> mapCantidad = new TreeMap<>();
			Map<String, Integer> mapColor = new TreeMap<>();
			Map<String, Integer> mapMarca = new TreeMap<>();
			Map<String, Integer> mapModelo = new TreeMap<>();
			Map<String, Integer> mapTalla = new TreeMap<>();

			String linea;
			while ((linea = br.readLine()) != null) {

				String[] datosLinea = linea.split(",", -1);

				sumarFrecuencias(mapCantidad, datosLinea[1]);
				sumarFrecuencias(mapColor, datosLinea[2]);
				sumarFrecuencias(mapMarca, datosLinea[3]);
				sumarFrecuencias(mapModelo, datosLinea[4]);
				sumarFrecuencias(mapTalla, datosLinea[5]);

			}
			generarReporteFrecuencias("cantidad", mapCantidad, bwA, bwD);
			generarReporteFrecuencias("color", mapColor, bwA, bwD);
			generarReporteFrecuencias("marca", mapMarca, bwA, bwD);
			generarReporteFrecuencias("modelo", mapModelo, bwA, bwD);
			generarReporteFrecuencias("talla", mapTalla, bwA, bwD);

		} catch (IOException e) {
			System.out.printf("%nProblemas con el procesamiento del archivo.%n");
		}
	}
	//MÉTODO AUXILIAR MAP
		private static void sumarFrecuencias(Map<String, Integer> mapa, String campo) {
			mapa.put(campo, mapa.getOrDefault(campo, 0) + 1);
		}
	
	//MÉTODO AUXILIAR ENUNCIADOS FRECUENCIAS
	private static String addEnunciado(String enunciado) {
		return String.format("%n-%S-%n", enunciado);
	}
	
	//MÉTODO AUXILIAR TEXTO FRECUENCIAS
	private static void generarReporteFrecuencias(String enunciado, Map<String, Integer> mapOriginal, BufferedWriter bwA,
			BufferedWriter bwD) {
		try {
			bwA.write(addEnunciado(enunciado));
			bwD.write(addEnunciado(enunciado));
			for (String clave : mapOriginal.keySet()) {
				bwA.write(enunciado + ": " + clave + " | Frecuencia: " + mapOriginal.get(clave) + "\n");
			}

			Map<String, Integer> mapDepurado = depurarMap(mapOriginal);
			for (String clave : mapDepurado.keySet()) {
				bwD.write(enunciado + ": " + clave + " | Frecuencia: " + mapDepurado.get(clave) + "\n");
			}
		} catch (IOException e) {
			System.out.printf("%nProblemas en el proceso de depuración.%n");
		}
	}
	
	//METODO AUXILIAR DEPURARMAP
	private static Map<String, Integer> depurarMap(Map<String, Integer> map) {
		Map<String, Integer> mapDepurado = new TreeMap<>();
		String palabraDepurada;

		for (String claveOriginal : map.keySet()) {
			palabraDepurada = normalizarTexto(claveOriginal);

			int valorClave = map.get(claveOriginal);

			mapDepurado.put(palabraDepurada, mapDepurado.getOrDefault(palabraDepurada, 0) + valorClave);
		}
		return mapDepurado;
	}
	
	//MÉTODO NORMALIZAR TEXTO
	private static String normalizarTexto(String texto) {
		if (texto == null || texto.isEmpty()) {
			return "";
		}
		String palabraDepurada = Normalizer.normalize(texto, Normalizer.Form.NFD)
								.replaceAll("\\p{M}", "")
								.toLowerCase()
								.trim();

		return palabraDepurada;
	}

	private static void archivoFinal() {
		try (BufferedReader br = new BufferedReader(new FileReader(pathArchivoSinErrores));
				BufferedWriter bw = new BufferedWriter(new FileWriter(pathCamisetasFinales, false));) {

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] arrayLinea = linea.split(",", -1);
				String campoNormalizado;
				for (int i = 1; i < arrayLinea.length; i++) {
					if (i == 1) {
						bw.write(arrayLinea[i]);
					} else {
						campoNormalizado = normalizarTexto(arrayLinea[i]);
						bw.write(campoNormalizado);
					}
					if (i < arrayLinea.length - 1) {
						bw.write(",");
					}
				}
				bw.write("\n");
			}

		} catch (IOException e) {
			System.out.printf("%nError durante la creación del archivo final.%n");
		}
	}
	
	//METODO AUXILIAR EVITAR ERROR EN SQL CON '
	private static String comillasSQL(String texto) {
		if (texto == null) {
			return "";  
		}
	    return texto.replace("'", "''");
	}
	
	private static void generarSQL() {
		try (BufferedReader br = new BufferedReader(new FileReader(pathCamisetasFinales));
				BufferedWriter bw = new BufferedWriter(new FileWriter(pathCamisetasSQL, false))) {
			bw.write(String.format("CREATE DATABASE camisetas;%n"));
			bw.write(String.format("show databases;%n"));
			bw.write(String.format("USE camisetas;%n"));
			bw.write(String.format(
					"CREATE TABLE camisetas (id INT AUTO_INCREMENT PRIMARY KEY, cantidad INT, color VARCHAR(50), marca VARCHAR(50), modelo VARCHAR(50), talla VARCHAR(30));%n"));
			bw.write(String.format("DESCRIBE camisetas;%n"));

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(",", -1);
				bw.write(String.format(
						"INSERT INTO camisetas (cantidad, color, marca, modelo, talla) VALUES (%s, '%s', '%s', '%s', '%s');\n",
						datos[0], comillasSQL(datos[1]), comillasSQL(datos[2]), comillasSQL(datos[3]),comillasSQL(datos[4])));
			}
		} catch (IOException e) {
			System.out.printf("%nNo se pudo realizar la importación al archivo SQL.%n");
		}
	}

	public static void main(String[] args) {
		leer();
		frecuencias();
		archivoFinal();
		generarSQL();
	}

}
