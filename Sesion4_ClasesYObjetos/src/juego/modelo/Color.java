package juego.modelo;

/**
 * 
 * @author Iván Ruiz Gázquez
 *
 */

public enum Color {
	BLANCO('O'),
	NEGRO('X');
	
	private char caracter;
	
	private Color(char car) {
		this.caracter = car;
	}
	
	public char toChar() {
		return caracter;
	}
}