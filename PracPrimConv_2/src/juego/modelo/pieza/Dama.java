package juego.modelo.pieza;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Clase del tipo de pieza Dama.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
public class Dama extends PiezaAbstracta{
	
	/**
	 * Letra de la dama.
	 */
	static final char LETRA = 'D';

	/**
	 * Constructor de la dama.
	 * 
	 * @param color Color de la dama.
	 */
	public Dama(Color color) {
		super(color);
	}
	
	@Override
	public char toChar() {
		return LETRA;
	}

	@Override
	public boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias) {
		if(!hayPiezasEntreMedias && sentido != null)
			return true;
		return false;
	}
}

