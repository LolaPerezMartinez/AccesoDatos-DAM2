package exception.autocloseable;

public class Door implements AutoCloseable{
	//implementamos autocloseable para que se cierre sola sin tener que decirselo
	public void open() {
		System.out.println("Abriendo puerta");
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("Cerrando puertas");	
	}
	
	public static void main(String[] args) {
		try(Door d1 = new Door()){
			d1.open();
			int i = 1/0;
		}catch(Exception e) {
			System.out.println("Detectada excepción");
		}
	}

}
