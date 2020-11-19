package juego.modelo;

/**
 * Pieza compuesta por un color insertada en una celda del tablero.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public class Pieza {
	/**
	 * Color.
	 */
	private Color color;
	
	/**
	 * Celda.
	 */
	private Celda celda;
	
	/**
	 * Crea una pieza que llama al establecimiento de su color.
	 * 
	 * 
	 * @param color Color de la pieza.
	 */
	public Pieza(Color color) {
		establecerColor(color);
	}
	
	/**
	 * Setter el color de la pieza.
	 * 
	 * @param color Color de la pieza.
	 */
	private void establecerColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Getter de color de la pieza.
	 * 
	 * @return Color de la pieza.
	 */
	public Color obtenerColor() {
		return this.color;
	}
	
	/**
	 * Establece la celda.
	 * 
	 * @param celda Celda a insertar.
	 */
	public void establecerCelda(Celda celda) {
		this.celda = celda;
	}
	
	/**
	 * Devuelve la celda que contiene la pieza.
	 * 
	 * @return Celda que contiene la pieza.
	 */
	public Celda obtenerCelda() {
		return this.celda;
	}
	
	/**
	 * Retorna la letra correspondiente con el color de la pieza.
	 * 
	 * 
	 * @return Letra correspondiente al color.
	 */
	public String toString() {
		return "" + color.toChar();
	}
}