package juego.modelo;

/**
 * Color establecido en las distintas piezas.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public enum Color {
	/**
	 * <b>'0'</b>.
	 */
	BLANCO('O'),
	
	/**
	 * <b>'X'</b>.
	 */
	NEGRO('X');
	
	/**
	 * Caracter del color.
	 */
	private char caracter;
	
	/**
	 * Coloca el caracter correspondiente al color.
	 * 
	 * @param car Caracter del color.
	 */
	private Color(char car) {
		this.caracter = car;
	}
	
	/**
	 * Retorna el caracter correspondiente al color.
	 * 
	 * @return Caracter del color.
	 */
	public char toChar() {
		return caracter;
	}
}