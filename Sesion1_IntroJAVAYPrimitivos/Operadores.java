/**
 * Tipos primitivos. Uso de operadores con tipos primitivos.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:cpardo@ubu.es">Carlos Pardo</a>
 * @version 1.0
 */
public class Operadores {

	/**
	 * Rutina principal (Método raíz).
	 *
	 * @param args parametros de entrada introducidos en línea de comandos
	 */
	public static void main(String[] args) {
		byte b = 0x0A; // asignación de 10 en hexadecimal
		System.out.printf("%d%n", b);
		b += 20;
		System.out.printf("%d%n", b);
		b *= 3;
		System.out.printf("%d%n", b);
		char c = (char) b;
		System.out.printf("%c%n", c);
		c--;
		System.out.printf("%c%n", c);
		int i = c;
		System.out.printf("%d%n", i);
		i %= 2;
		System.out.printf("%d%n", i);
		i = -2147483648;
		System.out.printf("%d%n", i);
		i = i - 1;
		System.out.printf("%d%n", i);
		float f = 1.24F; // notacion tradicional
		System.out.printf("%f%n", f * 1.0F);
		double d = 1.24; // notacion tradicional
		System.out.printf("%f%n", d * 1.0);

		boolean bool = false;
		System.out.printf("%b%n", bool);

		int resultado = ((10 * 9) / (8 % 3)) + (5 * 9 % 2);
		System.out.printf("%d",resultado); // ojo no incluimo salto de línea

	} // main

}
