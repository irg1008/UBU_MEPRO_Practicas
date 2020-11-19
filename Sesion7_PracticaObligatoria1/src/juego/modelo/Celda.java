package juego.modelo;

/**
 * Celda para colocar pieza en el tablero. Las celdas componen el tablero y tienen una fila y columna únicas.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public class Celda {
	/**
	 * Fila.
	 */
	private int fila;
	
	/**
	 * Columna.
	 */
	private int columna;
	
	/**
	 * Pieza.
	 */
	private Pieza pieza;
	
	
	/**
	 * Crea una nueva celda a partir de los valores dados.
	 * 
	 * 
	 * @param fila Fila de la celda.
	 * @param columna Columna de la celda.
	 */
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	
	/**
	 * Obtiene la pieza actual asignada a la celda.
	 * 
	 * @return Actual contenido de la celda.
	 */
	public Pieza obtenerPieza() {
		return pieza;
	}
	
	/**
	 * Establece la pieza de la celda.
	 * 
	 * @param pieza Pieza actual a colocar.
	 */
	public void establecerPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
	/**
	 * Obtiene la fila.
	 * 
	 * @return Fila.
	 */
	public int obtenerFila() {
		return fila;
	}
	
	/**
	 * Obtiene la columna.
	 * 
	 * @return Columna.
	 */
	public int obtenerColumna() {
		return columna;
	}
	
	/**
	 * Devuelve que la celda esta vacia.
	 * 
	 * @return Celda vacia.
	 */
	public boolean estaVacia() {
		return this.pieza == null;
	}
	
	/**
	 * Devuelve fila y columna.
	 * 
	 * @return Fila y columna de la celda.
	 */
	public String toString() {
		return "(" + fila + "/" + columna + ")";
	}
}