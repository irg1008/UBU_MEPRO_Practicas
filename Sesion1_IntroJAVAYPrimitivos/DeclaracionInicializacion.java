/**
 * Tipos primitivos. Ejemplos de uso de tipos primitivos en Java.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:cpardo@ubu.es">Carlos Pardo</a>
 * @version 1.0
 */
public class DeclaracionInicializacion {

	/**
	 * Rutina principal (Método raíz).
	 *
	 * @param args parametros de entrada introducidos en línea de comandos
	 */
	public static void main(String[] args) {
		// Inicializacion con valores maximos, base 10
		byte b = 127;
		short s = 32767;
		int i = 2147483647;
		long l = 9223372036854775807L;

		byte b2 = -128;

		// Inicialización otros tipos de literales, base octal y hexadecimal
		int octal = 0123; // 83
		int hexadecimal = 0xAF; // 175

		// Ejemplo del uso de numeros en punto flotante
		float f = 3.40282349E+38F;
		double d = 1.79769313486231570E+308;

		// Inicializacion de caracteres
		char c1 = '\u0061'; // a en unicode
		char c2 = 'a'; // a con comillas simples cuando es un carácter
		char c3 = 97; // a

		// Ejemplo de inicializacion de booleanos
		boolean bool1 = true; 
		boolean bool2 = false; 

		// Nota aclaratoria: algunos entornos de desarrollo y herramientas (como por
		// ejemplo Eclipse) detectan y avisan (warnings con color amarillo) de la
		// declaración de variables, que no son utilizadas posteriormente en el código.
		//
		// Son simples "avisos" al programador para que revise el uso de dichas variables,
		// pero no impiden la compilación del código fuente.
		// Generalmente esto es un indicio de algún problema de programación.
		//
		// En este ejemplo, asumimos que se producen muchos "warnings" al no utilizar
		// posteriormente la variables declaradas. Esto no es lo habitual.
	}

}
