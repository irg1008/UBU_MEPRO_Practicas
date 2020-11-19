package juego.modelo.pieza;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Clase de la pieza rey.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
public class Rey extends PiezaAbstracta{
	
	/**
	 * Letra de la clase rey.
	 */
	static final char LETRA = 'R';

	/**
	 * Constructor del rey.
	 * 
	 * @param color Color del rey.
	 */
	public Rey(Color color) {
		super(color);
	}

	@Override
	public char toChar() {
		return LETRA;
	}

	@Override
	public boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias) {
		return ((esLegalMoverEnSalto(1, 1, destino) || this.esLegalMoverEnSalto(0, 1, destino)) && sentido != null);
	}
}

