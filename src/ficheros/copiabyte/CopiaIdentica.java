package ficheros.copiabyte;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiaIdentica {
public static void main(String[] args) {
	 try (FileInputStream fis = new FileInputStream("src/ficheros/copiabyte/java.webp");
		  FileOutputStream fos = new FileOutputStream("src/ficheros/copiabyte/java2.webp")){
		int b = 0;
		while((b = fis.read()) != -1) {
			fos.write(b);
		}
	} catch (IOException e) {
		System.out.println("Problemas en la copia.");
		e.printStackTrace();
	} finally {
		System.out.println("Gracias por su confianza.");
	}
	
}
}
