package ficheros.productos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class Productos {
	private String nombre;
	private String marca;
	private double precio;
	private int stock;
	private static RandomGenerator generador = RandomGenerator.getDefault();
	
	private static final String pathNombres = "archivosp/nombres.txt";
	private static final String pathMarcas = "archivosp/marcas.txt";
	
	private static final List<String> nombres =generarLista(pathNombres);
	private static final List<String> marcas = generarLista(pathMarcas);
	
	
	public Productos() {
		this.nombre = aleatorio(nombres);
		this.marca = aleatorio(marcas);
		this.precio = generador.nextDouble(1, 25);
		this.stock = generador.nextInt(1, 201);
	}
	
	private static List<String> generarLista(String file){
		List <String> listaGenerada = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String [] arrayLista = br.readLine().trim().split(",");
			for (int i = 0; i < arrayLista.length; i++) {
				arrayLista [i] = arrayLista[i].trim();
				listaGenerada.add(arrayLista[i]);
			}
		} catch (IOException e) {
			System.out.println("Problema al generar listas.");
		}
		return listaGenerada;
	}
	
	private static String aleatorio(List<String> lista) {
		 return lista.get(generador.nextInt(lista.size()));
	}
	
	
	
	@Override
	public String toString() {
		return String.format("Productos [nombre=%s, marca=%s, precio=%.2f, stock=%d]", nombre, marca, precio, stock);
	}

	public static void main(String[] args) {
		System.out.printf("%nNombres de productos%n");
		System.out.println(generarLista(pathNombres));
		
		System.out.printf("%nNombres de marcas%n");
		System.out.println(generarLista(pathMarcas));
		
		System.out.printf("%nProductos Aleatorios%n");
		Productos p1 = new Productos();
		Productos p2 = new Productos();
		System.out.println(p1);
		System.out.println(p2);
		
	}
}
