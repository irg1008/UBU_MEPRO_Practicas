package juego.modelo.pieza;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Clase del alfil.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
public class Alfil extends PiezaAbstracta{
	
	/**
	 * Letra del alfil.
	 */
	static final char LETRA = 'A';
	
	/**
	 * Constructor del alfil.
	 * 
	 * @param color Color del alfil.
	 */
	public Alfil(Color color) {
		super(color);
	}

	@Override
	public char toChar() {
		return LETRA;
	}

	@Override
	public boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias) {
		if(!hayPiezasEntreMedias && sentido != null) {
			if(sentido == Sentido.DIAGONAL_NE || sentido == Sentido.DIAGONAL_NO
					|| sentido == Sentido.DIAGONAL_SE || sentido == Sentido.DIAGONAL_SO)
				return true;
		}
		return false;
	}
}

