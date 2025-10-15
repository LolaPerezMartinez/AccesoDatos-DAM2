package utilstest;

import java.io.File;

public class FileUtils {

	public static boolean analizada(File f) {
		if (f == null) {
			System.out.printf("%nEl archivo es nulo.%n");
			return false;
		} else if (!f.exists()) {
			System.out.printf("%nEl archivo/directorio no existe.");
			return false;

		} else {
			System.out.printf("%nPermiso de escritura: %s.%nPermiso de lectura: %s.%nPermiso de ejecución: %s.%n",
					f.canWrite() ? "Sí" : "No", f.canRead() ? "Sí" : "No", f.canExecute() ? "Sí" : "No");
			System.out.printf("Parent: %s.%nName: %s.%n", f.getParent(), f.getName());

			if (f.isFile()) {
				System.out.printf("El tamaño del archivo es de: %.4f.%n", (double) f.length() / 1024 / 1024);
			} else if (f.isDirectory()) {
				File[] listadoArchivos = f.listFiles();
				for (File archivo : listadoArchivos) {
					if (archivo.isFile()) {
						System.out.printf("%n[Archivo]: %s.%n", archivo.getName());
					}
				}

			}

		}

		return true;
	}
}

/*
 * File [] listaArchivos = f.listFiles(); for (File archivo : listaArchivos) {
 * if(archivo.getAbsolutePath() == f.getAbsolutePath()) {
 * if(archivo.isDirectory()) {
 * System.out.printf("[Carpeta]: %s tiene correspondencia.%n", f.getName());
 * }else if(archivo.isFile()) {
 * System.out.printf("%n[Archivo]: %s tiene correspondencia.%n ", f.getName());
 * } } /*if(file.isDirectory()) { System.out.printf("[Carpeta]: %s%n",
 * file.getName()); }else if (file.isFile()){
 * System.out.printf("[Archivo] %s%n", file.getName()); }else {
 * System.out.println("Es otro tipo de archivo.%n"); }
 */
