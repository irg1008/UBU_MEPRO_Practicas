package juego.modelo.pieza;
import juego.modelo.Celda;
import juego.modelo.Color;
import juego.util.Sentido;

/**
 * Clase abstracta que aplica la interfaz de la pieza.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
public abstract class PiezaAbstracta implements Pieza{

	/**
	 * Guarda si la pieza ha sido movida.
	 */
	private boolean piezaMovida;
	
	/**
	 * Color de la pieza.
	 */
	private Color color;
	
	/**
	 * Celda.
	 */
	private Celda celda;
	
	/**
	 * Constructor de la pieza dado un tipo y un color.
	 * 
	 * @param color Tipo de color.
	 */
	public PiezaAbstracta(Color color) {
		this.color = color;
		this.piezaMovida = false;
	}
	
	/**
	 * Comprueba la legalidad de las piezas que mueven en salto. También el rey, ya que mueve en todas direcciones de forma limitada.
	 *
	 * @param movFila Direccion de movimiento de la fila.
	 * @param movCol Dirección de movimiento de la columna.
	 * @param destino Destino del movimiento.
	 * @return Devuelve si el movimiento es posible.
	 */
	protected boolean esLegalMoverEnSalto(int movFila, int movCol, Celda destino) {
		boolean puedeSaltar = false;

		if(Math.abs(this.obtenerCelda().obtenerFila()-destino.obtenerFila()) == movFila
			&& Math.abs(this.obtenerCelda().obtenerColumna()-destino.obtenerColumna()) == movCol)
			puedeSaltar = true;
		else if(Math.abs(this.obtenerCelda().obtenerFila()-destino.obtenerFila()) == movCol
			&& Math.abs(this.obtenerCelda().obtenerColumna()-destino.obtenerColumna()) == movFila)
			puedeSaltar = true;

		return puedeSaltar;
	}
	
	public abstract boolean esCorrectoMoverA(Celda destino, Sentido sentido, boolean hayPiezasEntreMedias);
	
	
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
	 * Obtiene la celda en la que está la pieza.
	 * 
	 * @return Celda de la pieza.
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
		return ""+toChar()+"-"+this.obtenerColor()+"-"+esPrimerMovimiento();
	}
}
