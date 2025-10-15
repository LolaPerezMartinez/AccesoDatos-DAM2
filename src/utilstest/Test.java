package utilstest;
import java.io.File;




public class Test {
	public static void main(String[] args) {
		System.out.println("Con path ralativo");
		System.out.println("-----------------");
		File f1 = new File("src\\utilstest\\Prueba.txt");
		System.out.printf("¿Existe el archivo?: %s%n",f1.exists() ? "Sí" : "No");
		
		System.out.printf("%nCon path absoluto%n");
		System.out.println("-------------------");
		File f2 = new File("C:\\Users\\lolit\\eclipse-workspace\\AccesoDatos\\src\\utilstest Prueba.txt");
		System.out.printf("¿Existe el archivo?: %s%n",f2.exists() ? "Sí" : "No");
		System.out.printf("El nombre del archivo es: %s%n",f1.getName());
		
		System.out.printf("%nCorrespondencia entre archivos%n");
		System.out.print("-------------------------------");
		System.out.printf("%n→ El archivo f3. Directorio Correcto%n");
		File f3 = new File("C:\\Users\\lolit\\eclipse-workspace");
		FileUtils.analizada(f3);
		
		System.out.printf("%n→ El archivo f4. Nulo%n");
		File f4 = null;
		FileUtils.analizada(f4);
		
		System.out.printf("%n→ El archivo f5. Ruta errónea%n");
		File f5 = new File("C:\\Users\\lolit\\eclipse-workspace\\caracol.txt");
		FileUtils.analizada(f5);
		
		System.out.println();
		System.out.printf("%n→ El archivo f1. txt Correcto%n");
		FileUtils.analizada(f1);
		
		System.out.println();
		System.out.printf("%n→ El directorio f6. Correcto%n");
		File f6 = new File("C:\\Users\\lolit\\Desktop");
		FileUtils.analizada(f6);
		
		
	}

}
