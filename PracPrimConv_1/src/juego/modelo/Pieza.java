package juego.modelo;

/**
 * Pieza compuesta por un color y tipo insertada en una celda del tablero.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public class Pieza {
	/**
	 * Guarda si la pieza ha sido movida.
	 */
	private boolean piezaMovida;
	
	/**
	 * Color.
	 */
	private Color color;
	
	/**
	 * Tipo.
	 */
	private Tipo tipo;
	
	/**
	 * Celda.
	 */
	private Celda celda;
	
	/**
	 * Constructor de la pieza dado un tipo y un color.
	 * 
	 * @param tipo Tipo de pieza.
	 * @param color Tipo de color.
	 */
	public Pieza(Tipo tipo, Color color) {
		establecerColor(color);
		establecerTipo(tipo);
		this.piezaMovida = false;
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
	 * Setter del tipo de pieza.
	 * 
	 * @param tipo Tipo de pieza.
	 */
	private void establecerTipo(Tipo tipo) {
		this.tipo = tipo;
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
	 * Getter del tipo de pieza.
	 * 
	 * @return Tipo de pieza.
	 */
	public Tipo obtenerTipo() {
		return this.tipo;
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
	 * Marca el primer movimiento.
	 */
	public void marcarPrimerMovimiento() {
		this.piezaMovida = true; 
	}
	
	/**
	 * Devuelve si es el primer movimiento.
	 * 
	 * @return Si es primer movimiento.
	 */
	public boolean esPrimerMovimiento() {
		if(this.piezaMovida == false)
			return true;
		return false;
	}
	
	/**
	 * Retorna la letra correspondiente con el color de la pieza.
	 * 
	 * 
	 * @return Letra correspondiente al color.
	 */
	public String toString() {
		return ""+tipo+"-"+color+"-"+esPrimerMovimiento();
	}
}