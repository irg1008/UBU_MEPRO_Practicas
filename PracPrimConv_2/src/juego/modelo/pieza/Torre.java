package juego.modelo.pieza;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Clase de la torre. Hereda de la clase abstracta pieza.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
public class Torre extends PiezaAbstracta{
	
	/**
	 * Letra de la clase.
	 */
	static final char LETRA = 'T';

	/**
	 * Constructor de la torre. Pasa el color al constructor del padre.
	 * 
	 * @param color Color de la pieza.
	 */
	public Torre(Color color) {
		super(color);
	}

	@Override
	public char toChar() {
		return LETRA;
	}

	@Override
	public boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias) {
		if(!hayPiezasEntreMedias && sentido != null) {
			if(sentido == Sentido.HORIZONTAL_E || sentido == Sentido.HORIZONTAL_O
					|| sentido == Sentido.VERTICAL_N || sentido == Sentido.VERTICAL_S)
				return true;
		}
		return false;
	}
}

