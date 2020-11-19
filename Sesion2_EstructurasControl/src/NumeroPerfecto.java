/**
 * NumeroPerfecto
 *
 * @author <p>Iván Ruiz</p>
 * 
 * @version 1.0
 */

public class NumeroPerfecto{
	
	public static void main(String[] args) {
		int valor = 28;
		
		// Saca Resultado
		if (numeroPerfecto(valor))
			System.out.printf("El número %d es perfecto.", valor);
		else
			System.out.printf("El número %d NO es perfecto.", valor);
	}
	
	static boolean numeroPerfecto(int valor) {
		int divisor = 0;
		boolean esPerf= false;
		
		// True si es perfecto
		for (int i = 1; i < valor; i++) {
			if (valor % i == 0)
				divisor += i;
		}
		
		if (divisor == valor)
			esPerf = true;
		
		return esPerf;
	}
}