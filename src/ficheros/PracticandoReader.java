package ficheros;

import java.io.FileReader;
import java.io.IOException;

public class PracticandoReader {
public static void main(String[] args) {
	try(FileReader fr = new FileReader("src/ficheros/prueba.txt")){
		System.out.printf("Encoding: %s%n", fr.getEncoding());
		int c = 0;
		while((c = fr.read()) != -1) {
			System.out.print((char)c);
		}
		System.out.println();
	}catch(IOException e) {
		System.out.printf("Problemas abriendo el archivo: %s%n", e.getMessage());
		//System.out.println(e.getMessage());
	}finally {
		System.out.println("Gracias por su confianza.");
	}
}
}
