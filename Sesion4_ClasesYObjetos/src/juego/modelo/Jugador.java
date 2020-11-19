package juego.modelo;

/**
*
* @author Iván Ruiz Gázquez
*
*/

public class Jugador {
	private String nombre;
	
	private Color color;
	
	public Jugador(String nombre, Color color) {
		this.nombre = nombre;
		this.color = color;
	}
	
	public String obtenerNombre() {
		return nombre;
	}
	
	public Color obtenerColor() {
		return color;
	}
	
	
	public Pieza generarPieza() {
		// Retornar una nueva pieza con el color actual
		return new Pieza(obtenerColor());
	}
	
	public String toString() {
		return obtenerNombre() + "" + obtenerColor();
	}
}