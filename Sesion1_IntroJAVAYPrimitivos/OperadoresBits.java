/**
 * Tipos primitivos. Uso de operadores de bit y desplazamiento.
 * 
 * Dado el escaso o nulo uso que se hará de estos operadores en la asignatura,
 * se proporciona como simple ejemplo demostrativo.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class OperadoresBits {

	/**
	 * Rutina principal (Método raíz).
	 *
	 * @param args parametros de entrada introducidos en línea de comandos
	 */
	public static void main(String[] args) {
		// Operadores de bits y desplazamiento (el uso de Integer se explicará en
		// posteriores lecciones)
		byte valor = 0x0F; // 00001111
		// desde Java 7 podemos inicializar directamente con literales en formato
		// binario
		// manejados internamente como literales int de 4 bytes
		byte valorMaximo = 0b0111_1111; // 127,
		byte valorMinimo = (byte) 0b1000_0000; // - 128, obligatorio el molde en este caso

		System.out.printf("Valor %d entre valores min. %d y máx. %d:%n", valor, valorMinimo, valorMaximo);

		// se utiliza una función auxiliar para formatear todos los resultados a una
		// salida
		// binaria de 32 bits en caso contrario el efecto en pantalla es que se pierden
		// los
		// ceros por la izquierda y puede dificultar la intepretación de los
		// resultados...
		// (eliminar la llamada a format para comprobarlo)
		System.out.printf("Valor en formato binario: \t%s%n", format(valor));
		System.out.printf("Min. en formato binario: \t%s%n", format(valorMinimo));
		System.out.printf("Max. en formato binario: \t%s%n", format(valorMaximo));

		// Operadores de bit a bit
		System.out.printf("AND: \t%s%n", format(valor & valorMaximo));
		System.out.printf("OR : \t%s%n", format(valor | valorMaximo));
		System.out.printf("XOR: \t%s%n", format(valor ^ valorMaximo));
		System.out.printf("NOT: \t%s%n", Integer.toBinaryString(~valor));

		// Operadores de desplazamiento
		int val1 = valor >> 1; // desplazamos una posición a la derecha
		System.out.printf(">> 1 en formato binario: \t%s%n", format(val1));
		int val2 = valor >> 4; // nos quedamos a cero
		System.out.printf(">> 4 en formato binario: \t%s%n", format(val2));
		int val3 = valor << 4; // desplazamos los cuatro bits de menor peso a las posiciones de mayor peso
		System.out.printf("<< 4 en formato binario: \t%s%n", format(val3));

		int y = -1;
		System.out.printf("Valor negativo: \t%s%n", format(y));
		int y2 = y >> 8;
		System.out.printf("Desplazar con extensión de signo:\t%s%n", format(y2));
		int y3 = y >>> 8;
		System.out.printf("Desplazar sin extensión de signo:\t%s%n", format(y3));
	}

	/**
	 * Función auxiliar para formatear todas las cadenas de bits a una longitud de
	 * 32 bits rellenando por la izquierda por ceros cuando proceda.
	 * 
	 * @param val valor a formatear
	 * @return cadena "binaria" rellenando con ceros por la izquierda
	 */
	static String format(int val) {
		/*
		 * Utilizar la función, sin entrar al detalle de cómo se ha resuelto puesto que
		 * su resolución excede los conocimientos de una primera sesión de prácticas en
		 * Java.
		 */
		String cadena = Integer.toBinaryString(val);
		return String.format("%32s", cadena).replace(' ', '0');
	}
}
