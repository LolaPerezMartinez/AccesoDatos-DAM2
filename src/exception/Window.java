package exception;

public class Window {
	public void open() {
		System.out.println("Abriendo ventana");
	}
	
	public void close() throws Exception{
		System.out.println("Cerrando ventana");	
	}

	public static void main(String[] args) {
		Window w1 = new Window();
		try {
			w1.open();
			int i = 1/0;
		}catch(Exception e) {
			System.out.println("Detectada excepción");
		}finally{
			try {
				w1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
