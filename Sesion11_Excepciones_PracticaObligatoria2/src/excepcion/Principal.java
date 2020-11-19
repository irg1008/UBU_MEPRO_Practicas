package excepcion;

import java.util.Scanner;

/**
 * Principal.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class Principal {

	/**
	 * Método raíz.
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) throws RuntimeException{
		Scanner scanner = null;
		// Añadir bucle for 3 iteracciones
		try {
			scanner = new Scanner(System.in);
			System.out.print("\nIntroduce valor: ");
			String s = scanner.next();
			Conversor conversor = new Conversor();
			System.out.println(conversor.aMinusculas(s));
			System.out.println(conversor.aMayusculas(s));
		}
		catch(BException e) {
			throw new RuntimeException("Texto Informativo", e);
		}
		finally {
			if (scanner != null)
				scanner.close();
		}
	}
}