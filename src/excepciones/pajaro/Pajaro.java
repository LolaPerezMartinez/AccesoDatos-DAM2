package excepciones.pajaro;

public class Pajaro {
	private int ID;
	private static int nextID = 1;

	public Pajaro() {
		ID = nextID++;
	}

	@Override
	public String toString() {
		return "P-" + ID;
	}

	public static void main(String[] args) {
		Pajaro p1 = new Pajaro();
		Pajaro p2 = new Pajaro();
		System.out.println(p1.toString());
		System.out.println(p2.toString());
	}

}
