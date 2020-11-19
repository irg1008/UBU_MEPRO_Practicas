package juego.modelo;

/**
 * 
 * @author Iván Ruiz Gázquez
 * 
 */

public class Celda {
	private int fila;
	private int columna;
	
	private Pieza pieza;
	
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	public Pieza obtenerPieza() {
		return pieza;
	}
	
	public void establecerPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
	public int obtenerFila() {
		return fila;
	}
	
	public int obtenerColumna() {
		return columna;
	}
	
	public boolean estaVacia() {
		return this.pieza == null;
	}
	
	public String toString() {
		return "(" + fila + "/" + columna + ")";
	}
}