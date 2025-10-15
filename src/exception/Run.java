package exception;

public class Run {
public static void main(String[] args) {
	System.out.printf("EXCEPCIONES%n");
	System.out.println("Main antes de llamar a m1().");
	Excepciones.m1();
	System.out.println("Main despues de llamar a m1().");
	
	System.out.printf("%nEXCEPCIONES CREADAS%n");
	System.out.println("Main antes de llamar a m1().");
	ExcepcionesJavi.m1();
	System.out.println("Main despues de llamar a m1().");
}
}
