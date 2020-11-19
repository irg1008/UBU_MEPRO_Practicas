package juego.modelo.pieza;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Interfaz de pieza.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
public interface Pieza {
	
	/**
	 * Comprueba si es correcto mover al destino según la clase. Dado un sentido y dado el hecho de haber piezas en medio.
	 * 
	 * @param destino Destino.
	 * @param sentido Sentido del movimiento.
	 * @param hayPiezasEntreMedias Si hay piezas entre medias.
	 * @return Retorna si es correcto mover.
	 */
	public boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias);
	
	/**
	 * Devuelve si es el primer movimiento.
	 * 
	 * @return Si es primer movimiento.
	 */
	public boolean esPrimerMovimiento();
	
	/**
	 * Establece una celda para la pieza.
	 * 
	 * @param celda Celda a establecer.
	 */
	public void establecerCelda(Celda celda);
	
	/**
	 * Marca el primer movimiento.
	 */
	public void marcarPrimerMovimiento();
	
	/**
	 * Obtiene la celda en la que está la pieza.
	 * 
	 * @return Celda de la pieza.
	 */
	public Celda obtenerCelda();
	
	/**
	 * Getter de color de la pieza.
	 * 
	 * @return Color de la pieza.
	 */
	public Color obtenerColor();
	
	/**
	 * Devuelve el caracter de la clase de la pieza.
	 * 
	 * @return Caracter de la clase.
	 */
	public char toChar();	
}