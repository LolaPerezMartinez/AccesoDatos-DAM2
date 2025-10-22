package exception.autocloseable;

public class ExcepcionesJavi {
		
		public static void m1() {
			System.out.println("Soy m1 antes de llamar a m2().");
			try {
				m2();
			}catch(MyCheckedPadreException e) {
				System.out.println("Detectado error: " + e.getMessage());
			}catch(MyUncheckedPadreException e) {
				System.out.println("Detectado error: " + e.getMessage());
			}
		}
		
		public static void m2() throws MyCheckedPadreException {
			System.out.println("Soy m2 entrando en la excepcion.");
			
			// si lanzo la excepción el código de abajo es imposible
			boolean b = true;
			if(b) throw new MyCheckedPadreException("Soy la excepcion checked padre");
			if(b) throw new MyUncheckedPadreException("Soy la excepcion unchecked padre");
			
			//Este codigo nunca se ejecuta
			//System.out.println(8/0);
			
			System.out.println("Soy m2 antes de la excepcion.");
			
			
			
		}
		
	

}
