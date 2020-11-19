package juego.modelo;

/**
 * Excepción personalizada que hereda para las correctas coordenadas.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
@SuppressWarnings("serial")
public class CoordenadasIncorrectasException extends Exception{

	/**
	 * Constructor básico.
	 */
	public CoordenadasIncorrectasException() {

	}
	
	/**
	 * Constructor con mensaje.
	 * 
	 * @param message Mensaje.
	 */
	public CoordenadasIncorrectasException(String message) {
		super(message);		
	}
	
	/**
	 * Constructor con causa.
	 * 
	 * @param cause Causa.
	 */
	public CoordenadasIncorrectasException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructor con mensaje y causa.
	 * 
	 * @param message Mensaje.
	 * @param cause Causa.
	 */
	public CoordenadasIncorrectasException(String message, Throwable cause) {
		super(message, cause);
	}
}
