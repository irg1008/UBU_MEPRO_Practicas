package juego.modelo.pieza;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Clase del peón.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
public class Peon extends PiezaAbstracta{
	
	/**
	 * Letra del peón.
	 */
	static final char LETRA = 'P';

	/**
	 * Constructor del peón.
	 * 
	 * @param color Color del peón.
	 */
	public Peon(Color color) {
		super(color);
	}
	
	/**
	 * Método que comprueba la legalidad de los peones negros.
	 * 
	 * @param origen Celda origen.
	 * @param destino Celda destino.
	 * @param sentido Sentido desde el origen hasta el destino.
	 * 
	 * @return Si es legal o no el movimiento.
	 */
	private boolean esLegalPeonNegro(Celda origen, Celda destino, Sentido sentido) {
		if((sentido == Sentido.DIAGONAL_SE || sentido == Sentido.DIAGONAL_SO)
				&& destino.obtenerFila()-origen.obtenerFila() == 1 && !destino.estaVacia())
			return true;
		else if(sentido == Sentido.VERTICAL_S && destino.estaVacia() && origen.obtenerColumna()-destino.obtenerColumna() == 0) {
			if(destino.obtenerFila()-origen.obtenerFila() == 1)
				return true;
			else if(this.esPrimerMovimiento()
				&& destino.obtenerFila()-origen.obtenerFila() == 2)
				return true;
		}
		return false;
	}
	
	/**
	 * Método que comprueba la legalidad de los peones blancos.
	 * 
	 * @param origen Celda origen.
	 * @param destino Celda destino.
	 * @param sentido Sentido desde el origen hasta el destino.
	 * 
	 * @return Si es legal o no el movimiento.
	 */
	private boolean esLegalPeonBlanco(Celda origen, Celda destino, Sentido sentido) {
		if((sentido == Sentido.DIAGONAL_NE || sentido == Sentido.DIAGONAL_NO)
				&& destino.obtenerFila()-origen.obtenerFila() == -1 && !destino.estaVacia())
			return true;
		else if(sentido == Sentido.VERTICAL_N && destino.estaVacia() && origen.obtenerColumna()-destino.obtenerColumna() == 0) {
			if(destino.obtenerFila()-origen.obtenerFila() == -1)
				return true;
			else if(this.esPrimerMovimiento()
				&& destino.obtenerFila()-origen.obtenerFila() == -2)
				return true;
		}
		return false;
	}

	@Override
	public char toChar() {
		return LETRA;
	}

	@Override
	public boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias) {
		boolean esCorrecto = false;
		
		if(!hayPiezasEntreMedias && sentido != null) {
			if(this.obtenerColor() == Color.BLANCO)
				esCorrecto = this.esLegalPeonBlanco(this.obtenerCelda(), destino, sentido);

			else if(this.obtenerColor() == Color.NEGRO)
				esCorrecto = this.esLegalPeonNegro(this.obtenerCelda(), destino, sentido);
		}

		return esCorrecto;
	}
}

