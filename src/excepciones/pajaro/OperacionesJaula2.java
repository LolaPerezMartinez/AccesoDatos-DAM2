package excepciones.pajaro;

import java.util.Optional;
import java.util.Random;

public class OperacionesJaula2 implements AutoCloseable {
	private Jaula jaula;
	private static Random random = new Random();

	// Métodos auxiliares propios
	private boolean puertaAtascada() {
		int numAleatorio = random.nextInt(1, 6);
		return numAleatorio == 1;
	}

	private boolean jaulaLlena() {
		return pajarosEnJaula() >= jaula.getNumMaximo();
	}

	// Constructor
	public OperacionesJaula2(Jaula jaula) throws JaulaException {
		if (jaula == null || jaula.isAbierta()) {
			throw new JaulaNotAvailableException("EXCEPCIÓN: La jaula no está disponible");
		}

		if (!jaula.isAbierta()) {
			if (puertaAtascada()) {
				throw new PuertaAtascadaException("EXCEPCIÓN: La puerta esta atascada.");
			} else {
				jaula.setAbierta(true);
				System.out.println("La puerta se ha abierto correctamente.");
			}
		}
		this.jaula = jaula;
	}

	// Métodos
	public int pajarosEnJaula() {
		return jaula.getAlmacenamiento().size();
	}

	public Optional<Pajaro> meterPajaro(Pajaro pajaro) throws EspacioInsuficienteException {
		if (pajaro == null || jaula.getAlmacenamiento().contains(pajaro)) {
			System.out.println("No existe el pájaro o no se puede meter en la jaula.");
			return Optional.empty();
		}

		if (jaulaLlena()) {
			throw new EspacioInsuficienteException("EXCEPCIÓN: Espacio insuficiente en la jaula.");
		}
		if (!jaula.isAbierta()) {
			System.out.println("La jaula está cerrada.");
			return Optional.empty();
		} else {
			jaula.getAlmacenamiento().add(pajaro);
			System.out.println("El pajaro añadido: " + pajaro);

		}
		return Optional.of(pajaro);
	}

	/*
	 * public Pajaro sacarPajaro(Pajaro pajaro) { if (!jaula.isAbierta() ||
	 * !jaula.getAlmacenamiento().contains(pajaro)) { System.out.
	 * println("La jaula está cerrada o no se encuentra ese pájaro dentro."); } else
	 * { jaula.getAlmacenamiento().remove(pajaro); } return pajaro; }
	 */
	public Optional<Pajaro> sacarPajaro(Pajaro pajaro) {
		if (!jaula.isAbierta() || !jaula.getAlmacenamiento().contains(pajaro)) {
			System.out.println("La jaula está cerrada o no se encuentra ese pájaro dentro.");
			return Optional.empty();
		}
		jaula.getAlmacenamiento().remove(pajaro);
		System.out.println("El pájaro sacado: " + pajaro);
		return Optional.of(pajaro);
	}

	@Override
	public void close() throws JaulaException {
		if (jaula.isAbierta()) {
			if (puertaAtascada()) {
				throw new PuertaAtascadaException("EXCEPCIÓN: La puerta esta atascada en close().");
			} else {
				jaula.setAbierta(false);
				System.out.println("La jaula se cerró.");
			}
		}
	}

	public static void main(String[] args) {

		Jaula j1 = new Jaula(1);
		Pajaro p1 = new Pajaro();
		Pajaro p2 = new Pajaro();
		Pajaro p3 = new Pajaro();

		System.out.printf("-JAULA 1-%n");
		System.out.println(j1.toString());

		System.out.printf("Jaula añadiendo pajaros%n");
		try (OperacionesJaula2 oj1 = new OperacionesJaula2(j1)) {
			// oj1.meterPajaro(p1);
			// oj1.meterPajaro(p2);
			// oj1.sacaPajaro(p1);

			try {
				System.out.println("Meter: " + oj1.meterPajaro(null));
			} catch (EspacioInsuficienteException e) {
				e.printStackTrace();
			}
			System.out.println("Sacar: " + oj1.sacarPajaro(p2));

			System.out.println(j1.toString());
		} catch (JaulaException e) {
			System.out.print(e.getMessage());
		}

		System.out.println();
		System.out.printf("%n-JAULA 2-%n");
		Jaula j2 = new Jaula(3);
		Pajaro p4 = new Pajaro();
		Pajaro p5 = new Pajaro();
		Pajaro p6 = new Pajaro();
		Pajaro p7 = new Pajaro();

		System.out.println(j2.toString());

		System.out.printf("Jaula añadiendo pajaros%n");
		try (OperacionesJaula2 oj2 = new OperacionesJaula2(j2)) {
			oj2.meterPajaro(p4);
			oj2.meterPajaro(p5);
			//oj2.meterPajaro(p4);
			oj2.sacarPajaro(p6);
			oj2.meterPajaro(p6);
			//oj2.sacarPajaro(p5);
			oj2.meterPajaro(p7);

		} catch (JaulaException e) {
			System.out.print(e.getMessage());
		}
	}

}
