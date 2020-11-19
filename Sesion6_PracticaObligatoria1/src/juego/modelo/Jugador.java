package juego.modelo;

/**
 * Jugador que interactuará con el tablero.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public class Jugador {
	/**
	 * Nombre del jugador.
	 */
	private String nombre;
	
	/**
	 * Color del jugador.
	 */
	private Color color;
	
	/**
	 * Coloca el color y nombre del jugador.
	 * 
	 * @param nombre Nombre jugador.
	 * @param color Color jugador.
	 */
	public Jugador(String nombre, Color color) {
		this.nombre = nombre;
		this.color = color;
	}
	
	/**
	 * Obtiene el nombre del jugador.
	 * 
	 * @return Nombre.
	 */
	public String obtenerNombre() {
		return nombre;
	}
	
	/**
	 * Obtiene el color del jugador.
	 * 
	 * @return Color.
	 */
	public Color obtenerColor() {
		return color;
	}
	
	/**
	 * Genera un anueva pieza.
	 * 
	 * @return Color de la pieza.
	 */
	public Pieza generarPieza() {
		// Retornar una nueva pieza con el color actual
		return new Pieza(obtenerColor());
	}
	
	/**
	 * Devuelve los atributos del jugador.
	 * 
	 * @return Nombre del jugador mas su color.
	 */
	public String toString() {
		return obtenerNombre() + "" + obtenerColor();
	}
}