package exception.vector;

class VectorOutOfBoundsException extends Exception{

	public VectorOutOfBoundsException(String message) {
		super(message);	
	}	
}

public class Vector {
	private int x;
	private int y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector(int z) {
		x = y = z;
	}
	
	public Vector sumar(Vector v) throws VectorOutOfBoundsException{
		if((long) this.x + v.x > Long.MAX_VALUE ||
		   (long)this.y + v.y > Long.MAX_VALUE || 
		   (long) this.x + v.x < Long.MIN_VALUE ||
		   (long) this.y + v.y < Long.MIN_VALUE) 
			new VectorOutOfBoundsException("Suma fuera de rango");
		
		Vector v1 = new Vector(x + v.x, y + v.y);
		return v1;
	}
	
	
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}

	public static void main(String[] args) throws VectorOutOfBoundsException{
		Vector vMax = new Vector(Integer.MAX_VALUE);
		Vector vMin = new Vector(Integer.MIN_VALUE);
		Vector vPositivo = new Vector(1);
		Vector vNegativo = new Vector(-1);
		
		try {
			Vector vector = vMax.sumar(vMax);
			System.out.println(vector);
		}catch(VectorOutOfBoundsException e) {
			System.out.println(e.getMessage());	
		}
	}
	
	
	
}
