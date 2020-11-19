package juego.modelo;

/**
 *
 * @author Iván Ruiz Gázquez
 *
 */

public class Pieza {
	private Color color;
	
	private Celda celda;
	
	public Pieza(Color color) {
		establecerColor(color);
	}
	
	private void establecerColor(Color color) {
		this.color = color;
	}
	
	public Color obtenerColor() {
		return this.color;
	}
	
	public void establecerCelda(Celda celda) {
		this.celda = celda;
	}
	
	public Celda obtenerCelda() {
		return this.celda;
	}
	
	public String toString() {
		return "" + color.toChar();
	}
}