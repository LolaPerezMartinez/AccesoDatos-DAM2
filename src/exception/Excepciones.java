package exception;

public class Excepciones {
	
	public static void m1() {
		System.out.println("Soy m1 antes de llamar a m2().");
		try {
			m2();
		}catch (NullPointerException e){
			System.out.println("Hola");
		}catch(Exception  e) {
			System.out.println("Soy m1 después de llamar a m2().");
		}finally {
			System.out.println("Finally -> Esto se ejecuta de cualquier manera.");
		}
	}
	
	public static void m2() {
		System.out.println("Soy m2 antes de la excepcion.");
		
		System.out.println(8/0);
		
		System.out.println("Soy m2 antes de la excepcion.");
		
		/*try {
			int division = 8 / 0;
		}catch(ArithmeticException e) {
			System.out.println("Soy m2 despues de la excepcion.");
		}finally {
			System.out.println("Finally -> Esto se ejecuta de cualquier manera.");
		}*/
		
	}
	
}
