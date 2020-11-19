/**
 * Bisiesto
 *
 * @author <p>Iván Ruiz</p>
 * 
 * @version 1.0
 */

public class Bisiesto {
	
	public static void main(String[] args) {
		int anio = 2012;
		
		// Saca Resultado
		if (esBisiesto(anio))
			System.out.printf("El año %d es bisiesto.", anio);
		else
			System.out.printf("El año %d NO es bisiesto.", anio);
	}
	
	static boolean esBisiesto(int anio) {
		boolean esBis = false;
		
		// True si es bisisesto
		if (anio % 400 == 0 || (anio % 4 == 0 && anio % 100 != 0))
			esBis = true;
		
		return esBis;
	}
}