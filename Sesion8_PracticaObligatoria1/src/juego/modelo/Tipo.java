package juego.modelo;

/**
 * Tipo de pieza a colocar. Tenemos el Rey, Peón, Caballo, Alfil, la Dama y Torre.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public enum Tipo {
	/**Rey.*/
	REY('R'),
	/**Dama.*/
	DAMA('D'),
	/**Torre.*/
	TORRE('T'),
	/**Caballo.*/
	CABALLO('C'),
	/**Alfil.*/
	ALFIL('A'),
	/**Peón.*/
	PEON('P');
	
	/**
	 * Letra del tipo.
	 */
	private char letra;
	
	/**
	 * Cada enumerado tiene una letra correspondiente.
	 * 
	 * @param letra Letra del enumerado.
	 */
	private Tipo(char letra) {
		this.letra = letra;
	}
	
	/**
	 * Devuelve la letra del enumerado.
	 * 
	 * @return Letra.
	 */
	public char toChar() {
		return letra;
	}
}
