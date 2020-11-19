package musica;

/**
 * Instrumento.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public abstract class Instrumento implements Musical{

	private float precio;
	
	public Instrumento(float precio) {
		this.precio= precio;
	}
	
	//No hace falta por la interfaz
	/*@Override
	public void tocar(Nota nota) {		
		System.out.printf("Instrumento.tocar(): %s%n", nota.toString());
	}*/	
	
	/*@override
	public abstract void tocar(Nota nota);*/

	public float consultarPrecio() {
		return precio;
	}
	
	@Override
	public String toString() {
		return "[" + consultarPrecio() +  ":Instrumento]";
	}
}
