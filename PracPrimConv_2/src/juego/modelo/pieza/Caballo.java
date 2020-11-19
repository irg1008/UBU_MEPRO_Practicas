package juego.modelo.pieza;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Clase del caballo.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
public class Caballo extends PiezaAbstracta{
	
	/**
	 * Letra del caballo.
	 */
	static final char LETRA = 'C';

	/**
	 * Constructor del caballo.
	 * 
	 * @param color Color.
	 */
	public Caballo(Color color) {
		super(color);
	}

	@Override
	public char toChar() {
		return LETRA;
	}

	@Override
	public boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias) {
		return (esLegalMoverEnSalto(2, 1, destino) && sentido == null);
	}
}

