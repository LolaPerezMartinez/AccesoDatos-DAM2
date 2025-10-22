package ficheros;

import java.io.FileWriter;
import java.io.IOException;

public class PracticandoWriter {
public static void main(String[] args) {
	String linea01 = "¡Hola Mundo!";
	String linea02 = "¡Adiós Mundo!";
	
	String [] lineas = {linea01, linea02};
	
	//El archivo se guarda al terminar porque close tiene flush
	try(FileWriter fw = new FileWriter("src/ficheros/out.txt");){
		for (int i = 0; i < lineas.length; i++) {
			fw.append(lineas [i]);
			if( i != lineas.length -1) fw.append("\n");
			//fw.flush();
		}//while(true);
		System.out.println("Ya ha sido creado!");
	}catch(IOException e) {
		System.out.println("Problemas creando el archivo.");
	}finally {
		System.out.println("Gracias por su confianza.");
	}
}
}
