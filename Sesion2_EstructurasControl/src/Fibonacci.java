/**
 * Fibonacci
 *
 * @author <p>Iván Ruiz</p>
 *
 * @version 1.0
 */

public class Fibonacci{

	public static void main(String[] args) {
		long posicion = 0;

            for (posicion = 0; posicion < 100; posicion++) {
               	// Saca Resultado
		System.out.printf("El número %d de la serie de Fibonacci es el: %d. \n", posicion, fibonacci(posicion));
            }
	}

	static long fibonacci(long posicion) {

		// Busca el numero pedido TODO
		if (posicion <= 0)
                    return 0;
                else if (posicion == 1)
                    return 1;
		else
                    return fibonacci(posicion-1) + fibonacci(posicion-2);
	}
}
