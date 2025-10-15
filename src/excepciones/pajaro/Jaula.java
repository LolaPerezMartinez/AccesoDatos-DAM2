package excepciones.pajaro;

import java.util.ArrayList;
import java.util.Random;

public class Jaula {
	private int ID;
	private static int nextID = 1;
	private boolean abierta;
	private int numMaximo;
	private ArrayList <Pajaro> almacenamiento;
	private static Random random = new Random();
	
	
	public Jaula(int numMaximo) {
		ID = nextID++;
		this.numMaximo = numMaximo;
		//this.numMaximo = random.nextInt(3, 7);
		this.almacenamiento = new ArrayList<>();
	}


	public boolean isAbierta() {
		return abierta;
	}

	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}
	

	public int getNumMaximo() {
		return numMaximo;
	}


	public ArrayList<Pajaro> getAlmacenamiento() {
		return almacenamiento;
	}

	@Override
	public String toString() {
		return String.format("La Jaula [%d] contiene %d pajaros.%n", ID, getAlmacenamiento().size());
	}
	
	public static void main(String[] args) {
        Jaula j1 = new Jaula(2);
        Jaula j2 = new Jaula(4);
        Jaula j3 = new Jaula(5);
        
        System.out.println(j1.toString());
        System.out.println(j2.toString());
        System.out.println(j3.toString());
    }
	
	
	
	
}
