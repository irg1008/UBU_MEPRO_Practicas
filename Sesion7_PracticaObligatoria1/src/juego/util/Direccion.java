package juego.util;

/**
 * Establece las direcciones posibles.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public enum Direccion {
	/**
	 * Dirección horizontal.
	 */
	HORIZONTAL,
	
	/**
	 * Dirección vertical.
	 */
	VERTICAL,
	
	/**
	 * Dirección diagonal ascendente.
	 */
	DIAGONAL_SO_NE,
	
	/**
	 * Dirección diagonal descendente.
	 */
	DIAGONAL_NO_SE;
}
