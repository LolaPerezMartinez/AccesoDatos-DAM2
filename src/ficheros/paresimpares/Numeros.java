package ficheros.paresimpares;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Numeros {
    public static void main(String[] args) {

        try (FileWriter fwp = new FileWriter("src/ficheros/paresimpares/pares.txt");
             FileWriter fwi = new FileWriter("src/ficheros/paresimpares/impares.txt")) {
            for (char i = 1; i <= 100; i++) {
                if (i % 2 == 0)
                    fwp.append(Integer.toString(i)).append('\n');
                else
                    fwi.append(Integer.toString(i)).append('\n');
            }

        } catch (IOException e) {
            System.out.println("Fallo al escribir en los archivos pares.txt y/o impares.txt");
        }

        try (FileReader flp = new FileReader("src/ficheros/paresimpares/pares.txt");
             FileReader fli = new FileReader("src/ficheros/paresimpares/impares.txt")) {
            FileReader[] archivosLectura = {flp, fli};

            try (FileWriter todos = new FileWriter("src/ficheros/paresimpares/todos.txt");
                 FileWriter todosOrdenados = new FileWriter("src/ficheros/paresimpares/todos_ordenados.txt")) {

                List<Character> listaChar = new ArrayList<>();

                for (FileReader fileReader : archivosLectura) {
                    int c = 0;
                    while ((c = fileReader.read()) != -1) {
                        todos.append((char) c);
                        listaChar.add((char) c);
                    }
                }

              //StringBuilder
				StringBuilder sbTexto = new StringBuilder();
				for (Character c : listaChar) {
				    sbTexto.append(c);
				}

				//Obtener números individuales completos
				String [] numeros = sbTexto.toString().split("\n");

				//Creo lista para añadir los numeros
				List <String> numerosLista = new ArrayList<>();
				for (String num : numeros) {
				    if (!num.isEmpty()) {
				        numerosLista.add(num);
				    }
				}
				//Le doy orden inverso a la lista
				Collections.reverse(numerosLista);

				for (String numero : numerosLista) {
				    todosOrdenados.append(numero).append('\n');
				}


            }

        } catch (IOException e) {
            System.out.println("Fallo al crear todos.txt");
        }

    }
}





